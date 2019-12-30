package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.LoginWin;
import cn.triom.view.MainWin;

/**
 * ��¼�¼���
 * 
 * @author triom
 *
 */
public class LoginActionEvent implements ActionListener {
	// ��ȡ��¼����
	private LoginWin loginWin;
	// �û���ѯ��
	private UserSelectModel userSelectModel;
	// �û�������
	private UserUpdateModel userUpdateModel;

	public LoginActionEvent(LoginWin loginWin) {
		this.loginWin = loginWin;
		this.userSelectModel = new UserSelectModel();
		this.userUpdateModel = new UserUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {

		// ��ȡ��������û���
		String username = loginWin.getUsernameField().getText();
		// ��ȡ�û����������
		String password = new String(loginWin.getPasswordField().getPassword());

		// �쳣����
		if (username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "�û���������Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			// �����û��������û�
			User user = userSelectModel.selectUserByUsername(username);
			// ���û�Ϊ����û�и��û�
			if (user == null) {
				JOptionPane.showMessageDialog(null, "�û���������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ���û���״̬Ϊ1,��ʾ�ѵ�¼�������ٴε�¼
			if (user.getState() == 1) {
				JOptionPane.showMessageDialog(null, "�û��Ѿ���¼,���˳������µ�½", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// �ж���������������ݿ��ѯ���û��������Ƿ����
			if (user.getPassword().equals(password)) {
				// ���û�״̬��Ϊ1,��ʾ���ڵ�¼״̬
				user.setState(1);
				// �����û�
				userUpdateModel.updateUserByUsername(user);
				// �����´���
				MainWin mainWin = new MainWin();
				mainWin.setUsername(user.getUsername());
				// �ͷ���Դ
				loginWin.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "�û������������", "����", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
