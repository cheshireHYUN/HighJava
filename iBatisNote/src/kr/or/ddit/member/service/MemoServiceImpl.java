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
	
	private IMemoDAO memoDAO; 
	private SqlMapClient smc;
	
	private static IMemoService memoService;
	
	//또 싱글톤
	private MemoServiceImpl() {
		memoDAO = MemoDAOImpl.getInstance();
		smc = sqlMapClientFactory.getInstance();
		//이제 memoDAO와 smc를 쓸수있음
	}
	
	public static IMemoService getInstance() {
		if(memoService == null) {
			memoService = new MemoServiceImpl();
		}
		
		return memoService;
		// 즉 여기엔 insertMemo(MemoVO mv) 이런 오버라이드된 메소드들이 들어있다
	}
	
	///////////////////////////////////////////////////////

	@Override
	public int insertMemo(MemoVO mv) {
		int cnt = 0;
		try {
			smc.startTransaction();//트랜잭션 시작
			cnt = memoDAO.insertMemo(smc, mv); //삽입 수행
			//memoDAO.insertMemo에는 smc.update가 들어있고....
			smc.commitTransaction(); // 커밋...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //문제시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} 
		return cnt;
	}

	@Override
	public int deleteMemo(String no) {
		int cnt = 0;
		try {
			smc.startTransaction();
			cnt = memoDAO.deleteMemo(smc, no);
			smc.commitTransaction(); // 커밋...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //문제시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} 
		return cnt;
	}

	@Override
	public int updateMemo(MemoVO mv) {
		int cnt = 0;
		try {
			smc.startTransaction();
			cnt = memoDAO.updateMemo(smc,mv);
			smc.commitTransaction(); // 커밋...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //문제시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} 
		return cnt;
	}

	@Override
	public List<MemoVO> displayAllMemoList() {
		List<MemoVO> list = null;
		try {
			smc.startTransaction();
			list = memoDAO.displayAllMemoList(smc);
			smc.commitTransaction(); // 커밋...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //문제시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} 
		return list;
	}


	@Override
	public List<MemoVO> searchMemoList(MemoVO mv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkMemo(String no) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
