package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
	static Properties prop;

	static {
		
		
		prop = new Properties();
		
		try {
			// �����б⸦ ������ FileInputStream��ü ����
			FileInputStream fis = new FileInputStream("res/db.properties");
			
			// Properties��ü�� ���ϳ��� �б�
			// ���ϳ����� �о�� key��value������ �з��� �� Properties��ü�� ����ش�.
			prop.load(fis);
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		
		try {
			Class.forName(prop.getProperty("driver"));
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			System.out.println("DB���� ����");
			e.printStackTrace();
			return null;
		}
	}

	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}

	}



}
