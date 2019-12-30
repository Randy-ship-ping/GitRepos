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
 * ��������
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
		greetLabel = new JLabel("��ӭ����ͼ�����ϵͳ");
		menuBar = new JMenuBar();
		insertBookInfoMenu = new JMenu("ͼ����Ϣ¼��");
		selectBookInfoMenu = new JMenu("ͼ����Ϣ��ѯ");
		updatePersonalInfoMenu = new JMenu("������Ϣ�޸�");
		exitMenu = new JMenu("�˳�ϵͳ");

		setTitle("��ӭ����ͼ�����ϵͳ");
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
		greetLabel.setFont(new Font("����", Font.BOLD, 45));
		greetLabel.setBounds(0, 43, 634, 374);

		menuBar.setBounds(0, 0, 634, 38);
		menuBar.add(insertBookInfoMenu);
		menuBar.add(selectBookInfoMenu);
		menuBar.add(updatePersonalInfoMenu);
		menuBar.add(exitMenu);

		addActionEvent();
	}

	// ����¼�
	private void addActionEvent() {
		// �����鼮�˵��¼�
		insertBookInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BookInfoInsertWin();
			}
		});
		// ��ѯ�鼮�˵��¼�
		selectBookInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new BookInfoSelectWin();
			}
		});
		// �����鼮�˵��¼�
		updatePersonalInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new PersonalInfoUpdateWin(username);
			}
		});
		// �˳��˵��¼�
		exitMenu.addMouseListener(new ExitActionEvent(this));
		// Ϊ����������¼�
		this.addWindowListener(new MainWinCloseActionEvent(this));
	}

	// get����
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
