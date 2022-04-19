package kr.or.ddit.member.service;

import java.util.List;

// 각 메소드 들어있는 인터페이스
import kr.or.ddit.member.dao.IMemoDAO;
// 메소드를 구현해 데베와 통신하는 클래스
import kr.or.ddit.member.dao.MemoDAOImpl;
// 메모의 모든 필드값들과 게터세터 즉 메모객체
import kr.or.ddit.member.vo.MemoVO;

// IMemoService 인터페이스는 IMemoDAO 인터페이스와 내용이 같다
public class MemoServiceImpl implements IMemoService{
	
	private IMemoDAO memoDAO; //메소드 선언해놓은 인터페이스
	
	//생성자
	public MemoServiceImpl() {
		memoDAO = new MemoDAOImpl(); // DAO쿼리 로직 들어간다!
	}

	@Override
	public int insertMemo(MemoVO mv) {
		return memoDAO.insertMemo(mv);  // 서비스에 있는 insertMemo를 실행하면 DAO에 구현했던 로직이 돌아가서 리턴값을 받는다
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
