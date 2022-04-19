package kr.or.ddit.member.dao;
import java.util.List;

import kr.or.ddit.member.vo.MemoVO;
public interface IMemoDAO {
	
	public int insertMemo(MemoVO mv);
	public int deleteMemo(String no);
	public int updateMemo(MemoVO mv);
	public List<MemoVO> displayAllMemoList();
	public boolean checkMemo(String no);
	public List<MemoVO> searchMemoList(MemoVO mv);
	

}
