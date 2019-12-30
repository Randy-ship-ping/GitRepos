package cn.triom.bean;

/**
 * �鼮��Ϣʵ����
 * 
 * @author triom
 *
 */
public class BookInfo {
	// ���ԣ�ͼ���ţ�ͼ�������������ţ�isbn������ţ������������湫˾������ʱ�䣬ͼ������������
	private String bookCode;
	private String bookName;
	private String searchCode;
	private String isbnNum;
	private String kindNum;
	private String kindName;
	private String publicCompany;
	private String publicTime;
	private int bookNum;
	private String author;

	public BookInfo() {
	}

	// ���췽��
	public BookInfo(String bookCode, String bookName, String searchCode, String isbnNum, String kindNum,
			String kindName, String publicCompany, String publicTime, int bookNum, String author) {
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.searchCode = searchCode;
		this.isbnNum = isbnNum;
		this.kindNum = kindNum;
		this.kindName = kindName;
		this.publicCompany = publicCompany;
		this.publicTime = publicTime;
		this.bookNum = bookNum;
		this.author = author;
	}

	// set��get����
	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getIsbnNum() {
		return isbnNum;
	}

	public void setIsbnNum(String isbnNum) {
		this.isbnNum = isbnNum;
	}

	public String getKindNum() {
		return kindNum;
	}

	public void setKindNum(String kindNum) {
		this.kindNum = kindNum;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getPublicCompany() {
		return publicCompany;
	}

	public void setPublicCompany(String publicCompany) {
		this.publicCompany = publicCompany;
	}

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
