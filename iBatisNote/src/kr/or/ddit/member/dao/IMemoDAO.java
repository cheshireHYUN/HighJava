package kr.or.ddit.member.dao;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.member.vo.MemoVO;


public interface IMemoDAO {
	
	public int insertMemo(SqlMapClient smc, MemoVO mv);
	public int deleteMemo(SqlMapClient smc, String no);
	public int updateMemo(SqlMapClient smc, MemoVO mv);
	public List<MemoVO> displayAllMemoList(SqlMapClient smc);
	public int checkMemo(SqlMapClient smc, String no);
	public List<MemoVO> searchMemoList(SqlMapClient smc, MemoVO mv);
	

}
