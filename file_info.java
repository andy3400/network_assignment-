import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class File_info {
    public static String[] getInfo(String fileName) {
        String[] file = new String[2];

        try {
            int i = 0;
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) { // IP 주소와 포트 번호 읽어오기
                file[i++] = sc.next();
            }
        } catch (FileNotFoundException e) { // default value
            System.out.println("Error: File could not found");
            file[0] = "127.0.0.1";
            file[1] = "1234";
        }

        return file;
    }
}
