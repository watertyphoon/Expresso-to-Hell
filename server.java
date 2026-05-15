import java.io.*;
import java.net.*;
import java.util.*;

public class server {
	private static List<PrintWriter> players = Collections.synchronizedList(new ArrayList<>());
	private static class client implements Runnable {
		private Socket socket;
		private PrintWriter output;

		public client(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				output = new PrintWriter(socket.getOutputStream(), true);
				players.add(output);
				String text;
				while((text = input.readLine()) != null) {
					System.out.println("Recieved");
					synchronized(players) {
						for (PrintWriter p : players) {
							p.println(text);
						}
					}
				}
			}catch (IOException e) {
				System.out.println("Player disconnected.");
			}finally {
				players.remove(output);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Expresso Server started on port 6769...");
		ServerSocket serverSocket = new ServerSocket(6769);
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				new Thread(new client(socket)).start();
			}
		}finally {
			serverSocket.close();
			}
	}
}

