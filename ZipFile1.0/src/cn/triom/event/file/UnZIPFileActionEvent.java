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
 * ��ѹ�ļ�
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
		// �жϵ�ǰ�Ƿ����ļ���
		if (view.getDirCount() == 0) {
			JOptionPane.showMessageDialog(null, "��ǰδ���κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ѡ�񱣴�Ŀ�ĵ��ļ�
		File desFile = FileUtil.saveFile();
		// �쳣����
		if (desFile == null) {
			return;
		}
		if (desFile.isFile()) {
			JOptionPane.showMessageDialog(null, "����ѡ���ļ���", "����", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			if (!desFile.exists()) {
				desFile.mkdirs();
			}
		}

		// ��ȡ��ǰ�򿪵�zip�ļ�·��
		String zipFilePath = view.getZipFilePath().getText();
		try {
			// ��ѹ�ļ�
			unZIPFile.unZipFile(zipFilePath, desFile.getAbsolutePath());
		} catch (ZipException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ��ʾ
		JOptionPane.showMessageDialog(null, "��ѹ�ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
	}
}
