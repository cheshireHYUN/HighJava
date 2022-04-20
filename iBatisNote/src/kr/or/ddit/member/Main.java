package kr.or.ddit.member;


import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemoService;
import kr.or.ddit.member.service.MemoServiceImpl;
import kr.or.ddit.member.vo.MemoVO;

public class Main {

	private IMemoService memoService;
	private Scanner scan = new Scanner(System.in); 

	public Main() {		
		memoService = MemoServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		Main obj = new Main();
		obj.start();
	}
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === �� �� �� �� ===");
		System.out.println("  1. ���� �ۼ�");
		System.out.println("  2. �� ����");
		System.out.println("  3. �� ����");
		System.out.println("  4. ��ü ���");
		System.out.println("  5. �� �˻�");
		System.out.println("  6. �۾� ��.");
		System.out.println("----------------------");
		System.out.print("���ϴ� �۾� ���� >> ");
	}
	
	public void start(){
		int choice;
		do{
			displayMenu(); //�޴� ���
			choice = scan.nextInt(); // �޴���ȣ �Է¹ޱ�
			switch(choice){
				case 1 :  // �����ۼ�
					insertMemo();
					break;
				case 2 :  // �ۻ�
					deleteMemo();
					break;
				case 3 :  // �ۼ���
					updateMemo();
					break;
				case 4 :  // ��ü�� ���
					displayAllMemo();			
					break;
				case 5 :  // �۰˻�
					searchMemo();
					break;
				case 6 :  // �۾� ��
					System.out.println("�۾��� ��Ĩ�ϴ�.");
					break;
				default :
					System.out.println("��ȣ�� �߸� �Է��߽��ϴ�. �ٽ��Է��ϼ���");
			}
		}while(choice!=6);
	}





	private void insertMemo() {
		System.out.println();
		System.out.println("�� �Խù��� �ۼ��մϴ�");
		scan.nextLine(); // ���� ����
		
		System.out.println("TITLE : ");
		String title = scan.nextLine();
		
		System.out.println("�ۼ��� : ");
		String writer = scan.nextLine();
		

		System.out.println("���� : ");
		String content = scan.nextLine();
		
		MemoVO mv = new MemoVO();
		mv.setTitle(title);
		mv.setWriter(writer);
		mv.setContent(content);
		
		int cnt = memoService.insertMemo(mv); //memoService�� insertMemo�޼ҵ尡 memoDAO���� ������ ����϶�� ���!
		if(cnt>0) {
			System.out.println(writer+"���� �Խù� �ۼ� �Ϸ�");
		}else {
			System.out.println(writer+"���� �Խù��� ������ ���� ������� ���߽��ϴ�.");
		}
		
	}
	
	
	
	private void deleteMemo() {
		System.out.println();
		System.out.println("������ �Խù��� ��ȣ�� �Է��ϼ���");
		System.out.print("�Խñ� ��ȣ : ");
		
		String no = scan.next();
		scan.nextLine(); //���� �������
		
		int cnt = memoService.deleteMemo(no);
		
		if(cnt>0) {
			System.out.println(no+"�� �Խù��� �����Ͽ����ϴ�.");
		}else {
			System.out.println(no+"�� �Խù� ������ �����Ͽ����ϴ�");
		}
		
	}
	
	
	
	
	
	private void updateMemo() {
		boolean chk = false;
		String no = "";
		do {
			System.out.println();
			System.out.println("�����ϰ���� �Խù��� ��ȣ�� �Է��ϼ���");
			System.out.print("��ȣ : ");
			
			no = scan.next();
			
			chk = checkMemo(no);
			
			if(chk==false) {
				System.out.println(no+"�� �Խù��� �������� �ʽ��ϴ�.");
				System.out.println("�ٽ� �Է��ϼ���");
			}
			
		} while(chk==false); //�ߺ� ������
		
		scan.nextLine();
		System.out.println("���� >> ");
		String title = scan.nextLine();
		System.out.println("���� >> ");
		String content = scan.nextLine();
		
		MemoVO mv = new MemoVO();
		mv.setTitle(title);
		mv.setContent(content);
		mv.setNo(no);
		
		int cnt = memoService.updateMemo(mv);
		
		if(cnt>0) {
			System.out.println(no+"�� �Խù��� �����߽��ϴ�");
		}else {
			System.out.println(no+"�� �Խù� ������ �����Ͽ����ϴ�");
		}
	}
	
	
	
	
	
	
	private void displayAllMemo() {
		List<MemoVO> memoList = memoService.displayAllMemoList();
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
		System.out.println("-------------------------------------------------------------------");
		if(memoList.size()==0) {
			System.out.println("��ϵ� �Խñ��� �������� �ʽ��ϴ�");
		} else {
			for(MemoVO mv : memoList) {
				System.out.println("  "+mv.getNo()+"\t"+mv.getTitle()+"\t"+mv.getWriter()+"\t"+mv.getDate()+"\t"+mv.getContent());				
			}
		}
		
	}
	
	
	
	
	private void searchMemo() {
		System.out.println();
		scan.nextLine();
		System.out.println("�˻�� �Է��ϼ���");
		
		System.out.print("�۹�ȣ>> ");
		String no=scan.nextLine().trim(); //trim�� Ȥ�ø� ��������
		System.out.println();
		
		System.out.print("����>> ");
		String title = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("�ۼ���>> ");
		String writer = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("��¥>> ");
		String date = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("����>> ");
		String content = scan.nextLine().trim();
		System.out.println();
		
		
		MemoVO mv2 = new MemoVO();
		mv2.setNo(no);
		mv2.setTitle(title);
		mv2.setWriter(writer);
		mv2.setDate(date);
		mv2.setContent(content);
		
		List<MemoVO> memoList2 = memoService.searchMemoList(mv2);
		
		if(memoList2.size()==0) {
			System.out.println("�˻��� ������ �����ϴ�");
		} else {
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
			System.out.println("-------------------------------------------------------------------");
			for(MemoVO mv : memoList2) {
				System.out.println("  "+mv.getNo()+"\t"+mv.getTitle()+"\t"
									+mv.getWriter()+"\t"+mv.getDate()+"\t"+mv.getContent()+"\n");				
			}
		}
		System.out.println("-------------------------------------------------------------------");
	}
	
	
	
	
	private boolean checkMemo(String no) {
		return memoService.checkMemo(no);
	}

}
