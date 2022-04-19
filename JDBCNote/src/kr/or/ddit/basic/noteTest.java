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
	








/////////////////////////////////////////////////////////////////////////////
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


/////////////////////////////////////////////////////////////////////////////
	private void insertMemo() {
	
		System.out.println();
		System.out.println("�� �Խù��� �ۼ��մϴ�");
		scan.nextLine(); // ���� ����
		
		System.out.println("TITLE : ");
		String title = scan.nextLine();
		
		System.out.println("�ۼ��� : ");
		String writer = scan.nextLine();
		
		System.out.println("�ۼ���¥ : ");
		String date = scan.nextLine();

		System.out.println("���� : ");
		String content = scan.nextLine();
		
		try {
			// Ŀ�ؼǰ�ü �ҷ��ͼ�
			conn = JDBCUtil.getConnection();
			
			// sql�� ��������
			String sql = "insert into jdbc_board "+
						" (board_no, board_title, board_writer, board_date, board_content) " +
						" values " +
						" (board_seq.nextVal,?,?,?,?) ";
			
			// pstmt�� �ش� sql�� ����־ �� ����ǥ�� �Է°��� ���Խ�����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			
			int cnt = pstmt.executeUpdate(); //������Ʈ�� ������ cnt�� ����
			if(cnt>0) {
				System.out.println(writer+"���� �Խù� �ۼ� �Ϸ�");
			}else {
				System.out.println(writer+"���� �Խù��� ������ ���� ������� ���߽��ϴ�.");
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
		System.out.println("������ �Խù��� ��ȣ�� �Է��ϼ���");
		System.out.print("�Խñ� ��ȣ : ");
		
		String no = scan.next();
		scan.nextLine(); //���� �������
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(no+"�� �Խù��� �����Ͽ����ϴ�.");
			}else {
				System.out.println(no+"�� �Խù� ������ �����Ͽ����ϴ�");
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
		System.out.println("�ۼ��� >> ");
		String writer = scan.nextLine();
		System.out.println("�ۼ���¥ >> ");
		String date = scan.nextLine();
		System.out.println("���� >> ");
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
				System.out.println(no+"�� �Խù��� �����߽��ϴ�");
			}else {
				System.out.println(no+"�� �Խù� ������ �����Ͽ����ϴ�");
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
			System.out.println("��� �Խù��� ��µǾ����ϴ�");
		}catch(SQLException e) {
			System.out.println("�Խù� ����� ���� �߻�");
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
			System.out.println("�˻�� �Է��ϼ���");
			scan.nextLine();
			
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
			
			System.out.println("-------------------------------------------------------------------");
			
			MemoVO mv = new MemoVO();
			mv.setNo(no);
			mv.setTitle(title);
			mv.setWriter(writer);
			mv.setDate(date);
			mv.setContent(content);
			
			
			String sql =" select * from jdbc_board where 1=1 ";
			// �Է°��� ���ٸ� �ǳʶٰ� �Է°��� �ִٸ� ������ �߰�����
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
			
			// ����ǥ�� �� �־��ֱ�. ���� �����ϱ� index�� ���� ������
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
				
				// VO��ü �ȿ� �����ü�� ���...
				// mv���� �˻������� �������. mv�� �ᵵ ������.. �������ַ��� mv2�� ����
				MemoVO mv2 = new MemoVO();
				mv2.setNo(rs.getString("board_no"));
				mv2.setTitle(rs.getString("board_title"));
				mv2.setWriter(rs.getString("board_writer"));				
				mv2.setDate(rs.getString("board_date"));				
				mv2.setContent(rs.getString("board_content"));	
				memoList.add(mv2); //�� ���ǿ����� �˻�������� List�� ����!
			}
			
			if(memoList.size()==0) {
				System.out.println("�˻��� ������ �����ϴ�");
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
			System.out.println("�Խù� ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}	
		
	}	
	
	
	
}
