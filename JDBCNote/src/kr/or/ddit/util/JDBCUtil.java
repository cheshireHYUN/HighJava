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
			// 파일읽기를 수행할 FileInputStream객체 생성
			FileInputStream fis = new FileInputStream("res/db.properties");
			
			// Properties객체로 파일내용 읽기
			// 파일내용을 읽어와 key와value값으로 분류한 후 Properties객체에 담아준다.
			prop.load(fis);
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		
		try {
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
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
			System.out.println("DB연결 실패");
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
