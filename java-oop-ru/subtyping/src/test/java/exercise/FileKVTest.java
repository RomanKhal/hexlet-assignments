package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import exercise.Utils;
import org.junit.jupiter.api.Test;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void FileKVTest(){
        KeyValueStorage kvFile = new FileKV("src/test/resources/file", Map.of("key1", "val1"));
        assertThat(kvFile.get("key1", "default")).isEqualTo("val1");
        kvFile.set("key2", "val2");
        kvFile.set("key1", "val3");
        assertThat(kvFile.get("key1", "default")).isEqualTo("val3");
        assertThat(kvFile.get("key2", "default")).isEqualTo("val2");
        kvFile.unset("key1");
        assertThat(kvFile.get("key1", "haven't that key")).isEqualTo("haven't that key");
        assertThat(kvFile.toMap()).isEqualTo(Map.of("key2", "val2"));
    }
    // END
}
