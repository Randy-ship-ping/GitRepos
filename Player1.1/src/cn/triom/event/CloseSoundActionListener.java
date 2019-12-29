package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

/**
 * �ر��û����ŵĸ�����
 * 
 * @author Administrator
 *
 */
public class CloseSoundActionListener implements ActionListener {
	// �������豸
	private Sequencer player;

	public CloseSoundActionListener(Sequencer player) {
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (player.isOpen()) {
			// �ر��豸
			player.close();
		} else {
			JOptionPane.showMessageDialog(null, "�ر�ʧ��,��ǰδ�����κ�����", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

}
