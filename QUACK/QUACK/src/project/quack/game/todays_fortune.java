package project.quack.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class todays_fortune {

	public static void main(String[] args) {
		// 오늘의 운세 
		
	
	while(true) {	
		System.out.println();
		System.out.println("===오늘의 운세===");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		Date today = new Date();
		
		System.out.print("이름을 입력하세요(0=종료) : ");
		
		String name = scanner.nextLine();
		if(name.equals("0")) {
			break;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 ");
		System.out.print(df.format(today));
		
		System.out.println(name + "님의 운세"); 
		
	while(true) {
		System.out.print("결과 확인 (1), 종료(0) ==> ");
		int num = scanner.nextInt();
	
		if (num == 0) {	
			System.out.println("종료 하셨습니다.");
			System.out.println();
			break;
	
		} else if (num == 1){
			
		int rd1 = (int)(Math.random() * 6) + 1;
		int rd2 = (int)(Math.random() * 6) + 1;
		int rd3 = (int)(Math.random() * 6) + 1;
		
		System.out.println();
		System.out.println("** 총운 **");	
		switch (rd1) {
		case 1: System.out.println("전날의 피로를 잊게 할 만한 좋은 소식이 기다립니다. "
				+ "가정 내에 작은 경사가 생길 수도 있습니다. 남에게 자랑할만한 일이 생기는 날입니다. ");
			break;
		case 2: System.out.println("전반적으로 운의 영향을 많이 받게 될 날로 보이는가 하면 노력과 능력에 의한 "
				+ "결과도 충분히 맛볼 수 있는 날이 되지 않을까 싶습니다.");
			break;
		case 3: System.out.println("전반적으로 큰 무리나 사고는 없지만 작은 일로 인해 기분이 쳐질 수 있으니 "
				+ "가까이에 있는 사람이 지속적으로 스트레스를 주는 때라서 그런 것이 아닌가 싶습니다. ");
			break;
		case 4: System.out.println("상쾌하고 쾌적한 날이니 다른 사람과의 만남을 가져보는 것이 좋겠습니다. "
				+ "한 곳에 머물기만 하는 틀에 박힌 모습보다는 좀더 활동적인 태도를 유지하는 것이 좋습니다.");
			break;
		case 5: System.out.println("어려운 일이 있었다면 해결이 가능할 것이고 하던 일이 있었다면 마무리가 가능한 날입니다. "
				+ "마음을 답답하게 했던 일들을 벗어날 수 있는 좋은 기회를 맞이할 수 있습니다.");
			break;
		case 6: System.out.println("서두르는 마음만이 앞서서 아무것도 이루지 못할 수 있습니다. "
				+ "구체적인 계획이 필요한 날입니다. 마음만 앞서서 나가다보면 자칫 중요한 부분을 잊고 지나칠 수 있습니다.   ");
			break;
		}
		
		System.out.println();
		System.out.println("** 애정운 **");
		switch (rd2) {
		case 1:	System.out.println("작은 일로 인해 두 사람의 애정이 훨씬 크고 견고해질 수 있을 것으로 보이는 날입니다. "
				+ "항상 큰 이벤트로 인해 서로의 마음을 확인할 수 있는 것은 아닙니다.");
			break;
		case 2:	System.out.println("선물이나 이벤트를 준비해 보면 어떨까요. "
				+ "너무나 일상적인 생활로 변해버린 두 사람의 관계에 무언가 새로운 활력소가 필요한 때입니다.");
			break;
		case 3:	System.out.println("바라보고 쳐다보는 것 만으로도 충분히 즐겁고 행복한 날입니다. "
				+ "당신이 상대방을 사랑하는 만큼 상대방도 당신에 대한 마음이 애틋하기만 하니 이보다 더 좋은 애정 운은 없습니다.");
			break;
		case 4:	System.out.println("편안하게 둘의 관계를 이어갈 수 있는 날이지만 작은 갈등수가 있습니다. 그러나 큰 고비는 아니니 긴장할 필요는 없습니다. ");
				
			break;
		case 5:	System.out.println("사랑에 있어서 적극적인 모습을 보이는 것이 좋을지 어느 정도는 빼면서 흔히 말하는 튕기는 모습을 보이는 것이"
				+ " 좋을지에 대해서는 쉽게 판단하기 어려운 면이 있는 것이 사실입니다. ");
			break;
		case 6:	System.out.println("강한 애정의 기운이 당신의 주위를 감싸고 있으니 상대의 단점이나 질투도 모두 귀엽게 보이는 날이 될 것입니다. ");
			break;

		}	
		
		System.out.println();
		System.out.println("** 금전운 **");
		switch (rd3) {
		case 1: System.out.println("금전운이 당신의 곁을 지속적으로 머물고 있는 원만한 날이라 할 수 있겠습니다. 재물의 흐름이 원활하고 그 행운이 당신의 곁에 머무릅니다.");
			break;
		case 2: System.out.println("일단 오늘 정도의 금전운을 갖고 있다면 큰 손해는 피해갈 수 있을 것으로 예상되며 "
				+ "신중한 태도만 갖춘다면야 못할 것이 없는 하루가 되리라 기대해 볼 수 있겠습니다. ");
			break;
		case 3: System.out.println("투자에 있어서 방식을 약간 바꾸는 것도 고려해 봄직한 일입니다. "
				+ "항상 똑같은 방식으로 투자를 하다 보면 일의 효율성을 잃게 되는 경우도 생길 수 있습니다.  ");
			break;
		case 4: System.out.println("충동 구매의 우려가 있는 날입니다. 이성을 따르세요. 할인 판매 문구에 현혹되지 않도록 주의하세요. "
				+ "꼭 필요한 것만 사는 것이 이익이라는 것을 잊지 말아야 하는 하루입니다.  ");				
			break;
		case 5: System.out.println("바쁘고 복잡한 하루를 보내게 될 것으로 보이긴 하지만 그만한 소득은 따를 것이니 당신에게 이로운 날이 될 것입니다. ");
			break;
		case 6: System.out.println("가능성이 있어 보이는 곳이라면 과감히 투자를 하는 것이 좋겠습니다. 너무 많은 생각을 하게 되면 오히려 후회만 불러일으킬 수 있습니다. ");				
			break;
	
		}
		
			break;
		} else { 
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
		
			System.out.println();
			continue;
		}
	}
	
	}
	
	}
 
}

	


