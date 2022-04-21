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
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 출력");
		System.out.println("  5. 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 새글작성
					insertMemo();
					break;
				case 2 :  // 글삭
					deleteMemo();
					break;
				case 3 :  // 글수정
					updateMemo();
					break;
				case 4 :  // 전체글 출력
					displayAllMemo();			
					break;
				case 5 :  // 글검색
					searchMemo();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}





	private void insertMemo() {
		System.out.println();
		System.out.println("새 게시물을 작성합니다");
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println("TITLE : ");
		String title = scan.nextLine();
		
		System.out.println("작성자 : ");
		String writer = scan.nextLine();
		

		System.out.println("내용 : ");
		String content = scan.nextLine();
		
		MemoVO mv = new MemoVO();
		mv.setTitle(title);
		mv.setWriter(writer);
		mv.setContent(content);
		
		int cnt = memoService.insertMemo(mv); //memoService의 insertMemo메소드가 memoDAO에게 데베와 통신하라고 명령!
		if(cnt>0) {
			System.out.println(writer+"님의 게시물 작성 완료");
		}else {
			System.out.println(writer+"님의 게시물은 오류로 인해 저장되지 못했습니다.");
		}
		
	}
	
	
	
	private void deleteMemo() {
		System.out.println();
		System.out.println("삭제할 게시물의 번호를 입력하세요");
		System.out.print("게시글 번호 : ");
		
		String no = scan.next();
		scan.nextLine(); //버퍼 비워놓기
		
		int chk = memoService.checkMemo(no);
		if(chk==0) {
			System.out.println(no+"번 게시물은 존재하지 않습니다");
		}else {
			int cnt = memoService.deleteMemo(no);
			
			if(cnt>0) {
				System.out.println(no+"번 게시물을 삭제하였습니다.");
			}else {
				System.out.println(no+"번 게시물 삭제에 실패하였습니다");
			}
		}
		
		
		
	}
	
	
	
	
	
	private void updateMemo() {
		int chk = 0;
		String no = "";
		do {
			System.out.println();
			System.out.println("수정하고싶은 게시물의 번호를 입력하세요");
			System.out.print("번호 : ");
			
			no = scan.next();
			
			chk = memoService.checkMemo(no);
			
			if(chk==0) {
				System.out.println(no+"번 게시물은 존재하지 않습니다.");
				System.out.println("다시 입력하세요");
			}
			
		} while(chk==0); //중복 없을때
		
		scan.nextLine();
		System.out.println("제목 >> ");
		String title = scan.nextLine();
		System.out.println("내용 >> ");
		String content = scan.nextLine();
		
		MemoVO mv = new MemoVO();
		mv.setTitle(title);
		mv.setContent(content);
		mv.setNo(no);
		
		int cnt = memoService.updateMemo(mv);
		
		if(cnt>0) {
			System.out.println(no+"번 게시물을 수정했습니다");
		}else {
			System.out.println(no+"번 게시물 수정에 실패하였습니다");
		}
	}
	
	
	
	
	
	
	private void displayAllMemo() {
		List<MemoVO> memoList = memoService.displayAllMemoList();
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
		System.out.println("-------------------------------------------------------------------");
		if(memoList.size()==0) {
			System.out.println("등록된 게시글이 존재하지 않습니다");
		} else {
			for(MemoVO mv : memoList) {
				System.out.println("  "+mv.getNo()+"\t"+mv.getTitle()+"\t"+mv.getWriter()+"\t"+mv.getDate()+"\t"+mv.getContent());				
			}
		}
		
	}
	
	
	
	
	private void searchMemo() {
		System.out.println();
		scan.nextLine();
		System.out.println("검색어를 입력하세요");
		
		System.out.print("글번호>> ");
		String no=scan.nextLine().trim(); //trim은 혹시모를 공백제거
		System.out.println();
		
		System.out.print("제목>> ");
		String title = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("작성자>> ");
		String writer = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("날짜>> ");
		String date = scan.nextLine().trim();
		System.out.println();
		
		System.out.print("내용>> ");
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
			System.out.println("검색된 정보가 없습니다");
		} else {
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
			System.out.println("-------------------------------------------------------------------");
			for(MemoVO mv : memoList2) {
				System.out.print("  "+mv.getNo()+"\t"+mv.getTitle()+"\t"
									+mv.getWriter()+"\t"+mv.getDate()+"\t"+mv.getContent()+"\n");				
			}
		}
		System.out.println("-------------------------------------------------------------------");
	}
	
	
	

}
