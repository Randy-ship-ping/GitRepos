package cn.triom.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.triom.event.OkRandomSoundPropertyActionListener;

/**
 * 随机产生钢琴音
 * 
 * @author Administrator
 *
 */
public class RandomSoundPropertyFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	// 组件
	private JFrame frame = null;
	private JLabel pinDaoLabel = null;
	private JLabel yinFuShuLabel = null;
	private JLabel yinLiangLabel = null;
	private JLabel yinChangLabel = null;
	private JLabel yinFuRangeLabel = null;
	private JLabel yinFuRangeSyntaxLabel = null;
	private JTextField pinDaoField = null;
	private JTextField yinFuShuField = null;
	private JTextField yinLiangField = null;
	private JTextField yinChangField = null;
	private JTextField yinFuRangeOneField = null;
	private JTextField yinFuRangeTwoField = null;
	private JButton ok = null;
	private JButton cancel = null;

	public RandomSoundPropertyFrame(MainFrame mainFrame) {
		// 初始化
		frame = new JFrame("RandomSound");
		pinDaoLabel = new JLabel("频道选择(1-9):");
		yinLiangLabel = new JLabel("音量大小(0-100):");
		yinFuShuLabel = new JLabel("音符个数(0-9999):");
		yinChangLabel = new JLabel("音长设置(1-16):");
		yinFuRangeLabel = new JLabel("音符范围(1-128):");
		yinFuRangeSyntaxLabel = new JLabel("~~");
		pinDaoField = new JTextField();
		yinFuShuField = new JTextField();
		yinLiangField = new JTextField();
		yinChangField = new JTextField();
		yinFuRangeOneField = new JTextField();
		yinFuRangeTwoField = new JTextField();
		ok = new JButton("确定");
		cancel = new JButton("取消");
		// 组件设置
		frame.setBounds(430, 130, 500, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.add(this);
		frame.validate();

		this.setBounds(0, 0, 500, 500);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.requestFocus();
		this.add(pinDaoLabel);
		this.add(yinFuShuLabel);
		this.add(yinLiangLabel);
		this.add(yinChangLabel);
		this.add(yinFuRangeLabel);
		this.add(yinFuRangeSyntaxLabel);
		this.add(pinDaoField);
		this.add(yinFuShuField);
		this.add(yinLiangField);
		this.add(yinChangField);
		this.add(yinFuRangeOneField);
		this.add(yinFuRangeTwoField);
		this.add(ok);
		this.add(cancel);

		pinDaoLabel.setBounds(60, 40, 200, 40);
		yinFuShuLabel.setBounds(60, 110, 200, 40);
		yinLiangLabel.setBounds(60, 180, 200, 40);
		yinChangLabel.setBounds(60, 250, 200, 40);
		yinFuRangeLabel.setBounds(60, 320, 200, 40);
		yinFuRangeSyntaxLabel.setBounds(330, 320, 20, 40);
		pinDaoLabel.setFont(new Font("黑体", Font.BOLD, 18));
		yinFuShuLabel.setFont(new Font("黑体", Font.BOLD, 18));
		yinLiangLabel.setFont(new Font("黑体", Font.BOLD, 18));
		yinChangLabel.setFont(new Font("黑体", Font.BOLD, 18));
		yinFuRangeLabel.setFont(new Font("黑体", Font.BOLD, 18));
		yinFuRangeSyntaxLabel.setFont(new Font("黑体", Font.BOLD, 18));
		pinDaoField.setBounds(290, 40, 100, 40);
		yinFuShuField.setBounds(290, 110, 100, 40);
		yinLiangField.setBounds(290, 180, 100, 40);
		yinChangField.setBounds(290, 250, 100, 40);
		yinFuRangeOneField.setBounds(290, 320, 40, 40);
		yinFuRangeTwoField.setBounds(350, 320, 40, 40);

		ok.setBounds(30, 400, 120, 40);
		cancel.setBounds(330, 400, 120, 40);
		// 点击确定是设置属性
		ok.addActionListener(new OkRandomSoundPropertyActionListener(mainFrame));
		// 点击取消时关闭界面
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}

	public JTextField getPinDaoField() {
		return pinDaoField;
	}

	public void setPinDaoField(JTextField pinDaoField) {
		this.pinDaoField = pinDaoField;
	}

	public JTextField getYinFuShuField() {
		return yinFuShuField;
	}

	public void setYinFuShuField(JTextField yinFuShuField) {
		this.yinFuShuField = yinFuShuField;
	}

	public JTextField getYinLiangField() {
		return yinLiangField;
	}

	public void setYinLiangField(JTextField yinLiangField) {
		this.yinLiangField = yinLiangField;
	}

	public JTextField getYinChangField() {
		return yinChangField;
	}

	public void setYinChangField(JTextField yinChangField) {
		this.yinChangField = yinChangField;
	}

	public JTextField getYinFuRangeOneField() {
		return yinFuRangeOneField;
	}

	public void setYinFuRangeOneField(JTextField yinFuRangeOneField) {
		this.yinFuRangeOneField = yinFuRangeOneField;
	}

	public JTextField getYinFuRangeTwoField() {
		return yinFuRangeTwoField;
	}

	public void setYinFuRangeTwoField(JTextField yinFuRangeTwoField) {
		this.yinFuRangeTwoField = yinFuRangeTwoField;
	}

	public JFrame getFrame() {
		return frame;
	}
}
