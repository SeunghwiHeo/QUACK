package project.quack.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.quack.cmn.LoggerManager;
import project.quack.dao.CartDao;
import project.quack.dao.MenuDao;

public class OrderDaoTest implements LoggerManager {

	CartDao dao;
	Cart cart01;
	Cart cart02;
	Cart cart03;

	public OrderDaoTest() {
		dao = new CartDao();
		cart01 = new Cart("001", "오리훈제", "30000", "1");
		cart02 = new Cart("002", "오리주물럭", "30000", "1");
		cart03 = new Cart("003", "오리백숙", "30000", "1");
	}

	// 저장
	public void doSave() {
		int flag = dao.doSave(cart01);
		if (flag == 1) {
			LOG.debug(cart01);
			LOG.debug("등록 성공");
		} else {
			LOG.debug("등록 실패");
		}

		for (Cart cart : CartDao.data_cart) {
			System.out.println("Cart:" + cart);
		}

		dao.writeFile(CartDao.SAVE_FILE_PATH2);
	}

	// 삭제
	public void doDelete() {
		int flag = dao.doDelete(cart02);
		if (flag == 1) {
			LOG.debug(cart02);
			LOG.debug("삭제 성공");
		} else {
			LOG.debug("삭제 실패");
		}

		for (Cart cart : CartDao.data_cart) {
			System.out.println("cart:" + cart);
		}

		dao.writeFile(CartDao.SAVE_FILE_PATH2);
	}

	public void dispArrayList() {// ArrayList 출력
		for (Cart cart : CartDao.data_cart) {
			LOG.debug(cart);
		}
	}

	// 수정
	public void doUpdate() {

		cart01.setMenuNum(cart01.getMenuNum() + "_U");
		cart01.setMenuName(cart01.getMenuName() + "_U");
		cart01.setPrice(cart01.getPrice() + "_U");

		int flag = dao.doUpdate(cart01);
		if (1 == flag) {
			LOG.debug(cart01);
			LOG.debug("수정 성공");
		} else {
			LOG.debug("수정 실패");
		}

		dispArrayList();
		dao.writeFile(CartDao.SAVE_FILE_PATH2);
	}

	public void deleteAll() {
		dao.doDelete(cart01);
		dao.doDelete(cart02);
		dao.doDelete(cart03);
	}

	public void doSelectOne() {// 단건 조회(메뉴번호)

		cart01.setMenuNum("001");
		if (null != dao.doSelectOne(cart01)) {
			LOG.debug(dao.doSelectOne(cart01));
			LOG.debug("조회 성공");
		} else {
			LOG.debug("조회 실패");
		}
	}

	public void doRetrieve() {
		Search search = new Search(20, "오리주물럭");
		List<Cart> list = dao.doRetrieve(search);

		for (Cart cart : list) {
			LOG.debug("cart:" + cart);
		}

	}

	public Cart getInputData(Scanner scanner) {
		Cart cart = null;
		String[] dataArr = scanner.nextLine().trim().split(",");// 문자열 공백 제거, ","로 구분
		// 대여가능 여부
		// 1 -> true
		// 0 -> false
		cart = new Cart(dataArr[0], dataArr[1], dataArr[2], dataArr[3]);// 메뉴번호,메뉴이름,가격,수량

		return cart;
	}
	
	public static void viewCart() {// 카트 보기
		System.out.println("------------------------------------");
		System.out.println("메뉴번호\t메뉴이름\t가격(원)\t수량");
		System.out.println("------------------------------------");
		String delim4 = "\t";

		for (Cart tmp : CartDao.data_cart) {
			String outLine = tmp.getMenuNum() + delim4 + tmp.getMenuName() + delim4 + tmp.getPrice() + delim4
					+ tmp.getAmount() + "\n";

			System.out.println(outLine);
		}
	}
	
	public static void viewMenu() {// 메뉴 보기
		System.out.println("------------------------------------");
		System.out.println("메뉴번호\t메뉴이름\t가격(원)\t주문가능여부");
		System.out.println("------------------------------------");
		String delim = "\t";
		for (Menu tmp : MenuDao.data_menu) {
			String outLine1 = tmp.getMenuNum() + delim + tmp.getMenuName() + delim + tmp.getPrice() + delim
					+ tmp.isAvailable() + "\n";

			System.out.println(outLine1);
		}
	}

