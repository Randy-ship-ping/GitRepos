package cn.triom.event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.midi.Sequencer;

/**
 * 为窗口设置关闭事件
 * 
 * @author Administrator
 *
 */
public class CloseFrameWindowAction implements WindowListener {
	private Sequencer player;

	public CloseFrameWindowAction(Sequencer player) {
		this.player = player;
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		player.close();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}
}
