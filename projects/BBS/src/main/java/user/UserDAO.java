package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
//데이터 베이스 접근 객체의 약자. 데이터베이스에서 정보를 불러오거나 넣고자 할 때 사용
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //컴퓨터에 설치된 mysql자체를 의미
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!"; //비밀번호
			Class.forName("com.mysql.jdbc.Driver");//드라이버는 mysql에 접근할 수 있도록 매개체 역활을 함
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("conn :" +conn);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생1");
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?"; //user테이블에서 userPassword를 가져옴
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//로그인 성공
				}
				else
					return 0;//비밀번호 불일치
			}
			return -1;//아이디가 없음
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return-2;//데이터베이스 오류
	}
	
	public int join(User user) {

		String SQL = "INSERT INTO USER VALUES (?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUserID());

			pstmt.setString(2, user.getUserPassword());

			pstmt.setString(3, user.getUserName());

			pstmt.setString(4, user.getUserGender());

			pstmt.setString(5, user.getUserEmail());

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return -1; // DB 오류

	}


}
