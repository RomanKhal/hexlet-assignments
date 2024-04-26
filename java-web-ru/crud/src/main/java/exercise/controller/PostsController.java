package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // BEGIN
    public static void post(Context ctx) {
            try {
                Long id = ctx.pathParamAsClass("{id}", Long.class).get();
                PostPage page = new PostPage(PostRepository.find(id).get());
                ctx.render("posts/show.jte", model("page", page));
            } catch (NoSuchElementException e) {
                ctx.result("Page not found").status(404);
            }

    }

    public static void posts(Context ctx) {
        long currentPage = ctx.queryParamAsClass("page", Long.class).getOrDefault(1L);
        long index = ((currentPage - 1) * 5);
        List<Post> res = PostRepository.getEntities().stream().skip(index).limit(5L).toList();
        PostsPage page = new PostsPage(res, currentPage);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
