package cn.triom.event.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.triom.view.MainView;

/**
 * ȡ��ѡ�������ļ�
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
		// �жϵ�ǰ�Ƿ����ļ���
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ȫѡ
		view.getFileTable().selectAll();
	}

}
