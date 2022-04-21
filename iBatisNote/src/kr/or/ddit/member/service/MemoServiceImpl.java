package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.IMemoDAO;
import kr.or.ddit.member.dao.MemoDAOImpl;
// 메모의 모든 필드값들과 게터세터 즉 메모객체
import kr.or.ddit.member.vo.MemoVO;
import kr.or.ddit.util.sqlMapClientFactory;

// IMemoService 인터페이스는 IMemoDAO 인터페이스와 내용이 같다
public class MemoServiceImpl implements IMemoService{
	
	private IMemoDAO memoDAO;
	private SqlMapClient smc;
	private static IMemoService memoService;
	
	private MemoServiceImpl() {
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
		return memoDAO.insertMemo(smc, mv);
	}

	@Override
	public int deleteMemo(String no) {
		return memoDAO.deleteMemo(smc, no);
	}

	@Override
	public int updateMemo(MemoVO mv) {
		return memoDAO.updateMemo(smc, mv);
	}

	@Override
	public List<MemoVO> displayAllMemoList() {
		return memoDAO.displayAllMemoList(smc);
	}


	@Override
	public List<MemoVO> searchMemoList(MemoVO mv) {
		return memoDAO.searchMemoList(smc, mv);
	}

	@Override
	public int checkMemo(String no) {
		return memoDAO.checkMemo(smc, no);
	}

	
	

}
