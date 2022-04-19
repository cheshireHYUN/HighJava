import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 
10������ ������ �����ϴ� �渶 ���α׷� �ۼ��ϱ�

���� Horse��� �̸��� Ŭ������ �����ϰ�,
�� Ŭ������ ���̸�(String), ���(int)�� ��������� ���´�.
�׸���, �� Ŭ�������� ����� ������������ ó���� �� �ִ�
����� �ִ�.( Comparable �������̽� ����)

��� ������ 1~50�������� �Ǿ� �ִ�.

��� �� �߰��߰��� �� ������ ��ġ�� >�� ��Ÿ���ÿ�.
��)
1���� --->------------------------------------
2���� ----->----------------------------------
...
1���� -------------------->-----------------------------
2���� ------------------------------------->------------
��Ⱑ ������ ����� �������� �����Ͽ� ����Ѵ�.
 
 */

public class horseExample {
	static int rank = 1; //�����ϸ鼭 ������� ��ũ �Ҵ�
	
	public static void main(String[] args) {
		List<Horse> list = new ArrayList<Horse>();
		list.add(new Horse("1����"));
		list.add(new Horse("2����"));
		list.add(new Horse("3����"));
		list.add(new Horse("4����"));
		list.add(new Horse("5����"));
		list.add(new Horse("6����"));
		list.add(new Horse("7����"));
		list.add(new Horse("8����"));
		list.add(new Horse("9����"));
		list.add(new Horse("10����"));
		
		//Horse[] hr = new Horse[]{
		//	new Horse("1����"),
		//	new Horse("2����")
		//}
		// ���� ��Ƽ�����带 �̷������� ȣ���߾����� list�� new Horse�� �־�������Ƿ� list�� start�ϸ� ��
		
		for(Horse hr : list) {
			hr.start();
		}
		
		// ���ְ� ������ ���� ���� �ڵ尡 ����Ǹ� �ȵǹǷ� join
		for(Horse hr : list) {
			try {
				hr.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("��� ���� Ʈ���� �����Ͽ����ϴ�.");
		Collections.sort(list);
		System.out.println("==========�����==========");
		for(Horse hr : list) {
			System.out.println(hr);
		}

	}


	
	
	

}



class Horse extends Thread implements Comparable<Horse>{
	//Comparable�� ���ø���Ʈ�ϸ� �ᱹ compareTo()�� �����ؾ��Ѵٴ� ��
	String horseName;
	int rank;
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<50; i++) {
			System.out.print("\n"+horseName+" : ");
			for(int j=0;j<i;j++) {
				System.out.print("-");
			}System.out.print(">");
			for(int k=49; k>i; k--) {
				System.out.print("-");
			}
			System.out.println();
			System.out.println();
			System.out.println();
			
			try {
				Thread.sleep((int)(Math.random()*300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(horseName+" ����");
		
		setRank(horseExample.rank);
		horseExample.rank++;
	}

	
	
	@Override
	public String toString() {
		return horseName + "�� �����" + rank ;
	}

	// ����� �������� ������������ ó��
	@Override
	public int compareTo(Horse horse) {
		Integer tmp = new Integer(this.getRank());
		return tmp.compareTo(horse.getRank());
	}
}