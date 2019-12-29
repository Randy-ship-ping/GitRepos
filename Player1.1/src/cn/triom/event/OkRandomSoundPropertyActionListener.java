package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;
import cn.triom.view.RandomSoundPropertyFrame;

public class OkRandomSoundPropertyActionListener implements ActionListener {
	private MainFrame frame = null;
	private RandomSoundPropertyFrame randomSoundPropertyFrame = null;
	// 韵律实体
	private Rhythm rhythm = null;
	// 频道
	private int channel;
	// 音符个数
	private int yinFuShu;
	// 音量大小
	private int yinLiang;
	// 音长大小
	private int yinChang;
	// 音符范围一
	private int yinFuRangeOne;
	// 音符范围二
	private int yinFuRangeTwo;

	public OkRandomSoundPropertyActionListener(MainFrame frame) {
		this.frame = frame;
	}

	/**
	 * 获取数据
	 */
	private boolean getDataFromFrame() {
		randomSoundPropertyFrame = frame.getRandomSoundFrame();
		// 创建rhythm对象
		rhythm = new Rhythm();
		// 获取用户输入的信息
		String pinDaoStr = randomSoundPropertyFrame.getPinDaoField().getText();
		String yinFuShuStr = randomSoundPropertyFrame.getYinFuShuField().getText();
		String yinLiangStr = randomSoundPropertyFrame.getYinLiangField().getText();
		String yinChangStr = randomSoundPropertyFrame.getYinChangField().getText();
		String yinFuRangeOneStr = randomSoundPropertyFrame.getYinFuRangeOneField().getText();
		String yinFuRangeTwoStr = randomSoundPropertyFrame.getYinFuRangeTwoField().getText();
		// 异常处理
		if (!SoundUtil.isNum(pinDaoStr) || !SoundUtil.isNum(yinFuShuStr) || !SoundUtil.isNum(yinLiangStr)
				|| !SoundUtil.isNum(yinChangStr) || !SoundUtil.isNum(yinFuRangeOneStr)
				|| !SoundUtil.isNum(yinFuRangeTwoStr) || pinDaoStr.length() > 1 || yinFuShuStr.length() > 4
				|| yinLiangStr.length() > 4 || yinChangStr.length() > 2) {
			// 显示错误信息
			JOptionPane.showMessageDialog(randomSoundPropertyFrame, "存在空值或非法值", "错误", JOptionPane.ERROR_MESSAGE);
			// 退出方法
			return false;
		}
		// 将字符串转化为数值
		this.channel = Integer.valueOf(pinDaoStr);
		channel = (channel > 0 && channel < 10) ? channel : 1;
		// 将字符串转化为数值
		this.yinFuShu = Integer.valueOf(yinFuShuStr);
		yinFuShu = (yinFuShu > 0 && yinFuShu < 10000) ? yinFuShu : 100;
		// 将字符串转化为数值
		this.yinLiang = Integer.valueOf(yinLiangStr);
		yinLiang = (yinLiang > 0 && yinLiang < 101) ? yinLiang : 100;
		// 将字符串转化为数值
		this.yinChang = Integer.valueOf(yinChangStr);
		yinChang = (yinChang > 0 && yinChang < 17) ? yinChang : 2;
		// 将字符串转化为数值
		this.yinFuRangeOne = Integer.valueOf(yinFuRangeOneStr);
		yinFuRangeOne = (yinFuRangeOne > -1 && yinFuRangeOne < 128) ? yinFuRangeOne : 0;
		// 将字符串转化为数值
		this.yinFuRangeTwo = Integer.valueOf(yinFuRangeTwoStr);
		yinFuRangeTwo = (yinFuRangeTwo > 0 && yinFuRangeTwo < 128) ? yinFuRangeTwo : 127;
		// 音符范围检验
		if (yinFuRangeOne > yinFuRangeTwo) {
			yinFuRangeOne = 0;
			yinFuRangeTwo = 128;
		}
		return true;
	}

	/**
	 * 设置韵律实体并设置相应的属性
	 */
	private void setData() {
		// 添加韵律实体
		frame.setRandomSoundRhythm(rhythm);
		// 设置韵律实体的频道
		rhythm.setChannel(channel);
		// 设置韵律实体的音长
		rhythm.setYinChang(yinChang);
		// 设置韵律实体的音符个数
		rhythm.setYinFuShu(yinFuShu);
		// 设置韵律实体的音量
		rhythm.setYinLiang(yinLiang);
		// 产生音符并向韵律设置音符
		this.generateRandomNotes();
	}

	/**
	 * 产生音符
	 * 
	 */
	private void generateRandomNotes() {
		// 用于存储随机生成的音符
		String notes = "";
		// 循环来产生用户要求的音符数
		for (int i = 0; i < yinFuShu; i++) {
			// 随机产生音符
			int data = (int) (Math.random() * (yinFuRangeTwo - yinFuRangeOne) + yinFuRangeOne);
			notes += (char) data;
		}
		// 将随机生成的音符放入到韵律对象中
		rhythm.setNotes(notes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取用户输入的数据并向韵律对象中设置数据
		if (this.getDataFromFrame()) {
			this.setData();
			frame.getRandomSoundFrame().getFrame().dispose();
			JOptionPane.showMessageDialog(frame, "设置成功，请点击当前菜单下的开始播放来播放", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
