package kr.or.ddit.member.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemoVO;


public class MemoDAOImpl implements IMemoDAO{
	
	private static IMemoDAO memoDAO;
	
	private MemoDAOImpl() {
	}
	
	public static IMemoDAO getInstance() {
		if(memoDAO == null) {
			memoDAO = new MemoDAOImpl();
		}
		return memoDAO;
	}
	
	
	
	
	@Override
	public int insertMemo(SqlMapClient smc, MemoVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = smc.update("memo.insertMemo",mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("insert 예외 발생",e);
		}
		return cnt;
	}

	@Override
	public int deleteMemo(SqlMapClient smc, String no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemo(SqlMapClient smc, MemoVO mv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemoVO> displayAllMemoList(SqlMapClient smc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkMemo(SqlMapClient smc, String no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemoVO> searchMemoList(SqlMapClient smc, MemoVO mv) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	
//	
//	@Override
//	public int deleteMemo(SqlMapClient smc, String no) {
//		int cnt=0;
//		try {
//			cnt = smc.delete("memo.deleteMemo",mv);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		return cnt;
//	}
//	
//	
//	
//	@Override
//	public int updateMemo(MemoVO mv) {
//		int cnt =0;
//		
//		try {
//			conn = JDBCUtil.getConnection();
//			
//
//	
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, mv.getTitle());
//			pstmt.setString(2, mv.getWriter());
//			pstmt.setString(3, mv.getDate());
//			pstmt.setString(4, mv.getContent());
//			pstmt.setString(5, mv.getNo());
//			
//			cnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		
//		return cnt;
//	}
//	
//	
//	
//	
//	@Override
//	public List<MemoVO> displayAllMemoList() {
//		
//		List<MemoVO> memoList = new ArrayList<MemoVO>();
//		try {
//			conn = JDBCUtil.getConnection();
//			String sql =" select * from jdbc_board ";
//			
//			stmt = conn.createStatement();
//			
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				MemoVO mv = new MemoVO();
//				mv.setNo(rs.getString("board_no"));
//				mv.setTitle(rs.getString("board_title"));
//				mv.setWriter(rs.getString("board_writer"));
//				mv.setDate(rs.getString("board_date"));
//				mv.setContent(rs.getString("board_content"));
//				memoList.add(mv);
//			}
//
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		return memoList;
//	}
//	
//	
//	
//	
////	@Override
////	public boolean checkMemo(String no) {
////		// TODO Auto-generated method stub
////		return false;
////	}
//	
//	
//	
//	
//	@Override
//	public List<MemoVO> searchMemoList(MemoVO mv) {
//		List<MemoVO> memoList2 = new ArrayList<MemoVO>();
//		
//		try {
//			conn = JDBCUtil.getConnection();
//			
//			String sql =" select * from jdbc_board where 1=1 ";
//			if(mv.getNo() != null && !mv.getNo().equals("")) {
//				sql += " and board_no = ? ";
//			}
//			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
//				sql += " and board_title like '%' ||?|| '%' ";
//			}
//			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
//				sql += " and board_writer = ? ";
//			}
//			if(mv.getDate() != null && !mv.getDate().equals("")) {
//				sql += " and board_date like '%' ||?|| '%' ";
//			}
//			if(mv.getContent() != null && !mv.getContent().equals("")) {
//				sql += " and board_content like '%' ||?|| '%' ";
//			}
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			int index =1;
//			
//			// 물음표에 값 넣어주기. 동적 쿼리니까 index로 순서 세어줌
//			if(mv.getNo() != null && !mv.getNo().equals("")) {
//				pstmt.setString(index++, mv.getNo());
//			}
//			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
//				pstmt.setString(index++, mv.getTitle());
//			}
//			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
//				pstmt.setString(index++, mv.getWriter());
//			}
//			if(mv.getDate() != null && !mv.getDate().equals("")) {
//				pstmt.setString(index++, mv.getDate());
//			}
//			if(mv.getContent() != null && !mv.getContent().equals("")) {
//				pstmt.setString(index++, mv.getContent());
//			}
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				MemoVO mv2 = new MemoVO();
//				mv2.setNo(rs.getString("board_no"));
//				mv2.setTitle(rs.getString("board_title"));
//				mv2.setWriter(rs.getString("board_writer"));				
//				mv2.setDate(rs.getString("board_date"));				
//				mv2.setContent(rs.getString("board_content"));	
//				memoList2.add(mv2); //각 조건에대한 검색결과들이 List2에 들어갔다!
//			}
//
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}	
//		return memoList2;
//	}
//
//
//
//
//
//	@Override
//	public boolean checkMemo(String no) {
//		boolean chk = false;
//		try {
//			
//			conn = JDBCUtil.getConnection();
//		
//			String sql = "select count(*) as cnt from jdbc_board "
//					+ " where board_no = ? ";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, no);
//			
//			rs = pstmt.executeQuery();
//			
//			int cnt=0;
//			while(rs.next()) {
//				cnt = rs.getInt("CNT");
//			}
//			
//			if(cnt>0) {
//				chk=true;
//			}
//			
//			
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		return chk;
//	}


}
