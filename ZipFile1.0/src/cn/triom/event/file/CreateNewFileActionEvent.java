package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.triom.view.MainView;

/**
 * �������ļ�
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
		JOptionPane.showMessageDialog(null, "��ʱ������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		return;
		// // �жϵ�ǰ�Ƿ����ļ���
//		if (view.getDirCount() == 0) {
//			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		// ��ȡ��������û���
//		String showInputDialog = JOptionPane.showInputDialog(null, "������Ҫ�������ļ�����", "��ʾ",
//				JOptionPane.INFORMATION_MESSAGE);
//
//		// ��Ϊ��˵���û����ȡ���򷵻�
//		if (showInputDialog == null) {
//			return;
//		}
//		
//		
	}

}
