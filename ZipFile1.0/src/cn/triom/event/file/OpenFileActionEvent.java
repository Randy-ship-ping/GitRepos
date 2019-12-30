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
 * 打开文件
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
		// 存储ZIP文件
		ZipFile zipFile = null;
		// 选择文件
		File file = FileUtil.selectFileByFileChooser("\'ZIP\' & \'zip\' 文件类型", "zip", "ZIP");
		// 异常处理
		if (file == null) {
			return;
		}
		if (!FileUtil.isSpecifiedSuffix(file, "zip")) {
			JOptionPane.showMessageDialog(null, "只能打开zip文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 存储当前目录下的文件
		ArrayList<DIYFile> files = new ArrayList<DIYFile>();
		// 设置文件路径
		view.getZipFilePath().setText(file.getAbsolutePath());
		try {
			// 打开zipfile，并设置字节码
			zipFile = new ZipFile(file, Charset.forName("GBK"));
			// 获取文件树的根文件
			FileTree rootFileTree = getRootFileTree(zipFile);
			// 获取根文件的子文件
			ArrayList<FileTree> list = rootFileTree.getSons();
			for (FileTree fileTree : list) {
				files.add(fileTree.getFile());
			}
			// 设置根文件
			view.setRootFileTree(rootFileTree);
			// 设置目录级别
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

		// 存储行数据
		String[][] rowData = new String[files.size()][4];
		// 添加行数据
		for (int i = 0; i < rowData.length; i++) {
			DIYFile diyFile = files.get(i);
			rowData[i][0] = diyFile.getName();
			rowData[i][1] = diyFile.getType();
			rowData[i][2] = diyFile.getLastUpdateDate();
			rowData[i][3] = diyFile.getSize() + "";
		}
		// 设置当前表格内的数据
		view.setRowData(rowData);
		// 设置当前目录的文件
		view.setCurrentDirFile(files);
		// 创建TableModel
		DIYTableModel diyTableModel = new DIYTableModel(rowData, view.getColumnNames());
		// 获取原TableModel
		DefaultTableModel defaultTableModel = (DefaultTableModel) (view.getFileTable().getModel());
		// 清除原TableModel的数据
		defaultTableModel.getDataVector().clear();
		// 向table设置新的TableModel
		view.getFileTable().setModel(diyTableModel);
	}

	/**
	 * 获取整个文件树的根文件树
	 * 
	 * @param zipFile
	 * @return
	 */
	private static FileTree getRootFileTree(ZipFile zipFile) {
		// 根文件树
		FileTree tree = new FileTree(null, null, "", 0);
		// 获取所有的文件树
		ArrayList<FileTree> allFiletree = getAllFileTree(zipFile);
		// 存储父文件树
		ArrayList<FileTree> fathers = new ArrayList<FileTree>();
		// 存储子文件树
		ArrayList<FileTree> sons = new ArrayList<FileTree>();
		// 存储最大的目录层数
		int maxDirCount = 0;

		// 循环获得文件树的最大目录数
		for (FileTree fileTree : allFiletree) {
			int dirCount = fileTree.getDirCount();
			maxDirCount = (dirCount > maxDirCount) ? dirCount : maxDirCount;
		}

		// 将所有的文件树相连接
		for (int i = maxDirCount; i > 1; i--) {
			// 清除存储的数据
			fathers.clear();
			sons.clear();
			for (FileTree fileTree : allFiletree) {
				if (fileTree.getDirCount() == i) {
					sons.add(fileTree);
				} else if (fileTree.getDirCount() == i - 1) {
					fathers.add(fileTree);
				}
			}
			// 连接文件树
			connectFatherAndSon(fathers, sons);
		}
		// 将最终的父文件添加到根文件中
		tree.getSons().addAll(fathers);
		// 指定所有的最终的父文件的父文件为根文件
		for (FileTree father : fathers) {
			father.setFather(tree);
		}
		return tree;
	}

	/**
	 * 将文件数集合中的父文件树与对应的子文件树相连接
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
	 * 返回指定路径的父路径
	 * 
	 * @param path 指定的路径
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
	 * 返回ZipFile中包含的所有文件树对象
	 * 
	 * @param zipFile
	 * @return
	 */
	private static ArrayList<FileTree> getAllFileTree(ZipFile zipFile) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		ArrayList<FileTree> list = new ArrayList<>();
		// 遍历entries
		while (entries.hasMoreElements()) {
			ZipEntry f = entries.nextElement();
			// 将路径分割
			String[] names = f.getName().split("/");
			for (int i = 0; i < names.length; i++) {
				// 存储每个被分割的文件名的相对路径
				String path = "";
				for (int j = 0; j <= i; j++) {
					if (j == i) {
						path += names[j];
						continue;
					}
					path += names[j] + "/";
				}
				// 判断集合中是否有该文件树
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
	 * 判断FileTree集合中是否已经含有路径指定的FileTree
	 * 
	 * @param path 路径
	 * @param list 集合
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
	 * 根据路径从ZipFile中获取ZipEntry
	 * 
	 * @param path    路径
	 * @param name    文件名字
	 * @param zipFile 要获取ZipFile的存取文件
	 * @return
	 */
	private static DIYFile getDIYFile(String path, String name, ZipFile zipFile) {
		ZipEntry zipEntry = zipFile.getEntry(path);
		DIYFile file = null;
		if (zipEntry != null) {
			// 生成DIYFile对象
			file = new DIYFile(name, zipEntry.isDirectory() ? "--" : FileUtil.getFileSuffixName(name),
					FileUtil.millisConvertDate(zipEntry.getTime()), zipEntry.getSize());
		} else {
			file = new DIYFile(name, "--", "--", 0);
		}
		return file;
	}
}