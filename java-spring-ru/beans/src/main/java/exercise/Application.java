package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    public Daytime getName() {
        if (LocalDateTime.now().toLocalTime().isAfter(LocalTime.of(6, 0)) &&
                LocalDateTime.now().toLocalTime().isBefore(LocalTime.of(23, 0))){
            return new Day();
        }
        return new Night();
    }
    // END
}
