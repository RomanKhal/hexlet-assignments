package exercise.daytime;
import jakarta.annotation.PostConstruct;


public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public String postConstruct() {
        return "Day bean created";
    }
    // END
}
