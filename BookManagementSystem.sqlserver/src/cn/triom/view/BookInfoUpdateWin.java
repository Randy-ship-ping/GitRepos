package cn.triom.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.triom.event.BookInfoUpdateActionEvent;
import cn.triom.event.FrameDisposeActionEvent;



/**
 * 更新书籍信息窗体类
 * 
 * @author triom
 *
 */
public class BookInfoUpdateWin extends JFrame {

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

	private JButton updateButton;
	private JButton cancelButton;

	private String[] data;

	public BookInfoUpdateWin(String[] data) {
		this.data = data;
		contentPane = new JPanel();
		greetLabel = new JLabel("欢迎访问图书管理系统");
		inputPane = new JPanel();

		publicTimeLabel = new JLabel("出版时间");
		bookNumLabel = new JLabel("图书数量");
		kindNameLabel = new JLabel("分类名");
		authorLabel = new JLabel("作者");
		bookCodeLabel = new JLabel("图书编号");
		bookNameLabel = new JLabel("图书姓名");
		searchCodeLabel = new JLabel("索引号");
		kindNumLabel = new JLabel("分类号");
		publicCompanyLabel = new JLabel("出版公司");
		isbnNumLabel = new JLabel("I S B N");

		kindNumField = new JTextField();
		kindNameField = new JTextField();
		publicCompanyField = new JTextField();
		publicTimeField = new JTextField();
		bookNumField = new JTextField();
		authorField = new JTextField();
		bookCodeField = new JTextField();
		bookCodeField.setEditable(false);
		bookNameField = new JTextField();
		searchCodeField = new JTextField();
		isbnNumField = new JTextField();

		updateButton = new JButton("\u66F4\u65B0");
		cancelButton = new JButton("\u53D6\u6D88");

		setTitle("图书信息更新");
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
		greetLabel.setFont(new Font("楷体", Font.BOLD, 45));
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
		inputPane.add(updateButton);
		inputPane.add(cancelButton);

		updateButton.setBounds(106, 315, 78, 33);
		cancelButton.setBounds(431, 315, 78, 33);

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


		setFieldData();

		addActionEvent();
	}

	private void addActionEvent() {
		updateButton.addActionListener(new BookInfoUpdateActionEvent(this));
		cancelButton.addActionListener(new FrameDisposeActionEvent(this));
	}

	private void setFieldData() {
		if (data == null) {
			return;
		}
		bookCodeField.setText(data[0]);
		bookNameField.setText(data[1]);
		searchCodeField.setText(data[2]);
		isbnNumField.setText(data[3]);
		kindNumField.setText(data[4]);
		kindNameField.setText(data[5]);
		publicCompanyField.setText(data[6]);
		publicTimeField.setText(data[7]);
		bookNumField.setText(data[8]);
		authorField.setText(data[9]);
	}

	//get方法
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
