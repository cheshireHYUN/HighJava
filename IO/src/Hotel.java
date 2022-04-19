
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
   Map<String,String> map = new HashMap<String,String>();
   File f =new File("d:/D_Other/호텔예약현황.txt");
   static BufferedWriter w = null;
   static BufferedReader r = null;
   
   public Hotel() {
      if(!f.exists()) {
    	  try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }
   
   public static void main(String[] args) {
      new Hotel().service();
   }

   public int service() {
      int view = View.MENU;
      
      System.out.println("=====================================================");
      System.out.println("호텔 문이 열렸습니다");
      System.out.println("=====================================================");
      
      while(true) {
         switch(view) {
         case View.MENU : view=menu(); break; //처음메뉴
         case View.CHECKIN : view=checkIn(); break;//체크인
         case View.CHECKOUT : view=checkOut(); break;//체크아웃
         case View.ROOMSTATE : view=roomState(); break;//객실상태
         }
      }
   }



   private int menu() {
      System.out.println();
      System.out.println("=====================================================");
      System.out.println("어떤 업무를 하시겠습니까?");
      System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
      System.out.println("=====================================================");
      System.out.print("메뉴 선택 : ");
      Scanner sc = new Scanner(System.in);
      int menuSelect = sc.nextInt();
      switch(menuSelect) {
      case 1 : return View.CHECKIN;
      case 2 : return View.CHECKOUT;
      case 3 : return View.ROOMSTATE;
      case 4 :
         System.out.println("=====================================================");
         System.out.println("호텔 문을 닫았습니다");
         System.out.println("=====================================================");
         System.exit(0);
         break;
      default: System.out.println("다시 입력해주세요.");
      }
      return View.MENU;
      
   }

   
   
   public void now() {
	   // .txt에 들어있는 방번호와 손님명단을 map에 넣는 메소드
	   String s;
	   try {
	         r = new BufferedReader(new FileReader("D:\\D_Other\\호텔예약현황.txt"));
	         while((s=r.readLine()) != null) {
	        	 String[] saveFile = s.split(" ");
	           map.put(saveFile[0],saveFile[1]);
	         }
	   }catch(IOException e) {
		   e.printStackTrace();
       }
	   
   }
   
   
   
   public int checkIn() {
	   
	   now();

	      System.out.println();
	      System.out.println("어느 방에 체크인 하시겠습니까?");
	      Scanner sc = new Scanner(System.in);
	      String roomSelect = sc.next();
	      try{
	         w=new BufferedWriter( new FileWriter("D:\\D_Other\\호텔예약현황.txt",true));

		         if(map.containsKey(roomSelect)) {
		            System.out.println(roomSelect+"번 방은 이미 사람이 있습니다.");
		         } else {
		            System.out.println("\n누구를 체크인 하시겠습니까?");
		            String whoCheckIn = sc.next()+"\n";  
		            roomSelect = roomSelect+" ";
		            w.write(roomSelect);
		            w.write(whoCheckIn);
		            w.close();
		            System.out.println("체크인 되었습니다.\n");
		         	}
	      }
	       catch(IOException e){
	         System.err.println("파일입력오류");
	       }
	      
	      return View.MENU;

   }
   
   
   
   private int checkOut() {
      System.out.println();
      System.out.println("어느 방을 체크아웃 하시겠습니까?");
      Scanner sc = new Scanner(System.in);
      String roomSelect = sc.next();
      if(map.get(roomSelect)==null) {
         System.out.println(roomSelect+"번 방은 체크인 한 사람이 없습니다.");
      } else {
         map.remove(roomSelect);
         System.out.println("체크아웃 되었습니다.\n");
      }
      return View.MENU;
   }

   
   
   
   private int roomState() {
      
      try {
         
         now();
         Set<String> keySet = map.keySet();
         // map은 Iterator 인터페이스 사용 불가능하므로 keySet()메소드로 Set객체를 만든 후 Iterator 사용
         Iterator<String> itr = keySet.iterator();
         while(itr.hasNext()) {
            String roomNumber = itr.next();
            System.out.println("방번호: "+roomNumber+"\t 투숙객: "+map.get(roomNumber));
         }r.close();
      } catch (IOException e) {
         System.err.println("파일오류");
      }
      
      
      return View.MENU;
   }
   
   
}