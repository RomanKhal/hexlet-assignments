package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index(){
        return postRepository.findAll().stream().map(this::toDto).toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO show (@PathVariable long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post with id " + id + " not found"));
        return toDto(post);
    }

    private PostDTO toDto(Post post) {
        var postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        postDto.setComments(commentRepository.findByPostId(post.getId()).stream().map(this::toCommentDTO).toList());
        return postDto;
    }

    private CommentDTO toCommentDTO (Comment comment) {
        var commentDto = new CommentDTO();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
// END
