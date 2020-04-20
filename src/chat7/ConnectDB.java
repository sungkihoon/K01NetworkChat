package chat7;

import java.sql.*;
import java.sql.Statement;


//객체생성의 목적이 아닌 상속의 목적으로 정의된 추상클래스
public abstract class ConnectDB {
	
	protected Connection con;
	protected Statement stmt;
	protected ResultSet rs;
	
	//UpdateSQL에서 사용할 인자생성자
	public ConnectDB(String id, String pw) {
		try {
			// 오라클 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 커넥션(매개변수로 전달된 id, pw를 통해 연결)
			con = DriverManager.getConnection
					("jdbc:oracle:thin://@localhost:1521:orcl", id, pw);
			System.out.println("오라클 DB연결 성공");
		}
		catch (ClassNotFoundException e) {
			//ojdbc6.jar파일이 없거나 연동되지 않았을때 예외발생
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch (SQLException e) {
			//커넥션 URL이나 계정명이 잘못되었을때 발생되는 예외
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알수 없는 예외 발생");
		}
	}
	
	//자원반납을 위한 메소드
	public void close() {
		try {
			if(stmt != null) stmt.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
       
		}
		catch(SQLException e) {
			System.out.println("자원 반납 시 오류가 발생하였습니다.");
		}
	}////end of close
	
	/*
	오버라이딩을 목적으로 정의한 추상메소드
	 */
	abstract void execute();
}
