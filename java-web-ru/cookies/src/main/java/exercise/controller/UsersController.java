package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id).get();
        UserPage page = new UserPage(user);
        String token = user.getToken();
        if (token.equals(ctx.cookie("userToken"))) {
            ctx.render("users/show.jte", model("page", page));
        } else ctx.redirect(NamedRoutes.usersPath());
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        long id = UserRepository.getEntities().size();
        ctx.cookie("userToken", token);
        ctx.redirect(NamedRoutes.userPath(id));
    }
    // END
}
