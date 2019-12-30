package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.bean.BookInfo;
import cn.triom.util.JDBCUtil;

/**
 * 书籍信息更新类
 * 
 * @author triom
 *
 */
public class BookInfoUpdateModel {
	//connection对象
	private Connection connection;

	public BookInfoUpdateModel() {
		connection = JDBCUtil.getConnection();
	}

	public void updateBookInfoByBookCode(BookInfo bookInfo) throws SQLException {
		//准备sql语句
		PreparedStatement prepareStatement = connection.prepareStatement(
				"update bookinfo set  book_name=?,search_code=?,isbn_num=?,kind_num=?,kind_name=?,public_company=?,public_time=?,book_num=?,author=? where book_code=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		//设置参数
		prepareStatement.setString(1, bookInfo.getBookName());
		prepareStatement.setString(2, bookInfo.getSearchCode());
		prepareStatement.setString(3, bookInfo.getIsbnNum());
		prepareStatement.setString(4, bookInfo.getKindNum());
		prepareStatement.setString(5, bookInfo.getKindName());
		prepareStatement.setString(6, bookInfo.getPublicCompany());
		prepareStatement.setString(7, bookInfo.getPublicTime());
		prepareStatement.setInt(8, bookInfo.getBookNum());
		prepareStatement.setString(9, bookInfo.getAuthor());
		prepareStatement.setString(10, bookInfo.getBookCode());

		//执行语句
		prepareStatement.execute();
		//释放资源
		JDBCUtil.close(prepareStatement, null);
	}
}
