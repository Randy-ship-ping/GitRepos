package cn.triom.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.midi.Sequencer;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * ��Ӱ���������
 * 
 * @author Administrator
 *
 */
public class TapKeyListener implements KeyListener {
	// �豸
	private Sequencer player = null;
	// ������
	private MainFrame frame = null;
	// ���ɶ���
	private Rhythm rhythm = null;

	public TapKeyListener(MainFrame frame, Sequencer player) {
		this.frame = frame;
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		rhythm = frame.getFileRhythm();
		// ���û�����ʱ ��ȡ�����ַ��벢�ҽ���תΪint��Ϊ�������� �����¼�
		int keyNum = (int) e.getKeyChar();
		if (keyNum > 0 && keyNum < 128) {
			// ����������
			SoundUtil.generateSound(player, 1, keyNum, 100);
			// ��rhythm���������
			this.rhythm.setNotes(this.rhythm.getNotes() + ((char) keyNum));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
