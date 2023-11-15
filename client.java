import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Client {
    public static void main(String args[]) throws Exception {
        String[] file = new String[2];
        file = File_info.getInfo("file_info.java");

        String ip = file[0];
        int port = Integer.parseInt(file[1]);

        String sentence;
        String answerSentence;

        System.out.println("==When you want close socket, enter END.==");

        while (true) {
            // 소켓을 만들고 서버와 연결
            Socket clientSocket = new Socket(ip, port);

            // communicate...
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream outToServer = new DataOutputStream(
                    clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            // 클라이언트가 “END”를 입력하면 클라이언트 소켓 닫기
            if (sentence.equalsIgnoreCase("end")) {
                System.out.println("==END==");
                clientSocket.close();
                break;
            }

            answerSentence = inFromServer.readLine();
            System.out.println(answerSentence);
        }
    }
}
