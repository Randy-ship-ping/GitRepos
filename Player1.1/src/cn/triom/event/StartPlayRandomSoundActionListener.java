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
 * 开始播放随机钢琴音
 * 
 * @author Administrator
 *
 */
public class StartPlayRandomSoundActionListener implements ActionListener {
	private Sequencer player = null;
	private Rhythm rhythm = null;
	private MainFrame frame = null;

	// 构造方法
	public StartPlayRandomSoundActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取韵律对象
		rhythm = frame.getRandomSoundRhythm();
		// 异常处理
		if (rhythm == null) {
			JOptionPane.showMessageDialog(frame, "未生成任何随机音乐", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		player = frame.getPlayer();
		/**
		 * 添加midi事件并播放
		 */
		try {
			// 打开设备接收任意资源
			player.open();
			// 将sequence的对象放入到sequencer
			player.setSequence(SoundUtil.generateSequence(rhythm.getChannel(), rhythm.getNotes().toCharArray(),
					rhythm.getYinLiang(), rhythm.getYinChang(), rhythm.getYinFuShu()));
			// 从当前序列中播放midi数据
			player.start();
		} catch (MidiUnavailableException | InvalidMidiDataException e1) {
			e1.printStackTrace();
		}

	}

}
