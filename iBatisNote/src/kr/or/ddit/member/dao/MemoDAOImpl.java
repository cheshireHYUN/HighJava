package kr.or.ddit.member.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemoVO;


public class MemoDAOImpl implements IMemoDAO{
	
	// 싱글톤패턴 - IMemoDAO의 객체 memoDAO가 private static으로 선언
	private static IMemoDAO memoDAO;
	// 생성자가 private, 즉 new로 본 클래스 호출 불가능
	private MemoDAOImpl() {
	}
	public static IMemoDAO getInstance() {
		if(memoDAO == null) {
			// 최초의 new
			memoDAO = new MemoDAOImpl();
		}
		return memoDAO;
		// 즉 memoDAO는 IMemoDAO의 참조변수이며 getInstance를 통해 불러올수 있고, 그 값은 MemoDAOImpl에
		// 오버라이드 된 CRUD연산들이다. (이 연산들은 smc.insertMemo와 같은 메소드로 호출되며, 
		// sql구문은  sqlmap인 memo.xml에 들어있음
	}
	
	
	
	
	@Override
	public int insertMemo(SqlMapClient smc, MemoVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("memo.insertMemo",mv);
			//null이 나오면 작업성공한것
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("insert 예외 발생",e);
		}
		return cnt;
	}

	
	
	
	@Override
	public int deleteMemo(SqlMapClient smc, String no) {
		int cnt=0;
		try {
			cnt = smc.delete("memo.deleteMemo",no);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("delete 예외 발생",e);
		}
		return cnt;
	}
	
	

	@Override
	public int updateMemo(SqlMapClient smc, MemoVO mv) {
		int cnt =0;
		try {
			cnt = smc.update("memo.updateMemo",mv);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("update 예외 발생",e);
		}
		return cnt;
	}

	
	
	
	@Override
	public List<MemoVO> displayAllMemoList(SqlMapClient smc) {
		List<MemoVO> memoList = new ArrayList<MemoVO>();
		try {
			memoList = smc.queryForList("memo.displayAllMemoList");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("displayAllMemoList 예외 발생",e);
		}
		return memoList;
	}

	
	
	
	@Override
	public int checkMemo(SqlMapClient smc, String no) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("memo.checkMemo",no);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("checkMemo 예외 발생",e);
		}
		return cnt;
	}

	
	@Override
	public List<MemoVO> searchMemoList(SqlMapClient smc, MemoVO mv) {
		List<MemoVO> memoList2 = new ArrayList<MemoVO>();
		
		try {
			memoList2 = smc.queryForList("memo.searchMemo",mv);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("searchMemoList 예외 발생",e);
		}	
		return memoList2;
	}
	
	





}
