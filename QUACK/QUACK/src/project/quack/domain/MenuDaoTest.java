package project.quack.domain;

import java.util.List;
import java.util.Scanner;

import project.quack.cmn.LoggerManager;
import project.quack.dao.MenuDao;

public class MenuDaoTest implements LoggerManager {

	MenuDao dao;
	Menu menu01;
	Menu menu02;
	Menu menu03;

	public MenuDaoTest() {
		dao = new MenuDao();
		menu01 = new Menu("001", "오리훈제", "30000원", true);
		menu02 = new Menu("002", "오리주물럭", "30000원", true);
		menu03 = new Menu("003", "오리백숙", "30000원", true);
	}

	// 저장
	public void doSave() {
		int flag = dao.doSave(menu01);
		if (flag == 1) {
			LOG.debug(menu01);
			LOG.debug("등록 성공");
		} else {
			LOG.debug("등록 실패");
		}

		for (Menu menu : MenuDao.data_menu) {
			System.out.println("Menu:" + menu);
		}

		dao.writeFile(MenuDao.SAVE_FILE_PATH);
	}

	// 삭제
	public void doDelete() {
		int flag = dao.doDelete(menu02);
		if (flag == 1) {
			LOG.debug(menu02);
			LOG.debug("삭제 성공");
		} else {
			LOG.debug("삭제 실패");
		}

		for (Menu menu : MenuDao.data_menu) {
			System.out.println("menu:" + menu);
		}

		dao.writeFile(MenuDao.SAVE_FILE_PATH);
	}

	public void dispArrayList() {// ArrayList 출력
		for (Menu menu : MenuDao.data_menu) {
			LOG.debug(menu);
		}
	}

	// 수정
	public void doUpdate() {

		menu01.setMenuNum(menu01.getMenuNum() + "_U");
		menu01.setMenuName(menu01.getMenuName() + "_U");
		menu01.setPrice(menu01.getPrice() + "_U");

		int flag = dao.doUpdate(menu01);
		if (1 == flag) {
			LOG.debug(menu01);
			LOG.debug("수정 성공");
		} else {
			LOG.debug("수정 실패");
		}

		dispArrayList();
		dao.writeFile(MenuDao.SAVE_FILE_PATH);
	}

	public void deleteAll() {
		dao.doDelete(menu01);
		dao.doDelete(menu02);
		dao.doDelete(menu03);
	}

	public void doSelectOne() {// 단건 조회(메뉴번호)

		menu01.setMenuNum("001");
		if (null != dao.doSelectOne(menu01)) {
			LOG.debug(dao.doSelectOne(menu01));
			LOG.debug("조회 성공");
		} else {
			LOG.debug("조회 실패");
		}
	}

	public void doRetrieve() {
		Search search = new Search(20, "오리주물럭");
		List<Menu> list = dao.doRetrieve(search);

		for (Menu menu : list) {
			LOG.debug("menu:" + menu);
		}

	}

	public Menu getInputData(Scanner scanner) {
		Menu menu = null;
		String[] dataArr = scanner.nextLine().trim().split(",");// 문자열 공백 제거, ","로 구분
		// 대여가능 여부
		// 1 -> true
		// 0 -> false
		boolean avaliable = dataArr[3].equals("1") ? true : false;
		menu = new Menu(dataArr[0], dataArr[1], dataArr[2], avaliable);

		return menu;
	}
	
	public static void viewMenu() {//메뉴 보기
		System.out.println("-----------------------------------------");
		System.out.println("메뉴번호\t메뉴이름\t가격(원)\t주문가능여부");
		System.out.println("-----------------------------------------");
		String delim = "\t";
		for (Menu tmp : MenuDao.data_menu) {
			String outLine = tmp.getMenuNum() + delim + tmp.getMenuName() + delim + tmp.getPrice() + delim
					+ tmp.isAvailable() + "\n";
			
			System.out.println(outLine);
		}
	}

