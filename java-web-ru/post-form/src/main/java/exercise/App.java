package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.http.HttpStatus;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/users/build", context -> {
            context.render("users/build.jte");
        });
        app.post("/users", context -> {
            String firstName = StringUtils.capitalize(context.formParam("firstName"));
            String lastName = StringUtils.capitalize(context.formParam("lastName"));
            String email = Objects.requireNonNull(context.formParam("email")).toLowerCase().trim();
            String password = Security.encrypt(Objects.requireNonNull(context.formParam("password")));
            UserRepository.save(new User(firstName, lastName, email, password));
            context.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
