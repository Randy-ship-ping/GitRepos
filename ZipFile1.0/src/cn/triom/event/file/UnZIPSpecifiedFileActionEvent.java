package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cn.triom.bean.DIYFile;
import cn.triom.bean.FileTree;
import cn.triom.dao.UnZIPFile;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;
import net.lingala.zip4j.exception.ZipException;

/**
 * ��ѹ�û���ѡ����ļ�
 * 
 * @author Administrator
 *
 */
public class UnZIPSpecifiedFileActionEvent implements ActionListener {
	private MainView view;
	private UnZIPFile unZIPFile = new UnZIPFile();;

	public UnZIPSpecifiedFileActionEvent(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �жϵ�ǰ�Ƿ����ļ���
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ѡ�񱣴�Ŀ�ĵ��ļ�
		File desFile = FileUtil.saveFile();
		// �쳣����
		if (desFile == null) {
			return;
		}
		if (desFile.isFile()) {
			JOptionPane.showMessageDialog(null, "����ѡ���ļ���", "����", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			if (!desFile.exists()) {
				desFile.mkdirs();
			}
		}
		// ��ȡ�û���ѡ����
		int[] rows = view.getFileTable().getSelectedRows();
		// ��ȡZIP�ļ���·��
		String filePath = view.getZipFilePath().getText();
		// ��ȡ��ǰĿ¼�µ������ļ�
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// ��ȡ��ǰ·���ĸ�·��
		String parentPath = getCurrentDirectoryParentPath(view.getRootFileTree(), view.getDirCount());
		// ѭ���������н�ѹ
		for (int i = 0; i < rows.length; i++) {
			DIYFile file = currentListFile.get(i);
			try {
				// ���н�ѹ
				if (file.getType().equalsIgnoreCase("--")) {
					unZIPDirctory(filePath,
							getSpecifiedFileTreeByDiYFile(view.getRootFileTree(), view.getDirCount(), file),
							desFile.getAbsolutePath());
				} else {
					unZIPFile.unZipSpecifiedFile(filePath,
							(view.getDirCount() == 1) ? file.getName() : (parentPath + "/" + file.getName()),
							desFile.getAbsolutePath());
				}
			} catch (ZipException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * ���ݸ���DIYFile��ȡ��ǰĿ¼��ָ�����ļ���
	 * 
	 * @param rootFileTree ���ļ���
	 * @param dirCount     ��ǰĿ¼
	 * @param file         ָ����DIYFile
	 * @return
	 */
	private FileTree getSpecifiedFileTreeByDiYFile(FileTree rootFileTree, int dirCount, DIYFile file) {
		// �洢���ص��ļ���
		FileTree returnFileTree = null;
		// ��ȡ��ǰĿ¼�µ��ļ���
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(rootFileTree, dirCount);
		// �����ļ���
		for (FileTree fileTree : specifiedDirFileTree) {
			DIYFile file2 = fileTree.getFile();
			if (file2.getName().equals(file.getName()) && file2.getType().equals(file.getType())
					&& file2.getLastUpdateDate().equals(file.getLastUpdateDate())
					&& file2.getSize() == file.getSize()) {
				returnFileTree = fileTree;
				break;
			}
		}
		return returnFileTree;
	}

	/**
	 * ��ѹ�ļ���
	 * 
	 * @param directory Ҫ��ѹ���ļ��а�
	 * @param srcPath   ԭ�ļ�·��
	 * @param fileName  Ҫ��ѹ���ļ�����
	 * @param desPath   Ŀ�ĵ�·��
	 * @throws IOException
	 * @throws ZipException
	 */
	private void unZIPDirctory(String srcPath, FileTree directory, String desPath) throws IOException, ZipException {
		// ��ȡҪ��ѹ���ļ��е����ļ�
		ArrayList<FileTree> sons = directory.getSons();
		// ����ǰ�ļ��������ļ�
		if (sons.size() != 0) {
			File file = new File(desPath + File.separator + directory.getFile().getName());
			// �����ļ���
			file.mkdirs();
			// ��ȡ��ǰ·���ĸ�·��
//			String parentPath = getCurrentDirectoryParentPath(view.getRootFileTree(), view.getDirCount());
			// �ݹ��ѹ�ļ�
			for (FileTree fileTree : sons) {
				unZIPDirctory(srcPath, fileTree, file.getAbsolutePath());
			}
			// ����ǰ�ļ���û�����ļ�
		} else if (sons.size() == 0 && directory.getFile().getType().equals("--")) {
			// �����ļ���
			File file = new File(desPath + File.separator + directory.getFile().getName());
			file.mkdirs();
			// ����ǰ�ļ�����һ���ļ�
		} else {
			// ��ѹ�ļ�
			unZIPFile.unZipSpecifiedFile(srcPath, directory.getPath(), desPath);
		}
	}

	/**
	 * ��ȡ��ǰĿ¼�ĸ�Ŀ¼
	 * 
	 * @param fileTree �ļ���
	 * @param dirCount ��ǰĿ¼����
	 * @return
	 */
	private String getCurrentDirectoryParentPath(FileTree fileTree, int dirCount) {
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(fileTree, dirCount);
		String path = specifiedDirFileTree.get(0).getPath();
		String parentPath = path.substring(0, path.lastIndexOf("/"));
		return parentPath;
	}

}
