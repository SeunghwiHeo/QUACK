package project.quack.chat01;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import project.quack.domain.Mainscreen;

public class ClientSendThread extends Thread {

	private final Socket socket;
	Scanner scanner = new Scanner(System.in);

	public ClientSendThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			DataOutputStream sendWriter = new DataOutputStream(socket.getOutputStream());
			String sendString;
			while (true) {
				System.out.print("손님 : ");
				sendString = scanner.nextLine();
				if(sendString.equalsIgnoreCase("/q")) {
					System.out.println("채팅을 종료합니다");
					sendWriter.writeUTF("손님께서 퇴장하셨습니다.");
					sendWriter.close();
					Mainscreen.main(null);//손님이 "q"를 입력한 경우 서버로 전송 후 연결 종료
				} else if(sendString.equalsIgnoreCase("/w")) { // /w 입력시 카운터에 호출합니다 전송
					sendWriter.writeUTF("호출 합니다");
				} else {
					sendWriter.writeUTF(sendString + "\n");  // "q" 문자열 전송
					sendWriter.flush();
				}
			}
		} catch (IOException e) {
		}

	}

}