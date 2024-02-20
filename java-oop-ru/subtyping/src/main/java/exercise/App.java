package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage data) {
        String key;
        String value;
        for (Map.Entry<String, String> entry : data.toMap().entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            data.unset(key);
            data.set(value, key);
        }
    }
}
// END
