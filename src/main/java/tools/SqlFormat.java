package tools;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 格式化sql为：
 * sql.append("..............");
 * 格式
 * @author zhapshipin
 */
public class SqlFormat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> sqlLines = new ArrayList<>(20);
        boolean generateVar = true;
        String varName = "sql";
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
        if(generateVar){
            sql.append("StringBuilder ");
            sql.append(varName);
            sql.append(" = new StringBuilder();\n");
        }
        for (String sqlLine : sqlLines) {
            String sql1 = String.format(format, varName, sqlLine);
            sql.append(sql1);
            sql.append("\n");
        }
        System.out.println(sql.toString());
    }
}
