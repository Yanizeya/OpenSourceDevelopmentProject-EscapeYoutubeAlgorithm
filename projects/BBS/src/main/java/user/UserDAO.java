package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
//������ ���̽� ���� ��ü�� ����. �����ͺ��̽����� ������ �ҷ����ų� �ְ��� �� �� ���
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //��ǻ�Ϳ� ��ġ�� mysql��ü�� �ǹ�
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!"; //��й�ȣ
			Class.forName("com.mysql.jdbc.Driver");//����̹��� mysql�� ������ �� �ֵ��� �Ű�ü ��Ȱ�� ��
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("conn :" +conn);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�1");
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?"; //user���̺��� userPassword�� ������
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//�α��� ����
				}
				else
					return 0;//��й�ȣ ����ġ
			}
			return -1;//���̵� ����
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return-2;//�����ͺ��̽� ����
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

		return -1; // DB ����

	}


}
