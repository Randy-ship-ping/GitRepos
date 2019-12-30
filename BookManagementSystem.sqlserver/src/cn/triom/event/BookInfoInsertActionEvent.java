package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoInsertModel;
import cn.triom.view.BookInfoInsertWin;

/**
 * �����鼮�¼���
 * 
 * @author triom
 *
 */
public class BookInfoInsertActionEvent implements ActionListener {
	// �鼮���봰����
	private BookInfoInsertWin bookInfoInsertWin;
	// �鼮���������
	private BookInfoInsertModel bookInfoInsertModel = null;

	public BookInfoInsertActionEvent(BookInfoInsertWin bookInfoInsertWin) {
		this.bookInfoInsertWin = bookInfoInsertWin;
		this.bookInfoInsertModel = new BookInfoInsertModel();
	}

	public void actionPerformed(ActionEvent e) {
		// ��ȡ�û����������
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
		// �쳣�����ж��Ƿ��п�ֵ
		if (bookCode.equals("") || bookName.equals("") || searchCode.equals("") || isbnNum.equals("")
				|| kindNum.equals("") || kindName.equals("") || publicCompany.equals("") || publicTime.equals("")
				|| bookNum.equals("") || author.equals("")) {

			JOptionPane.showMessageDialog(null, "���ڿ�ֵ", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Ҫ���û��ж��Ƿ���������������
		int choice = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�����������?", "��ʾ", JOptionPane.OK_CANCEL_OPTION);

		// ���û����ȡ������������
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		try {
			// �����û���������ݴ����鼮��Ϣʵ�������
			BookInfo bookInfo = new BookInfo(bookCode, bookName, searchCode, isbnNum, kindNum, kindName, publicCompany,
					publicTime, Integer.valueOf(bookNum), author);
			// ��������
			bookInfoInsertModel.insertBookInfo(bookInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// �����鼮���봰��
		bookInfoInsertWin.dispose();
		//��ʾ�û�����ɹ�
		JOptionPane.showMessageDialog(null, "����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
	}

}
