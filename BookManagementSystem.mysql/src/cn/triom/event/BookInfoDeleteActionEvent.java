package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.model.BookInfoDeleteModel;
import cn.triom.view.BookInfoSelectWin;

/**
 * 删除书籍事件类
 * 
 * @author triom
 *
 */
public class BookInfoDeleteActionEvent implements ActionListener {
	// 书籍查询类对象
	private BookInfoSelectWin bookInfoSelectWin;
	// 书籍删除类对象
	private BookInfoDeleteModel bookInfoDeleteModel;

	public BookInfoDeleteActionEvent(BookInfoSelectWin bookInfoSelectWin) {
		this.bookInfoSelectWin = bookInfoSelectWin;
		this.bookInfoDeleteModel = new BookInfoDeleteModel();
	}

	public void actionPerformed(ActionEvent e) {
		// 用于存储用户选择的行
		int[] selectedRows = bookInfoSelectWin.getTable().getSelectedRows();

		// 若用户未选择行，则跳出该方法
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(null, "未选中任何行", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 要求用户判断是否删除所选中的数据
		int choice = JOptionPane.showConfirmDialog(null, "确定要删除这" + selectedRows.length + "数据吗?", "提示",
				JOptionPane.OK_CANCEL_OPTION);

		// 若用户点击取消则跳出方法
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		
		// 获取书籍信息查询窗口的行数据
		String[][] rowData = bookInfoSelectWin.getRowData();
		// 用来存储要删除的数据
		String[] selectedBookCode = new String[selectedRows.length];

		// 遍历行数据来获取要删除的数据，并将行数据内的数据设为空置
		for (int i = 0; i < selectedBookCode.length; i++) {
			selectedBookCode[i] = rowData[selectedRows[i]][0];
			rowData[i] = null;
		}
		// 存储删除后的行数据
		String[][] rowDataAfterDelete = new String[rowData.length - selectedBookCode.length][10];
		// 获取删除后的数据
		for (int i = 0, j = 0; i < rowData.length; i++) {
			if (rowData[i] != null) {
				rowDataAfterDelete[j] = rowData[i];
				j++;
			}
		}

		// 循环遍历来删除数据
		try {
			for (int i = 0; i < selectedBookCode.length; i++) {
				bookInfoDeleteModel.deleteBookinfoByBookCode(selectedBookCode[i]);
			}
			bookInfoSelectWin.setRowData(rowDataAfterDelete);
			bookInfoSelectWin.listRowDataInTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// 提示删除成功
		JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
	}
}
