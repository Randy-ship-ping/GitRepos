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
 * ˫���ļ�����Ŀ¼�¼�
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
		// ��������´��ļ�
		if (e.getClickCount() == 2) {
			// ��ȡ�ļ���Ŀ¼
			FileTree rootFileTree = view.getRootFileTree();
			// �洢�û�Ҫ��ȡ�����ļ�
			ArrayList<DIYFile> files = new ArrayList<DIYFile>();
			// ��ȡ�������
			int selectedRow = view.getFileTable().getSelectedRow();
			// ��ȡ���ֺ�����
			String name = (String) view.getFileTable().getValueAt(selectedRow, 0);
			String type = (String) view.getFileTable().getValueAt(selectedRow, 1);
			// ���������ж��Ƿ����ļ�
			if (type.equals("--")) {
				// ��ȡ��ǰĿ¼�������ļ�
				ArrayList<FileTree> trees = FileUtil.getSpecifiedDirFileTree(rootFileTree, view.getDirCount());
				// �����û���������ļ����������ļ�
				for (FileTree tree : trees) {
					if (tree.getFile().getName().equals(name)) {
						ArrayList<FileTree> sons = tree.getSons();
						for (FileTree son : sons) {
							files.add(son.getFile());
						}
						break;
					}
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
				DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
				// ��ȡԭTableModel
				DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
				// ���ԭTableModel������
				defaultTableModel.getDataVector().clear();
				// ��table�����µ�TableModel
				view.getFileTable().setModel(diyTableModel);
				// �Ƴ�fileView����������������ļ�һ��ǩ����ʽ�г�
				view.setDirCount(view.getDirCount() + 1);
			} else {
				JOptionPane.showMessageDialog(null, "�ļ����ܴ�", "����", JOptionPane.ERROR_MESSAGE);
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
