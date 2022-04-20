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
	
	private IMemoDAO memoDAO; 
	private SqlMapClient smc;
	
	private static IMemoService memoService;
	
	//�� �̱���
	private MemoServiceImpl() {
		memoDAO = MemoDAOImpl.getInstance();
		smc = sqlMapClientFactory.getInstance();
		//���� memoDAO�� smc�� ��������
	}
	
	public static IMemoService getInstance() {
		if(memoService == null) {
			memoService = new MemoServiceImpl();
		}
		
		return memoService;
		// �� ���⿣ insertMemo(MemoVO mv) �̷� �������̵�� �޼ҵ���� ����ִ�
	}
	
	///////////////////////////////////////////////////////

	@Override
	public int insertMemo(MemoVO mv) {
		int cnt = 0;
		try {
			smc.startTransaction();//Ʈ����� ����
			cnt = memoDAO.insertMemo(smc, mv); //���� ����
			//memoDAO.insertMemo���� smc.update�� ����ְ�....
			smc.commitTransaction(); // Ŀ��...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //������ �ѹ�
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
			smc.commitTransaction(); // Ŀ��...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //������ �ѹ�
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
			smc.commitTransaction(); // Ŀ��...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //������ �ѹ�
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
			smc.commitTransaction(); // Ŀ��...
		} catch (SQLException e) {
			try {
				smc.endTransaction(); //������ �ѹ�
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
