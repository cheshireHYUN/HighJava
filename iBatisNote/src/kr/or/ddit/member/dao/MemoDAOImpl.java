package kr.or.ddit.member.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemoVO;


public class MemoDAOImpl implements IMemoDAO{
	
	// �̱������� - IMemoDAO�� ��ü memoDAO�� private static���� ����
	private static IMemoDAO memoDAO;
	// �����ڰ� private, �� new�� �� Ŭ���� ȣ�� �Ұ���
	private MemoDAOImpl() {
	}
	public static IMemoDAO getInstance() {
		if(memoDAO == null) {
			// ������ new
			memoDAO = new MemoDAOImpl();
		}
		return memoDAO;
		// �� memoDAO�� IMemoDAO�� ���������̸� getInstance�� ���� �ҷ��ü� �ְ�, �� ���� MemoDAOImpl��
		// �������̵� �� CRUD������̴�. (�� ������� smc.insertMemo�� ���� �޼ҵ�� ȣ��Ǹ�, 
		// sql������  sqlmap�� memo.xml�� �������
	}
	
	
	
	
	@Override
	public int insertMemo(SqlMapClient smc, MemoVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("memo.insertMemo",mv);
			//null�� ������ �۾������Ѱ�
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("insert ���� �߻�",e);
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
			throw new RuntimeException("delete ���� �߻�",e);
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
			throw new RuntimeException("update ���� �߻�",e);
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
			throw new RuntimeException("displayAllMemoList ���� �߻�",e);
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
			throw new RuntimeException("checkMemo ���� �߻�",e);
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
			throw new RuntimeException("searchMemoList ���� �߻�",e);
		}	
		return memoList2;
	}
	
	





}
