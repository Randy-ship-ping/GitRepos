package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * �������ļ�
 * 
 * @author Administrator
 *
 */
public class OpenRhythmFileMenuActionListener implements ActionListener {
	// �ļ�ѡ��
	private JFileChooser chooser;
	// �����
	private MainFrame frame;
	// ���ɶ���
	private Rhythm rhythm;

	public OpenRhythmFileMenuActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �жϵ�ǰ�Ƿ���¼��
		if (frame.isControlRecord()) {
			JOptionPane.showMessageDialog(frame, "����������¼����,��ֹͣ¼�ƺ��ٴ��ļ�");
			return;
		}
		// ѡ���ļ�
		chooser = new JFileChooser("c:");
		// �����ļ�������
		chooser.setFileFilter(new FileNameExtensionFilter("\'zjq\' & \'ZJQ\' �ļ�����", "zjq", "ZJQ"));
		// ���ļ�ѡ����
		chooser.showDialog(new JFrame(), "ѡ���ļ�");
		if (chooser.getSelectedFile() != null) {
			// ��ȡѡ����ļ�
			File file = chooser.getSelectedFile();
			// ��ȡ�ļ�����
			if (file != null) {
				rhythm = SoundUtil.readRhythm(file);
			}
			// ���ļ��л�ȡ���ɶ���
			if (rhythm != null) {
				frame.setFileRhythm(rhythm);
				JOptionPane.showMessageDialog(frame, "�ļ��򿪳ɹ�,�����ļ��˵��еĿ�ʼ����������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "��ȡ�����ļ����Ͳ�ƥ��", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
