package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Sequencer;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * ����¼�Ƹ�����
 * 
 * @author Administrator
 *
 */
public class ControlRecordActionListener implements ActionListener {
	// �����
	private MainFrame frame = null;
	// ����������
	private TapKeyListener tapKeyListener = null;

	public ControlRecordActionListener(MainFrame frame, Sequencer player) {
		this.frame = frame;
		this.tapKeyListener = new TapKeyListener(this.frame, player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ȡ�û�����Ĳ˵���
		JMenuItem item = (JMenuItem) e.getSource();
		// ��ȡ�û�����Ĳ˵��������
		String name = item.getText();
		// �����û������ѡ�������ƿ�ʼ��ֹͣ
		if (name.equals("��ʼ¼��") && !frame.isControlRecord()) {
			// ����������������
			frame.setFileRhythm(new Rhythm(1, "", 100, 2, 0));
			// ��Ӱ���������
			frame.addKeyListener(tapKeyListener);
			// ��ʾ��ʼ¼��
			JOptionPane.showMessageDialog(frame, "¼�ƿ�ʼ,����������ģ�¸�����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			// ��ʼ¼��
			frame.setControlRecord(true);
		} else if (name.equals("ֹͣ¼��") && frame.isControlRecord()) {
			// ����������
			frame.getFileRhythm().setYinFuShu(frame.getFileRhythm().getNotes().length());
			// ȥ��������
			frame.addKeyListener(null);
			// ֹͣ¼��
			frame.setControlRecord(false);
			// ���û����ֹͣ¼��ʱѯ���Ƿ񱣴�
			if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴��ļ�¼���ļ�", "��ʾ",
					JOptionPane.OK_CANCEL_OPTION)) {
				// �����ļ�
				if (SoundUtil.saveRhythmFile(frame.getFileRhythm())) {
					JOptionPane.showMessageDialog(frame, "����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					frame.setFileRhythm(null);
				} else {
					JOptionPane.showMessageDialog(frame, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// ��ʾ����һ�ַ�ʽ�����ļ�
				JOptionPane.showMessageDialog(frame, "��ͨ������ò˵��µı���¼���������ļ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
