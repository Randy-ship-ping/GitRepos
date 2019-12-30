package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.util.JDBCUtil;

/**
 * 插入用户类
 * @author triom
 *
 */
public class UserInsertModel {
	// connection对象
	private Connection connection;

	public UserInsertModel() {
		connection = JDBCUtil.getConnection();
	}

	public void insertUser(User user) throws SQLException {
		//准备sql语句
		PreparedStatement prepareStatement = connection
				.prepareStatement("insert into users values(?,?,?,?,?)");
		//设置参数
		prepareStatement.setString(1, user.getUsername());
		prepareStatement.setString(2, user.getPassword());
		prepareStatement.setString(3, user.getEmail());
		prepareStatement.setString(4, user.getTelephone());
		prepareStatement.setInt(5, 0);
		//执行语句
		prepareStatement.execute();
		//关闭资源
		JDBCUtil.close(prepareStatement, null);
	}
}
