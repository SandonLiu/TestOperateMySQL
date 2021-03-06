package DBTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDemo {

	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
	// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";

	// MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASSWORD = "password";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try {

			System.out.println("---------------查询开始---------------");

			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			// 执行查询
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employee";
			ResultSet queryResultList = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (queryResultList.next()) {
				// 通过字段检索
				int empId = queryResultList.getInt("empId");
				String empName = queryResultList.getString("empName");

				// 输出数据
				System.out.print("empId: " + empId);
				System.out.print(", 站点empName: " + empName);
				System.out.print("\n");
			}
			// 完成后关闭
			queryResultList.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		System.out.println("---------------查询结束---------------");
	}
}