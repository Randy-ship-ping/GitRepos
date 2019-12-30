package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoInsertModel;
import cn.triom.view.BookInfoInsertWin;

/**
 * 插入书籍事件类
 * 
 * @author triom
 *
 */
public class BookInfoInsertActionEvent implements ActionListener {
	// 书籍插入窗口类
	private BookInfoInsertWin bookInfoInsertWin;
	// 书籍插入类对象
	private BookInfoInsertModel bookInfoInsertModel = null;

	public BookInfoInsertActionEvent(BookInfoInsertWin bookInfoInsertWin) {
		this.bookInfoInsertWin = bookInfoInsertWin;
		this.bookInfoInsertModel = new BookInfoInsertModel();
	}

	public void actionPerformed(ActionEvent e) {
		// 获取用户输入的数据
		String bookCode = bookInfoInsertWin.getBookCodeField().getText();
		String bookName = bookInfoInsertWin.getBookNameField().getText();
		String searchCode = bookInfoInsertWin.getSearchCodeField().getText();
		String isbnNum = bookInfoInsertWin.getIsbnNumField().getText();
		String kindNum = bookInfoInsertWin.getKindNumField().getText();
		String kindName = bookInfoInsertWin.getKindNameField().getText();
		String publicCompany = bookInfoInsertWin.getPublicCompanyField().getText();
		String publicTime = bookInfoInsertWin.getPublicTimeField().getText();
		String bookNum = bookInfoInsertWin.getBookNumField().getText();
		String author = bookInfoInsertWin.getAuthorField().getText();
		// 异常处理，判断是否有空值
		if (bookCode.equals("") || bookName.equals("") || searchCode.equals("") || isbnNum.equals("")
				|| kindNum.equals("") || kindName.equals("") || publicCompany.equals("") || publicTime.equals("")
				|| bookNum.equals("") || author.equals("")) {

			JOptionPane.showMessageDialog(null, "存在空值", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 要求用户判断是否插入所填入的数据
		int choice = JOptionPane.showConfirmDialog(null, "确定要插入该数据吗?", "提示", JOptionPane.OK_CANCEL_OPTION);

		// 若用户点击取消则跳出方法
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		try {
			// 利用用户输入的数据创建书籍信息实体类对象
			BookInfo bookInfo = new BookInfo(bookCode, bookName, searchCode, isbnNum, kindNum, kindName, publicCompany,
					publicTime, Integer.valueOf(bookNum), author);
			// 插入数据
			bookInfoInsertModel.insertBookInfo(bookInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 销毁书籍插入窗口
		bookInfoInsertWin.dispose();
		//提示用户插入成功
		JOptionPane.showMessageDialog(null, "插入成功", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

}
