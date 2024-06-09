package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<Post>> index() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    ResponseEntity<Post> show(@PathVariable String id) {
        Optional<Post> first = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        return ResponseEntity.of(first);
    }

    @PostMapping("/posts")
    ResponseEntity<Post> create(@RequestBody Post data) {
        posts.add(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    @PutMapping("/posts/{id}")
    ResponseEntity update(@PathVariable String id,@RequestBody Post data) {
        Optional<Post> maybePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setId(data.getId());
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.status(204).build();
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
