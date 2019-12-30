package cn.triom.event.select;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.dao.DIYTableModel;
import cn.triom.view.MainView;

public class SelectFileByTypeActionEvent implements ActionListener {
	private MainView view;

	public SelectFileByTypeActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 判断当前是否有文件打开
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 存储查找的数据
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		// 获取用户输入的类型
		String type = JOptionPane.showInputDialog(null, "请输入文件类型", "提示", JOptionPane.INFORMATION_MESSAGE);
		// 若用户点击取消则退出
		if (type == null) {
			return;
		}
		// 存储当前目录的所有文件
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// 遍历文件进行对比
		for (DIYFile diyFile : currentListFile) {
			if (diyFile.getType().equals(type)) {
				files.add(diyFile);
			}
		}
		// 判断是否存在该文件
		if (files.size() == 0) {
			JOptionPane.showMessageDialog(null, "无此类型文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 设置行数据
		String[][] rowData = new String[files.size()][4];
		for (int i = 0; i < rowData.length; i++) {
			rowData[i][0] = files.get(i).getName();
			rowData[i][1] = files.get(i).getType();
			rowData[i][2] = files.get(i).getLastUpdateDate();
			rowData[i][3] = files.get(i).getSize() + "";
		}
		// 创建TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// 获取原TableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// 清除原TableModel的数据
		defaultTableModel.getDataVector().clear();
		// 向table设置新的TableModel
		view.getFileTable().setModel(diyTableModel);
	}

}
