package project.quack.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.quack.cmn.DTO;
import project.quack.cmn.LoggerManager;
import project.quack.cmn.WorkDiv;
import project.quack.domain.Menu;
import project.quack.domain.Search;

public class MenuDao implements WorkDiv<Menu>, LoggerManager {

	public static ArrayList<Menu> data_menu = new ArrayList<Menu>();

	public final static String SAVE_FILE_PATH = "C:\\DCOM_0725\\03_JAVA\\workspace2\\QUACK\\menu.txt";

	public MenuDao() {
		readFile(SAVE_FILE_PATH);
		LOG.debug("data.size:" + MenuDao.data_menu.size());// 메뉴 개수 data.size 출력

		for (Menu menu : MenuDao.data_menu) {
			LOG.debug(menu.toString());
		}

	}

	public int readFile(String filepath) {// readFile
		int flag = 0;

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(SAVE_FILE_PATH);
			br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				// 001, 오리훈제, 30000원, 1

				String inArray[] = line.split(",");

				// 주문상태 가능여부
				// 1 -> true
				// 0 -> false
				boolean available = inArray[3].equals("true") ? true : false;

				Menu menu = new Menu(inArray[0], inArray[1], inArray[2], available);
				// ArrayList 에 추가
				MenuDao.data_menu.add(menu);

			}

			if (MenuDao.data_menu.size() > 0)
				flag++;

		} catch (IOException e) {
			LOG.debug("IOException:" + e.getMessage());

		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.debug("IOException:" + e.getMessage());
				}
			}
		}

		return flag;
	}

	public int writeFile(String filepath) {// writeFile
		// ArrayList<Menu> -> File로 기록

		try (FileWriter fw = new FileWriter(filepath); BufferedWriter bw = new BufferedWriter(fw);) {
			// 005,제육볶음,18000원,1
			for (Menu menu : MenuDao.data_menu) {

				String delim = ",";

				String outLine = menu.getMenuNum() + delim + menu.getMenuName() + delim + menu.getPrice() + delim
						+ menu.isAvailable() + "\n";
				bw.write(outLine);
			}
		} catch (IOException e) {
			System.out.println("IOException:" + e.getMessage());
		}

		return MenuDao.data_menu.size();
	}

	@Override
	public List<Menu> doRetrieve(DTO dto) {// 검색
		// 데이터를 찾아 return
		List<Menu> list = new ArrayList<Menu>();

		Search search = (Search) dto;

		int div = search.getSearchDiv();
		String searchWord = search.getSearchWord();
		for (Menu menu : MenuDao.data_menu) {

			switch (div) {

			case 10:// 메뉴번호
				if (menu.getMenuNum().matches(".*" + searchWord + ".*")) {// 메뉴번호로 검색
					list.add(menu);
				}
				break;

			case 20:// 메뉴이름
				if (menu.getMenuName().matches(".*" + searchWord + ".*")) {// 메뉴이름으로 검색
					list.add(menu);
				}
				break;

			case 100:// 전체
				list.add(menu);
				break;
			}

		}

		return list;
	}

	/**
	 * 메뉴목록에 메뉴존재 확인
	 * 
	 * @param menu
	 * @return true(존재)/false(없음)
	 */
	public boolean isMenuExists(Menu menu) {// 존재확인
		boolean flag = false;

		for (Menu vo : MenuDao.data_menu) {
			if (menu.getMenuNum().equals(vo.getMenuNum())) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	@Override
	public int doSave(Menu dto) {// 저장
		Menu menu = dto;// param 읽기
		if (isMenuExists(menu) == true) {
			LOG.debug("menuNum을 확인 하세요.(" + menu.getMenuNum() + ")");
			return 0;
		}

		boolean isContains = MenuDao.data_menu.contains(menu);
		LOG.debug("isContains:" + isContains);

		boolean flag = MenuDao.data_menu.add(menu);
		return (flag == true) ? 1 : 0;
	}

	@Override
	public int doUpdate(Menu dto) {// 수정
		int flag = 0;
		// data_menu delete, insert
		// 1. 존재 여부 확인
		// 2. 기존 데이터 삭제
		// 3. 삭제후 새 데이터 등록
		if (isMenuExists(dto) == true) {
			int tmp = 0;
			tmp += doDelete(dto);
			tmp += doSave(dto);

			if (tmp == 2)
				flag++;
		}
		return flag;
	}

	@Override
	public int doDelete(Menu dto) {// 삭제

		int flag = 0;

		for (int i = MenuDao.data_menu.size() - 1; i >= 0; i--) {
			Menu tmp = MenuDao.data_menu.get(i);
			if (dto.getMenuNum().equals(tmp.getMenuNum())) {
				Menu menu = MenuDao.data_menu.remove(i);
				LOG.debug("삭제 데이터:" + menu);
				flag = 1;
				break;
			}
		}
		return flag;
	}

	@Override
	public Menu doSelectOne(Menu obj) {// 단건조회
		Menu menu = null;
		// 1. 존재 확인
		// 2. 존재하면 Menu을 return
		if (isMenuExists(obj)) {
			for (Menu tmpMenu : MenuDao.data_menu) {
				if (tmpMenu.getMenuNum().equals(obj.getMenuNum())) {
					menu = tmpMenu;
					break;
				}
			}
		}
		return menu;
	}

}
