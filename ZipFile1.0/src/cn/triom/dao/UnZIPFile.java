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
 * 加压文件类
 * 
 * @author Administrator
 *
 */
public class UnZIPFile {
	/**
	 * 解压文件
	 * 
	 * @param srcFile 原文件
	 * @param desFile 解压目的地文件
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
		// 解压文件
		zipFile.extractAll(desFile.getPath());
	}

	/**
	 * 解压指定的文件
	 * 
	 * @param srcFile 原文件
	 * @param path    要解压的文件路径
	 * @param desFile 目的文件
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
		// 初始化ZipFile
		ZipFile zipFile = new ZipFile(srcFile);
		// 根据filename获取fileheader
		FileHeader fileHeader = zipFile.getFileHeader(fileName);
		// 根据fileHeader获取zip输入流
		ZipInputStream zipInputStream = zipFile.getInputStream(fileHeader);
		// 用来存储zip输入流读取的数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		// 获取文件名字
		String name = fileName.substring(fileName.lastIndexOf("/") + 1);
		// 写出流用来写出文件
		FileOutputStream fileOutputStream = new FileOutputStream(desFile.getAbsoluteFile() + File.separator + name);

		// 缓存区
		byte[] buff = new byte[1024];

		// 读取文件
		while (zipInputStream.read(buff) != -1) {
			// 将缓存区的文件写入到字节输出流中
			byteArrayOutputStream.write(buff);
		}
		// 写出数据
		fileOutputStream.write(byteArrayOutputStream.toByteArray());

		// 关闭流
		fileOutputStream.close();
		byteArrayOutputStream.close();
		zipInputStream.close();

	}

	/**
	 * 解压文件
	 * 
	 * @param srcFile 原文件
	 * @param desFile 解压目的地文件
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipFile(File srcFile, File desFile) throws ZipException, IOException {
		unZipFile0(srcFile, desFile);
	}

	/**
	 * 解压文件
	 * 
	 * @param srcFile 原文件路径
	 * @param desFile 解压目的地文件路径
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipFile(String srcFilePath, String desFilePath) throws ZipException, IOException {
		unZipFile0(new File(srcFilePath), new File(desFilePath));
	}

	/**
	 * 解压指定的文件
	 * 
	 * @param srcFile 原文件
	 * @param path    要解压的文件路径
	 * @param desFile 目的文件
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unZipSpecifiedFile(File srcFile, String path, File desFile) throws ZipException, IOException {
		unZipSpecifiedFile0(srcFile, path, desFile);
	}

	/**
	 * 解压指定的文件
	 * 
	 * @param srcPath 原文件路径
	 * @param path    指定文件路径
	 * @param desFile 目的文件路径
	 * @throws ZipException
	 * @throws IOException
	 */

	public void unZipSpecifiedFile(String srcPath, String path, String desFile) throws ZipException, IOException {
		unZipSpecifiedFile0(new File(srcPath), path, new File(desFile));
	}

}
