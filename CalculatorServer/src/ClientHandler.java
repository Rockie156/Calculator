import java.io.BufferedInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import Expressions.Expression;
import Visitors.TreeVisitor;

public class ClientHandler extends Thread {
	Socket client;

	public static int count = 0;
	public static ArrayList<Expression> expressions = new ArrayList<>();
	
	public ClientHandler(Socket client_socket) {
		client = client_socket;
	}
	
	@Override
	public void run() {
		Expression readExpression = null;
		try {
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			readExpression = (Expression) input.readObject();
			client.close();
		} catch (Exception e) {
			System.err.println("Failed to read expression");
			e.printStackTrace();
		}
		String tree = readExpression.accept(new TreeVisitor());
		
		try {
			FileWriter fw = new FileWriter("equations.txt", true);
			PrintWriter writer = new PrintWriter(fw); 
			writer.println(tree);
			writer.println("");
			writer.flush();
			writer.close();
			//fw.close();
		} catch (Exception e) {
			System.err.println("Failed to write to file.");
			e.printStackTrace();
		}
		synchronized (this) {
			count++;
			expressions.add(readExpression);
			System.out.println("The total number of expressions received is: " + count);
			System.out.println("The tree for the expression received is as follows:");
			System.out.println(tree);
		}
	}

}
