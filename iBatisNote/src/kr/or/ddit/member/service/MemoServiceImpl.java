package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

// �� �޼ҵ� ����ִ� �������̽�
import kr.or.ddit.member.dao.IMemoDAO;
// �޼ҵ带 ������ ������ ����ϴ� Ŭ����
import kr.or.ddit.member.dao.MemoDAOImpl;
// �޸��� ��� �ʵ尪��� ���ͼ��� �� �޸�ü
import kr.or.ddit.member.vo.MemoVO;
import kr.or.ddit.util.sqlMapClientFactory;

// IMemoService �������̽��� IMemoDAO �������̽��� ������ ����
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
			smc.startTransaction();//Ʈ����� ����
			int cnt = memoDAO.updateMemo(smc, mv); //���� ����
			smc.commitTransaction(); // Ŀ��...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //������ �ѹ�
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
