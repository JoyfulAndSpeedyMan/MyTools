package tools;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 格式化sql为：
 * sql.append("..............");
 * 格式
 *
 * @author zhapshipin
 */
public class SQLFormat {
    public static void main(String[] args) {
        new SQLFormat().start();
    }

    public void start() {
        boolean generateVar = true;
        String varName = "sql";
        int lineMaxLen = 120;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> sqlLines = new ArrayList<>(20);
        String line;
        int max = Integer.MIN_VALUE;
        while (true) {
            line = scan.nextLine();
            line = line.replace("\t", "    ");
            if (line.trim().isEmpty()) {
                break;
            }
            sqlLines.add(line);
            if (max < line.length()) {
                max = line.length();
            }
        }
        lineMaxLen -= varName.length() + ".append(\"".length() + "\");".length();
        max = Math.min(max + 3, lineMaxLen);
        String format = "%s.append(\"%-" + (max) + "s\");\n";
        StringBuilder sql = new StringBuilder();
        if (generateVar) {
            sql.append("StringBuilder ");
            sql.append(varName);
            sql.append(" = new StringBuilder();\n");
        }
        for (String sqlLine : sqlLines) {
            int len = sqlLine.length();
            int spiltLineNum;
            if ((spiltLineNum = (len / lineMaxLen)) > 0) {
                int spaceOffset = findNotBlankChar(sqlLine);
                if (spaceOffset > lineMaxLen) {
                    spaceOffset = 0;
                }
                String head = sqlLine.substring(spaceOffset, lineMaxLen);
                sql.append(String.format(format, varName, head));
                String s = sqlLine.substring(lineMaxLen + 1);
                String[] split = splitByMaxLen(s, lineMaxLen - spaceOffset + 1);
                String splitFormat = "%s.append(\"%" + (max + 3) + "s\");\n";
                for (String ss : split) {
                    sql.append(String.format(splitFormat, varName, ss));
                }
            } else {
                String sql1 = String.format(format, varName, sqlLine);
                sql.append(sql1);
            }
        }

        System.out.println(sql.toString());
    }

    public String[] splitByMaxLen(String line, int maxLen) {
        int len = line.length();
        if (len <= maxLen) {
            return new String[]{line};
        }
        int n = len / maxLen;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            int start = i * maxLen;
            int end = start + maxLen;
            result[i] = line.substring(start,end);
        }
        return result;
    }

    public int findNotBlankChar(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!Character.isSpaceChar(c)) {
                return i;
            }
        }
        return -1;
    }
}
