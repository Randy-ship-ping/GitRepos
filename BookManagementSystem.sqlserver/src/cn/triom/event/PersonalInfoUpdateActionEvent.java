package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.User;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.PersonalInfoUpdateWin;

/**
 * ������Ϣ�޸Ĵ����¼���
 * 
 * @author triom
 *
 */
public class PersonalInfoUpdateActionEvent implements ActionListener {
	// ������Ϣ�޸Ĵ���
	private PersonalInfoUpdateWin personalInfoUpdateWin;
	private UserUpdateModel userUpdateModel;

	public PersonalInfoUpdateActionEvent(PersonalInfoUpdateWin personalInfoUpdateWin) {
		this.personalInfoUpdateWin = personalInfoUpdateWin;
		this.userUpdateModel = new UserUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {
		// ��ȡ����������
		String username = personalInfoUpdateWin.getUsernameField().getText();
		String password = personalInfoUpdateWin.getPasswordField().getText();
		String email = personalInfoUpdateWin.getEmailField().getText();
		String telephone = personalInfoUpdateWin.getTelephoneField().getText();
		// �쳣����
		if (username.equals("") || password.equals("") || email.equals("") || telephone.equals("")) {
			JOptionPane.showMessageDialog(null, "���ڿ�ֵ", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡuser����
		User user = new User(username, password, email, telephone, 1);
		try {
			// ��������
			userUpdateModel.updateUserByUsername(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// ��ʾ
		JOptionPane.showMessageDialog(null, "���³ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		// �ͷŴ���
		personalInfoUpdateWin.dispose();
	}

}
