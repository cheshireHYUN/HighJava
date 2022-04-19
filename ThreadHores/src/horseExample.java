import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...
1번말 -------------------->-----------------------------
2번말 ------------------------------------->------------
경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 
 */

public class horseExample {
	static int rank = 1; //공유하면서 순서대로 랭크 할당
	
	public static void main(String[] args) {
		List<Horse> list = new ArrayList<Horse>();
		list.add(new Horse("1번마"));
		list.add(new Horse("2번마"));
		list.add(new Horse("3번마"));
		list.add(new Horse("4번마"));
		list.add(new Horse("5번마"));
		list.add(new Horse("6번마"));
		list.add(new Horse("7번마"));
		list.add(new Horse("8번마"));
		list.add(new Horse("9번마"));
		list.add(new Horse("10번마"));
		
		//Horse[] hr = new Horse[]{
		//	new Horse("1번마"),
		//	new Horse("2번마")
		//}
		// 원래 멀티스레드를 이런식으로 호출했었으나 list에 new Horse를 넣어버렸으므로 list를 start하면 됨
		
		for(Horse hr : list) {
			hr.start();
		}
		
		// 완주가 끝나기 전에 다음 코드가 실행되면 안되므로 join
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
		System.out.println("모든 말이 트랙을 완주하였습니다.");
		Collections.sort(list);
		System.out.println("==========경기결과==========");
		for(Horse hr : list) {
			System.out.println(hr);
		}

	}


	
	
	

}



class Horse extends Thread implements Comparable<Horse>{
	//Comparable을 임플리먼트하면 결국 compareTo()를 구현해야한다는 뜻
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
		System.out.println(horseName+" 완주");
		
		setRank(horseExample.rank);
		horseExample.rank++;
	}

	
	
	@Override
	public String toString() {
		return horseName + "의 등수는" + rank ;
	}

	// 등수를 기준으로 오름차순으로 처리
	@Override
	public int compareTo(Horse horse) {
		Integer tmp = new Integer(this.getRank());
		return tmp.compareTo(horse.getRank());
	}
}