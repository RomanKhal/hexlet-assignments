package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static void main(String[] args) {
        String[][] image = {
                {"*"}
        };
        System.out.println(Arrays.deepToString(enlargeArrayImage(image)));
    }

    public static String[][] enlargeArrayImage(String[][] input) {

        return Arrays.stream(input)
                .map(line -> Arrays.stream(line)
                        .map(item -> new String[]{item, item})
                        .flatMap(Arrays::stream)
                        .toArray(String[]::new)
                )
                .map(item -> new String[][]{item, item})
                .flatMap(Arrays::stream)
                .toArray(String[][]::new);
    }

}
// END