	public static void main(String[] args) {
		MenuDaoTest main = new MenuDaoTest();

		Scanner scanner = new Scanner(System.in);
		String inCommand = "";// 명령어
		do {
			System.out.println("===관리자 메뉴===");
			System.out.println("C  : 메뉴추가    ");
			System.out.println("U  : 메뉴수정    ");
			System.out.println("D  : 메뉴삭제    ");
			System.out.println("SO : 메뉴단건조회  ");
			System.out.println("SA : 메뉴전체조회  ");
			System.out.println("Q  : 종료    ");
			System.out.print(">>");

			// scanner에서 데이터 입력
			inCommand = scanner.nextLine();
			inCommand = inCommand.trim();// 양쪽 공백, 대문자로 변환

			Menu menu = null;
			//String[] dataArr = null;
			String readData = "";// command 입력 데이터

			switch (inCommand.toUpperCase()) {// 대상 문자열 대문자로 변환

			case "C":// 메뉴 추가
				viewMenu();

				System.out.println(
						"메뉴추가: 메뉴번호(001),메뉴이름(오리훈제),가격(30000),주문가능상태(1=가능,0=불가능) 형식으로 추가하세요.\n ex)001,오리훈제,30000,1>>");
				// 메뉴번호,메뉴이름,가격,1(true) or 0(false)>>형식으로 입력

				menu = main.getInputData(scanner);
				int saveFlag = main.dao.doSave(menu);
				if (saveFlag == 1) {
					LOG.debug(menu.getMenuName() + " 등록 완료!");
				} else {
					LOG.debug(menu.getMenuName() + " 등록 실패!.");
				}

				break;

			case "U":// 메뉴 수정
				
				viewMenu();
				
				System.out.println(
						"메뉴수정: 메뉴번호(001),메뉴이름(오리훈제),가격(30000),주문가능상태(1=가능,0=불가능) 형식으로 수정하세요\n ex)001,오리훈제,30000,1>>");
				// 메뉴번호,메뉴이름,가격,available>>형식으로 입력

				Menu saveMenu = main.getInputData(scanner);
				int upFlag = main.dao.doUpdate(saveMenu);
				if (1 == upFlag) {
					LOG.debug("수정 완료!");
				} else {
					LOG.debug("수정 실패!");
				}

				break;

			case "D":// 메뉴 삭제
				viewMenu();
				System.out.print("메뉴삭제:메뉴번호(001)를 입력하여 삭제하세요.\n ex)001>>");// 메뉴번호로 삭제
				readData = scanner.nextLine().trim();
				menu = new Menu();
				menu.setMenuNum(readData);
				int delFlag = main.dao.doDelete(menu);
				if (1 == delFlag) {
					LOG.debug(menu.getMenuNum() + " 삭제 완료!");
				} else {
					LOG.debug(menu.getMenuNum() + " 삭제 실패!");
				}

				break;

			case "SO":// 메뉴 단건 조회
				System.out.print("메뉴단건조회:메뉴번호(001)를 입력하여 조회하세요.\n ex)001>>");// 메뉴번호로 조회
				readData = scanner.nextLine().trim();
				menu = new Menu();
				menu.setMenuNum(readData);

				Menu selectOne = main.dao.doSelectOne(menu);
				if (null != selectOne) {
					LOG.debug(selectOne + " 조회 완료!");
				} else {
					LOG.debug(selectOne + " 조회 실패!");
				}
				break;

			case "SA":// 메뉴 전체 조회

				viewMenu();

				break;

			case "Q":// data파일에 저장하고, 종료
				int flag = main.dao.writeFile(MenuDao.SAVE_FILE_PATH);
				LOG.debug("저장건수:" + flag);
				break;
			}// --switch

		} while (!inCommand.equalsIgnoreCase("Q"));

		LOG.debug("==========================================================");
		LOG.debug("========================프로그램 종료=========================");
		LOG.debug("==========================================================");
	}

}
