package cn.triom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.triom.entity.Rhythm;

public class SoundUtil {
	// 文件选择
	private static final JFileChooser chooser = new JFileChooser("c:");

	// 不让其初始化
	private SoundUtil() {
	}

	/**
	 * 创建音频事件
	 * 
	 * @param command 类型 信息类型 144打开 128 关闭
	 * @param channel 频道 演奏者 例如：钢琴，吉他
	 * @param data1   音符 0-127代表不同音高
	 * @param data2   音道 声音高低
	 * @param tick    音长
	 * @return 返回MidIEvent
	 */

	public static MidiEvent generateMidiEvent(int command, int channel, int data1, int data2, int tick) {
		ShortMessage message = null;
		MidiEvent event = null;
		try {
			message = new ShortMessage();
			message.setMessage(command, channel, data1, data2);
			event = new MidiEvent(message, tick);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		return event;
	}

	/**
	 * 获取一个sequence
	 * 
	 * @param channel  频道
	 * @param notes    音符
	 * @param yinLiang 音量
	 * @param yinChang 音长
	 * @return 返回一个sequence
	 * @throws InvalidMidiDataException
	 */
	public static Sequence generateSequence(int channel, char[] notes, int yinLiang, int yinChang, int yinFuShu)
			throws InvalidMidiDataException {
		// 用来判断当前获取第几个音符
		int note = 0;
		// 要返回的sequence
		Sequence seq = new Sequence(Sequence.PPQ, 4);
		// 轨迹
		Track track = seq.createTrack();

		// 循环向轨迹中添加midi事件
		for (int i = 0; i < yinFuShu * yinChang; i += yinChang) {
			track.add(SoundUtil.generateMidiEvent(144, channel, (int) notes[note], yinLiang, i));
			track.add(SoundUtil.generateMidiEvent(128, channel, (int) notes[note], yinLiang, i + yinChang));
			note++;
		}
		return seq;
	}

	/**
	 * 产生音频
	 * 
	 * @param player  Sequencer
	 * @param channel 频道
	 * @param data1   音符
	 * @param data2   声音大小
	 */
	public static void generateSound(Sequencer player, int channel, int data1, int data2) {
		try {
			player.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			track.add(SoundUtil.generateMidiEvent(144, channel, data1, data2, 0));
			track.add(SoundUtil.generateMidiEvent(128, channel, data1, data2, 2));
			player.setSequence(seq);
			player.start();
		} catch (InvalidMidiDataException | MidiUnavailableException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 检查字符串是否全是数字
	 * 
	 * @param str 要检查的字符串
	 * @return true:是;false:不是
	 */
	public static boolean isNum(String str) {
		boolean flag = true;
		if (str == null || str.equals("")) {
			flag = false;
		} else {
			char[] chars = str.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				if (chars[i] < '0' || chars[i] > '9') {
					flag = false;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 将韵律对象写入到文件中来保存数据
	 * 
	 * @param file   要保存的目的地
	 * @param rhythm 要保存的对象
	 */
	public static void writeRhythm(File file, Rhythm rhythm) {
		// 异常处理
		if (file == null || rhythm == null || !checkRhythm(file)) {
			return;
		}
		// 输出流
		ObjectOutputStream oos = null;
		try {
			// 写入数据
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(rhythm);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从文件中读取韵律
	 * 
	 * @param file 要读取的文件
	 * @return 韵律实体
	 */
	public static Rhythm readRhythm(File file) {
		// 存储韵律实体
		Rhythm rhythm = null;
		// 输入流
		ObjectInputStream ois = null;
		// 异常检查
		if (!(file == null) && file.exists() && checkRhythm(file)) {
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				rhythm = (Rhythm) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// 关闭流
				try {
					if (ois != null)
						ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rhythm;
	}

	/**
	 * 检查韵律文件名是否合法
	 * 
	 * @param fileName 要检查的文件名
	 * @return true:合法；false：不合法
	 */
	private static boolean checkRhythm(String path) {
		boolean flag = true;
		if (path == null || path.equals("")) {
			flag = false;
		} else {
			String suffix = path.substring(path.lastIndexOf(".") + 1);
			flag = (suffix.equalsIgnoreCase("zjq")) ? true : false;
		}
		return flag;
	}

	/**
	 * 检查韵律文件是否合法
	 * 
	 * @param file 要检查的文件
	 * @return true:合法；false：不合法
	 */
	public static boolean checkRhythm(File file) {
		return checkRhythm(file.getPath());
	}

	/**
	 * 保存韵律文件
	 * 
	 * @param rhythm 要保存的韵律对象
	 * @return true:保存成功 ; false:保存失败
	 */
	public static boolean saveRhythmFile(Rhythm rhythm) {
		// 返回值
		boolean flag = true;
		// 异常检验
		if (rhythm == null) {
			return false;
		}
		// 设置文件过滤器
		chooser.setFileFilter(new FileNameExtensionFilter("\'zjq\'&\'ZJQ\'文件类型", "zjq", "ZJQ"));
		// 显示保存窗口
		chooser.showSaveDialog(new JFrame());
		// 获取用户保存的文件
		File file = chooser.getSelectedFile();
		// 保存文件
		if (file != null && SoundUtil.checkRhythm(file)) {
			// 将韵律对象写入到文件中
			SoundUtil.writeRhythm(file, rhythm);
		} else {
			flag = false;
		}
		return flag;
	}
}
