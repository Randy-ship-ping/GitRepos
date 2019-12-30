package cn.triom.event.select;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.dao.DIYTableModel;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;

/**
 * ����col���ļ����� 0:�������� 1:�������� 2:�������� 3:���մ�С
 * 
 * @author Administrator
 *
 */
public class SortFileActionEvent implements ActionListener {
	private MainView view;
	private int sortedColumn;

	public SortFileActionEvent(MainView view, int sortedColumn) {
		this.view = view;
		this.sortedColumn = sortedColumn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �жϵ�ǰ�Ƿ����ļ���
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡ����
		String[][] rowData = view.getRowData();
		// ����
		switch (sortedColumn) {
		case 0:
			// ������������
			sortData(rowData, 0, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			break;
		case 1:
			// ������������
			sortData(rowData, 1, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			break;
		case 2:
			// ���մ�С����
			sortData(rowData, 2, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					long l1 = 0;
					long l2 = 0;
					try {
						l1 = FileUtil.dateConvertMillis(o1);
						l2 = FileUtil.dateConvertMillis(o2);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return (l1 - l2 > 0) ? 1 : (l1 - l2 == 0) ? 0 : -1;
				}
			});
			break;
		case 3:
			// ��������޸���������
			sortData(rowData, 3, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					long l1 = Long.valueOf(o1);
					long l2 = Long.valueOf(o2);
					return (l1 - l2 > 0) ? 1 : (l1 - l2 == 0) ? 0 : -1;
				}
			});
			break;
		}

		// ��MainView�е�CurrentListFile���Ͻ�������
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		for (int i = 0; i < rowData.length; i++) {
			for (DIYFile file : view.getCurrentListFile()) {
				if (rowData[i][0].equals(file.getName()) && rowData[i][1].equals(file.getType())
						&& rowData[i][2].equals(file.getLastUpdateDate())
						&& rowData[i][3].equals(String.valueOf(file.getSize()))) {
					files.add(file);
					break;
				}
			}
		}

		// ���õ�ǰ����ڵ�����
		view.setRowData(rowData);
		// ���õ�ǰĿ¼���ļ���
		view.setCurrentDirFile(files);
		// ����TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// ��ȡԭTableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// ���ԭTableModel������
		defaultTableModel.getDataVector().clear();
		// ��table�����µ�TableModel
		view.getFileTable().setModel(diyTableModel);
	}

	/**
	 * �������������
	 * 
	 * @param rowData Ҫ���������
	 * @param col     ָ�����������
	 * @param com     �Ƚ���
	 */
	@SuppressWarnings("all")
	private void sortData(String[][] rowData, int col, Comparator com) {
		for (int i = 0; i < rowData.length; i++) {
			// Ѱ�����ֵ
			int k = i;
			for (int j = i; j < rowData.length; j++) {
				if (com.compare(rowData[k][col], rowData[j][col]) > 0) {
					k = j;
				}
			}
			// �滻Ԫ��
			String[] temp = rowData[i];
			rowData[i] = rowData[k];
			rowData[k] = temp;
		}
	}
}
