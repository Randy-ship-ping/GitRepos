package cn.triom.event.other;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.bean.FileTree;
import cn.triom.dao.DIYTableModel;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;

/**
 * 双击文件打开子目录事件
 * 
 * @author Administrator
 *
 */
public class DoubleClickFileToOpenSonFileMouseAction implements MouseListener {
	private MainView view;

	public DoubleClickFileToOpenSonFileMouseAction(MainView view) {
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 当点击两下打开文件
		if (e.getClickCount() == 2) {
			// 获取文件根目录
			FileTree rootFileTree = view.getRootFileTree();
			// 存储用户要获取的子文件
			ArrayList<DIYFile> files = new ArrayList<DIYFile>();
			// 获取点击的行
			int selectedRow = view.getFileTable().getSelectedRow();
			// 获取名字和类型
			String name = (String) view.getFileTable().getValueAt(selectedRow, 0);
			String type = (String) view.getFileTable().getValueAt(selectedRow, 1);
			// 根据类型判断是否是文件
			if (type.equals("--")) {
				// 获取当前目录的所有文件
				ArrayList<FileTree> trees = FileUtil.getSpecifiedDirFileTree(rootFileTree, view.getDirCount());
				// 查找用户所点击得文件的所有子文件
				for (FileTree tree : trees) {
					if (tree.getFile().getName().equals(name)) {
						ArrayList<FileTree> sons = tree.getSons();
						for (FileTree son : sons) {
							files.add(son.getFile());
						}
						break;
					}
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
				DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
				// 获取原TableModel
				DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
				// 清除原TableModel的数据
				defaultTableModel.getDataVector().clear();
				// 向table设置新的TableModel
				view.getFileTable().setModel(diyTableModel);
				// 移除fileView的所有组件并将新文件一标签的形式列出
				view.setDirCount(view.getDirCount() + 1);
			} else {
				JOptionPane.showMessageDialog(null, "文件不能打开", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
