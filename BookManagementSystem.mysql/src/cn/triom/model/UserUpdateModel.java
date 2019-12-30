package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;

/**
 * �����û���Ϣ��
 * 
 * @author triom
 *
 */

public class UserUpdateModel {
	// connection����
	private Connection connection;

	public UserUpdateModel() {
		connection = JDBCUtil.getConnection();
	}

	public void updateUserByUsername(User user) throws SQLException {
		//׼��sql���
		PreparedStatement prepareStatement = connection
				.prepareStatement("update users set password=?,email=?,telephone=?,state=? where username=?");
		//���ò���
		prepareStatement.setString(1, user.getPassword());
		prepareStatement.setString(2, user.getEmail());
		prepareStatement.setString(3, user.getTelephone());
		prepareStatement.setInt(4, user.getState());
		prepareStatement.setString(5, user.getUsername());

		//ִ�����
		prepareStatement.execute();
		//�ͷ���Դ
		JDBCUtil.close(prepareStatement, null);
	}
}
