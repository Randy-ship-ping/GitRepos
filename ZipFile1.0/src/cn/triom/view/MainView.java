package cn.triom.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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
import javax.swing.plaf.BorderUIResource;

import cn.triom.bean.DIYFile;
import cn.triom.bean.FileTree;
import cn.triom.dao.DIYTableModel;
import cn.triom.event.edit.SelectAllFileActionEvent;
import cn.triom.event.file.CreateNewFileActionEvent;
import cn.triom.event.file.ExitActionEvent;
import cn.triom.event.file.OpenFileActionEvent;
import cn.triom.event.file.UnZIPFileActionEvent;
import cn.triom.event.file.UnZIPSpecifiedFileActionEvent;
import cn.triom.event.other.BackPresDirectoryActionEvent;
import cn.triom.event.other.DoubleClickFileToOpenSonFileMouseAction;
import cn.triom.event.select.ListAllFileActionEvent;
import cn.triom.event.select.SelectFileByNameActionEvent;
import cn.triom.event.select.SelectFileByTypeActionEvent;
import cn.triom.event.select.SelectFileIntangiblyActionEvent;
import cn.triom.event.select.SortFileActionEvent;

public class MainView extends JFrame {

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
	private JTable fileTable;
	// 存储列名
	private String[] columnNames = { "姓名", "类型", "最后修改日期", "大小" };
	// 存储文件数据的行
	private String[][] rowData = null;
	// 存储整个文件树
	private FileTree rootFileTree = null;
	// 存储当前目录下的文件
	private ArrayList<DIYFile> currentDirFile = null;
	// 存储目前在几级目录
	private int dirCount = 0;

	public MainView() {
		// 组件初始化
		contentPane = new JPanel();
		functionMenuBar = new JMenuBar();
		fileMenu = createMenu("文件", "打开文件", "解压文件", "解压所选文件", "新建文件夹", "新建文件", "退出");
		editMenu = createMenu("编辑", "全选", "取消全选", "反选");
		selectMenu = createMenu("查看", "按姓名排序", "按类型排序", "按最后修改日期排序", "按大小排序", "查找指定姓名的文件", "查找指定类型的文件", "模糊查找文件",
				"列出全部文件");
		backPresDirctoryButton = new JButton("<--");
		zipFilePath = new JLabel("........");
		scrollPane = new JScrollPane();
		// 创建文件表格
		fileTable = new JTable(new DIYTableModel(rowData, columnNames));

		// 组件设置
		setResizable(false);
		setTitle("ZIP");
		setVisible(true);
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

		zipFilePath.setBounds(70, 46, 520, 33);
		zipFilePath.setBorder(BorderUIResource.getEtchedBorderUIResource());

		backPresDirctoryButton.setBackground(UIManager.getColor("Button.background"));
		backPresDirctoryButton.setFont(new Font("宋体", Font.PLAIN, 20));
		backPresDirctoryButton.setBounds(0, 46, 64, 33);

		scrollPane.setBounds(0, 89, 595, 283);

		scrollPane.setViewportView(fileTable);
		fileTable.setRowHeight(20);
		// 向组件中添加事件
		addActionToComponent();
	}

	/**
	 * 向组件中添加事件
	 */
	private void addActionToComponent() {
		// 打开文件事件
		fileMenu.getItem(0).addActionListener(new OpenFileActionEvent(this));
		// 解压文件
		fileMenu.getItem(1).addActionListener(new UnZIPFileActionEvent(this));
		// 解压所选文件
		fileMenu.getItem(2).addActionListener(new UnZIPSpecifiedFileActionEvent(this));
		// 创建新文件
		fileMenu.getItem(4).addActionListener(new CreateNewFileActionEvent(this));
		// 退出程序
		fileMenu.getItem(5).addActionListener(new ExitActionEvent());

		// 全选
		editMenu.getItem(0).addActionListener(new SelectAllFileActionEvent(this));

		// 按照名字排序
		selectMenu.getItem(0).addActionListener(new SortFileActionEvent(this, 0));
		// 按照类型排序
		selectMenu.getItem(1).addActionListener(new SortFileActionEvent(this, 1));
		// 按照日期排序
		selectMenu.getItem(2).addActionListener(new SortFileActionEvent(this, 2));
		// 按照大小排序
		selectMenu.getItem(3).addActionListener(new SortFileActionEvent(this, 3));
		// 按照姓名查找指定文件
		selectMenu.getItem(4).addActionListener(new SelectFileByNameActionEvent(this));
		// 按照类型查找指定文件
		selectMenu.getItem(5).addActionListener(new SelectFileByTypeActionEvent(this));
		// 按照关键字查找指定文件
		selectMenu.getItem(6).addActionListener(new SelectFileIntangiblyActionEvent(this));
		// 列出全部文件
		selectMenu.getItem(7).addActionListener(new ListAllFileActionEvent(this));

		// 打开用户点击的文件目录
		fileTable.addMouseListener(new DoubleClickFileToOpenSonFileMouseAction(this));
		// 退回到上一层目录
		backPresDirctoryButton.addActionListener(new BackPresDirectoryActionEvent(this));

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

	public JLabel getZipFilePath() {
		return zipFilePath;
	}

	public FileTree getRootFileTree() {
		return rootFileTree;
	}

	public void setRootFileTree(FileTree rootFileTree) {
		this.rootFileTree = rootFileTree;
	}

	public int getDirCount() {
		return dirCount;
	}

	public void setDirCount(int dirCount) {
		this.dirCount = dirCount;
	}

	public void setRowData(String[][] rowData) {
		this.rowData = rowData;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public String[][] getRowData() {
		return rowData;
	}

	public JTable getFileTable() {
		return fileTable;
	}

	public void setCurrentDirFile(ArrayList<DIYFile> currentDirFile) {
		this.currentDirFile = currentDirFile;
	}

	public ArrayList<DIYFile> getCurrentListFile() {
		return currentDirFile;
	}
}
