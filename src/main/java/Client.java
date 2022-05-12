import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws IOException, ConnectException {
        String host = "netology.homework";
        String serverMassage;
        Scanner scanner = new Scanner(System.in);
        String massage = "start";
        int port = 8089;
        System.out.println(host);
        System.out.println("для завершения диалога введите  - end#");
        while (!massage.equals("end#")) {
            try (Socket clientSocket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                serverMassage = in.readLine();
                System.out.println(serverMassage);
                if (serverMassage.equals("Write your name") || serverMassage.equals("Are you child? (yes/no)")) {
                    massage = scanner.nextLine();
                    out.println(massage);
                }


            } catch (IOException e) {
//
                System.out.println("Сервер не отвечает");
            }

        }
        System.out.println("the server is stopped");
    }
}