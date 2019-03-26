import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDriver {
    public static int port = 6500;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket(port);

        while (true) {
            Socket client_socket = server_socket.accept();
            ClientHandler handler = new ClientHandler(client_socket);
            handler.start();
        }

    }

}
