package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
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
            var title = context.formParamAsClass("title", String.class);
            var content = context.formParamAsClass("content", String.class);
            try {
                var checkedTitle = title.check(v -> v.length() > 2, "Длина названия короче 2х символов")
                        .check(v -> !ArticleRepository.existsByTitle(v), "Не оригинальное название")
                        .get();
                var checkedContent = content.check(v -> v.length() > 10, "Длина статьи меньше 10 символов").get();
                Article article = new Article(checkedTitle, checkedContent);
                ArticleRepository.save(article);
                context.redirect("/articles");
            } catch (ValidationException e) {
                var page = new BuildArticlePage(title.get(),e.getErrors());
                context.status(422);
                context.render("articles/build.jte", model("page", page));
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
