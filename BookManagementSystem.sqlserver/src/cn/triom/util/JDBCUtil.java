package cn.triom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 链接数据库类
 * @author triom
 *
 */
public class JDBCUtil {
	// 设置url，类，用户名，密码
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=bookmanage";
	private static final String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";
	// 存储链接对象
	private static Connection conn = null;
	//让它只运行一次
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
	public static void close(PreparedStatement prepareStatement,
			ResultSet resultSet) {
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