package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> list, Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book :
                list) {
            if (book.entrySet().containsAll(map.entrySet())) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
