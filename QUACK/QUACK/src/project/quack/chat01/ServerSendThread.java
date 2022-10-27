package project.quack.chat01;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerSendThread extends Thread {

	private final Socket socket;
	private Scanner scanner = new Scanner(System.in);

	public ServerSendThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println("대기중....");
			DataOutputStream sendWriter = new DataOutputStream(socket.getOutputStream());
			String sendString;
			while (true) {
				System.out.print("카운터 : ");
				sendString = scanner.nextLine();
				if(sendString != null) {
					sendWriter.writeUTF(sendString);
					sendWriter.flush();					
				} else if(sendString.equalsIgnoreCase("/q")) {
					System.out.println("채팅을 종료합니다.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("대기중....");
		}
	}
}