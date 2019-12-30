package cn.triom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.triom.util.JDBCUtil;

/**
 * �鼮��Ϣɾ����
 * 
 * @author triom
 *
 */
public class BookInfoDeleteModel {
	// connection����
	private Connection connection;

	public BookInfoDeleteModel() {
		connection = JDBCUtil.getConnection();
	}

	public void deleteBookinfoByBookCode(String bookCode) throws SQLException {

		PreparedStatement prepareStatement = connection.prepareStatement("delete from bookinfo where book_code=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ����sql����еĲ���
		prepareStatement.setString(1, bookCode);
		// ִ��sql���
		prepareStatement.execute();
		// �ر����ͷ���Դ
		JDBCUtil.close(prepareStatement, null);
	}
}
