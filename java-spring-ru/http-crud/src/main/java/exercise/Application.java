package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    List<Post> index(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().skip(page - 1).limit(limit).toList();
    }

    @GetMapping("/posts/{id}")
    Optional<Post> show(@PathVariable String id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }
    // END

    @PutMapping("/posts/{id}")
    Post update(@PathVariable String id, @RequestBody Post data) {
        var mayBePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (mayBePost.isPresent()) {
            var post = mayBePost.get();
            post.setId(data.getId());
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}")
    void delete(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }
}
