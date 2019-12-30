package cn.triom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 链接数据库类
 * 
 * @author triom
 *
 */
public class JDBCUtil {
	// 设置url，类，用户名，密码
	private static final String URL = "jdbc:mysql://localhost:3306/bookmanage?characterEncoding=utf-8";
	private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	// 存储链接对象
	private static Connection conn = null;

	static {
		try {
			// 加载类
			Class.forName(CLASS_NAME);
			// 获取链接
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private JDBCUtil() {
	}

	// 获取链接
	public static Connection getConnection() {
		return conn;
	}

	// 释放资源
	public static void close(PreparedStatement prepareStatement, ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
			if (prepareStatement != null)
				prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}