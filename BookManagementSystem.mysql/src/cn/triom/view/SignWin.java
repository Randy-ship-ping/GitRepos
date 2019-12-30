package cn.triom.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.triom.event.SignActionEvent;

/**
 * 注册窗体类
 * @author triom
 *
 */
public class SignWin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel greetLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField telephoneField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel telephoneLabel;
	private JButton signButton;

	public SignWin() {
		contentPane = new JPanel();
		greetLabel = new JLabel("欢迎访问图书管理系统");
		usernameLabel = new JLabel("用户名");
		passwordLabel = new JLabel("密码");
		emailLabel = new JLabel("Email");
		telephoneLabel = new JLabel("电话");
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		emailField = new JTextField();
		telephoneField = new JTextField();
		signButton = new JButton("注册");
		greetLabel.setBounds(5, 5, 634, 52);

		setTitle("注册新用户");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		contentPane.add(emailLabel);
		contentPane.add(telephoneLabel);
		contentPane.add(passwordField);
		contentPane.add(emailField);
		contentPane.add(telephoneField);
		contentPane.add(signButton);

		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetLabel.setFont(new Font("楷体", Font.BOLD, 45));
		greetLabel.setEnabled(false);
		greetLabel.setBackground(Color.WHITE);

		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(149, 109, 100, 36);

		usernameField.setBounds(289, 109, 166, 36);
		usernameField.setColumns(10);

		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(149, 171, 100, 36);

		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setBounds(149, 234, 100, 36);

		telephoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		telephoneLabel.setBounds(149, 303, 100, 36);

		passwordField.setColumns(10);
		passwordField.setBounds(289, 171, 166, 36);

		emailField.setColumns(10);
		emailField.setBounds(289, 234, 166, 36);

		telephoneField.setColumns(10);
		telephoneField.setBounds(289, 303, 166, 36);

		signButton.setBounds(290, 378, 93, 36);

		signButton.addActionListener(new SignActionEvent(this));
	}

	//get方法
	public JTextField getUsernameField() {
		return usernameField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getTelephoneField() {
		return telephoneField;
	}
}
