package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Expressions.Expression;

public class Client {
	public String host = "127.0.0.1";
	public int port = 6500;
	public void sendToServer(Expression expr) {
		try {
			Socket socket = new Socket(host, port);
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(expr);
			output.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Failed to send equation to server.");
		//			e.printStackTrace();
		}
		
	}
}
