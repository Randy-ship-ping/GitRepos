package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.bean.BookInfo;
import cn.triom.util.JDBCUtil;

/**
 * 书籍信息插入类
 * @author triom
 *
 */
public class BookInfoInsertModel {
	// connection对象
	private Connection connection;

	public BookInfoInsertModel() {
		connection = JDBCUtil.getConnection();
	}

	public void insertBookInfo(BookInfo bookInfo) throws SQLException {
		PreparedStatement prepareStatement = connection
				.prepareStatement("insert into bookinfo values(?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		//设置参数
		prepareStatement.setString(1, bookInfo.getBookCode());
		prepareStatement.setString(2, bookInfo.getBookName());
		prepareStatement.setString(3, bookInfo.getSearchCode());
		prepareStatement.setString(4, bookInfo.getIsbnNum());
		prepareStatement.setString(5, bookInfo.getKindNum());
		prepareStatement.setString(6, bookInfo.getKindName());
		prepareStatement.setString(7, bookInfo.getPublicCompany());
		prepareStatement.setString(8, bookInfo.getPublicTime());
		prepareStatement.setInt(9, bookInfo.getBookNum());
		prepareStatement.setString(10, bookInfo.getAuthor());

		//执行sql语句
		prepareStatement.execute();
		//释放资源
		JDBCUtil.close(prepareStatement, null);
	}

}
