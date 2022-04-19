package kr.or.ddit.member.service;

import java.util.List;

// �� �޼ҵ� ����ִ� �������̽�
import kr.or.ddit.member.dao.IMemoDAO;
// �޼ҵ带 ������ ������ ����ϴ� Ŭ����
import kr.or.ddit.member.dao.MemoDAOImpl;
// �޸��� ��� �ʵ尪��� ���ͼ��� �� �޸�ü
import kr.or.ddit.member.vo.MemoVO;

// IMemoService �������̽��� IMemoDAO �������̽��� ������ ����
public class MemoServiceImpl implements IMemoService{
	
	private IMemoDAO memoDAO; //�޼ҵ� �����س��� �������̽�
	
	//������
	public MemoServiceImpl() {
		memoDAO = new MemoDAOImpl(); // DAO���� ���� ����!
	}

	@Override
	public int insertMemo(MemoVO mv) {
		return memoDAO.insertMemo(mv);  // ���񽺿� �ִ� insertMemo�� �����ϸ� DAO�� �����ߴ� ������ ���ư��� ���ϰ��� �޴´�
	}

	@Override
	public int deleteMemo(String no) {
		return memoDAO.deleteMemo(no);
	}

	@Override
	public int updateMemo(MemoVO mv) {
		return memoDAO.updateMemo(mv);
	}

	@Override
	public List<MemoVO> displayAllMemoList() {
		return memoDAO.displayAllMemoList();
	}

	@Override
	public List<MemoVO> searchMemoList(MemoVO mv) {
		return memoDAO.searchMemoList(mv);
	}

	@Override
	public boolean checkMemo(String no) {
		return memoDAO.checkMemo(no);
	}
	
	

}
