package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cn.triom.bean.DIYFile;
import cn.triom.bean.FileTree;
import cn.triom.dao.DIYTableModel;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;

/**
 * ���ļ�
 * 
 * @author Administrator
 *
 */
public class OpenFileActionEvent implements ActionListener {
	private MainView view;

	public OpenFileActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �洢ZIP�ļ�
		ZipFile zipFile = null;
		// ѡ���ļ�
		File file = FileUtil.selectFileByFileChooser("\'ZIP\' & \'zip\' �ļ�����", "zip", "ZIP");
		// �쳣����
		if (file == null) {
			return;
		}
		if (!FileUtil.isSpecifiedSuffix(file, "zip")) {
			JOptionPane.showMessageDialog(null, "ֻ�ܴ�zip�ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// �洢��ǰĿ¼�µ��ļ�
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		// �����ļ�·��
		view.getZipFilePath().setText(file.getAbsolutePath());
		try {
			// ��zipfile���������ֽ���
			zipFile = new ZipFile(file, Charset.forName("GBK"));
			// ��ȡ�ļ����ĸ��ļ�
			FileTree rootFileTree = getRootFileTree(zipFile);
			// ��ȡ���ļ������ļ�
			ArrayList<FileTree> list = rootFileTree.getSons();
			for (FileTree fileTree : list) {
				files.add(fileTree.getFile());
			}
			// ���ø��ļ�
			view.setRootFileTree(rootFileTree);
			// ����Ŀ¼����
			view.setDirCount(1);
		} catch (ZipException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (zipFile != null)
				try {
					zipFile.close();
				} catch (IOException e1) {
					e1.printStackTrace();
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
		// ����TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// ��ȡԭTableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// ���ԭTableModel������
		defaultTableModel.getDataVector().clear();
		// ��table�����µ�TableModel
		view.getFileTable().setModel(diyTableModel);
	}

	/**
	 * ��ȡ�����ļ����ĸ��ļ���
	 * 
	 * @param zipFile
	 * @return
	 */
	private static FileTree getRootFileTree(ZipFile zipFile) {
		// ���ļ���
		FileTree tree = new FileTree(null, null, "", 0);
		// ��ȡ���е��ļ���
		ArrayList<FileTree> allFiletree = getAllFileTree(zipFile);
		// �洢���ļ���
		ArrayList<FileTree> fathers = new ArrayList<FileTree>();
		// �洢���ļ���
		ArrayList<FileTree> sons = new ArrayList<FileTree>();
		// �洢����Ŀ¼����
		int maxDirCount = 0;

		// ѭ������ļ��������Ŀ¼��
		for (FileTree fileTree : allFiletree) {
			int dirCount = fileTree.getDirCount();
			maxDirCount = (dirCount > maxDirCount) ? dirCount : maxDirCount;
		}

		// �����е��ļ���������
		for (int i = maxDirCount; i > 1; i--) {
			// ����洢������
			fathers.clear();
			sons.clear();
			for (FileTree fileTree : allFiletree) {
				if (fileTree.getDirCount() == i) {
					sons.add(fileTree);
				} else if (fileTree.getDirCount() == i - 1) {
					fathers.add(fileTree);
				}
			}
			// �����ļ���
			connectFatherAndSon(fathers, sons);
		}
		// �����յĸ��ļ���ӵ����ļ���
		tree.getSons().addAll(fathers);
		// ָ�����е����յĸ��ļ��ĸ��ļ�Ϊ���ļ�
		for (FileTree father : fathers) {
			father.setFather(tree);
		}
		return tree;
	}

	/**
	 * ���ļ��������еĸ��ļ������Ӧ�����ļ���������
	 * 
	 * @param fathers
	 * @param sons
	 */
	private static void connectFatherAndSon(ArrayList<FileTree> fathers, ArrayList<FileTree> sons) {
		for (FileTree father : fathers) {
			for (FileTree son : sons) {
				if (father.getPath().equals(getFileTreeParentPath(son.getPath()))) {
					father.getSons().add(son);
					son.setFather(father);
				}
			}
		}
	}

	/**
	 * ����ָ��·���ĸ�·��
	 * 
	 * @param path ָ����·��
	 * @return
	 */
	private static String getFileTreeParentPath(String path) {
		if (!path.contains("/")) {
			return path;
		}
		String parentPath = path.substring(0, path.lastIndexOf("/"));
		return parentPath;
	}

	/**
	 * ����ZipFile�а����������ļ�������
	 * 
	 * @param zipFile
	 * @return
	 */
	private static ArrayList<FileTree> getAllFileTree(ZipFile zipFile) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		ArrayList<FileTree> list = new ArrayList<>();
		// ����entries
		while (entries.hasMoreElements()) {
			ZipEntry f = entries.nextElement();
			// ��·���ָ�
			String[] names = f.getName().split("/");
			for (int i = 0; i < names.length; i++) {
				// �洢ÿ�����ָ���ļ��������·��
				String path = "";
				for (int j = 0; j <= i; j++) {
					if (j == i) {
						path += names[j];
						continue;
					}
					path += names[j] + "/";
				}
				// �жϼ������Ƿ��и��ļ���
				if (!hasFileTree(path, list)) {
					DIYFile file = getDIYFile(path, names[i], zipFile);
					FileTree fileTree = new FileTree(null, file, path, i + 1);
					list.add(fileTree);
				}
			}
		}
		return list;
	}

	/**
	 * �ж�FileTree�������Ƿ��Ѿ�����·��ָ����FileTree
	 * 
	 * @param path ·��
	 * @param list ����
	 * @return
	 */
	private static boolean hasFileTree(String path, ArrayList<FileTree> list) {
		for (FileTree fileTree : list) {
			if (fileTree.getPath().equals(path)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ����·����ZipFile�л�ȡZipEntry
	 * 
	 * @param path    ·��
	 * @param name    �ļ�����
	 * @param zipFile Ҫ��ȡZipFile�Ĵ�ȡ�ļ�
	 * @return
	 */
	private static DIYFile getDIYFile(String path, String name, ZipFile zipFile) {
		ZipEntry zipEntry = zipFile.getEntry(path);
		DIYFile file = null;
		if (zipEntry != null) {
			// ����DIYFile����
			file = new DIYFile(name, zipEntry.isDirectory() ? "--" : FileUtil.getFileSuffixName(name),
					FileUtil.millisConvertDate(zipEntry.getTime()), zipEntry.getSize());
		} else {
			file = new DIYFile(name, "--", "--", 0);
		}
		return file;
	}
}