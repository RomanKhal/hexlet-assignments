package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;


// BEGIN
class App {
    public static String getForwardedVariables(String file) {
        return Arrays.stream(file.split("\n"))
                .filter(line -> App.selectParam(line, "environment"))
                .map(args -> args.substring(12, args.length() - 1))
                .map(arg -> arg.split(","))
                .flatMap(Arrays::stream)
                .filter(param -> App.selectParam(param, "X_FORWARDED_"))
                .map(arg -> arg.substring(12))
                .collect(Collectors.joining(","));
    }

    private static boolean selectParam(String elem, String param) {
        return elem.startsWith(param);
    }
}
//END
