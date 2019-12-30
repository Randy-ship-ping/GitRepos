package cn.triom.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;

import cn.triom.event.file.ExitActionEvent;

public class TestView extends JFrame {

	private static final long serialVersionUID = 1L;

	// 组件
	private JPanel contentPane;
	private final JLabel zipFilePath;
	private final JScrollPane scrollPane;
	private JMenuBar functionMenuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu selectMenu;
	private JButton backPresDirctoryButton;
	private DefaultTableModel defaultTableModel;
	private JTable table;
	// 存储列名
	private String[] columnNames = { "姓名", "类型", "最后修改日期", "大小" };
	// 存储文件数据的行
	private String[][] tableValues = null;

	public TestView() {
		// 组件初始化
		contentPane = new JPanel();
		functionMenuBar = new JMenuBar();
		fileMenu = createMenu("文件", "打开文件", "退出");
		editMenu = createMenu("编辑", "全选");
		selectMenu = createMenu("选择", "姓名", "类型", "最后修改日期", "大小");
		backPresDirctoryButton = new JButton("<--");
		zipFilePath = new JLabel("........");
		scrollPane = new JScrollPane();
		// 设置单元格都不能被修改 但可点击
		defaultTableModel = new DefaultTableModel(tableValues, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// 向表格中添加defaultTableModel
		table = new JTable(defaultTableModel);

		// 组件设置
		setResizable(false);
		setTitle("ZIP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 600, 400);
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(functionMenuBar);
		contentPane.add(zipFilePath);
		contentPane.add(backPresDirctoryButton);
		contentPane.add(scrollPane);

		functionMenuBar.setForeground(Color.WHITE);
		functionMenuBar.setFont(new Font("MingLiU_HKSCS", Font.PLAIN, 18));
		functionMenuBar.setBorderPainted(false);
		functionMenuBar.setBounds(0, 0, this.getWidth(), 36);
		functionMenuBar.add(fileMenu);
		functionMenuBar.add(editMenu);
		functionMenuBar.add(selectMenu);

		fileMenu.setFont(new Font("新宋体", Font.PLAIN, 20));
		editMenu.setFont(new Font("新宋体", Font.PLAIN, 20));
		selectMenu.setFont(new Font("新宋体", Font.PLAIN, 20));

		zipFilePath.setFont(new Font("Batang", Font.PLAIN, 20));
		zipFilePath.setBounds(70, 46, 520, 33);
		zipFilePath.setBorder(BorderUIResource.getEtchedBorderUIResource());

		backPresDirctoryButton.setBackground(UIManager.getColor("Button.background"));
		backPresDirctoryButton.setFont(new Font("宋体", Font.PLAIN, 20));
		backPresDirctoryButton.setBounds(0, 46, 64, 33);

		scrollPane.setBounds(0, 89, 595, 283);
		scrollPane.setViewportView(table);

		// 获取表格中被选中 的单元格
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int[] row = table.getSelectedRows();
					for (int i : row) {
						System.out.println(i);
					}
				}
			}
		});
		// 向组件中添加事件
		addActionToComponent();
	}

	/**
	 * 向组件中添加事件
	 */
	private void addActionToComponent() {
		fileMenu.getItem(1).addActionListener(new ExitActionEvent());
//		fileMenu.getItem(0).addActionListener(new OpenFileActionEvent(functionView, fileView));
	}

	/**
	 * 创建菜单
	 * 
	 * @param menuName      要创建的菜单名字
	 * @param menuItemNames 菜单下条目的名字
	 * @return
	 */
	private JMenu createMenu(String menuName, String... menuItemNames) {
		JMenu menu = new JMenu(menuName);
		if (menuItemNames != null) {
			for (int i = 0; i < menuItemNames.length; i++) {
				JMenuItem item = new JMenuItem(menuItemNames[i]);
				item.setFont(new Font("楷体", Font.PLAIN, 15));
				menu.add(item);
			}
		}
		return menu;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView frame = new TestView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
