package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {


    public static void main(String[] args) {

    }
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(gender -> gender.get("gender").equals("male"))
                .sorted(Comparator.comparing(date -> date.get("birthday")))
                .map(name -> name.get("name"))
                .collect(Collectors.toList());
    }
}
// END
