package project.quack.domain;

import java.util.Scanner;

public class AdminService {

	public static void main(String[] args) {

		// 객체 생성

		// Admin AdminService = new Admin();

		// 메소드 호출
		Scanner sc = new Scanner(System.in);
		System.out.println("ID를 입력하세요>>");
		String idInput = sc.nextLine();
		System.out.println("PW를 입력하세요>>");
		String pwInput = sc.nextLine();

		if (idInput.equals("quack") && (pwInput.equals("1234"))) {
			System.out.println("로그인 되었습니다.");
			MenuDaoTest.main(args);

		} else {
			System.out.println("ID 또는 패스워드가 올바르지 않습니다.");

		}

	}

}
