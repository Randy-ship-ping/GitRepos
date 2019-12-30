package cn.triom.dao;

import java.io.File;
import java.io.IOException;

import cn.triom.util.FileUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 压缩文件类
 * 
 * @author Administrator
 *
 */
public class ZIPFile {
	/**
	 * 压缩文件
	 * 
	 * @param srcFile 原文件
	 * @param desFile 目的文件
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
		// 设定目的文件
		desFile = new File(desFile.getParent() + desFile.getName());
		if (desFile.isDirectory()) {
			desFile = new File(desFile.getPath() + File.separator
					+ (srcFile.isDirectory() ? srcFile.getName() : FileUtil.getFilePrefixName(srcFile)) + ".zip");
		}
		// 初始化压缩文件
		ZipFile zipFile = new ZipFile(desFile);
		// 初始化ZipParameters
		ZipParameters parameters = new ZipParameters();
		// 设置压缩文件的方式
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		// 设置压缩文件的级别
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		// 压缩
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
	 * 压缩文件
	 * 
	 * @param srcFile 原文件
	 * @param desFile 目的文件
	 * @throws ZipException
	 * @throws IOException
	 */
	public void zipFile(File srcFile, File desFile) throws ZipException, IOException {
		zipFile0(srcFile, desFile);
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcFile 原文件路径
	 * @param desFile 目的文件路径
	 * @throws ZipException
	 * @throws IOException
	 */
	public void zipFile(String srcFilePath, String desFilePath) throws ZipException, IOException {
		zipFile0(new File(srcFilePath), new File(desFilePath));
	}
}
