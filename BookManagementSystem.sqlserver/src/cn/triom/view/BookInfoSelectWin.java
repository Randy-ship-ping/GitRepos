package cn.triom.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.triom.event.BookInfoDeleteActionEvent;
import cn.triom.event.BookInfoSelectActionEvent;

/**
 * 查询书籍信息窗体类
 * 
 * @author triom
 *
 */
public class BookInfoSelectWin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField selectCondition;
	private JTable table;
	private JPanel selectInfoPanel;
	private JLabel greetLabel;
	private JLabel selectNoticeLabel;
	private JComboBox<String> selectConditionBox;
	private JButton selectButton;
	private JButton deleteSelectedBookInfoButton;
	private JScrollPane scrollPane;
	private final String[] COLUMN_DATA = { "图书编号", "图书性名", "索引号", "I S B N", "分类号", "分类名", "出版社", "出版时间", "图书数量",
			"作者" };
	private String[][] rowData = null;

	public BookInfoSelectWin() {
		contentPane = new JPanel();
		greetLabel = new JLabel("欢迎访问图书管理系统");
		selectNoticeLabel = new JLabel("查询条件");
		selectCondition = new JTextField();
		selectConditionBox = new JComboBox<String>();
		selectButton = new JButton("查询");
		deleteSelectedBookInfoButton = new JButton("删除所选行");
		selectInfoPanel = new JPanel();
		scrollPane = new JScrollPane();
		table = new JTable();

		setTitle("图书信息查询");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 465);
		setResizable(false);
		setVisible(true);
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(greetLabel);
		contentPane.add(selectInfoPanel);

		greetLabel.setBounds(0, 5, 629, 52);
		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetLabel.setFont(new Font("楷体", Font.BOLD, 45));
		greetLabel.setEnabled(false);
		greetLabel.setBackground(Color.WHITE);

		selectNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectNoticeLabel.setBounds(10, 10, 91, 34);

		selectButton.setBounds(333, 12, 80, 31);

		deleteSelectedBookInfoButton.setBounds(523, 12, 101, 31);
		deleteSelectedBookInfoButton.setBackground(Color.RED);

		selectCondition.setBounds(111, 12, 101, 31);
		selectCondition.setColumns(10);

		selectConditionBox.setModel(new DefaultComboBoxModel<String>(COLUMN_DATA));
		selectConditionBox.setBounds(222, 13, 101, 29);

		selectInfoPanel.setLayout(null);
		selectInfoPanel.setBounds(0, 67, 634, 360);
		selectInfoPanel.add(selectButton);
		selectInfoPanel.add(selectNoticeLabel);
		selectInfoPanel.add(selectCondition);
		selectInfoPanel.add(selectConditionBox);
		selectInfoPanel.add(deleteSelectedBookInfoButton);
		selectInfoPanel.add(scrollPane);

		scrollPane.setBounds(0, 54, 634, 318);
		scrollPane.setViewportView(table);

		listRowDataInTable();

		addActionEvent();
	}

	// 添加事件
	private void addActionEvent() {
		selectButton.addActionListener(new BookInfoSelectActionEvent(this));

		deleteSelectedBookInfoButton.addActionListener(new BookInfoDeleteActionEvent(this));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedRow = table.getSelectedRow();
					new BookInfoUpdateWin(rowData[selectedRow]);
				}
			}
		});
	}

	//将数据在表格中列出
	public void listRowDataInTable() {
		table.setModel(new DefaultTableModel(rowData, COLUMN_DATA) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.validate();
	}

	//get方法
	public String[][] getRowData() {
		return rowData;
	}

	public void setRowData(String[][] rowData) {
		this.rowData = rowData;
	}

	public JTextField getSelectCondition() {
		return selectCondition;
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox<String> getSelectConditionBox() {
		return selectConditionBox;
	}

}
