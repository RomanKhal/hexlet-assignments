package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.post("/articles", context -> {
            try {
                var checkedTitle = context.formParamAsClass("title", String.class)
                        .check(v -> v.length() > 2, "Название не должно быть короче двух символов")
                        .check(v -> !ArticleRepository.existsByTitle(v), "Статья с таким названием уже существует")
                        .get();
                var checkedContent = context.formParamAsClass("content", String.class)
                        .check(v -> v.length() > 10, "Статья должна быть не короче 10 символов").get();
                ArticleRepository.save(new Article(checkedTitle, checkedContent));
                context.redirect("/articles");
            } catch (ValidationException e) {
                var page = new BuildArticlePage(new Article(context.formParam("title"), context.formParam("content")), e.getErrors());
                context.render("articles/build.jte", model("page", page)).status(422);
            }
        });
        app.get("/articles/build", context -> {
            var page = new BuildArticlePage();
            context.render("articles/build.jte", model("page", page));
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
