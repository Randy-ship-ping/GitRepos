package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import cn.triom.dao.UnZIPFile;
import cn.triom.util.FileUtil;
import cn.triom.view.MainView;
import net.lingala.zip4j.exception.ZipException;

/**
 * 解压文件
 * 
 * @author Administrator
 *
 */
public class UnZIPFileActionEvent implements ActionListener {
	private UnZIPFile unZIPFile = new UnZIPFile();
	private MainView view;

	public UnZIPFileActionEvent(MainView view) {
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

		// 获取当前打开的zip文件路径
		String zipFilePath = view.getZipFilePath().getText();
		try {
			// 解压文件
			unZIPFile.unZipFile(zipFilePath, desFile.getAbsolutePath());
		} catch (ZipException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 提示
		JOptionPane.showMessageDialog(null, "解压成功", "成功", JOptionPane.INFORMATION_MESSAGE);
	}
}
