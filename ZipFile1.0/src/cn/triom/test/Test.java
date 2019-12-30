package cn.triom.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;

@SuppressWarnings("all")
public class Test {

	public static void main(String[] args) throws ZipException, IOException {
		ZipFile zipFile = new ZipFile("F:\\LuanQiBaZao\\Test.zip");
		// 初始化FileHeader
//		FileHeader fileHeader = new FileHeader();
		// 设置FileHeader的文件名字
//		fileHeader.setFileName(fileName);
		// 解压文件
//		zipFile.extractFile(fileHeader, desFile.getPath());
//		List<FileHeader> fileHeaders = zipFile.getFileHeaders();
//		for (FileHeader fileHeader : fileHeaders) {
//			System.out.println(fileHeader.getFileName());
//		}
//		zipFile.extractFile(zipFile.getFileHeader("Test/A.class"), "F:\\LuanQiBaZao\\985623\\123");
//		FileHeader fileHeader = zipFile.getFileHeader("Test/Test.java");
//		ZipInputStream inputStream = zipFile.getInputStream(fileHeader);
//		String filePath = fileHeader.getFileName();
//		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buff = new byte[1024];
//		while (inputStream.read(buff) != -1) {
//			baos.write(buff);
//		}
//		File file = new File("F:\\LuanQiBaZao\\985623\\123\\");
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//		FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\LuanQiBaZao\\985623\\123\\" + fileName));
//		fileOutputStream.write(baos.toByteArray());
		
	}

}
