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
 * 通过文件名字选择文件
 * 
 * @author Administrator
 *
 */
public class SelectFileByNameActionEvent implements ActionListener {
	private MainView view;

	public SelectFileByNameActionEvent(MainView view) {
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
		DIYFile file = null;
		// 获取用户输入的姓名
		String name = JOptionPane.showInputDialog(null, "请输入文件名字", "提示", JOptionPane.INFORMATION_MESSAGE);
		// 若用户点击取消则退出
		if (name == null) {
			return;
		}
		// 存储当前目录的所有文件
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// 遍历文件进行对比
		for (DIYFile diyFile : currentListFile) {
			if (diyFile.getName().equals(name)) {
				file = diyFile;
				break;
			}
		}
		// 判断是否存在该文件
		if (file == null) {
			JOptionPane.showMessageDialog(null, "无此文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 设置行数据
		String[][] rowData = { { file.getName(), file.getType(), file.getLastUpdateDate(), file.getSize() + "" } };
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
