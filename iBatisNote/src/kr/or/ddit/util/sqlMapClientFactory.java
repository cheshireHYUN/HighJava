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
				// 싱글톤으로 선언, smc가 없으면 불러와주고 smc를 리턴하므로 즉 smc는 sqlMapConfig를 읽어온 정보가 들어있다
				Charset charset = Charset.forName("UTF-8"); //설정파일 인코딩 정보 설정
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close(); // Reader객체 닫기
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		return smc;
	}

}
