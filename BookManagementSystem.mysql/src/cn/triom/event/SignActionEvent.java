package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.User;
import cn.triom.model.UserInsertModel;
import cn.triom.model.UserSelectModel;
import cn.triom.view.SignWin;

/**
 * ע���¼���
 * 
 * @author triom
 *
 */
public class SignActionEvent implements ActionListener {
	// ע�ᴰ��
	private SignWin signWin;
	// �û�������
	private UserInsertModel userInsertModel;
	// �û���ѯ��
	private UserSelectModel userSelectModel;

	public SignActionEvent(SignWin signWin) {
		this.signWin = signWin;
		this.userInsertModel = new UserInsertModel();
		this.userSelectModel = new UserSelectModel();
	}

	public void actionPerformed(ActionEvent arg0) {
		// ��ȡ�û�������û���
		String username = signWin.getUsernameField().getText();
		// ��ȡ�û����������
		String password = signWin.getPasswordField().getText();
		// ��ȡ�û������email
		String email = signWin.getEmailField().getText();
		// ��ȡ�û�����ĵ绰
		String telephone = signWin.getTelephoneField().getText();
		// �쳣����
		if (username.equals("") || password.equals("") || email.equals("") || telephone.equals("")) {
			JOptionPane.showMessageDialog(null, "���ڿ�ֵ", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			// �����û�������û�������ѯ�û�
			User user = userSelectModel.selectUserByUsername(username);
			// ���û���Ϊ�գ���֤���Ѿ����ڸ��û���
			if (user != null) {
				JOptionPane.showMessageDialog(null, "�Ѵ��ڸ��û�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// �����û��������Ϣ���������û���
				user = new User(username, password, email, telephone, 0);
				// �����û�����
				userInsertModel.insertUser(user);
				//��ʾ
				JOptionPane.showMessageDialog(null, "ע��ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ����ע�ᴰ��
				signWin.dispose();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
