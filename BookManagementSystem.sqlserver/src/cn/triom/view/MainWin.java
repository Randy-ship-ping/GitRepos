package cn.triom.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.triom.event.ExitActionEvent;
import cn.triom.event.MainWinCloseActionEvent;

/**
 * 主窗体类
 * 
 * @author triom
 *
 */
public class MainWin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel greetLabel;
	private JMenuBar menuBar;
	private JMenu insertBookInfoMenu;
	private JMenu selectBookInfoMenu;
	private JMenu updatePersonalInfoMenu;
	private JMenu exitMenu;
	private String username;

	public MainWin() {
		contentPane = new JPanel();
		greetLabel = new JLabel("欢迎访问图书管理系统");
		menuBar = new JMenuBar();
		insertBookInfoMenu = new JMenu("图书信息录入");
		selectBookInfoMenu = new JMenu("图书信息查询");
		updatePersonalInfoMenu = new JMenu("个人信息修改");
		exitMenu = new JMenu("退出系统");

		setTitle("欢迎访问图书管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 465);
		setResizable(false);
		setVisible(true);
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(greetLabel);
		contentPane.add(menuBar);

		greetLabel.setEnabled(false);
		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetLabel.setBackground(Color.WHITE);
		greetLabel.setFont(new Font("楷体", Font.BOLD, 45));
		greetLabel.setBounds(0, 43, 634, 374);

		menuBar.setBounds(0, 0, 634, 38);
		menuBar.add(insertBookInfoMenu);
		menuBar.add(selectBookInfoMenu);
		menuBar.add(updatePersonalInfoMenu);
		menuBar.add(exitMenu);

		addActionEvent();
	}

	// 添加事件
	private void addActionEvent() {
		// 插入书籍菜单事件
		insertBookInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BookInfoInsertWin();
			}
		});
		// 查询书籍菜单事件
		selectBookInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BookInfoSelectWin();
			}
		});
		// 更新书籍菜单事件
		updatePersonalInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new PersonalInfoUpdateWin(username);
			}
		});
		// 退出菜单事件
		exitMenu.addMouseListener(new ExitActionEvent(this));
		// 为本窗体添加事件
		this.addWindowListener(new MainWinCloseActionEvent(this));
	}

	// get方法
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
