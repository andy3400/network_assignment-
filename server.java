import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import path.to.File_info; // Replace "path.to" with the actual package path

public class Server {
    public static void main(String[] args) throws Exception {
        String[] file = new String[2];
        file = File_info.getInfo("file_info.java");

        String ip = file[0];
        int port = Integer.parseInt(file[1]);

        String client_Sentence;
        String represent = "";
        String answer_Sentence;
        String split[]; // to store integer value

        // create a TCP socket
        ServerSocket welcomeSocket = new ServerSocket(port);
        // server start
        System.out.println("Server start...");

        while (true) {
            // 연결 기다림
            // 클라이언트 서버가 연결이 되면, 서버 소켓이 연결됨
            Socket connectionSocket = welcomeSocket.accept();

            // 대화중… communicate…
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            client_Sentence = inFromClient.readLine();

            // 클라이언트 서버가 “END”를 입력하면 프로그램 종료
            if (client_Sentence.equalsIgnoreCase("end")) {
                System.out.println("Client entered END");
                continue;
            }

            split = client_Sentence.split(" ");

            try {
                // 에러 확인
                if (split.length < 3) { // 클라이언트으로부터 받은 메시지의 길이가 3보다 작으면
                    represent = "Incorrect: too short arguments";
                    throw new Exception("Error: too short arguments");
                } else if (split.length > 3) { // 클라이언트로부터 받은 메시지의 길이가 3보다 작으면
                    represent = "Incorrect: too long arguments";
                    throw new Exception("Error: too long arguments");
                }

                // 덧셈, 뺄셈, 곱셈, 나눗셈
                else {
                    Double a = Double.parseDouble(split[1]);
                    Double b = Double.parseDouble(split[1]);
                    switch (split[0].toUpperCase()) {
                        case "ADD":
                            answer_Sentence = "Answer: " + String.valueOf(
                                    Double.parseDouble(split[1]) + Double.parseDouble(split[2]));
                            represent = split[1] + " + " + split[2];
                            break;
                        case "MINUS":
                            answer_Sentence = "Answer: " + String.valueOf(
                                    Double.parseDouble(split[1]) - Double.parseDouble(split[2]));
                            represent = split[1] + " - " + split[2];
                            break;
                        case "MUL":
                            answer_Sentence = "Answer: " + String.valueOf(
                                    Double.parseDouble(split[1]) * Double.parseDouble(split[2]));
                            represent = split[1] + " x " + split[2];
                            break;
                        case "DIV":
                            answer_Sentence = "Answer: " + String.valueOf(
                                    Double.parseDouble(split[1]) / Double.parseDouble(split[2]));
                            represent = split[1] + " / " + split[2];
                            break;
                        default:
                            represent = "Incorrect: invalid arguments";
                            throw new Exception("Error: invalid arguments");
                    }
                }
            } catch (NumberFormatException e) {
                represent = "Incorrect: invalid arguments";
                throw new Exception("Error: invalid arguments");
            } catch (Exception e) {
                answer_Sentence = e.getMessage();
            }

            System.out.println(represent);

            outToClient.writeBytes(answer_Sentence + "\n");
        }
    }
}
