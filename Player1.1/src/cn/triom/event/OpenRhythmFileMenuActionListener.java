package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.triom.entity.Rhythm;
import cn.triom.util.SoundUtil;
import cn.triom.view.MainFrame;

/**
 * 打开韵律文件
 * 
 * @author Administrator
 *
 */
public class OpenRhythmFileMenuActionListener implements ActionListener {
	// 文件选择
	private JFileChooser chooser;
	// 主框架
	private MainFrame frame;
	// 韵律对象
	private Rhythm rhythm;

	public OpenRhythmFileMenuActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 判断当前是否处于录制
		if (frame.isControlRecord()) {
			JOptionPane.showMessageDialog(frame, "现在正处于录制中,请停止录制后再打开文件");
			return;
		}
		// 选择文件
		chooser = new JFileChooser("c:");
		// 设置文件过滤器
		chooser.setFileFilter(new FileNameExtensionFilter("\'zjq\' & \'ZJQ\' 文件类型", "zjq", "ZJQ"));
		// 打开文件选择器
		chooser.showDialog(new JFrame(), "选择文件");
		if (chooser.getSelectedFile() != null) {
			// 获取选择的文件
			File file = chooser.getSelectedFile();
			// 读取文件内容
			if (file != null) {
				rhythm = SoundUtil.readRhythm(file);
			}
			// 从文件中获取韵律对象
			if (rhythm != null) {
				frame.setFileRhythm(rhythm);
				JOptionPane.showMessageDialog(frame, "文件打开成功,请点击文件菜单中的开始播放来播放", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "读取错误，文件类型不匹配", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
