package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;
/**
 * 查询用户类
 * 
 * @author triom
 *
 */
public class UserSelectModel {
	// connection对象
	private Connection connection;

	public UserSelectModel() {
		connection = JDBCUtil.getConnection();
	}

	public User selectUserByUsername(String username) throws SQLException {
		//准备sql语句
		PreparedStatement prepareStatement = connection.prepareStatement(
				"select * from users where username=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//设置参数
		prepareStatement.setString(1, username);
		//执行sql语句返回结果集
		ResultSet resultSet = prepareStatement.executeQuery();
		//存储查询到的用户
		User user = null;
		while (resultSet.next()) {
			user = new User(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getString(4),
					resultSet.getInt(5));
		}
		//释放资源
		JDBCUtil.close(prepareStatement, resultSet);
		return user;
	}

}
