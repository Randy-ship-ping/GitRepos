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
 * ��ʼ�������������
 * 
 * @author Administrator
 *
 */
public class StartPlayRandomSoundActionListener implements ActionListener {
	private Sequencer player = null;
	private Rhythm rhythm = null;
	private MainFrame frame = null;

	// ���췽��
	public StartPlayRandomSoundActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ȡ���ɶ���
		rhythm = frame.getRandomSoundRhythm();
		// �쳣����
		if (rhythm == null) {
			JOptionPane.showMessageDialog(frame, "δ�����κ��������", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		player = frame.getPlayer();
		/**
		 * ���midi�¼�������
		 */
		try {
			// ���豸����������Դ
			player.open();
			// ��sequence�Ķ�����뵽sequencer
			player.setSequence(SoundUtil.generateSequence(rhythm.getChannel(), rhythm.getNotes().toCharArray(),
					rhythm.getYinLiang(), rhythm.getYinChang(), rhythm.getYinFuShu()));
			// �ӵ�ǰ�����в���midi����
			player.start();
		} catch (MidiUnavailableException | InvalidMidiDataException e1) {
			e1.printStackTrace();
		}

	}

}
