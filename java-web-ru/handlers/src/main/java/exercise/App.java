package exercise;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


public final class App {

    public static Javalin getApp() {
        // BEGIN
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });
        var mapper = new ObjectMapper();
        app.get("/phones", context -> context.result(mapper.writeValueAsString(Data.getPhones())));
        app.get("/domains", context -> context.result(mapper.writeValueAsString(Data.getDomains())));
        return  app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
