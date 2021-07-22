package server;
import com.hit.algorithm.AES;
import service.ChattingService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server  {
    private ServerSocket serverSocket;
    private final ChattingService chattingService = new ChattingService(new AES(), "src/resources/DB/Users.json");
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Server is waiting for requests...");
            Socket socket;
            try {
                socket = serverSocket.accept();
                Thread thread = new Thread(new HandleRequest(socket, chattingService));
                thread.start();
            } catch (SocketException ignored) {
            } //ignored on purpose
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(1111);
        server.run();
    }
}
