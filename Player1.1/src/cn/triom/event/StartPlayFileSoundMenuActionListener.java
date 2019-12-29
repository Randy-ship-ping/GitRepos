package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * ���Ŵ򿪵������ļ�
 * 
 * @author Administrator
 *
 */
public class StartPlayFileSoundMenuActionListener implements ActionListener {
	// �洢�豸
	private Sequencer player = null;
	// ��ȡ�����
	private MainFrame frame = null;
	// ����ʵ��
	private Rhythm rhythm = null;
	// Ƶ��
	private int channel;
	// ����
	private int yinLiang;
	// ����
	private int yinChang;
	// ������
	private int yinFuShu;
	// �洢����������
	private char[] notes = null;

	public StartPlayFileSoundMenuActionListener(MainFrame frame, Sequencer player) {
		this.player = player;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ȡ���ɶ���
		rhythm = frame.getFileRhythm();
		// ������Ϊ������ʾ��Ϣ��ֱ�ӷ���
		if (rhythm == null) {
			JOptionPane.showMessageDialog(frame, "δ�����κ��ļ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡ���ֵ�һЩ����
		channel = rhythm.getChannel();
		yinLiang = rhythm.getYinLiang();
		yinChang = rhythm.getYinChang();
		yinFuShu = rhythm.getYinFuShu();
		// ��ȡ����
		notes = new String(rhythm.getNotes()).toCharArray();
		try {
			// �����豸
			player.open();
			// ���豸������sequence
			player.setSequence(SoundUtil.generateSequence(channel, notes, yinLiang, yinChang, yinFuShu));
			// ��ʾ�û�
			JOptionPane.showMessageDialog(frame, "���ֿ�ʼ����,�ɵ�������е�ֹͣ������ֹͣ", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			// ��ʼ����
			player.start();
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
	}
}
