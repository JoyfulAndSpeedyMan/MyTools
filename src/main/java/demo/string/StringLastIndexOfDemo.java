package demo.string;

public class StringLastIndexOfDemo {
    public static void main(String[] args) {
        String s = "abcdefg";
        int i;
        i = s.lastIndexOf("fg",s.length()-1);
        System.out.println(i);
        i = s.lastIndexOf("fg",s.length()-2);
        System.out.println(i);
        i = s.lastIndexOf("fg",s.length()-3);
        System.out.println(i);
    }
}
