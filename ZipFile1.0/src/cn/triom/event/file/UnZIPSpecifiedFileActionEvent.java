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
 * 解压用户所选择的文件
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
		// 判断当前是否有文件打开
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "当前未打开任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 选择保存目的地文件
		File desFile = FileUtil.saveFile();
		// 异常处理
		if (desFile == null) {
			return;
		}
		if (desFile.isFile()) {
			JOptionPane.showMessageDialog(null, "必须选择文件夹", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			if (!desFile.exists()) {
				desFile.mkdirs();
			}
		}
		// 获取用户所选择行
		int[] rows = view.getFileTable().getSelectedRows();
		// 获取ZIP文件的路径
		String filePath = view.getZipFilePath().getText();
		// 获取当前目录下的所有文件
		ArrayList<DIYFile> currentListFile = view.getCurrentListFile();
		// 获取当前路径的父路径
		String parentPath = getCurrentDirectoryParentPath(view.getRootFileTree(), view.getDirCount());
		// 循环遍历进行解压
		for (int i = 0; i < rows.length; i++) {
			DIYFile file = currentListFile.get(i);
			try {
				// 进行解压
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
	 * 根据给的DIYFile获取当前目录下指定的文件树
	 * 
	 * @param rootFileTree 根文件树
	 * @param dirCount     当前目录
	 * @param file         指定的DIYFile
	 * @return
	 */
	private FileTree getSpecifiedFileTreeByDiYFile(FileTree rootFileTree, int dirCount, DIYFile file) {
		// 存储返回的文件树
		FileTree returnFileTree = null;
		// 获取当前目录下的文家树
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(rootFileTree, dirCount);
		// 遍历文件树
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
	 * 解压文件夹
	 * 
	 * @param directory 要解压的文件夹爱
	 * @param srcPath   原文件路径
	 * @param fileName  要解压的文件名字
	 * @param desPath   目的地路径
	 * @throws IOException
	 * @throws ZipException
	 */
	private void unZIPDirctory(String srcPath, FileTree directory, String desPath) throws IOException, ZipException {
		// 获取要解压的文件夹的子文件
		ArrayList<FileTree> sons = directory.getSons();
		// 若当前文件夹有子文件
		if (sons.size() != 0) {
			File file = new File(desPath + File.separator + directory.getFile().getName());
			// 创建文件夹
			file.mkdirs();
			// 获取当前路径的父路径
//			String parentPath = getCurrentDirectoryParentPath(view.getRootFileTree(), view.getDirCount());
			// 递归解压文件
			for (FileTree fileTree : sons) {
				unZIPDirctory(srcPath, fileTree, file.getAbsolutePath());
			}
			// 若当前文件夹没有子文件
		} else if (sons.size() == 0 && directory.getFile().getType().equals("--")) {
			// 创建文件夹
			File file = new File(desPath + File.separator + directory.getFile().getName());
			file.mkdirs();
			// 若当前文件夹是一个文件
		} else {
			// 解压文件
			unZIPFile.unZipSpecifiedFile(srcPath, directory.getPath(), desPath);
		}
	}

	/**
	 * 获取当前目录的父目录
	 * 
	 * @param fileTree 文件树
	 * @param dirCount 当前目录层数
	 * @return
	 */
	private String getCurrentDirectoryParentPath(FileTree fileTree, int dirCount) {
		ArrayList<FileTree> specifiedDirFileTree = FileUtil.getSpecifiedDirFileTree(fileTree, dirCount);
		String path = specifiedDirFileTree.get(0).getPath();
		String parentPath = path.substring(0, path.lastIndexOf("/"));
		return parentPath;
	}

}
