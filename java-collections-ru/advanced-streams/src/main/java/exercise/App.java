package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String file) {
        return Arrays.stream(file.split("\n"))
                .filter(line -> App.selectParam(line, "environment"))
                .map(args -> args.substring(12, args.length()-1))
                .map(arg -> arg.split(","))
                .flatMap(Arrays::stream)
                .filter(param -> App.selectParam(param, "X_FORWARDED_"))
                .map(arg -> arg.substring(12))
                .collect(Collectors.joining(","));
    }

    private static boolean selectParam(String elem, String param) {
        return elem.startsWith(param);
    }

    public static void main(String[] args) {
        String file = "[program:prepare]\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "autorestart=false\n" +
                "environment=\"X_FORWARDED_MAIL=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"\n" +
                "\n" +
                "[program:http_server]\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make environment'\n" +
                "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"";
        System.out.println(getForwardedVariables(file));
    }
}
//END
