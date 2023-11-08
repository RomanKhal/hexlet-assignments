package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emailsList) {
        return (int) emailsList.stream()
                .filter(e -> e.contains("@gmail.com") || e.contains("@yandex.ru") || e.contains("@hotmail.com"))
                .count();
    }
}
// END
