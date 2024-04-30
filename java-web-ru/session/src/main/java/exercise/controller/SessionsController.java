package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.eclipse.jetty.util.StringUtil;

public class SessionsController {
    private static int sessionId = 0;

    // BEGIN
    public static void index(Context ctx) {
        Object name = ctx.sessionAttribute("name");
        var page = new MainPage(name);
        ctx.render("index.jte", model("page", page));
    }
    public static void build(Context ctx) {
        ctx.cookie("SID", String.valueOf(sessionId++));
        ctx.render("build.jte");
    }

    public static void create(Context ctx) {
        String name = StringUtil.asciiToLowerCase(ctx.formParam("name"));
        String password = Security.encrypt(ctx.formParam("password"));
        User user = UsersRepository.findByName(name);
        if (user != null && user.getPassword().equals(password)) {
            ctx.sessionAttribute("name", name);
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }
        LoginPage loginPage = new LoginPage(name, user == null ? "Wrong " + name : "Wrong password");
        ctx.render("build.jte", model("page", loginPage));
    }

    public static void delete(Context ctx) {
        ctx.removeCookie("SID");
        ctx.consumeSessionAttribute("name");
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
