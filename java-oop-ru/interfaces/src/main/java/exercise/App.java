package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {
        ReversedSequence reversedSequence = new ReversedSequence("abc");
        System.out.println(reversedSequence.length());
        System.out.println(reversedSequence.charAt(1));
        System.out.println(reversedSequence);
        System.out.println(reversedSequence.subSequence(1,3));
    }
    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        return  homes.stream().sorted(Home::compareTo)
                .limit(n)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
