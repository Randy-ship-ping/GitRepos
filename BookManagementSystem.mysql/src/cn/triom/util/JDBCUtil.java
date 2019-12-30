package cn.triom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �������ݿ���
 * 
 * @author triom
 *
 */
public class JDBCUtil {
	// ����url���࣬�û���������
	private static final String URL = "jdbc:mysql://localhost:3306/bookmanage?characterEncoding=utf-8";
	private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	// �洢���Ӷ���
	private static Connection conn = null;

	static {
		try {
			// ������
			Class.forName(CLASS_NAME);
			// ��ȡ����
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private JDBCUtil() {
	}

	// ��ȡ����
	public static Connection getConnection() {
		return conn;
	}

	// �ͷ���Դ
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