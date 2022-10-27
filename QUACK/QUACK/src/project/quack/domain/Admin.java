package project.quack.domain;

public class Admin {
	String id;

	String password;

	// 메소드

	boolean login(String id, String password) {

		if (id == "quack" && password == "1234") {

			return true;

		}

		else {

			return false;

		}

	}

	void logout(String id) {

		System.out.println("로그아웃 되었습니다.");

	}
}
