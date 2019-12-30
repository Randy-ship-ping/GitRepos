package cn.triom.bean;

/**
 * 自定义文件类
 * 
 * @author Administrator
 *
 */
public class DIYFile {
	// 文件属性 姓名，类型，最后更新时间，大小
	private String name;
	private String type;
	private String lastUpdateDate;
	private long size;

	public DIYFile(String name, String type, String lastUpdateDate, long size) {
		this.name = name;
		this.type = type;
		this.lastUpdateDate = lastUpdateDate;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return name + "       " + type + "       " + lastUpdateDate + "       " + size;
	}

}
