package org.ah.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	// public static final String DRIVER = "com.mysql.jdbc.Driver";
	// public static final String URL = "jdbc:mysql://127.0.0.1:3306/test";
	public static final String URL = "jdbc:mysql://127.0.0.1/test";

	public static final String USER_NAME = "root";
	public static final String USER_PWD = "root";

	public Connection conn = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	public void getConn() {
		try {
			// Class.forName(DRIVER);// 指定连接类型
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PWD);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}