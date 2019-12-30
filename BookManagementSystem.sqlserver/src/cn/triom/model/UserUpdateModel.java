package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;

/**
 * 更新用户信息类
 * 
 * @author triom
 *
 */

public class UserUpdateModel {
	// connection对象
	private Connection connection;

	public UserUpdateModel() {
		connection = JDBCUtil.getConnection();
	}

	public void updateUserByUsername(User user) throws SQLException {
		//准备sql语句
		PreparedStatement prepareStatement = connection
				.prepareStatement("update users set password=?,email=?,telephone=?,state=? where username=?");
		//设置参数
		prepareStatement.setString(1, user.getPassword());
		prepareStatement.setString(2, user.getEmail());
		prepareStatement.setString(3, user.getTelephone());
		prepareStatement.setInt(4, user.getState());
		prepareStatement.setString(5, user.getUsername());

		//执行语句
		prepareStatement.execute();
		//释放资源
		JDBCUtil.close(prepareStatement, null);
	}
}
