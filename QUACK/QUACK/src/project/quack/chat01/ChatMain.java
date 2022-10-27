package project.quack.chat01;

import java.io.IOException;
import java.net.Socket;

public class ChatMain {

	public static void main(String[] args) throws IOException {
		// 192.168.3.16
		Socket sock = new Socket("192.168.3.26", 8888);
		System.out.println("서버와 접속되었습니다.");
		System.out.println("종료 : /q, 호출 : /w");
		ClientReceiveThread receiveThread = new ClientReceiveThread(sock);
		receiveThread.start();
		ClientSendThread sendThread = new ClientSendThread(sock);
		sendThread.start();
	}

}
