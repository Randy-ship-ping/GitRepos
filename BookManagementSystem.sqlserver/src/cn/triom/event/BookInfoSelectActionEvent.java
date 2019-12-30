package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import cn.triom.bean.BookInfo;
import cn.triom.model.BookInfoSelectModel;
import cn.triom.view.BookInfoSelectWin;

/**
 * 查询书籍事件类
 * 
 * @author triom
 *
 */
public class BookInfoSelectActionEvent implements ActionListener {
	// 书籍查询窗体
	private BookInfoSelectWin bookInfoSelectWin;
	// 书籍查询类对象
	private BookInfoSelectModel bookInfoSelectModel;

	public BookInfoSelectActionEvent(BookInfoSelectWin bookInfoSeletWin) {
		this.bookInfoSelectWin = bookInfoSeletWin;
		this.bookInfoSelectModel = new BookInfoSelectModel();
	}

	public void actionPerformed(ActionEvent e) {
		// 获取用户输入的查询条件
		String selectCondition = bookInfoSelectWin.getSelectCondition().getText();

		// 获取用户选择下拉列表框的选项
		int selectedIndex = bookInfoSelectWin.getSelectConditionBox().getSelectedIndex();
		BookInfo[] bookInfos = null;
		try {
			// 若用户输入的条件为空则之间搜索全部
			if (selectCondition.equals("")) {
				bookInfos = bookInfoSelectModel.selectAllBookInfos();
			} else {
				// 根据用户选择的下拉列表框来选择查询方式
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
		// 将数据组组装成二维数组
		String[][] rowData = getRowData(bookInfos);
		// 设置数据
		bookInfoSelectWin.setRowData(rowData);
		// 将数据在表格中列出
		bookInfoSelectWin.listRowDataInTable();
	}

	//将书籍信息类对象数据转换换成二维数组
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