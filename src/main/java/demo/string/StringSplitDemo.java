package demo.string;

import java.util.Arrays;

public class StringSplitDemo {
    public static void main(String[] args) {
        String s="  aa bb cc    ";
        System.out.println(Arrays.toString(s.split(" ")));
    }
}
