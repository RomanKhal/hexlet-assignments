package exercise;

import java.io.CharArrayReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final String data;

    public ReversedSequence(String str) {
        char[] data = new char[str.length()];
        for (int i = 0; i < data.length; i++) {
            data[i] = str.charAt(str.length() - i - 1);
        }
        this.data = new String(data);
    }

//    private void reverse() {
//        for (int i = 0; i < data.length / 2; i++) {
//            byte temp =data[i];
//            data[i] = data[length() - i - 1];
//            data[length() - i - 1] = temp;
//        }
//    }

    @Override
    public boolean isEmpty() {
        return CharSequence.super.isEmpty();
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    @Override
    public int length() {
        return data.length();
    }

    @Override
    public char charAt(int index) {
        return data.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return data.substring(start, end);
    }

    @Override
    public String toString() {
        return data;
    }
}
// END