	public static void main(String[] args) {
		OrderDaoTest main = new OrderDaoTest();

		// List<String> cartList = new ArrayList<String>();

		Scanner scanner = new Scanner(System.in);
		String inCommand = "";// 명령어
		do {
			System.out.println("☆★☆★ QUACK ☆★☆★");
			System.out.println("1 : 주문하기    ");
			System.out.println("2 : 주문내역    ");
			System.out.println("3 : 주문수정    ");
			System.out.println("4 : 취소하기    ");
			System.out.println("5 : 영수증 ");
			System.out.println("Q : 주문완료    ");
			System.out.print(">>");

			// scanner에서 데이터 입력
			inCommand = scanner.nextLine();
			inCommand = inCommand.trim();// 양쪽 공백, 대문자로 변환

			int inPrice = 0;// 영수금액
			int tax = 0;// 부가세
			int total = 0;// 합계금액
			String ppNum = "";
			Cart cart = null;
			//String[] dataArr = null;
			String readData = "";// command 입력 데이터

			switch (inCommand.toUpperCase()) {// 대상 문자열 대문자로 변환

			case "1":// 주문하기
				System.out.println("-----------------------------------------");
				System.out.println("-               [MENU]                  -");
				System.out.println("-----------------------------------------");
				System.out.println("-----------------------------------------");
				System.out.println("메뉴번호,메뉴이름,가격(원),주문가능상태(true:가능,false:불가능)");
				System.out.println("-----------------------------------------");
				//String delim = "\t";

				Scanner sc2;
				try {
					sc2 = new Scanner(new File("C:\\DCOM_0725\\03_JAVA\\workspace2\\QUACK\\menu.txt"));
					while (sc2.hasNext()) {
						String str = sc2.next();
						System.out.println(str);

					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("주문하기: 메뉴번호(001),메뉴이름(오리훈제),가격(30000),수량(1) 형식으로 추가하세요.\n ex)001,오리훈제,30000,1>>");
				// 메뉴번호,메뉴이름,가격,1(true) or 0(false)>>형식으로 입력

				cart = main.getInputData(scanner);
				int saveFlag = main.dao.doSave(cart);
				if (saveFlag == 1) {
					LOG.debug(cart.getMenuName() + " 추가 완료!");
				} else {
					LOG.debug(cart.getMenuName() + " 추가 실패!.");
				}

				break;

			case "2":// 장바구니 조회

				viewCart();

				break;

			case "3":// 주문 수정

				viewCart();
				
				System.out.println(
						"메뉴수정: 메뉴번호(001),메뉴이름(오리훈제),가격(30000),수량(0,1,2,3...) 형식으로 수정하세요\n ex)001,오리훈제,30000,1>>");
				// 메뉴번호,메뉴이름,가격,available>>형식으로 입력
				Cart saveCart = main.getInputData(scanner);
				int upFlag = main.dao.doUpdate(saveCart);
				if (1 == upFlag) {
					LOG.debug("수정 완료!");
				} else {
					LOG.debug("수정 실패!");
				}

				break;

			case "4":// 주문 삭제
				viewCart();
				
				System.out.print("메뉴삭제:메뉴번호(001)를 입력하여 삭제하세요.\n ex)001>>");// 메뉴번호로 삭제
				readData = scanner.nextLine().trim();
				cart = new Cart(readData, readData, readData, readData);//
				cart.setMenuNum(readData);
				int delFlag = main.dao.doDelete(cart);
				if (1 == delFlag) {
					LOG.debug(cart.getMenuNum() + " 삭제 완료!");
				} else {
					LOG.debug(cart.getMenuNum() + " 삭제 실패!");
				}

				break;

			case "5":// 영수증

				System.out.println("------------------------------------");
				System.out.println("               주문 내역                                  ");
				viewCart();

				for (int i = 0; i < CartDao.data_cart.size(); i++) {// total 금액 구하기
					total += (Integer.parseInt(CartDao.data_cart.get(i).getPrice().toString()))
							* (Integer.parseInt(CartDao.data_cart.get(i).getAmount().toString()));
				}
				tax = total / 10;
				inPrice = total - tax;
				System.out.println("====================================");
				System.out.println("=               영수증                                  =");
				System.out.println("====================================");
				System.out.println("금 액:                        " + inPrice + "원");
				System.out.println("부가세:                        " + tax + "원");
				System.out.println("합 계:                        " + total + "원");
				System.out.println("-------------------------------------");
				System.out.println("가맹점명:                       QUACK");
				System.out.println("사업자번호:                 1234567890");
				System.out.println("대표자명:                      팀QUACK");
				System.out.println("주소:      서울시 마포구 서강로 136 2층 C강의실");
				System.out.println("TEL:                      1234-5678");
				System.out.println("====================================");

				System.out.println("총 금액 :" + total + "원 입니다.");
				System.out.println("더치페이 금액을 확인할 인원을 입력하세요>>");
				ppNum = scanner.nextLine().trim();
				System.out.println("1인당 지불할 금액은 :" + (total / Integer.parseInt(ppNum)) + "원 입니다.");

				break;

			case "Q":// data파일에 저장하고, 종료

				int flag = main.dao.writeFile(CartDao.SAVE_FILE_PATH2);
				LOG.debug("저장건수:" + flag);
				break;
			}// --switch

		} while (!inCommand.equalsIgnoreCase("Q"));

		LOG.debug("==========================================================");
		LOG.debug("========================프로그램 종료=========================");
		LOG.debug("==========================================================");
	}

}
