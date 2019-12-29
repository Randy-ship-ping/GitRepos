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
	// �ļ�ѡ��
	private static final JFileChooser chooser = new JFileChooser("c:");

	// �������ʼ��
	private SoundUtil() {
	}

	/**
	 * ������Ƶ�¼�
	 * 
	 * @param command ���� ��Ϣ���� 144�� 128 �ر�
	 * @param channel Ƶ�� ������ ���磺���٣�����
	 * @param data1   ���� 0-127����ͬ����
	 * @param data2   ���� �����ߵ�
	 * @param tick    ����
	 * @return ����MidIEvent
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
	 * ��ȡһ��sequence
	 * 
	 * @param channel  Ƶ��
	 * @param notes    ����
	 * @param yinLiang ����
	 * @param yinChang ����
	 * @return ����һ��sequence
	 * @throws InvalidMidiDataException
	 */
	public static Sequence generateSequence(int channel, char[] notes, int yinLiang, int yinChang, int yinFuShu)
			throws InvalidMidiDataException {
		// �����жϵ�ǰ��ȡ�ڼ�������
		int note = 0;
		// Ҫ���ص�sequence
		Sequence seq = new Sequence(Sequence.PPQ, 4);
		// �켣
		Track track = seq.createTrack();

		// ѭ����켣�����midi�¼�
		for (int i = 0; i < yinFuShu * yinChang; i += yinChang) {
			track.add(SoundUtil.generateMidiEvent(144, channel, (int) notes[note], yinLiang, i));
			track.add(SoundUtil.generateMidiEvent(128, channel, (int) notes[note], yinLiang, i + yinChang));
			note++;
		}
		return seq;
	}

	/**
	 * ������Ƶ
	 * 
	 * @param player  Sequencer
	 * @param channel Ƶ��
	 * @param data1   ����
	 * @param data2   ������С
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
	 * ����ַ����Ƿ�ȫ������
	 * 
	 * @param str Ҫ�����ַ���
	 * @return true:��;false:����
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
	 * �����ɶ���д�뵽�ļ�������������
	 * 
	 * @param file   Ҫ�����Ŀ�ĵ�
	 * @param rhythm Ҫ����Ķ���
	 */
	public static void writeRhythm(File file, Rhythm rhythm) {
		// �쳣����
		if (file == null || rhythm == null || !checkRhythm(file)) {
			return;
		}
		// �����
		ObjectOutputStream oos = null;
		try {
			// д������
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(rhythm);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر���
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���ļ��ж�ȡ����
	 * 
	 * @param file Ҫ��ȡ���ļ�
	 * @return ����ʵ��
	 */
	public static Rhythm readRhythm(File file) {
		// �洢����ʵ��
		Rhythm rhythm = null;
		// ������
		ObjectInputStream ois = null;
		// �쳣���
		if (!(file == null) && file.exists() && checkRhythm(file)) {
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				rhythm = (Rhythm) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// �ر���
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
	 * ��������ļ����Ƿ�Ϸ�
	 * 
	 * @param fileName Ҫ�����ļ���
	 * @return true:�Ϸ���false�����Ϸ�
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
	 * ��������ļ��Ƿ�Ϸ�
	 * 
	 * @param file Ҫ�����ļ�
	 * @return true:�Ϸ���false�����Ϸ�
	 */
	public static boolean checkRhythm(File file) {
		return checkRhythm(file.getPath());
	}

	/**
	 * ���������ļ�
	 * 
	 * @param rhythm Ҫ��������ɶ���
	 * @return true:����ɹ� ; false:����ʧ��
	 */
	public static boolean saveRhythmFile(Rhythm rhythm) {
		// ����ֵ
		boolean flag = true;
		// �쳣����
		if (rhythm == null) {
			return false;
		}
		// �����ļ�������
		chooser.setFileFilter(new FileNameExtensionFilter("\'zjq\'&\'ZJQ\'�ļ�����", "zjq", "ZJQ"));
		// ��ʾ���洰��
		chooser.showSaveDialog(new JFrame());
		// ��ȡ�û�������ļ�
		File file = chooser.getSelectedFile();
		// �����ļ�
		if (file != null && SoundUtil.checkRhythm(file)) {
			// �����ɶ���д�뵽�ļ���
			SoundUtil.writeRhythm(file, rhythm);
		} else {
			flag = false;
		}
		return flag;
	}
}
