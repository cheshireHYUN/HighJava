import java.util.Random;

import javax.swing.JOptionPane;

public class RSP_Game {
public static boolean inputChk;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
			th1.start();
			th2.start();
		
	}

}



class DataInput extends Thread {
	
	public void run() {
		String user = JOptionPane.showInputDialog("가위바위보중 하나를 입력하세요");
		
		RSP_Game.inputChk = true;
			
		Random rand = new Random();
		int ai = rand.nextInt(3)+1;
		String aiString = "";
		
		if (ai == 1) {
			aiString = "가위";
		} else if (ai == 1) {
			aiString = "바위";
		} else {
			aiString = "보";
		}
		
		System.out.println("== 결과 ==");
		System.out.println("컴퓨터: "+aiString);
		System.out.println("당신 : "+user);
		
		if(user.equals(aiString)) {
			System.out.println("결과 : 비겼습니다");
		}else if ((user.equals("가위")&&aiString.equals("바위"))||(user.equals("바위")&&aiString.equals("보"))||(user.equals("보")&&aiString.equals("가위"))){
			System.out.println("결과 : 컴퓨터가이겼습니다");
		}else {
			System.out.println("결과 : 당신이 이겼습니다");
		}
		
		
		
	}
}



class CountDown extends Thread {
	
	public void run() {
		for(int i=5; i>=1; i--) {
			System.out.println(i);
			if(RSP_Game.inputChk) {
				return;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);

	}
	
}