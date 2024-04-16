package exercise;

import exercise.dto.users.UsersPage;
import exercise.dto.users.UserPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", context -> {
            UsersPage usersPage = new UsersPage(USERS);
            context.render("users/index.jte", model("page", usersPage));
        });
        app.get("/users/{id}", ctx ->{
            var id = ctx.pathParam("id");
                User user = USERS.stream().filter(s -> Long.valueOf(id).equals(s.getId())).findFirst()
                        .orElseThrow(() -> new NotFoundResponse("User not found"));
            System.out.println(user);
                UserPage userPage = new UserPage(user);
                ctx.render("users/show.jte", model("page", userPage));
    });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
