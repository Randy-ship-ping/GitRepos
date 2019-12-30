package cn.triom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �������ݿ���
 * @author triom
 *
 */
public class JDBCUtil {
	// ����url���࣬�û���������
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=bookmanage";
	private static final String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";
	// �洢���Ӷ���
	private static Connection conn = null;
	//����ֻ����һ��
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