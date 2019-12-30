package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.model.BookInfoDeleteModel;
import cn.triom.view.BookInfoSelectWin;

/**
 * ɾ���鼮�¼���
 * 
 * @author triom
 *
 */
public class BookInfoDeleteActionEvent implements ActionListener {
	// �鼮��ѯ�����
	private BookInfoSelectWin bookInfoSelectWin;
	// �鼮ɾ�������
	private BookInfoDeleteModel bookInfoDeleteModel;

	public BookInfoDeleteActionEvent(BookInfoSelectWin bookInfoSelectWin) {
		this.bookInfoSelectWin = bookInfoSelectWin;
		this.bookInfoDeleteModel = new BookInfoDeleteModel();
	}

	public void actionPerformed(ActionEvent e) {
		// ���ڴ洢�û�ѡ�����
		int[] selectedRows = bookInfoSelectWin.getTable().getSelectedRows();

		// ���û�δѡ���У��������÷���
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(null, "δѡ���κ���", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Ҫ���û��ж��Ƿ�ɾ����ѡ�е�����
		int choice = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����" + selectedRows.length + "������?", "��ʾ",
				JOptionPane.OK_CANCEL_OPTION);

		// ���û����ȡ������������
		if (choice == JOptionPane.CANCEL_OPTION) {
			return;
		}
		
		// ��ȡ�鼮��Ϣ��ѯ���ڵ�������
		String[][] rowData = bookInfoSelectWin.getRowData();
		// �����洢Ҫɾ��������
		String[] selectedBookCode = new String[selectedRows.length];

		// ��������������ȡҪɾ�������ݣ������������ڵ�������Ϊ����
		for (int i = 0; i < selectedBookCode.length; i++) {
			selectedBookCode[i] = rowData[selectedRows[i]][0];
			rowData[i] = null;
		}
		// �洢ɾ�����������
		String[][] rowDataAfterDelete = new String[rowData.length - selectedBookCode.length][10];
		// ��ȡɾ���������
		for (int i = 0, j = 0; i < rowData.length; i++) {
			if (rowData[i] != null) {
				rowDataAfterDelete[j] = rowData[i];
				j++;
			}
		}

		// ѭ��������ɾ������
		try {
			for (int i = 0; i < selectedBookCode.length; i++) {
				bookInfoDeleteModel.deleteBookinfoByBookCode(selectedBookCode[i]);
			}
			bookInfoSelectWin.setRowData(rowDataAfterDelete);
			bookInfoSelectWin.listRowDataInTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// ��ʾɾ���ɹ�
		JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
	}
}
