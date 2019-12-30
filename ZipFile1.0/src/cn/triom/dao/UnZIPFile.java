package cn.triom.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;

/**
 * ��ѹ�ļ���
 * 
 * @author Administrator
 *
 */
public class UnZIPFile {
	/**
	 * ��ѹ�ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param desFile ��ѹĿ�ĵ��ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	private static void unZipFile0(File srcFile, File desFile) throws ZipException, IOException {
		if (srcFile == null || desFile == null) {
			return;
		}
		if (!srcFile.exists() || desFile.isFile() || srcFile.isDirectory()) {
			return;
		}
		if (!desFile.exists()) {
			desFile.createNewFile();
		}

		ZipFile zipFile = new ZipFile(srcFile);
		// ��ѹ�ļ�
		zipFile.extractAll(desFile.getPath());
	}

	/**
	 * ��ѹָ�����ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param path    Ҫ��ѹ���ļ�·��
	 * @param desFile Ŀ���ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	private static void unZipSpecifiedFile0(File srcFile, String fileName, File desFile)
			throws ZipException, IOException {
		if (srcFile == null || desFile == null) {
			return;
		}
		if (!srcFile.exists() || desFile.isFile() || srcFile.isDirectory()) {
			return;
		}
		if (!desFile.exists()) {
			desFile.createNewFile();
		}
		// ��ʼ��ZipFile
		ZipFile zipFile = new ZipFile(srcFile);
		// ����filename��ȡfileheader
		FileHeader fileHeader = zipFile.getFileHeader(fileName);
		// ����fileHeader��ȡzip������
		ZipInputStream zipInputStream = zipFile.getInputStream(fileHeader);
		// �����洢zip��������ȡ������
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		// ��ȡ�ļ�����
		String name = fileName.substring(fileName.lastIndexOf("/") + 1);
		// д��������д���ļ�
		FileOutputStream fileOutputStream = new FileOutputStream(desFile.getAbsoluteFile() + File.separator + name);

		// ������
		byte[] buff = new byte[1024];

		// ��ȡ�ļ�
		while (zipInputStream.read(buff) != -1) {
			// �����������ļ�д�뵽�ֽ��������
			byteArrayOutputStream.write(buff);
		}
		// д������
		fileOutputStream.write(byteArrayOutputStream.toByteArray());

		// �ر���
		fileOutputStream.close();
		byteArrayOutputStream.close();
		zipInputStream.close();

	}

	/**
	 * ��ѹ�ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param desFile ��ѹĿ�ĵ��ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipFile(File srcFile, File desFile) throws ZipException, IOException {
		unZipFile0(srcFile, desFile);
	}

	/**
	 * ��ѹ�ļ�
	 * 
	 * @param srcFile ԭ�ļ�·��
	 * @param desFile ��ѹĿ�ĵ��ļ�·��
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipFile(String srcFilePath, String desFilePath) throws ZipException, IOException {
		unZipFile0(new File(srcFilePath), new File(desFilePath));
	}

	/**
	 * ��ѹָ�����ļ�
	 * 
	 * @param srcFile ԭ�ļ�
	 * @param path    Ҫ��ѹ���ļ�·��
	 * @param desFile Ŀ���ļ�
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipSpecifiedFile(File srcFile, String path, File desFile) throws ZipException, IOException {
		unZipSpecifiedFile0(srcFile, path, desFile);
	}

	/**
	 * ��ѹָ�����ļ�
	 * 
	 * @param srcPath ԭ�ļ�·��
	 * @param path    ָ���ļ�·��
	 * @param desFile Ŀ���ļ�·��
	 * @throws ZipException
	 * @throws IOException
	 */

	public void unZipSpecifiedFile(String srcPath, String path, String desFile) throws ZipException, IOException {
		unZipSpecifiedFile0(new File(srcPath), path, new File(desFile));
	}

}
