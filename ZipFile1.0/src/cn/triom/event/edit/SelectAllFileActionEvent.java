package cn.triom.event.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.triom.view.MainView;

/**
 * 取消选择所有文件
 * 
 * @author Administrator
 *
 */
public class SelectAllFileActionEvent implements ActionListener {
	private MainView view;

	public SelectAllFileActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 判断当前是否有文件打开
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 全选
		view.getFileTable().selectAll();
	}

}
