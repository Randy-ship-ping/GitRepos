package cn.triom.event.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.bean.FileTree;
import cn.triom.dao.DIYTableModel;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;

/**
 * 返回当前目录的上一级目录
 * 
 * @author Administrator
 *
 */
public class BackPresDirectoryActionEvent implements ActionListener {
	private MainView view;

	public BackPresDirectoryActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 获取当前目录几倍
		int count = view.getDirCount();
		// 异常处理
		if (count == 0) {
			JOptionPane.showMessageDialog(null, "未打开任何文件", "error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (count == 1) {
			JOptionPane.showMessageDialog(null, "这已经是第一层目录了", "error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取当前目录的上一级目录文件
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(view.getRootFileTree(), --count);
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		for (FileTree file : specifiedDirFileTree) {
			files.add(file.getFile());
		}
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
		// 设置当前表格内的数据
		view.setRowData(rowData);
		// 设置当前目录的文件
		view.setCurrentDirFile(files);
		// 创建TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// 获取原TableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// 清除原TableModel的数据
		defaultTableModel.getDataVector().clear();
		// 向table设置新的TableModel
		view.getFileTable().setModel(diyTableModel);
		view.setDirCount(count);
	}
}
