package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Sequencer;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * 控制录制钢琴音
 * 
 * @author Administrator
 *
 */
public class ControlRecordActionListener implements ActionListener {
	// 主框架
	private MainFrame frame = null;
	// 按键监听器
	private TapKeyListener tapKeyListener = null;

	public ControlRecordActionListener(MainFrame frame, Sequencer player) {
		this.frame = frame;
		this.tapKeyListener = new TapKeyListener(this.frame, player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取用户点击的菜单项
		JMenuItem item = (JMenuItem) e.getSource();
		// 获取用户点击的菜单项的名字
		String name = item.getText();
		// 根据用户点击的选项来控制开始与停止
		if (name.equals("开始录制") && !frame.isControlRecord()) {
			// 向主界面设置韵律
			frame.setFileRhythm(new Rhythm(1, "", 100, 2, 0));
			// 添加按键监听器
			frame.addKeyListener(tapKeyListener);
			// 提示开始录制
			JOptionPane.showMessageDialog(frame, "录制开始,请点击键盘来模仿钢琴音", "提示", JOptionPane.INFORMATION_MESSAGE);
			// 开始录制
			frame.setControlRecord(true);
		} else if (name.equals("停止录制") && frame.isControlRecord()) {
			// 设置音符数
			frame.getFileRhythm().setYinFuShu(frame.getFileRhythm().getNotes().length());
			// 去除监听器
			frame.addKeyListener(null);
			// 停止录制
			frame.setControlRecord(false);
			// 当用户点击停止录制时询问是否保存
			if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(frame, "是否保存文件录制文件", "提示",
					JOptionPane.OK_CANCEL_OPTION)) {
				// 保存文件
				if (SoundUtil.saveRhythmFile(frame.getFileRhythm())) {
					JOptionPane.showMessageDialog(frame, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					frame.setFileRhythm(null);
				} else {
					JOptionPane.showMessageDialog(frame, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// 提示以另一种方式保存文件
				JOptionPane.showMessageDialog(frame, "可通过点击该菜单下的保存录制来保存文件", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
