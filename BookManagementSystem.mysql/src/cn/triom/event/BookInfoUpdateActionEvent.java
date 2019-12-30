package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoUpdateModel;
import cn.triom.view.BookInfoUpdateWin;

/**
 * �����鼮�¼���
 * 
 * @author triom
 *
 */
public class BookInfoUpdateActionEvent implements ActionListener {
	// �鼮���´���
	private BookInfoUpdateWin bookInfoUpdateWin;
	// �鼮������
	private BookInfoUpdateModel bookInfoUpdateModel;

	public BookInfoUpdateActionEvent(BookInfoUpdateWin bookInfoUpdateWin) {
		this.bookInfoUpdateWin = bookInfoUpdateWin;
		this.bookInfoUpdateModel = new BookInfoUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {
		//��ȡ�û����������
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
		//�쳣����
		if (bookCode.equals("") || bookName.equals("") || searchCode.equals("") || isbnNum.equals("")
				|| kindNum.equals("") || kindName.equals("") || publicCompany.equals("") || publicTime.equals("")
				|| bookNum.equals("") || author.equals("")) {
			JOptionPane.showMessageDialog(null, "���ڿ�ֵ", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//Ҫ���û��ж��Ƿ������Ϣ����
		int choice = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ���¸�������?", "��ʾ", JOptionPane.OK_CANCEL_OPTION);

		//��ѡ��ȡ������������
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		try {
			// �����û���������ݴ����鼮��Ϣʵ�������
			BookInfo bookInfo = new BookInfo(bookCode, bookName, searchCode, isbnNum, kindNum, kindName, publicCompany,
					publicTime, Integer.valueOf(bookNum), author);
			//��������
			bookInfoUpdateModel.updateBookInfoByBookCode(bookInfo);
			//�رո������ݴ�����
			bookInfoUpdateWin.dispose();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
