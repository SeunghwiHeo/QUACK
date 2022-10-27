package project.quack.chat01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) throws IOException {
		int port = 8888;
		ServerSocket socketServer = new ServerSocket(port);
		System.out.println("접속을 기다리는 중...");
		while (true) {
			Socket sock = socketServer.accept();
			System.out.println("ip : " + sock.getInetAddress() + " 와 연결되었습니다.");
			ServerReceiveThread receiveThread = new ServerReceiveThread(sock);
			receiveThread.start();
			ServerSendThread sendThead = new ServerSendThread(sock);
			sendThead.start();
		}
	}
}
