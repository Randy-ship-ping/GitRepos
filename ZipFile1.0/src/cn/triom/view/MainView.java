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

	// ���
	private JPanel contentPane;
	private final JLabel zipFilePath;
	private final JScrollPane scrollPane;
	private JMenuBar functionMenuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu selectMenu;
	private JButton backPresDirctoryButton;
	private JTable fileTable;
	// �洢����
	private String[] columnNames = { "����", "����", "����޸�����", "��С" };
	// �洢�ļ����ݵ���
	private String[][] rowData = null;
	// �洢�����ļ���
	private FileTree rootFileTree = null;
	// �洢��ǰĿ¼�µ��ļ�
	private ArrayList<DIYFile> currentDirFile = null;
	// �洢Ŀǰ�ڼ���Ŀ¼
	private int dirCount = 0;

	public MainView() {
		// �����ʼ��
		contentPane = new JPanel();
		functionMenuBar = new JMenuBar();
		fileMenu = createMenu("�ļ�", "���ļ�", "��ѹ�ļ�", "��ѹ��ѡ�ļ�", "�½��ļ���", "�½��ļ�", "�˳�");
		editMenu = createMenu("�༭", "ȫѡ", "ȡ��ȫѡ", "��ѡ");
		selectMenu = createMenu("�鿴", "����������", "����������", "������޸���������", "����С����", "����ָ���������ļ�", "����ָ�����͵��ļ�", "ģ�������ļ�",
				"�г�ȫ���ļ�");
		backPresDirctoryButton = new JButton("<--");
		zipFilePath = new JLabel("........");
		scrollPane = new JScrollPane();
		// �����ļ����
		fileTable = new JTable(new DIYTableModel(rowData, columnNames));

		// �������
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

		fileMenu.setFont(new Font("������", Font.PLAIN, 20));
		editMenu.setFont(new Font("������", Font.PLAIN, 20));
		selectMenu.setFont(new Font("������", Font.PLAIN, 20));

		zipFilePath.setBounds(70, 46, 520, 33);
		zipFilePath.setBorder(BorderUIResource.getEtchedBorderUIResource());

		backPresDirctoryButton.setBackground(UIManager.getColor("Button.background"));
		backPresDirctoryButton.setFont(new Font("����", Font.PLAIN, 20));
		backPresDirctoryButton.setBounds(0, 46, 64, 33);

		scrollPane.setBounds(0, 89, 595, 283);

		scrollPane.setViewportView(fileTable);
		fileTable.setRowHeight(20);
		// �����������¼�
		addActionToComponent();
	}

	/**
	 * �����������¼�
	 */
	private void addActionToComponent() {
		// ���ļ��¼�
		fileMenu.getItem(0).addActionListener(new OpenFileActionEvent(this));
		// ��ѹ�ļ�
		fileMenu.getItem(1).addActionListener(new UnZIPFileActionEvent(this));
		// ��ѹ��ѡ�ļ�
		fileMenu.getItem(2).addActionListener(new UnZIPSpecifiedFileActionEvent(this));
		// �������ļ�
		fileMenu.getItem(4).addActionListener(new CreateNewFileActionEvent(this));
		// �˳�����
		fileMenu.getItem(5).addActionListener(new ExitActionEvent());

		// ȫѡ
		editMenu.getItem(0).addActionListener(new SelectAllFileActionEvent(this));

		// ������������
		selectMenu.getItem(0).addActionListener(new SortFileActionEvent(this, 0));
		// ������������
		selectMenu.getItem(1).addActionListener(new SortFileActionEvent(this, 1));
		// ������������
		selectMenu.getItem(2).addActionListener(new SortFileActionEvent(this, 2));
		// ���մ�С����
		selectMenu.getItem(3).addActionListener(new SortFileActionEvent(this, 3));
		// ������������ָ���ļ�
		selectMenu.getItem(4).addActionListener(new SelectFileByNameActionEvent(this));
		// �������Ͳ���ָ���ļ�
		selectMenu.getItem(5).addActionListener(new SelectFileByTypeActionEvent(this));
		// ���չؼ��ֲ���ָ���ļ�
		selectMenu.getItem(6).addActionListener(new SelectFileIntangiblyActionEvent(this));
		// �г�ȫ���ļ�
		selectMenu.getItem(7).addActionListener(new ListAllFileActionEvent(this));

		// ���û�������ļ�Ŀ¼
		fileTable.addMouseListener(new DoubleClickFileToOpenSonFileMouseAction(this));
		// �˻ص���һ��Ŀ¼
		backPresDirctoryButton.addActionListener(new BackPresDirectoryActionEvent(this));

	}

	/**
	 * �����˵�
	 * 
	 * @param menuName      Ҫ�����Ĳ˵�����
	 * @param menuItemNames �˵�����Ŀ������
	 * @return
	 */
	private JMenu createMenu(String menuName, String... menuItemNames) {
		JMenu menu = new JMenu(menuName);
		if (menuItemNames != null) {
			for (int i = 0; i < menuItemNames.length; i++) {
				JMenuItem item = new JMenuItem(menuItemNames[i]);
				item.setFont(new Font("����", Font.PLAIN, 15));
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
