package project.quack.game;

import java.io.FileInputStream;
import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class AlligatorTeeth_Game {

	public static void main(String[] args) {
		
		// 악어이빨 게임 
		
		Scanner scanner = new Scanner(System.in);
	
		while (true) {
			try {
				FileInputStream fis = new FileInputStream("C:\\DCOM_0725\\03_JAVA\\workspace2\\QUACK\\alligator.txt");
				Scanner s = new Scanner(fis);
				while (s.hasNext())
					System.out.println(s.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int num01 = scanner.nextInt();
			
		if(num01 == 1) {
			
		System.out.printf   ("\n"                        +                               	
							"    {21} {20} {19}\n"       +
						    "   {22}        {18}\n"      +										
						    "  {23}          {17}\n"     +								
						    " {24}            {16}\n"    +
						    " {25}            {15}\n"    +
						    " {26}            {14}\n"    +
						    "========================\n" +
					        " {1}             {13}\n"    +
					        " {2}	         {12}\n"    +
						    " {3}             {11}\n"    +
						    "  {4}           {10}\n"     +
							"   {5}          {9}\n"      +
							"     {6} {7} {8}\n"        );
			  
			int rd = (int)(Math.random() * 26) + 1;
			//System.out.println(rd);
			
			ArrayList<Integer> num = new ArrayList<Integer>();
			boolean check = true;
				
			while (true) {
			
			System.out.println();
			System.out.print("숫자를 입력하세요. ==> ");
			int n1 = scanner.nextInt();
			System.out.print("0입력 시 종료");
			System.out.println();
			
			
			if(n1 == rd) {
				System.out.println("**벌칙 당첨!**");
				break;				
			} else if(n1==0) {
				System.out.println("게임을 종료합니다.");
				break;
			}else if (n1 < 1 || n1 > 26) {
				System.out.println("1 ~ 26사이의 숫자를 입력하세요.");	
			} else if (num.contains(n1)) {
				System.out.println("이미 입력하신 숫자입니다. 다시 입력해 주세요.");
				continue;
			} else if(n1 > 0 || n1 < 27){
				num.add(n1);
				System.out.print("입력한 숫자 : ");
				Collections.sort(num);
				System.out.print(num);
			}
				System.out.println();
			
		   
	       }//while
		
		} else if (num01 == 2){
			System.out.println("게임을 종료 하셨습니다.");
			break; 
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			System.out.println();
			continue;
		}
		
		System.out.println();
		}
				
	}

}
