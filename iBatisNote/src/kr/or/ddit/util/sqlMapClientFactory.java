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
				// 1-1. xml ���� �о����
				Charset charset = Charset.forName("UTF-8"); //�������� ���ڵ� ���� ����
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// ���� static���� ������ smc�� �״�� ���!
				//SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close(); // Reader��ü �ݱ�
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		return smc;
	}

}
