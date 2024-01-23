package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;


// BEGIN
class App {
    public static String getForwardedVariables(String file) {
        String copy = file.replace("\n", " ");
        var list = getParams(copy);
        return list.stream()
                .map(elem -> elem.split(","))
                .flatMap(Arrays::stream)
                .filter(e -> e.contains("X_FORWARDED_"))
                .map(e -> e.substring(12))
                .collect(Collectors.joining(","));
    }

    private static List<String> getParams(String elem) {
        var res = new ArrayList<String>();
        while (elem.contains("environment")) {
            int index = elem.indexOf("environment") + 13;
            int end = elem.indexOf("\"", index);
            res.add(elem.substring(index, end));
            elem = elem.substring(end);
        }
        return res;
    }
}
//END
