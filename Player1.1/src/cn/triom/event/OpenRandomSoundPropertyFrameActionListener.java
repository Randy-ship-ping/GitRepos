package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.triom.view.MainFrame;
import cn.triom.view.RandomSoundPropertyFrame;

/**
 * ���õ��������������������������ʱ��ʼ������������Խ���
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
		// ��ʼ��
		frame.setRandomSoundFrame(new RandomSoundPropertyFrame(frame));
	}

}
