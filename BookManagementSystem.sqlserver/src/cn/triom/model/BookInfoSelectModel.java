package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.bean.BookInfo;
import cn.triom.util.JDBCUtil;

/**
 * 书籍信息查询类
 * 
 * @author triom
 *
 */
public class BookInfoSelectModel {
	// connection对象
	private Connection connection;

	public BookInfoSelectModel() {
		connection = JDBCUtil.getConnection();
	}

	// 查询所有的书籍
	public BookInfo[] selectAllBookInfos() throws SQLException {
		// 存储查询的数据
		BookInfo[] bookInfos = null;
		// 设置所有数据的大小，用来初始化数组
		int size = 0;
		// 用于向数组中添加数据
		int count = 0;

		//执行sql语句
		PreparedStatement prepareStatement = connection.prepareStatement("select * from bookinfo",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = prepareStatement.executeQuery();

		//获取数据大小
		while (resultSet.next()) {
			size++;
		}

		//将next指向第一个
		resultSet.beforeFirst();

		//创建数组
		bookInfos = new BookInfo[size];

		//获取数据
		while (resultSet.next()) {
			BookInfo bookInfo = new BookInfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
					resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10));
			bookInfos[count++] = bookInfo;
		}
		//释放资源
		JDBCUtil.close(prepareStatement, resultSet);
		return bookInfos;
	}

	//根据sql语句查询数据
	private BookInfo[] selectBookInfos(String sql, String param) throws SQLException {
		BookInfo[] bookInfos = null;
		int size = 0;
		int count = 0;

		PreparedStatement prepareStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		prepareStatement.setString(1, param);

		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			size++;
		}

		resultSet.beforeFirst();

		bookInfos = new BookInfo[size];

		while (resultSet.next()) {
			BookInfo bookInfo = new BookInfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
					resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10));
			bookInfos[count++] = bookInfo;
		}

		JDBCUtil.close(prepareStatement, resultSet);
		return bookInfos;
	}

	public BookInfo[] selectBookInfosByBookCode(String bookCode) throws SQLException {
		String sql = "select * from bookinfo where book_code=?";
		return selectBookInfos(sql, bookCode);
	}

	public BookInfo[] selectBookInfosByBookName(String bookName) throws SQLException {
		String sql = "select * from bookinfo where book_name=?";
		return selectBookInfos(sql, bookName);
	}

	public BookInfo[] selectBookInfosBySearchCode(String searchCode) throws SQLException {
		String sql = "select * from bookinfo where search_code=?";
		return selectBookInfos(sql, searchCode);
	}

	public BookInfo[] selectBookInfosByIsbnNum(String isbnNum) throws SQLException {
		String sql = "select * from bookinfo where isbn_num=?";
		return selectBookInfos(sql, isbnNum);
	}

	public BookInfo[] selectBookInfosByKindNum(String kindNum) throws SQLException {
		String sql = "select * from bookinfo where kind_num=?";
		return selectBookInfos(sql, kindNum);
	}

	public BookInfo[] selectBookInfosByKindName(String kindName) throws SQLException {
		String sql = "select * from bookinfo where kind_name=?";
		return selectBookInfos(sql, kindName);
	}

	public BookInfo[] selectBookInfosByPublicCompany(String publicCompany) throws SQLException {
		String sql = "select * from bookinfo where public_company=?";
		return selectBookInfos(sql, publicCompany);
	}

	public BookInfo[] selectBookInfosByPublicTime(String publicTime) throws SQLException {
		String sql = "select * from bookinfo where public_time=?";
		return selectBookInfos(sql, publicTime);
	}

	public BookInfo[] selectBookInfosByAuthor(String author) throws SQLException {
		String sql = "select * from bookinfo where author=?";
		return selectBookInfos(sql, author);
	}

	public BookInfo[] selectBookInfosByBookNum(int bookNum) throws SQLException {
		BookInfo[] bookInfos = null;
		int size = 0;
		int count = 0;

		PreparedStatement prepareStatement = connection.prepareStatement("select * from bookinfo where book_num=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		prepareStatement.setInt(1, bookNum);

		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			size++;
		}

		resultSet.beforeFirst();

		bookInfos = new BookInfo[size];

		while (resultSet.next()) {
			BookInfo bookInfo = new BookInfo(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
					resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10));
			bookInfos[count++] = bookInfo;
		}

		JDBCUtil.close(prepareStatement, resultSet);
		return bookInfos;
	}
}
