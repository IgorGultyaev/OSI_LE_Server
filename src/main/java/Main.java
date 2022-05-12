import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static String clientRequest(String request, ServerSocket serverSocket, boolean needRequest) throws IOException {

        Socket clientSocket = serverSocket.accept(); // ждем подключения
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println(request);

        if (needRequest) {
            return in.readLine();
        } else return "";
    }

    public static void main(String[] args) throws IOException {

        int port = 8089;
        String massage = "Write your name";
        String request = "start";
        String name = "noname";
        Boolean child;

        ServerSocket serverSocket = new ServerSocket(port);
        while (!request.equals("end#")) {

            request = clientRequest(massage, serverSocket, true);

            if (massage.equals("Write your name")) {
                name = request;
                massage = "Are you child? (yes/no)";
            } else if (request.equals("yes")) {
                clientRequest("Welcome to the kids area, " + name + " Let's play!", serverSocket, false);
                massage = "Write your name";
            } else {
                clientRequest("Welcome to the adult zone, " +
                                name + " Have a good rest, or a good working day!",
                        serverSocket, false);
                massage = "Write your name";
            }
        }
        System.out.println("the server is stopped");
    }
}
