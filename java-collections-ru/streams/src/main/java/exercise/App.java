package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    private static final List<String> FREE_DOMAINS = Arrays.asList("gmail.com","yandex.ru", "hotmail.com");
    public static int getCountOfFreeEmails(List<String> emailsList) {
        return (int) emailsList.stream()
                .map(email -> email.split("@")[1])
                .filter(email -> FREE_DOMAINS.contains(email))
                .count();
    }
}
// END
