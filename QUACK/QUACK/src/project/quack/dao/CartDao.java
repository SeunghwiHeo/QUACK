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
import project.quack.cmn.WorkDiv2;
import project.quack.domain.Cart;
import project.quack.domain.Search;

public class CartDao implements WorkDiv2<Cart>, LoggerManager {

	public static ArrayList<Cart> data_cart = new ArrayList<Cart>();

	public final static String SAVE_FILE_PATH2 = "C:\\DCOM_0725\\03_JAVA\\workspace2\\QUACK\\cart.txt";

	public CartDao() {
		readFile(SAVE_FILE_PATH2);
		LOG.debug("data.size:" + CartDao.data_cart.size());// 메뉴 개수 data.size 출력

		for (Cart cart : CartDao.data_cart) {
			LOG.debug(cart.toString());
		}

	}

	public int readFile(String filepath) {
		int flag = 0;

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(SAVE_FILE_PATH2);
			br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				// 001, 오리훈제, 30000원, 1

				String inArray[] = line.split(",");

				Cart cart = new Cart(inArray[0], inArray[1], inArray[2], inArray[3]);
				// ArrayList 에 추가

				CartDao.data_cart.add(cart);
			}

			if (CartDao.data_cart.size() > 0)
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

	public int writeFile(String filepath) {
		// ArrayList<Cart> -> File로 기록

		try (FileWriter fw = new FileWriter(filepath); BufferedWriter bw = new BufferedWriter(fw);) {
			// 005,제육볶음,18000원,1
			for (Cart cart : CartDao.data_cart) {

				String delim = ",";

				String outLine = cart.getMenuNum() + delim + cart.getMenuName() + delim + cart.getPrice() + delim
						+ cart.getAmount() + "\n";
				bw.write(outLine);
			}
		} catch (IOException e) {
			System.out.println("IOException:" + e.getMessage());
		}

		return CartDao.data_cart.size();
	}

	@Override
	public List<Cart> doRetrieve(DTO dto) {// 검색
		// 데이터를 찾아 return
		List<Cart> list = new ArrayList<Cart>();

		Search search = (Search) dto;

		int div = search.getSearchDiv();
		String searchWord = search.getSearchWord();
		for (Cart cart : CartDao.data_cart) {

			switch (div) {

			case 10:// 메뉴번호
				if (cart.getMenuNum().matches(".*" + searchWord + ".*")) {// 메뉴번호로 검색
					list.add(cart);
				}
				break;

			case 20:// 메뉴이름
				if (cart.getMenuName().matches(".*" + searchWord + ".*")) {// 메뉴이름으로 검색
					list.add(cart);
				}
				break;

			case 100:// 전체
				list.add(cart);
				break;
			}

		}

		return list;
	}

	public boolean isCartExists(Cart cart) {// 존재확인
		boolean flag = false;

		for (Cart vo : CartDao.data_cart) {
			if (cart.getMenuNum().equals(vo.getMenuNum())) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	@Override
	public int doSave(Cart dto) {// 저장
		Cart cart = dto;// 
		if (isCartExists(cart) == true) {
			LOG.debug("cartNum을 확인 하세요.(" + cart.getMenuNum() + ")");

			return 0;
		}

		boolean isContains = CartDao.data_cart.contains(cart);
		LOG.debug("isContains:" + isContains);

		boolean flag = CartDao.data_cart.add(cart);
		return (flag == true) ? 1 : 0;
	}

	@Override
	public int doUpdate(Cart dto) {// 수정
		int flag = 0;
		// data_cart delete, insert
		// 1. 존재 여부 확인
		// 2. 기존 데이터 삭제
		// 3. 삭제후 새 데이터 등록
		if (isCartExists(dto) == true) {
			int tmp = 0;
			tmp += doDelete(dto);
			tmp += doSave(dto);

			if (tmp == 2)
				flag++;
		}
		return flag;
	}

	@Override
	public int doDelete(Cart dto) {// 삭제

		int flag = 0;

		for (int i = CartDao.data_cart.size() - 1; i >= 0; i--) {
			Cart tmp = CartDao.data_cart.get(i);
			if (dto.getMenuNum().equals(tmp.getMenuNum())) {
				Cart cart = CartDao.data_cart.remove(i);
				LOG.debug("삭제 데이터:" + cart);
				flag = 1;
				break;
			}
		}
		return flag;
	}

	@Override
	public Cart doSelectOne(Cart obj) {// 단건조회
		// data.get(i)
		Cart cart = null;
		// 1. 존재 확인
		// 2. 존재하면 Cart을 return
		if (isCartExists(obj)) {
			for (Cart tmpCart : CartDao.data_cart) {
				if (tmpCart.getMenuNum().equals(obj.getMenuNum())) {
					cart = tmpCart;
					break;
				}
			}
		}
		return cart;
	}
}
