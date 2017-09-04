package org.teach.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String username = "root";
	private static String password = "root";

	// 获取链接
	public static Connection getConnection() throws Exception {
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	// 释放资源
	public static void release(ResultSet rs, Statement stm, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
