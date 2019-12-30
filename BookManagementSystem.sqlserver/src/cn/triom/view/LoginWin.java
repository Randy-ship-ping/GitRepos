package cn.triom.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.triom.event.LoginActionEvent;

/**
 * ��¼������
 * 
 * @author triom
 *
 */
public class LoginWin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel greetLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton clearButton;
	private JButton signButton;

	public LoginWin() {
		contentPane = new JPanel();
		greetLabel = new JLabel("��ӭ����ͼ�����ϵͳ");
		usernameLabel = new JLabel("�û���");
		passwordLabel = new JLabel("����");
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("��¼");
		clearButton = new JButton("���");
		signButton = new JButton("ע��");

		setTitle("ͼ�����ϵͳ��¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 465);
		setResizable(false);
		setVisible(true);
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(greetLabel);
		contentPane.add(usernameLabel);
		contentPane.add(usernameField);
		contentPane.add(passwordLabel);
		contentPane.add(loginButton);
		contentPane.add(clearButton);
		contentPane.add(signButton);
		contentPane.add(passwordField);

		greetLabel.setBounds(5, 5, 634, 115);
		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetLabel.setFont(new Font("����", Font.BOLD, 45));
		greetLabel.setEnabled(false);
		greetLabel.setBackground(Color.WHITE);

		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(87, 165, 112, 37);

		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(87, 237, 112, 37);

		usernameField.setBounds(246, 168, 223, 31);
		usernameField.setColumns(10);
		passwordField.setEchoChar('*');
		passwordField.setBounds(246, 240, 223, 31);

		loginButton.setBounds(107, 349, 92, 37);
		clearButton.setBounds(246, 349, 92, 37);
		signButton.setBounds(377, 349, 92, 37);

		addActionEvent();
	}

	// ����¼�
	private void addActionEvent() {
		// ��¼�¼�
		loginButton.addActionListener(new LoginActionEvent(this));
		// ����¼�
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
			}
		});

		// ע���¼�
		signButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new SignWin();
			}
		});

	}

	// get����
	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

}
