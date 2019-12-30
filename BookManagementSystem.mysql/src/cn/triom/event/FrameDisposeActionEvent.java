package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * ���ڹرմ����࣬�ͷ���Դ
 * 
 * @author triom
 *
 */
public class FrameDisposeActionEvent implements ActionListener {
	private JFrame frame;

	public FrameDisposeActionEvent(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		if (frame == null) {
			return;
		}
		frame.dispose();
	}

}
