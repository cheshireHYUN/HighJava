package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

public class noteTest {
	public static void main(String[] args) {
		noteTest note = new noteTest();
		note.start();
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	
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
	








/////////////////////////////////////////////////////////////////////////////
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


/////////////////////////////////////////////////////////////////////////////
	private void insertMemo() {
	
		System.out.println();
		System.out.println("새 게시물을 작성합니다");
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println("TITLE : ");
		String title = scan.nextLine();
		
		System.out.println("작성자 : ");
		String writer = scan.nextLine();
		
		System.out.println("작성날짜 : ");
		String date = scan.nextLine();

		System.out.println("내용 : ");
		String content = scan.nextLine();
		
		try {
			// 커넥션객체 불러와서
			conn = JDBCUtil.getConnection();
			
			// sql문 만들어놓고
			String sql = "insert into jdbc_board "+
						" (board_no, board_title, board_writer, board_date, board_content) " +
						" values " +
						" (board_seq.nextVal,?,?,?,?) ";
			
			// pstmt에 해당 sql문 집어넣어서 각 물음표에 입력값을 대입시켜줌
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			
			int cnt = pstmt.executeUpdate(); //업데이트된 갯수를 cnt에 대입
			if(cnt>0) {
				System.out.println(writer+"님의 게시물 작성 완료");
			}else {
				System.out.println(writer+"님의 게시물은 오류로 인해 저장되지 못했습니다.");
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
/////////////////////////////////////////////////////////////////////////////
	
	private void deleteMemo() {
		System.out.println();
		System.out.println("삭제할 게시물의 번호를 입력하세요");
		System.out.print("게시글 번호 : ");
		
		String no = scan.next();
		scan.nextLine(); //버퍼 비워놓기
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(no+"번 게시물을 삭제하였습니다.");
			}else {
				System.out.println(no+"번 게시물 삭제에 실패하였습니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}	
	
/////////////////////////////////////////////////////////////////////////////
	
	private void updateMemo() {
		boolean chk = false;
		String no = "";
		do {
			System.out.println();
			System.out.println("수정하고싶은 게시물의 번호를 입력하세요");
			System.out.print("번호 : ");
			
			no = scan.next();
			
			chk = checkMemo(no);
			
			if(chk==false) {
				System.out.println(no+"번 게시물은 존재하지 않습니다.");
				System.out.println("다시 입력하세요");
			}
			
		} while(chk==false); //중복 없을때
		
		scan.nextLine();
		System.out.println("제목 >> ");
		String title = scan.nextLine();
		System.out.println("작성자 >> ");
		String writer = scan.nextLine();
		System.out.println("작성날짜 >> ");
		String date = scan.nextLine();
		System.out.println("내용 >> ");
		String content = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " update jdbc_board " + 
					" set board_title = ?, " + 
					" board_writer = ?, " + 
					" board_date = ?, " + 
					" board_content = ? " + 
					" where board_no = ? " ;
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			pstmt.setString(5, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(no+"번 게시물을 수정했습니다");
			}else {
				System.out.println(no+"번 게시물 수정에 실패하였습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
	
	private boolean checkMemo(String no) {
		boolean chk = false;
		try {
		
			conn = JDBCUtil.getConnection();
		
			String sql = "select count(*) as cnt from jdbc_board "
					+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			int cnt=0;
			while(rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if(cnt>0) {
				chk=true;
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	return chk;
	}





////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void displayAllMemo() {
		
		
		try {
			conn = JDBCUtil.getConnection();
			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
			System.out.println("-------------------------------------------------------------------");
			
			String sql =" select * from jdbc_board ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				System.out.println("  "+no+"\t"+title+"\t"+writer+"\t"+date+"\t"+content);
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println("모든 게시물이 출력되었습니다");
		}catch(SQLException e) {
			System.out.println("게시물 출력중 오류 발생");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	private void searchMemo() {
		
		List<MemoVO> memoList = new ArrayList<MemoVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			System.out.println();
			System.out.println("검색어를 입력하세요");
			scan.nextLine();
			
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
			
			System.out.println("-------------------------------------------------------------------");
			
			MemoVO mv = new MemoVO();
			mv.setNo(no);
			mv.setTitle(title);
			mv.setWriter(writer);
			mv.setDate(date);
			mv.setContent(content);
			
			
			String sql =" select * from jdbc_board where 1=1 ";
			// 입력값이 없다면 건너뛰고 입력값이 있다면 쿼리를 추가해줌
			if(mv.getNo() != null && !mv.getNo().equals("")) {
				sql += " and board_no = ? ";
			}
			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
				sql += " and board_title like '%' ||?|| '%' ";
			}
			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
			if(mv.getDate() != null && !mv.getDate().equals("")) {
				sql += " and board_date like '%' ||?|| '%' ";
			}
			if(mv.getContent() != null && !mv.getContent().equals("")) {
				sql += " and board_content like '%' ||?|| '%' ";
			}
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			int index =1;
			
			// 물음표에 값 넣어주기. 동적 쿼리니까 index로 순서 세어줌
			if(mv.getNo() != null && !mv.getNo().equals("")) {
				pstmt.setString(index++, mv.getNo());
			}
			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
				pstmt.setString(index++, mv.getTitle());
			}
			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
				pstmt.setString(index++, mv.getWriter());
			}
			if(mv.getDate() != null && !mv.getDate().equals("")) {
				pstmt.setString(index++, mv.getDate());
			}
			if(mv.getContent() != null && !mv.getContent().equals("")) {
				pstmt.setString(index++, mv.getContent());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// VO객체 안에 멤버객체를 담아...
				// mv에는 검색조건이 담겨있음. mv를 써도 되지만.. 구분해주려고 mv2로 쓸게
				MemoVO mv2 = new MemoVO();
				mv2.setNo(rs.getString("board_no"));
				mv2.setTitle(rs.getString("board_title"));
				mv2.setWriter(rs.getString("board_writer"));				
				mv2.setDate(rs.getString("board_date"));				
				mv2.setContent(rs.getString("board_content"));	
				memoList.add(mv2); //각 조건에대한 검색결과들이 List에 들어갔다!
			}
			
			if(memoList.size()==0) {
				System.out.println("검색된 정보가 없습니다");
			} else {
				
				System.out.println("-------------------------------------------------------------------");
				System.out.println(" no\tTITLE\t\tWriter\t\tDate\t\tContent");
				System.out.println("-------------------------------------------------------------------");
				for(MemoVO mv2 : memoList) {
					System.out.println("  "+mv2.getNo()+"\t"+mv2.getTitle()+"\t"
										+mv2.getWriter()+"\t"+mv2.getDate()+"\t"+mv2.getContent()+"\n");				
				}
			}
			System.out.println("-------------------------------------------------------------------");
			
		}catch(SQLException e) {
			System.out.println("게시물 출력중 오류 발생");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}	
		
	}	
	
	
	
}
