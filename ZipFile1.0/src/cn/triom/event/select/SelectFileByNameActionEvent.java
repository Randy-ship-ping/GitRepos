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
 * ͨ���ļ�����ѡ���ļ�
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
		// �жϵ�ǰ�Ƿ����ļ���
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// �洢���ҵ�����
		DIYFile file = null;
		// ��ȡ�û����������
		String name = JOptionPane.showInputDialog(null, "�������ļ�����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		// ���û����ȡ�����˳�
		if (name == null) {
			return;
		}
		// �洢��ǰĿ¼�������ļ�
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// �����ļ����жԱ�
		for (DIYFile diyFile : currentListFile) {
			if (diyFile.getName().equals(name)) {
				file = diyFile;
				break;
			}
		}
		// �ж��Ƿ���ڸ��ļ�
		if (file == null) {
			JOptionPane.showMessageDialog(null, "�޴��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ����������
		String[][] rowData = { { file.getName(), file.getType(), file.getLastUpdateDate(), file.getSize() + "" } };
		// ����TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// ��ȡԭTableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// ���ԭTableModel������
		defaultTableModel.getDataVector().clear();
		// ��table�����µ�TableModel
		view.getFileTable().setModel(diyTableModel);
	}

}
