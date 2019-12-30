package cn.triom.event.select;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.dao.DIYTableModel;
import cn.triom.view.MainView;

/**
 * 列出当前目录的文件
 * 
 * @author Administrator
 *
 */
public class ListAllFileActionEvent implements ActionListener {
	private MainView view;

	public ListAllFileActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 判断当前是否有文件打开
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取文件数据
		ArrayList<DIYFile> files = view.getCurrentListFile();
		// 存储行数据
		String[][] rowData = new String[files.size()][4];
		// 添加行数据
		for (int i = 0; i < rowData.length; i++) {
			DIYFile diyFile = files.get(i);
			rowData[i][0] = diyFile.getName();
			rowData[i][1] = diyFile.getType();
			rowData[i][2] = diyFile.getLastUpdateDate();
			rowData[i][3] = diyFile.getSize() + "";
		}
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// 获取原TableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// 清除原TableModel的数据
		defaultTableModel.getDataVector().clear();
		// 向table设置新的TableModel
		view.getFileTable().setModel(diyTableModel);
	}

}
