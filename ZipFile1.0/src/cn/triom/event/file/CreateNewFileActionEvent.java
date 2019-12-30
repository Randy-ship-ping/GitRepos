package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.triom.view.MainView;

/**
 * 创建新文件
 * 
 * @author Administrator
 *
 */
public class CreateNewFileActionEvent implements ActionListener {
	private MainView view;

	public CreateNewFileActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "暂时不能用", "提示", JOptionPane.INFORMATION_MESSAGE);
		return;
		// // 判断当前是否有文件打开
//		if (view.getDirCount() == 0) {
//			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		// 获取用输入的用户名
//		String showInputDialog = JOptionPane.showInputDialog(null, "请输入要创建的文件名字", "提示",
//				JOptionPane.INFORMATION_MESSAGE);
//
//		// 若为空说明用户点击取消则返回
//		if (showInputDialog == null) {
//			return;
//		}
//		
//		
	}

}
