package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    public Task setUpTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
       Task expected = setUpTask();

       taskRepository.save(expected);

       var id = expected.getId();

       var request = get("/tasks/" + id);

       var response = mockMvc.perform(request)
               .andExpect(status().isOk())
               .andReturn();

       var body = response.getResponse().getContentAsString();

        assertThatJson(body).isObject().containsAllEntriesOf(
                Map.of("title", expected.getTitle(),
                        "description", expected.getDescription(),
                        "id", id));
    }

    @Test
    public void testCreate() throws Exception {
        Task expected = setUpTask();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(expected));

        var response = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = response.getResponse().getContentAsString();

        var id = taskRepository.findByTitle(expected.getTitle()).get().getId();

        assertThatJson(body).isObject().containsAllEntriesOf(
                Map.of("title", expected.getTitle(),
                "description", expected.getDescription(),
                "id", id));
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = taskRepository.findById(1L).get();
        var data = new HashMap<>();
        data.put("description", "desc");
        data.put("title", "tit");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = response.getResponse().getContentAsString();

        assertThatJson(body).isObject().containsAllEntriesOf(
                Map.of("title", "tit",
                        "description", "desc"));
    }

    @Test
    public void testDelete() throws Exception {
        Task expected = setUpTask();

        taskRepository.save(expected);

        var id = expected.getId();

        var request = delete("/tasks/" + id);

        var response = mockMvc.perform(request).andExpect(status().isOk());

        assertThat(taskRepository.findById(id)).isEmpty();
    }
    // END
}
