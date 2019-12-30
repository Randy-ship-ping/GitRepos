package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;
/**
 * ��ѯ�û���
 * 
 * @author triom
 *
 */
public class UserSelectModel {
	// connection����
	private Connection connection;

	public UserSelectModel() {
		connection = JDBCUtil.getConnection();
	}

	public User selectUserByUsername(String username) throws SQLException {
		//׼��sql���
		PreparedStatement prepareStatement = connection.prepareStatement(
				"select * from users where username=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//���ò���
		prepareStatement.setString(1, username);
		//ִ��sql��䷵�ؽ����
		ResultSet resultSet = prepareStatement.executeQuery();
		//�洢��ѯ�����û�
		User user = null;
		while (resultSet.next()) {
			user = new User(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getString(4),
					resultSet.getInt(5));
		}
		//�ͷ���Դ
		JDBCUtil.close(prepareStatement, resultSet);
		return user;
	}

}
