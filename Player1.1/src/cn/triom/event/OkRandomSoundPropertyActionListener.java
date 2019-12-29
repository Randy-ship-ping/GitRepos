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
	// ����ʵ��
	private Rhythm rhythm = null;
	// Ƶ��
	private int channel;
	// ��������
	private int yinFuShu;
	// ������С
	private int yinLiang;
	// ������С
	private int yinChang;
	// ������Χһ
	private int yinFuRangeOne;
	// ������Χ��
	private int yinFuRangeTwo;

	public OkRandomSoundPropertyActionListener(MainFrame frame) {
		this.frame = frame;
	}

	/**
	 * ��ȡ����
	 */
	private boolean getDataFromFrame() {
		randomSoundPropertyFrame = frame.getRandomSoundFrame();
		// ����rhythm����
		rhythm = new Rhythm();
		// ��ȡ�û��������Ϣ
		String pinDaoStr = randomSoundPropertyFrame.getPinDaoField().getText();
		String yinFuShuStr = randomSoundPropertyFrame.getYinFuShuField().getText();
		String yinLiangStr = randomSoundPropertyFrame.getYinLiangField().getText();
		String yinChangStr = randomSoundPropertyFrame.getYinChangField().getText();
		String yinFuRangeOneStr = randomSoundPropertyFrame.getYinFuRangeOneField().getText();
		String yinFuRangeTwoStr = randomSoundPropertyFrame.getYinFuRangeTwoField().getText();
		// �쳣����
		if (!SoundUtil.isNum(pinDaoStr) || !SoundUtil.isNum(yinFuShuStr) || !SoundUtil.isNum(yinLiangStr)
				|| !SoundUtil.isNum(yinChangStr) || !SoundUtil.isNum(yinFuRangeOneStr)
				|| !SoundUtil.isNum(yinFuRangeTwoStr) || pinDaoStr.length() > 1 || yinFuShuStr.length() > 4
				|| yinLiangStr.length() > 4 || yinChangStr.length() > 2) {
			// ��ʾ������Ϣ
			JOptionPane.showMessageDialog(randomSoundPropertyFrame, "���ڿ�ֵ��Ƿ�ֵ", "����", JOptionPane.ERROR_MESSAGE);
			// �˳�����
			return false;
		}
		// ���ַ���ת��Ϊ��ֵ
		this.channel = Integer.valueOf(pinDaoStr);
		channel = (channel > 0 && channel < 10) ? channel : 1;
		// ���ַ���ת��Ϊ��ֵ
		this.yinFuShu = Integer.valueOf(yinFuShuStr);
		yinFuShu = (yinFuShu > 0 && yinFuShu < 10000) ? yinFuShu : 100;
		// ���ַ���ת��Ϊ��ֵ
		this.yinLiang = Integer.valueOf(yinLiangStr);
		yinLiang = (yinLiang > 0 && yinLiang < 101) ? yinLiang : 100;
		// ���ַ���ת��Ϊ��ֵ
		this.yinChang = Integer.valueOf(yinChangStr);
		yinChang = (yinChang > 0 && yinChang < 17) ? yinChang : 2;
		// ���ַ���ת��Ϊ��ֵ
		this.yinFuRangeOne = Integer.valueOf(yinFuRangeOneStr);
		yinFuRangeOne = (yinFuRangeOne > -1 && yinFuRangeOne < 128) ? yinFuRangeOne : 0;
		// ���ַ���ת��Ϊ��ֵ
		this.yinFuRangeTwo = Integer.valueOf(yinFuRangeTwoStr);
		yinFuRangeTwo = (yinFuRangeTwo > 0 && yinFuRangeTwo < 128) ? yinFuRangeTwo : 127;
		// ������Χ����
		if (yinFuRangeOne > yinFuRangeTwo) {
			yinFuRangeOne = 0;
			yinFuRangeTwo = 128;
		}
		return true;
	}

	/**
	 * ��������ʵ�岢������Ӧ������
	 */
	private void setData() {
		// �������ʵ��
		frame.setRandomSoundRhythm(rhythm);
		// ��������ʵ���Ƶ��
		rhythm.setChannel(channel);
		// ��������ʵ�������
		rhythm.setYinChang(yinChang);
		// ��������ʵ�����������
		rhythm.setYinFuShu(yinFuShu);
		// ��������ʵ�������
		rhythm.setYinLiang(yinLiang);
		// ������������������������
		this.generateRandomNotes();
	}

	/**
	 * ��������
	 * 
	 */
	private void generateRandomNotes() {
		// ���ڴ洢������ɵ�����
		String notes = "";
		// ѭ���������û�Ҫ���������
		for (int i = 0; i < yinFuShu; i++) {
			// �����������
			int data = (int) (Math.random() * (yinFuRangeTwo - yinFuRangeOne) + yinFuRangeOne);
			notes += (char) data;
		}
		// ��������ɵ��������뵽���ɶ�����
		rhythm.setNotes(notes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ȡ�û���������ݲ������ɶ�������������
		if (this.getDataFromFrame()) {
			this.setData();
			frame.getRandomSoundFrame().getFrame().dispose();
			JOptionPane.showMessageDialog(frame, "���óɹ���������ǰ�˵��µĿ�ʼ����������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
