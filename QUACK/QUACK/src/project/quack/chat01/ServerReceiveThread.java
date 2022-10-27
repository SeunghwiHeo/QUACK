package project.quack.chat01;

import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerReceiveThread extends Thread {

	private final Socket socket;
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("(HH:mm:ss) ");
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	public ServerReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			DataInputStream tmpbuf = new DataInputStream(socket.getInputStream());
			String receiveString;
			while (true) {
				receiveString = tmpbuf.readUTF();
				System.out.printf("\n" + formatter.format(date) + "손님 : " + receiveString + "\n");
				if(receiveString.equals("호출 합니다")) {
					for(int i = 0; i < 5; i++) {
						toolkit.beep();
						System.out.println("띵동");
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
						}
					}
				} else if (receiveString.equals("손님께서 퇴장하셨습니다.")) {
					System.out.println("엔터를 두번 눌러주세요");
				}
			}
		} catch (SocketException e1) {
			System.out.println("상대방 연결이 종료되었습니다.");
		} catch (IOException e2) {
			System.out.println();
		}
	}
}
