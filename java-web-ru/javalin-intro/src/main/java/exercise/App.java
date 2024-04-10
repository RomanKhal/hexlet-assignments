package exercise;

// BEGIN
import io.javalin.Javalin;
// END


public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin javalin = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        return javalin.get("/welcome", context -> context.result("Welcome to Hexlet"));
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
