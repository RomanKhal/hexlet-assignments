package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

// BEGIN
class App {

    private static final String ADDED = "added";
    private static final String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        var keySetOfBoth = new TreeSet<String>();
        keySetOfBoth.addAll(map1.keySet());
        keySetOfBoth.addAll(map2.keySet());
        var res = new LinkedHashMap<String, String>();
        for (var key : keySetOfBoth) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    res.put(key, UNCHANGED);
                } else {
                    res.put(key, CHANGED);
                }
            }
            if (map1.containsKey(key)) {
                res.putIfAbsent(key, DELETED);
            } else {
                res.putIfAbsent(key, ADDED);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true)
        );

        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );

        System.out.println(genDiff(data1, data2));
    }
}

//END
