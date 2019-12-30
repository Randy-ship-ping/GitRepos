package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.util.JDBCUtil;

/**
 * 书籍信息删除类
 * 
 * @author triom
 *
 */
public class BookInfoDeleteModel {
	// connection对象
	private Connection connection;

	public BookInfoDeleteModel() {
		connection = JDBCUtil.getConnection();
	}

	public void deleteBookinfoByBookCode(String bookCode) throws SQLException {

		PreparedStatement prepareStatement = connection.prepareStatement("delete from bookinfo where book_code=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// 设置sql语句中的参数
		prepareStatement.setString(1, bookCode);
		// 执行sql语句
		prepareStatement.execute();
		// 关闭来释放资源
		JDBCUtil.close(prepareStatement, null);
	}
}
