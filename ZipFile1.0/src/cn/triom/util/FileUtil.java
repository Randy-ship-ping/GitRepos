package cn.triom.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.triom.bean.FileTree;

/**
 * 文件工具类
 * 
 * @author Administrator
 *
 */
public class FileUtil {
	private static final JFileChooser fileChooser = new JFileChooser("F:");

	private FileUtil() {
	}

	/**
	 * 获取文件前缀名
	 * 
	 * @param file 要获取的文件
	 * @return
	 */
	public static String getFilePrefixName(File file) {
		if (file.isDirectory()) {
			return null;
		}
		String fileName = file.getName();
		String prefixName = fileName.substring(0, fileName.lastIndexOf("."));

		return prefixName;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file 要获取的文件
	 * @return
	 */
	public static String getFileSuffixName(File file) {
		if (file.isDirectory()) {
			return null;
		}

		String fileName = file.getName();
		String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);

		return suffixName;
	}

	/**
	 * 获取文件前缀名
	 * 
	 * @param fileName 要获取的文件名字
	 * @return
	 */
	public static String getFilePrefixName(String fileName) {
		if (fileName == null) {
			return null;
		}
		String prefixName = fileName.substring(0, fileName.lastIndexOf("."));

		return prefixName;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName 要获取的文件名字
	 * @return
	 */
	public static String getFileSuffixName(String fileName) {
		if (fileName == null) {
			return null;
		}

		String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);

		return suffixName;
	}

	/**
	 * 判断文件后缀名是否与指定后缀名相同
	 * 
	 * @param file       要判断的文件
	 * @param suffixName 后缀名
	 * @return
	 */
	public static boolean isSpecifiedSuffix(File file, String suffixName) {
		String fileSuffixName = getFileSuffixName(file);
		return fileSuffixName.equalsIgnoreCase(suffixName);
	}

	/**
	 * 通过文件选择器选择文件
	 * 
	 * @param description 文件描述
	 * @param extensions  文件类型
	 * @return 文件
	 */
	public static File selectFileByFileChooser(String description, String... extensions) {
		fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		int showDialog = fileChooser.showDialog(null, "选择文件");
		if (showDialog == JFileChooser.CANCEL_OPTION) {
			return null;
		}
		File selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}

	/**
	 * 通过文件选择器选择文件
	 * 
	 * @return 保存目的文件
	 */
	public static File saveFile() {
		int showSaveDialog = fileChooser.showSaveDialog(null);
		if (showSaveDialog == JFileChooser.CANCEL_OPTION) {
			return null;
		}
		File selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}

	/**
	 * 将毫秒数转换成日期
	 * 
	 * @param millis 要转换的秒数
	 * @return
	 */
	public static String millisConvertDate(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(millis));
	}

	/**
	 * 将日期转换成毫秒数
	 * 
	 * @param time 要转换的日期
	 * @return
	 * @throws ParseException
	 */

	public static long dateConvertMillis(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(time).getTime();
	}

	/**
	 * 获取指定级别的文件树
	 * 
	 * @param fileTree 根文件数
	 * @param dirCount 要获取的级别
	 * @return
	 */
	public static ArrayList<FileTree> getSpecifiedDirFileTree(FileTree fileTree, int dirCount) {
		if (fileTree == null) {
			return null;
		}
		// 存储文件树
		ArrayList<FileTree> trees = new ArrayList<FileTree>();
		if (fileTree.getDirCount() < dirCount) {
			// 若当前目录级别小于指定级别则利用循环和递归遍历
			ArrayList<FileTree> sons = fileTree.getSons();
			for (FileTree son : sons) {
				trees.addAll(getSpecifiedDirFileTree(son, dirCount));
			}
		} else if (fileTree.getDirCount() == dirCount) {
			// 若相等则添加
			trees.add(fileTree);
		}
		return trees;

	}
}
