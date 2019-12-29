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
 * 播放打开的韵律文件
 * 
 * @author Administrator
 *
 */
public class StartPlayFileSoundMenuActionListener implements ActionListener {
	// 存储设备
	private Sequencer player = null;
	// 获取主框架
	private MainFrame frame = null;
	// 韵律实体
	private Rhythm rhythm = null;
	// 频道
	private int channel;
	// 音量
	private int yinLiang;
	// 音长
	private int yinChang;
	// 音符数
	private int yinFuShu;
	// 存储所有音符数
	private char[] notes = null;

	public StartPlayFileSoundMenuActionListener(MainFrame frame, Sequencer player) {
		this.player = player;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取韵律对象
		rhythm = frame.getFileRhythm();
		// 若韵律为空则提示信息并直接返回
		if (rhythm == null) {
			JOptionPane.showMessageDialog(frame, "未加载任何文件", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取音乐的一些属性
		channel = rhythm.getChannel();
		yinLiang = rhythm.getYinLiang();
		yinChang = rhythm.getYinChang();
		yinFuShu = rhythm.getYinFuShu();
		// 获取音符
		notes = new String(rhythm.getNotes()).toCharArray();
		try {
			// 开启设备
			player.open();
			// 向设备中设置sequence
			player.setSequence(SoundUtil.generateSequence(channel, notes, yinLiang, yinChang, yinFuShu));
			// 提示用户
			JOptionPane.showMessageDialog(frame, "音乐开始播放,可点击功能中的停止播放来停止", "提示", JOptionPane.INFORMATION_MESSAGE);
			// 开始播放
			player.start();
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
	}
}
