package cn.triom.bean;

/**
 * �û�ʵ����
 * @author triom
 *
 */
public class User {
//	���ԣ��û��������룬���䣬�绰��state��״̬���������ж��û��Ƿ��ڵ�¼״̬��0��δ��¼��1����¼
	private String username;
	private String password;
	private String email;
	private String telephone;
	private int state;

	public User(String username, String password, String email,
			String telephone, int state) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
