package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var token = ctx.cookie("token") == null ? null : ctx.cookie("token");
        User user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User not found"));
        if (user == null || !user.getToken().equals(token)) {
            ctx.redirect(NamedRoutes.usersPath());
            return;
        }
        UserPage page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, Security.encrypt(password), token);
        UserRepository.save(user);
        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }
    // END
}
