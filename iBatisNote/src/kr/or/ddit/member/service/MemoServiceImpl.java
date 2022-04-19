package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

// 각 메소드 들어있는 인터페이스
import kr.or.ddit.member.dao.IMemoDAO;
// 메소드를 구현해 데베와 통신하는 클래스
import kr.or.ddit.member.dao.MemoDAOImpl;
// 메모의 모든 필드값들과 게터세터 즉 메모객체
import kr.or.ddit.member.vo.MemoVO;
import kr.or.ddit.util.sqlMapClientFactory;

// IMemoService 인터페이스는 IMemoDAO 인터페이스와 내용이 같다
public class MemoServiceImpl implements IMemoService{
	
	private static IMemoService memoService;
	private IMemoDAO memoDAO; 
	private SqlMapClient smc;
	
	public MemoServiceImpl() {
		memoDAO = MemoDAOImpl.getInstance();
		smc = sqlMapClientFactory.getInstance();
	}
	
	public static IMemoService getInstance() {
		if(memoService == null) {
			memoService = new MemoServiceImpl();
		}
		
		return memoService;
	}
	
	///////////////////////////////////////////////////////

	@Override
	public int insertMemo(MemoVO mv) {
		try {
			smc.startTransaction();//트랜잭션 시작
			int cnt = memoDAO.updateMemo(smc, mv); //삽입 수행
			smc.commitTransaction(); // 커밋...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //문제시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} 
		return memoDAO.insertMemo(smc,mv);
	}

	@Override
	public int deleteMemo(String no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemo(MemoVO mv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemoVO> displayAllMemoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkMemo(String no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemoVO> searchMemoList(MemoVO mv) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	@Override
//	public int deleteMemo(String no) {
//		return memoDAO.deleteMemo(no);
//	}
//
//	@Override
//	public int updateMemo(MemoVO mv) {
//		return memoDAO.updateMemo(mv);
//	}
//
//	@Override
//	public List<MemoVO> displayAllMemoList() {
//		return memoDAO.displayAllMemoList();
//	}
//
//	@Override
//	public List<MemoVO> searchMemoList(MemoVO mv) {
//		return memoDAO.searchMemoList(mv);
//	}
//
//	@Override
//	public boolean checkMemo(String no) {
//		return memoDAO.checkMemo(no);
//	}
	
	

}
