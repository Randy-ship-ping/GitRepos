package cn.triom.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.midi.Sequencer;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * 添加按键监听器
 * 
 * @author Administrator
 *
 */
public class TapKeyListener implements KeyListener {
	// 设备
	private Sequencer player = null;
	// 主窗口
	private MainFrame frame = null;
	// 韵律对象
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
		// 当用户按键时 获取键的字符码并且将其转为int作为音符传入 产生事件
		int keyNum = (int) e.getKeyChar();
		if (keyNum > 0 && keyNum < 128) {
			// 产生钢琴音
			SoundUtil.generateSound(player, 1, keyNum, 100);
			// 向rhythm中添加音符
			this.rhythm.setNotes(this.rhythm.getNotes() + ((char) keyNum));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
