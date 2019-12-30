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
 * 按照col对文件排序 0:按照姓名 1:按照类型 2:按照日期 3:按照大小
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
		// 判断当前是否有文件打开
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取数据
		String[][] rowData = view.getRowData();
		// 排序
		switch (sortedColumn) {
		case 0:
			// 按照姓名排序
			sortData(rowData, 0, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			break;
		case 1:
			// 按照类型排序
			sortData(rowData, 1, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			break;
		case 2:
			// 按照大小排序
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
			// 按照最后修改日期排序
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

		// 对MainView中的CurrentListFile集合进行排序
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

		// 设置当前表格内的数据
		view.setRowData(rowData);
		// 设置当前目录的文件树
		view.setCurrentDirFile(files);
		// 创建TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// 获取原TableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// 清除原TableModel的数据
		defaultTableModel.getDataVector().clear();
		// 向table设置新的TableModel
		view.getFileTable().setModel(diyTableModel);
	}

	/**
	 * 对数组进行排序
	 * 
	 * @param rowData 要排序的数组
	 * @param col     指定排序的依据
	 * @param com     比较器
	 */
	@SuppressWarnings("all")
	private void sortData(String[][] rowData, int col, Comparator com) {
		for (int i = 0; i < rowData.length; i++) {
			// 寻找最大值
			int k = i;
			for (int j = i; j < rowData.length; j++) {
				if (com.compare(rowData[k][col], rowData[j][col]) > 0) {
					k = j;
				}
			}
			// 替换元素
			String[] temp = rowData[i];
			rowData[i] = rowData[k];
			rowData[k] = temp;
		}
	}
}
