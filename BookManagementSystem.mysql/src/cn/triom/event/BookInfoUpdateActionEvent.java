package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoUpdateModel;
import cn.triom.view.BookInfoUpdateWin;

/**
 * 更新书籍事件类
 * 
 * @author triom
 *
 */
public class BookInfoUpdateActionEvent implements ActionListener {
	// 书籍更新窗体
	private BookInfoUpdateWin bookInfoUpdateWin;
	// 书籍更新类
	private BookInfoUpdateModel bookInfoUpdateModel;

	public BookInfoUpdateActionEvent(BookInfoUpdateWin bookInfoUpdateWin) {
		this.bookInfoUpdateWin = bookInfoUpdateWin;
		this.bookInfoUpdateModel = new BookInfoUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {
		//获取用户输入的内容
		String bookCode = bookInfoUpdateWin.getBookCodeField().getText();
		String bookName = bookInfoUpdateWin.getBookNameField().getText();
		String searchCode = bookInfoUpdateWin.getSearchCodeField().getText();
		String isbnNum = bookInfoUpdateWin.getIsbnNumField().getText();
		String kindNum = bookInfoUpdateWin.getKindNumField().getText();
		String kindName = bookInfoUpdateWin.getKindNameField().getText();
		String publicCompany = bookInfoUpdateWin.getPublicCompanyField().getText();
		String publicTime = bookInfoUpdateWin.getPublicTimeField().getText();
		String bookNum = bookInfoUpdateWin.getBookNumField().getText();
		String author = bookInfoUpdateWin.getAuthorField().getText();
		//异常处理
		if (bookCode.equals("") || bookName.equals("") || searchCode.equals("") || isbnNum.equals("")
				|| kindNum.equals("") || kindName.equals("") || publicCompany.equals("") || publicTime.equals("")
				|| bookNum.equals("") || author.equals("")) {
			JOptionPane.showMessageDialog(null, "存在空值", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//要求用户判断是否更新信息数据
		int choice = JOptionPane.showConfirmDialog(null, "确定要更新该数据吗?", "提示", JOptionPane.OK_CANCEL_OPTION);

		//若选择取消则跳出方法
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		try {
			// 利用用户输入的数据创建书籍信息实体类对象
			BookInfo bookInfo = new BookInfo(bookCode, bookName, searchCode, isbnNum, kindNum, kindName, publicCompany,
					publicTime, Integer.valueOf(bookNum), author);
			//更新数据
			bookInfoUpdateModel.updateBookInfoByBookCode(bookInfo);
			//关闭更新数据窗体类
			bookInfoUpdateWin.dispose();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
