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
		String user = JOptionPane.showInputDialog("������������ �ϳ��� �Է��ϼ���");
		
		RSP_Game.inputChk = true;
			
		Random rand = new Random();
		int ai = rand.nextInt(3)+1;
		String aiString = "";
		
		if (ai == 1) {
			aiString = "����";
		} else if (ai == 1) {
			aiString = "����";
		} else {
			aiString = "��";
		}
		
		System.out.println("== ��� ==");
		System.out.println("��ǻ��: "+aiString);
		System.out.println("��� : "+user);
		
		if(user.equals(aiString)) {
			System.out.println("��� : �����ϴ�");
		}else if ((user.equals("����")&&aiString.equals("����"))||(user.equals("����")&&aiString.equals("��"))||(user.equals("��")&&aiString.equals("����"))){
			System.out.println("��� : ��ǻ�Ͱ��̰���ϴ�");
		}else {
			System.out.println("��� : ����� �̰���ϴ�");
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