package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

/**
 * 关闭用户播放的钢琴音
 * 
 * @author Administrator
 *
 */
public class CloseSoundActionListener implements ActionListener {
	// 开启的设备
	private Sequencer player;

	public CloseSoundActionListener(Sequencer player) {
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (player.isOpen()) {
			// 关闭设备
			player.close();
		} else {
			JOptionPane.showMessageDialog(null, "关闭失败,当前未播放任何音乐", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

}
