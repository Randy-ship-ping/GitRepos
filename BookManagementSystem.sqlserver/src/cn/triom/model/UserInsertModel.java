package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;

/**
 * �����û���
 * @author triom
 *
 */
public class UserInsertModel {
	// connection����
	private Connection connection;

	public UserInsertModel() {
		connection = JDBCUtil.getConnection();
	}

	public void insertUser(User user) throws SQLException {
		//׼��sql���
		PreparedStatement prepareStatement = connection
				.prepareStatement("insert into users values(?,?,?,?,?)");
		//���ò���
		prepareStatement.setString(1, user.getUsername());
		prepareStatement.setString(2, user.getPassword());
		prepareStatement.setString(3, user.getEmail());
		prepareStatement.setString(4, user.getTelephone());
		prepareStatement.setInt(5, 0);
		//ִ�����
		prepareStatement.execute();
		//�ر���Դ
		JDBCUtil.close(prepareStatement, null);
	}
}
