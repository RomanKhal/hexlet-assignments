package exercise.controller.users;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> list = new ArrayList<>();

    @GetMapping("/users/{id}/posts")
    List<Post> index(@PathVariable int id) {
        return list.stream().filter(post -> post.getUserId() == id).toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@PathVariable int id, @RequestBody Post data) throws URISyntaxException {
        Post post = new Post();
        post.setUserId(id);
        post.setTitle(data.getTitle());
        post.setSlug(data.getSlug());
        post.setBody(data.getBody());
        list.add(post);
        return post;
    }
}
// END
