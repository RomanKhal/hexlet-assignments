package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static Map<String, Integer> getWordCount(String sentence) {
        if (sentence.isEmpty()) return new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        String[] sentencesSplited = sentence.split(" ");
        for (var item : sentencesSplited) {
            if (map.containsKey(item)) {
                map.replace(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) return "{}";
        StringBuilder result = new StringBuilder();
        for (var key : map.keySet()) {
            result.append("  ").append(key).append(": ").append(map.get(key)).append("\n");
        }
        result.insert(0, "{\n").append("}");
        return result.toString();
    }
}
//END
