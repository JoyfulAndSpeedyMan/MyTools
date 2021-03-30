package tools;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 格式化sql为：
 * sql.append("..............");
 * 格式
 * @author zhapshipin
 */
public class SQLFormat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> sqlLines = new ArrayList<>(20);
        String line;
        int max = Integer.MIN_VALUE;
        while (true) {
            line = scan.nextLine();
            line = line.replace("\t","    ");
            if (line.trim().isEmpty()) {
                break;
            }
            sqlLines.add(line);
            if (max < line.length()) {
                max = line.length();
            }
        }
        String format = "%s.append(\"%-" + (max + 3) + "s\");";
        StringBuilder sql = new StringBuilder();
        for (String sqlLine : sqlLines) {
            String sql1 = String.format(format, "sql", sqlLine);
            sql.append(sql1);
            sql.append("\n");
        }
        System.out.println(sql.toString());
    }
}
