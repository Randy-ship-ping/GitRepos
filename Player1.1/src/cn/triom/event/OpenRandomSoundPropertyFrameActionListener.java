package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.triom.view.MainFrame;
import cn.triom.view.RandomSoundPropertyFrame;

/**
 * 当用点击主界面的设置随机钢琴音属性时初始化随机钢琴属性界面
 * 
 * @author Administrator
 *
 */
public class OpenRandomSoundPropertyFrameActionListener implements ActionListener {
	private MainFrame frame = null;

	public OpenRandomSoundPropertyFrameActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 初始化
		frame.setRandomSoundFrame(new RandomSoundPropertyFrame(frame));
	}

}
