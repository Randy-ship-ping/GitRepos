package cn.triom.bean;

import java.util.ArrayList;

/**
 * �ļ�������
 * 
 * @author Administrator
 *
 */
public class FileTree {
	// ���� ��ǰ�ļ�����Ŀ¼����Ŀ¼����ǰ�ļ�����ǰ�ļ�·�������ļ���
	private int dirCount = -1;
	private FileTree father = null;
	private DIYFile file = null;
	private String path = null;
	private ArrayList<FileTree> sons = null;

	public FileTree(FileTree father, DIYFile file, String path, int dirCount) {
		this.father = father;
		this.file = file;
		this.dirCount = dirCount;
		this.path = path;
		this.sons = new ArrayList<FileTree>();
	}

	public int getDirCount() {
		return dirCount;
	}

	public void setFather(FileTree father) {
		this.father = father;
	}

	public FileTree getFather() {
		return father;
	}

	public DIYFile getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	public ArrayList<FileTree> getSons() {
		return sons;
	}

	
	@Override
	public String toString() {
		return "FileTree [dirCount=" + dirCount + ", father=" + father + ", file=" + file + ", path=" + path + "]";
	}

}
