package cn.triom.dao;

import java.io.File;
import java.io.IOException;

import cn.triom.util.FileUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * ѹ���ļ���
 * 
 * @author Administrator
 *
 */
public class ZIPFile {
	/**
	 * ѹ���ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param desFile Ŀ���ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	private static void zipFile0(File srcFile, File desFile) throws ZipException, IOException {
		if (srcFile == null || desFile == null) {
			return;
		}
		if (!srcFile.exists()) {
			return;
		}
		if (!desFile.exists()) {
			desFile.createNewFile();
		}
		// �趨Ŀ���ļ�
		desFile = new File(desFile.getParent() + desFile.getName());
		if (desFile.isDirectory()) {
			desFile = new File(desFile.getPath() + File.separator
					+ (srcFile.isDirectory() ? srcFile.getName() : FileUtil.getFilePrefixName(srcFile)) + ".zip");
		}
		// ��ʼ��ѹ���ļ�
		ZipFile zipFile = new ZipFile(desFile);
		// ��ʼ��ZipParameters
		ZipParameters parameters = new ZipParameters();
		// ����ѹ���ļ��ķ�ʽ
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		// ����ѹ���ļ��ļ���
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		// ѹ��
		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					zipFile.addFolder(f.getPath(), parameters);
				} else {
					zipFile.addFile(f, parameters);
				}
			}
		} else {
			zipFile.addFile(srcFile, parameters);
		}
	}
	
	private static void addFileToZipFile(File srcFile, File file) throws ZipException {

	}

	/**
	 * ѹ���ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param desFile Ŀ���ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	public void zipFile(File srcFile, File desFile) throws ZipException, IOException {
		zipFile0(srcFile, desFile);
	}

	/**
	 * ѹ���ļ�
	 * 
	 * @param srcFile ԭ�ļ�·��
	 * @param desFile Ŀ���ļ�·��
	 * @throws ZipException
	 * @throws IOException
	 */
	public void zipFile(String srcFilePath, String desFilePath) throws ZipException, IOException {
		zipFile0(new File(srcFilePath), new File(desFilePath));
	}
}
