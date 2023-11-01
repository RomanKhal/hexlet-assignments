package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
        public static boolean scrabble(String charSet, String word) {
        List<Character> characterList = strToCharArr(charSet);
        List<Character> wordList = strToCharArr(word);
        for (Character item :
                wordList) {
            if (characterList.contains(item)) {
                characterList.remove(item);
            } else {
                return false;
            }
        }
        return true;
    }

    static List<Character> strToCharArr(String str) {
        List<Character> result = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            result.add(str.toLowerCase().charAt(i));
        }
        return result;
    }
}
//END
