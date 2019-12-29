package cn.triom.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * 韵律类 用来存储和音乐相关的属性
 * 
 * @author Administrator
 *
 */
public class Rhythm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id = UUID.randomUUID().toString();
	private int channel;
	private String notes = null;
	private int yinLiang;
	private int yinChang;
	private int yinFuShu;

	public Rhythm() {
		this.channel = 1;
		this.notes = "";
		this.yinLiang = 100;
		this.yinChang = 2;
		this.yinFuShu = 0;
	}

	public Rhythm(int channel, String notes, int yinLiang, int yinChang, int yinFuShu) {
		this.channel = channel;
		this.notes = notes;
		this.yinLiang = yinLiang;
		this.yinChang = yinChang;
		this.yinFuShu = yinFuShu;
	}

	public String getId() {
		return id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getYinLiang() {
		return yinLiang;
	}

	public void setYinLiang(int yinLiang) {
		this.yinLiang = yinLiang;
	}

	public int getYinChang() {
		return yinChang;
	}

	public void setYinChang(int yinChang) {
		this.yinChang = yinChang;
	}

	public int getYinFuShu() {
		return yinFuShu;
	}

	public void setYinFuShu(int yinFuShu) {
		this.yinFuShu = yinFuShu;
	}
}
