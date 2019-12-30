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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.triom.event.BookInfoInsertActionEvent;
import cn.triom.event.FrameDisposeActionEvent;

/**
 * �����鼮��Ϣ������
 * 
 * @author triom
 *
 */
public class BookInfoInsertWin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel greetLabel;
	private JPanel inputPane;

	private JLabel publicTimeLabel;
	private JLabel bookNumLabel;
	private JLabel kindNameLabel;
	private JLabel authorLabel;
	private JLabel bookCodeLabel;
	private JLabel bookNameLabel;
	private JLabel searchCodeLabel;
	private JLabel kindNumLabel;
	private JLabel publicCompanyLabel;
	private JLabel isbnNumLabel;

	private JTextField bookCodeField;
	private JTextField bookNameField;
	private JTextField searchCodeField;
	private JTextField isbnNumField;
	private JTextField kindNumField;
	private JTextField kindNameField;
	private JTextField publicCompanyField;
	private JTextField publicTimeField;
	private JTextField bookNumField;
	private JTextField authorField;

	private JButton submitButton;
	private JButton resetButton;
	private JButton closeButton;

	public BookInfoInsertWin() {
		contentPane = new JPanel();
		greetLabel = new JLabel("��ӭ����ͼ�����ϵͳ");
		inputPane = new JPanel();

		publicTimeLabel = new JLabel("����ʱ��");
		bookNumLabel = new JLabel("ͼ������");
		kindNameLabel = new JLabel("������");
		authorLabel = new JLabel("����");
		bookCodeLabel = new JLabel("ͼ����");
		bookNameLabel = new JLabel("ͼ������");
		searchCodeLabel = new JLabel("������");
		kindNumLabel = new JLabel("�����");
		publicCompanyLabel = new JLabel("���湫˾");
		isbnNumLabel = new JLabel("I S B N");

		kindNumField = new JTextField();
		kindNameField = new JTextField();
		publicCompanyField = new JTextField();
		publicTimeField = new JTextField();
		bookNumField = new JTextField();
		authorField = new JTextField();
		bookCodeField = new JTextField();
		bookNameField = new JTextField();
		searchCodeField = new JTextField();
		isbnNumField = new JTextField();

		submitButton = new JButton("¼��");
		resetButton = new JButton("����");
		closeButton = new JButton("�ر�");

		setTitle("ͼ����Ϣ¼��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 465);
		setResizable(false);
		setVisible(true);
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(greetLabel);
		contentPane.add(inputPane);

		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetLabel.setFont(new Font("����", Font.BOLD, 45));
		greetLabel.setEnabled(false);
		greetLabel.setBackground(Color.WHITE);
		greetLabel.setBounds(0, 0, 634, 59);

		inputPane.setBounds(0, 69, 634, 358);
		inputPane.setLayout(null);
		inputPane.add(publicTimeLabel);
		inputPane.add(bookNumLabel);
		inputPane.add(kindNameLabel);
		inputPane.add(kindNumField);
		inputPane.add(authorLabel);
		inputPane.add(kindNameField);
		inputPane.add(publicCompanyField);
		inputPane.add(bookCodeLabel);
		inputPane.add(publicTimeField);
		inputPane.add(bookNumField);
		inputPane.add(authorField);
		inputPane.add(bookCodeField);
		inputPane.add(bookNameLabel);
		inputPane.add(searchCodeLabel);
		inputPane.add(kindNumLabel);
		inputPane.add(bookNameField);
		inputPane.add(searchCodeField);
		inputPane.add(isbnNumField);
		inputPane.add(publicCompanyLabel);
		inputPane.add(isbnNumLabel);
		inputPane.add(submitButton);
		inputPane.add(closeButton);
		inputPane.add(resetButton);

		bookCodeField.setBackground(Color.WHITE);
		bookCodeField.setBounds(106, 22, 161, 33);
		bookCodeField.setColumns(10);

		bookCodeLabel.setBounds(25, 21, 71, 34);
		bookCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setBounds(25, 78, 71, 34);

		searchCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchCodeLabel.setBounds(25, 136, 71, 34);

		kindNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kindNumLabel.setBounds(25, 248, 71, 34);

		bookNameField.setColumns(10);
		bookNameField.setBounds(106, 79, 161, 33);

		searchCodeField.setColumns(10);
		searchCodeField.setBounds(106, 137, 161, 33);

		isbnNumField.setColumns(10);
		isbnNumField.setBounds(106, 192, 161, 33);

		publicCompanyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		publicCompanyLabel.setBounds(348, 78, 71, 34);

		isbnNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isbnNumLabel.setBounds(25, 191, 71, 34);

		publicTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		publicTimeLabel.setBounds(348, 136, 71, 34);

		bookNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNumLabel.setBounds(348, 191, 71, 34);

		kindNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kindNameLabel.setBounds(348, 21, 71, 34);

		kindNumField.setColumns(10);
		kindNumField.setBounds(106, 249, 161, 33);

		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setBounds(348, 248, 71, 34);

		kindNameField.setColumns(10);
		kindNameField.setBackground(Color.WHITE);
		kindNameField.setBounds(431, 22, 161, 33);

		publicCompanyField.setColumns(10);
		publicCompanyField.setBackground(Color.WHITE);
		publicCompanyField.setBounds(431, 78, 161, 33);

		publicTimeField.setColumns(10);
		publicTimeField.setBackground(Color.WHITE);
		publicTimeField.setBounds(431, 136, 161, 33);

		bookNumField.setColumns(10);
		bookNumField.setBackground(Color.WHITE);
		bookNumField.setBounds(431, 192, 161, 33);

		authorField.setColumns(10);
		authorField.setBackground(Color.WHITE);
		authorField.setBounds(431, 248, 161, 33);

		submitButton.setBounds(106, 315, 78, 33);
		resetButton.setBounds(279, 315, 78, 33);
		closeButton.setBounds(431, 315, 78, 33);

		addActionEvent();
	}

	// ����齨�¼�
	private void addActionEvent() {
		//�����¼�
		submitButton.addActionListener(new BookInfoInsertActionEvent(this));
		//�����¼�
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookCodeField.setText("");
				bookNameField.setText("");
				searchCodeField.setText("");
				isbnNumField.setText("");
				kindNumField.setText("");
				kindNameField.setText("");
				publicCompanyField.setText("");
				publicTimeField.setText("");
				bookNumField.setText("");
				authorField.setText("");
			}
		});
		//�ر��¼�
		closeButton.addActionListener(new FrameDisposeActionEvent(this));
	}

	//set����
	public JTextField getBookCodeField() {
		return bookCodeField;
	}

	public JTextField getBookNameField() {
		return bookNameField;
	}

	public JTextField getSearchCodeField() {
		return searchCodeField;
	}

	public JTextField getIsbnNumField() {
		return isbnNumField;
	}

	public JTextField getKindNumField() {
		return kindNumField;
	}

	public JTextField getKindNameField() {
		return kindNameField;
	}

	public JTextField getPublicCompanyField() {
		return publicCompanyField;
	}

	public JTextField getPublicTimeField() {
		return publicTimeField;
	}

	public JTextField getBookNumField() {
		return bookNumField;
	}

	public JTextField getAuthorField() {
		return authorField;
	}
}
