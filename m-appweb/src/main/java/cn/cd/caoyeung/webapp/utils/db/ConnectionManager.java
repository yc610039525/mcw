package cn.cd.caoyeung.webapp.utils.db;

import java.sql.*;
import java.util.Map;

public class ConnectionManager {
	// 桥连驱动
	private static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

	// 桥连URL
	private static String DATABASE_URL;// = "jdbc:oracle:thin:@192.168.100.98:1521:demo"

	// 数据库用户名
	private static String DATABASE_USER;//= "scott"

	// 数据库密码
	private static String DATABASE_PWD;//= "tiger"

	// 构造
	public ConnectionManager(Map<String,String> map) {
		if(map.containsKey("DRIVER_CLASS")) DRIVER_CLASS = map.get("DRIVER_CLASS"); 
		DATABASE_URL = map.get("DATABASE_URL");
		DATABASE_USER = map.get("DATABASE_USER");
		DATABASE_PWD = map.get("DATABASE_PWD");
	}

	// 得到连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
					DATABASE_PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("类驱动加载失败！" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("sql异常！" + e.getMessage());
		}
		return conn;
	}

	// 重载close方法--关闭 ResultSet
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			System.out.println("关闭ResultSet失败！" + e.getMessage());
		}
	}

	// 重载close方法--关闭 PreparedStatement
	public static void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (SQLException e) {
			System.out.println("关闭PreparedStatement失败！" + e.getMessage());
		}
	}

	// 重载close方法--关闭 Statement
	public static void close(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			System.out.println("关闭Statement失败！" + e.getMessage());
		}

	}

	// 重载close方法-- 关闭 Connection
	public static void close(Connection conn) {
		try {
			if (conn != null && (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭Conection失败！" + e.getMessage());
		}
	}
}
