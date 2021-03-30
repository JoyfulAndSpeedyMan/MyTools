package demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDemo {
    public static void main(String[] args) throws IOException {
        String sp="C:\\Users\\Administrator\\Desktop";
        String sn="新建文件夹.zip";
        File file = new File(sp, sn);
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        ArrayList<String> errorList = new ArrayList<>();


        while((entry=zipInputStream.getNextEntry())!=null){
            if (entry.isDirectory()) {
                zipInputStream.closeEntry();
                continue;
            }
            String fileName = entry.getName();
            if (fileName.contains("/")) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            }
            try {
                long fileSize = entry.getSize();
//                if (fileSize > this.getSingleFileLimitSize()) {
//                    throw new RuntimeException(this.getSingleFileSizeOverMessage());
//                }
//                this.handleSingleFile(fileName, zin, extraParams);
                System.out.println(fileName);
            } catch (RuntimeException e) {
                errorList.add(fileName + e.getMessage());
            } finally {
                zipInputStream.closeEntry();
            }
        }
    }
}
