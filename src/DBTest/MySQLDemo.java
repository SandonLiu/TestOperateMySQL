package DBTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDemo {

	// MySQL 8.0 ���°汾 - JDBC �����������ݿ� URL
	// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";

	// MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	// ���ݿ���û��������룬��Ҫ�����Լ�������
	static final String USER = "root";
	static final String PASSWORD = "password";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try {

			System.out.println("---------------��ѯ��ʼ---------------");

			// ע�� JDBC ����
			Class.forName(JDBC_DRIVER);

			// ������
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			// ִ�в�ѯ
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employee";
			ResultSet queryResultList = stmt.executeQuery(sql);

			// չ����������ݿ�
			while (queryResultList.next()) {
				// ͨ���ֶμ���
				int empId = queryResultList.getInt("empId");
				String empName = queryResultList.getString("empName");

				// �������
				System.out.print("empId: " + empId);
				System.out.print(", վ��empName: " + empName);
				System.out.print("\n");
			}
			// ��ɺ�ر�
			queryResultList.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// ���� JDBC ����
			se.printStackTrace();
		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
		} finally {
			// �ر���Դ
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // ʲô������
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		System.out.println("---------------��ѯ����---------------");
	}
}