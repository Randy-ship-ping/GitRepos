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
 * ģ�������ļ�
 * 
 * @author Administrator
 *
 */
public class SelectFileIntangiblyActionEvent implements ActionListener {
	private MainView view;

	public SelectFileIntangiblyActionEvent(MainView view) {
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
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		// ��ȡ�û����������
		String keyWord = JOptionPane.showInputDialog(null, "������ؼ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		// ���û����ȡ�����˳�
		if (keyWord == null) {
			return;
		}
		// �洢��ǰĿ¼�������ļ�
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// �����ļ����жԱ�
		for (DIYFile diyFile : currentListFile) {
			if (diyFile.getName().toLowerCase().contains(keyWord.toLowerCase())) {
				files.add(diyFile);
			}
		}
		// �ж��Ƿ���ڸ��ļ�
		if (files.size() == 0) {
			JOptionPane.showMessageDialog(null, "�޴�����ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ����������
		String[][] rowData = new String[files.size()][4];
		for (int i = 0; i < rowData.length; i++) {
			rowData[i][0] = files.get(i).getName();
			rowData[i][1] = files.get(i).getType();
			rowData[i][2] = files.get(i).getLastUpdateDate();
			rowData[i][3] = files.get(i).getSize() + "";
		}

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
