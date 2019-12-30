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
 * ���ص�ǰĿ¼����һ��Ŀ¼
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

		// ��ȡ��ǰĿ¼����
		int count = view.getDirCount();
		// �쳣����
		if (count == 0) {
			JOptionPane.showMessageDialog(null, "δ���κ��ļ�", "error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (count == 1) {
			JOptionPane.showMessageDialog(null, "���Ѿ��ǵ�һ��Ŀ¼��", "error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡ��ǰĿ¼����һ��Ŀ¼�ļ�
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(view.getRootFileTree(), --count);
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		for (FileTree file : specifiedDirFileTree) {
			files.add(file.getFile());
		}
		// �洢������
		String[][] rowData = new String[files.size()][4];
		// ���������
		for (int i = 0; i < rowData.length; i++) {
			DIYFile diyFile = files.get(i);
			rowData[i][0] = diyFile.getName();
			rowData[i][1] = diyFile.getType();
			rowData[i][2] = diyFile.getLastUpdateDate();
			rowData[i][3] = diyFile.getSize() + "";
		}
		// ���õ�ǰ����ڵ�����
		view.setRowData(rowData);
		// ���õ�ǰĿ¼���ļ�
		view.setCurrentDirFile(files);
		// ����TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// ��ȡԭTableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// ���ԭTableModel������
		defaultTableModel.getDataVector().clear();
		// ��table�����µ�TableModel
		view.getFileTable().setModel(diyTableModel);
		view.setDirCount(count);
	}
}
