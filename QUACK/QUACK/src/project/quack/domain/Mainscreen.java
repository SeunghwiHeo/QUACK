package project.quack.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import project.quack.chat01.ChatMain;
import project.quack.game.AlligatorTeeth_Game;
import project.quack.game.todays_fortune;

public class Mainscreen {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		AlligatorTeeth_Game atg = new AlligatorTeeth_Game();
		todays_fortune tf = new todays_fortune();
		// 메인 화면 메모장 읽어 오기

		int i;
		int staff; // 직원호출 코드
		while (true) {
			try {
				FileInputStream fis = new FileInputStream("C:\\DCOM_0725\\03_JAVA\\workspace2\\QUACK\\mainscreen.txt");
				Scanner s = new Scanner(fis);
				while (s.hasNext())
					System.out.println(s.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
			}

			i = sc.nextInt();

			if (i == 1) {

				// -------주문 하기------------
				OrderDaoTest.main(args);
			}  else if (i == 2) {
				// ---------직원 호출------------
					try {
						ChatMain.main(args);
						break;
					} catch (IOException e) {
					}

			} else if (i == 3) {
				System.out.println("1. 악어이빨 게임💣💣💣💣💣");
				System.out.println("2. 오늘의 운세※※※※※");
				i = sc.nextInt();
				if (i == 1) {
					atg.main(args);
				}
				if (i == 2) {
					tf.main(args);
				}
			} else if (i == 1234) {
				AdminService.main(args);
				
			} else if(i == 0) {
				break;
			}

		}
	}

}
