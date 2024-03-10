package exercise;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws IOException {
        Files.writeString(path, car.serialize());
    }

    public static Car extract(Path path) throws IOException {
//        String json = Files.readAllLines(path).toString();
        Car car = Car.unserialize(Files.readString(path));
        return car;
    }
}
// END
