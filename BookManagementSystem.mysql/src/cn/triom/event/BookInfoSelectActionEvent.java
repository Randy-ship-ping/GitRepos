package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoSelectModel;
import cn.triom.view.BookInfoSelectWin;

/**
 * ��ѯ�鼮�¼���
 * 
 * @author triom
 *
 */
public class BookInfoSelectActionEvent implements ActionListener {
	// �鼮��ѯ����
	private BookInfoSelectWin bookInfoSelectWin;
	// �鼮��ѯ�����
	private BookInfoSelectModel bookInfoSelectModel;

	public BookInfoSelectActionEvent(BookInfoSelectWin bookInfoSeletWin) {
		this.bookInfoSelectWin = bookInfoSeletWin;
		this.bookInfoSelectModel = new BookInfoSelectModel();
	}

	public void actionPerformed(ActionEvent e) {
		// ��ȡ�û�����Ĳ�ѯ����
		String selectCondition = bookInfoSelectWin.getSelectCondition().getText();

		// ��ȡ�û�ѡ�������б���ѡ��
		int selectedIndex = bookInfoSelectWin.getSelectConditionBox().getSelectedIndex();
		BookInfo[] bookInfos = null;
		try {
			// ���û����������Ϊ����֮������ȫ��
			if (selectCondition.equals("")) {
				bookInfos = bookInfoSelectModel.selectAllBookInfos();
			} else {
				// �����û�ѡ��������б����ѡ���ѯ��ʽ
				switch (selectedIndex) {
				case 0:
					bookInfos = bookInfoSelectModel.selectBookInfosByBookCode(selectCondition);
					break;
				case 1:
					bookInfos = bookInfoSelectModel.selectBookInfosByBookName(selectCondition);
					break;
				case 2:
					bookInfos = bookInfoSelectModel.selectBookInfosBySearchCode(selectCondition);
					break;
				case 3:
					bookInfos = bookInfoSelectModel.selectBookInfosByIsbnNum(selectCondition);
					break;
				case 4:
					bookInfos = bookInfoSelectModel.selectBookInfosByKindNum(selectCondition);
					break;
				case 5:
					bookInfos = bookInfoSelectModel.selectBookInfosByKindName(selectCondition);
					break;
				case 6:
					bookInfos = bookInfoSelectModel.selectBookInfosByPublicCompany(selectCondition);
					break;
				case 7:
					bookInfos = bookInfoSelectModel.selectBookInfosByPublicTime(selectCondition);
					break;
				case 8:
					bookInfos = bookInfoSelectModel.selectBookInfosByBookNum(Integer.valueOf(selectCondition));
					break;
				case 9:
					bookInfos = bookInfoSelectModel.selectBookInfosByAuthor(selectCondition);
					break;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// ����������װ�ɶ�ά����
		String[][] rowData = getRowData(bookInfos);
		// ��������
		bookInfoSelectWin.setRowData(rowData);
		// �������ڱ�����г�
		bookInfoSelectWin.listRowDataInTable();
	}

	//���鼮��Ϣ���������ת�����ɶ�ά����
	private String[][] getRowData(BookInfo[] bookInfos) {
		if (bookInfos == null) {
			return null;
		}
		String[][] rowData = new String[bookInfos.length][10];
		for (int i = 0; i < bookInfos.length; i++) {
			rowData[i][0] = bookInfos[i].getBookCode();
			rowData[i][1] = bookInfos[i].getBookName();
			rowData[i][2] = bookInfos[i].getSearchCode();
			rowData[i][3] = bookInfos[i].getIsbnNum();
			rowData[i][4] = bookInfos[i].getKindNum();
			rowData[i][5] = bookInfos[i].getKindName();
			rowData[i][6] = bookInfos[i].getPublicCompany();
			rowData[i][7] = bookInfos[i].getPublicTime();
			rowData[i][8] = bookInfos[i].getBookNum() + "";
			rowData[i][9] = bookInfos[i].getAuthor();
		}
		return rowData;
	}
}