package cn.triom.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.triom.entity.Rhythm;
import cn.triom.event.CloseSoundActionListener;
import cn.triom.event.ControlRecordActionListener;
import cn.triom.event.OpenRandomSoundPropertyFrameActionListener;
import cn.triom.event.OpenRhythmFileMenuActionListener;
import cn.triom.event.StartPlayFileSoundMenuActionListener;
import cn.triom.event.StartPlayRandomSoundActionListener;
import cn.triom.util.SoundUtil;

/**
 * ����������
 * 
 * @author Administrator
 *
 */
public class MainFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	// ���
	private JFrame frame = null;
	private JMenuBar menuBar = null;
	private JMenu fileMenus = null;
	private JMenu functionMenu = null;
	private JMenu randomGenerateSoundMenu = null;
	private JMenuItem openMenuItem = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem startPlayMenuItem = null;
	private JMenuItem stopPlayMenuItem = null;
	private JMenuItem startRecordMenuItem = null;
	private JMenuItem stopRecordMenuItem = null;
	private JMenuItem saveRecordMenuItem = null;
	private JMenuItem setRandomSoundPropertyMenuItem = null;
	private JMenuItem startPlayRandomSoundMenuItem = null;
	private JMenuItem stopPlayRandomSoundMenuItem = null;
	private JMenuItem saveRandomSoundMenuItem = null;
	// �洢�豸
	private Sequencer player = null;
	// �洢���ļ��ж�ȡ�����ɶ���
	private Rhythm fileRhythm = null;
	// �洢������ɵ����ɶ���
	private Rhythm randomSoundRhythm = null;
	private RandomSoundPropertyFrame randomSoundFrame = null;
	// �����Ƿ���¼��
	private boolean controlRecord = false;

	public MainFrame() {
		// �����ʼ��
		frame = new JFrame("����");
		menuBar = new JMenuBar();
		fileMenus = new JMenu("�ļ�");
		functionMenu = new JMenu("����");
		randomGenerateSoundMenu = new JMenu("�������������");
		openMenuItem = new JMenuItem("��");
		saveRecordMenuItem = new JMenuItem("����¼��");
		exitMenuItem = new JMenuItem("�˳�");
		startPlayMenuItem = new JMenuItem("��ʼ����");
		stopPlayMenuItem = new JMenuItem("ֹͣ����");
		startRecordMenuItem = new JMenuItem("��ʼ¼��");
		stopRecordMenuItem = new JMenuItem("ֹͣ¼��");
		setRandomSoundPropertyMenuItem = new JMenuItem("�������������������");
		startPlayRandomSoundMenuItem = new JMenuItem("��ʼ�������������");
		stopPlayRandomSoundMenuItem = new JMenuItem("ֹͣ�������������");
		saveRandomSoundMenuItem = new JMenuItem("���沥�����������");

		// ��ʼ���豸
		try {
			player = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}

		// �������
		frame.setBounds(400, 100, 500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.add(this);
		frame.validate();

		this.setBounds(0, 0, 500, 500);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.requestFocus();
		this.add(menuBar);

		menuBar.setBounds(0, 0, 500, 30);
		menuBar.setLayout(null);
		menuBar.add(fileMenus);
		menuBar.add(functionMenu);
		menuBar.add(randomGenerateSoundMenu);

		fileMenus.setBounds(5, 2, 45, 25);
		fileMenus.add(openMenuItem);
		fileMenus.add(startPlayMenuItem);
		fileMenus.add(stopPlayMenuItem);
		fileMenus.add(exitMenuItem);
		functionMenu.setBounds(55, 2, 45, 25);
		functionMenu.add(startRecordMenuItem);
		functionMenu.add(stopRecordMenuItem);
		functionMenu.add(saveRecordMenuItem);
		randomGenerateSoundMenu.setBounds(105, 2, 100, 25);
		randomGenerateSoundMenu.add(setRandomSoundPropertyMenuItem);
		randomGenerateSoundMenu.add(startPlayRandomSoundMenuItem);
		randomGenerateSoundMenu.add(stopPlayRandomSoundMenuItem);
		randomGenerateSoundMenu.add(saveRandomSoundMenuItem);
		setRandomSoundPropertyMenuItem.addActionListener(new OpenRandomSoundPropertyFrameActionListener(this));
		// ����¼�
		this.setEvent();
	}

	// Ϊ�˵��������¼�
	public void setEvent() {
		CloseSoundActionListener closeSoundActionListener = new CloseSoundActionListener(player);
		openMenuItem.addActionListener(new OpenRhythmFileMenuActionListener(this));
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		startPlayMenuItem.addActionListener(new StartPlayFileSoundMenuActionListener(this, player));
		stopPlayMenuItem.addActionListener(closeSoundActionListener);
		saveRecordMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �����ļ�
				if (SoundUtil.saveRhythmFile(fileRhythm)) {
					JOptionPane.showMessageDialog(frame, "����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					fileRhythm = null;
				} else {
					JOptionPane.showMessageDialog(frame, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		startRecordMenuItem.addActionListener(new ControlRecordActionListener(this, player));
		stopRecordMenuItem.addActionListener(new ControlRecordActionListener(this, player));
		startPlayRandomSoundMenuItem.addActionListener(new StartPlayRandomSoundActionListener(this));
		stopPlayRandomSoundMenuItem.addActionListener(closeSoundActionListener);
		saveRandomSoundMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �����ļ�
				if (SoundUtil.saveRhythmFile(randomSoundRhythm)) {
					JOptionPane.showMessageDialog(frame, "����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					randomSoundRhythm = null;
				} else {
					JOptionPane.showMessageDialog(frame, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public Rhythm getFileRhythm() {
		return fileRhythm;
	}

	public void setFileRhythm(Rhythm fileRhythm) {
		this.fileRhythm = fileRhythm;
	}

	public Rhythm getRandomSoundRhythm() {
		return randomSoundRhythm;
	}

	public void setRandomSoundRhythm(Rhythm randomSoundRhythm) {
		this.randomSoundRhythm = randomSoundRhythm;
	}

	public boolean isControlRecord() {
		return controlRecord;
	}

	public void setControlRecord(boolean controlRecord) {
		this.controlRecord = controlRecord;
	}

	public void setRandomSoundFrame(RandomSoundPropertyFrame randomSoundFrame) {
		this.randomSoundFrame = randomSoundFrame;
	}

	public RandomSoundPropertyFrame getRandomSoundFrame() {
		return this.randomSoundFrame;
	}

	public Sequencer getPlayer() {
		return player;
	}
}
