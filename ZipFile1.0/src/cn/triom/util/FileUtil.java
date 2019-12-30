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
 * �ļ�������
 * 
 * @author Administrator
 *
 */
public class FileUtil {
	private static final JFileChooser fileChooser = new JFileChooser("F:");

	private FileUtil() {
	}

	/**
	 * ��ȡ�ļ�ǰ׺��
	 * 
	 * @param file Ҫ��ȡ���ļ�
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
	 * ��ȡ�ļ���׺��
	 * 
	 * @param file Ҫ��ȡ���ļ�
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
	 * ��ȡ�ļ�ǰ׺��
	 * 
	 * @param fileName Ҫ��ȡ���ļ�����
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
	 * ��ȡ�ļ���׺��
	 * 
	 * @param fileName Ҫ��ȡ���ļ�����
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
	 * �ж��ļ���׺���Ƿ���ָ����׺����ͬ
	 * 
	 * @param file       Ҫ�жϵ��ļ�
	 * @param suffixName ��׺��
	 * @return
	 */
	public static boolean isSpecifiedSuffix(File file, String suffixName) {
		String fileSuffixName = getFileSuffixName(file);
		return fileSuffixName.equalsIgnoreCase(suffixName);
	}

	/**
	 * ͨ���ļ�ѡ����ѡ���ļ�
	 * 
	 * @param description �ļ�����
	 * @param extensions  �ļ�����
	 * @return �ļ�
	 */
	public static File selectFileByFileChooser(String description, String... extensions) {
		fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		int showDialog = fileChooser.showDialog(null, "ѡ���ļ�");
		if (showDialog == JFileChooser.CANCEL_OPTION) {
			return null;
		}
		File selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}

	/**
	 * ͨ���ļ�ѡ����ѡ���ļ�
	 * 
	 * @return ����Ŀ���ļ�
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
	 * ��������ת��������
	 * 
	 * @param millis Ҫת��������
	 * @return
	 */
	public static String millisConvertDate(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(millis));
	}

	/**
	 * ������ת���ɺ�����
	 * 
	 * @param time Ҫת��������
	 * @return
	 * @throws ParseException
	 */

	public static long dateConvertMillis(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(time).getTime();
	}

	/**
	 * ��ȡָ��������ļ���
	 * 
	 * @param fileTree ���ļ���
	 * @param dirCount Ҫ��ȡ�ļ���
	 * @return
	 */
	public static ArrayList<FileTree> getSpecifiedDirFileTree(FileTree fileTree, int dirCount) {
		if (fileTree == null) {
			return null;
		}
		// �洢�ļ���
		ArrayList<FileTree> trees = new ArrayList<FileTree>();
		if (fileTree.getDirCount() < dirCount) {
			// ����ǰĿ¼����С��ָ������������ѭ���͵ݹ����
			ArrayList<FileTree> sons = fileTree.getSons();
			for (FileTree son : sons) {
				trees.addAll(getSpecifiedDirFileTree(son, dirCount));
			}
		} else if (fileTree.getDirCount() == dirCount) {
			// ����������
			trees.add(fileTree);
		}
		return trees;

	}
}
