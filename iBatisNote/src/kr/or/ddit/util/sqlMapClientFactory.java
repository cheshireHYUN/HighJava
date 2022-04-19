package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class sqlMapClientFactory {
	
	private static SqlMapClient smc;
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			try {
				// 1-1. xml 문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); //설정파일 인코딩 정보 설정
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 위에 static으로 생성한 smc를 그대로 사용!
				//SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close(); // Reader객체 닫기
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		return smc;
	}

}
