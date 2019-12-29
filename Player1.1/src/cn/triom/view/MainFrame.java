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
 * 播放主界面
 * 
 * @author Administrator
 *
 */
public class MainFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	// 组件
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
	// 存储设备
	private Sequencer player = null;
	// 存储从文件中读取的韵律对象
	private Rhythm fileRhythm = null;
	// 存储随机生成的韵律对象
	private Rhythm randomSoundRhythm = null;
	private RandomSoundPropertyFrame randomSoundFrame = null;
	// 控制是否开启录制
	private boolean controlRecord = false;

	public MainFrame() {
		// 组件初始化
		frame = new JFrame("测试");
		menuBar = new JMenuBar();
		fileMenus = new JMenu("文件");
		functionMenu = new JMenu("功能");
		randomGenerateSoundMenu = new JMenu("随机产生钢琴音");
		openMenuItem = new JMenuItem("打开");
		saveRecordMenuItem = new JMenuItem("保存录制");
		exitMenuItem = new JMenuItem("退出");
		startPlayMenuItem = new JMenuItem("开始播放");
		stopPlayMenuItem = new JMenuItem("停止播放");
		startRecordMenuItem = new JMenuItem("开始录制");
		stopRecordMenuItem = new JMenuItem("停止录制");
		setRandomSoundPropertyMenuItem = new JMenuItem("设置随机钢琴音的属性");
		startPlayRandomSoundMenuItem = new JMenuItem("开始播放随机钢琴音");
		stopPlayRandomSoundMenuItem = new JMenuItem("停止播放随机钢琴音");
		saveRandomSoundMenuItem = new JMenuItem("保存播放随机钢琴音");

		// 初始化设备
		try {
			player = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}

		// 组件设置
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
		// 添加事件
		this.setEvent();
	}

	// 为菜单项设置事件
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
				// 保存文件
				if (SoundUtil.saveRhythmFile(fileRhythm)) {
					JOptionPane.showMessageDialog(frame, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					fileRhythm = null;
				} else {
					JOptionPane.showMessageDialog(frame, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
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
				// 保存文件
				if (SoundUtil.saveRhythmFile(randomSoundRhythm)) {
					JOptionPane.showMessageDialog(frame, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					randomSoundRhythm = null;
				} else {
					JOptionPane.showMessageDialog(frame, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
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
