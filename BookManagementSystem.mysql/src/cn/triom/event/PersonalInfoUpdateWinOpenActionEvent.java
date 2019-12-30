package cn.triom.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.view.PersonalInfoUpdateWin;

/**
 * ������Ϣ�޸Ĵ��ڴ��¼�
 * 
 * @author triom
 *
 */
public class PersonalInfoUpdateWinOpenActionEvent extends WindowAdapter {
	// ������Ϣ���´���
	private PersonalInfoUpdateWin personalInfoUpdateWin;
	// �û���ѯ��
	private UserSelectModel userSelectModel;
	// ��ǰ��¼���û���
	private String username;

	public PersonalInfoUpdateWinOpenActionEvent(
			PersonalInfoUpdateWin personalInfoUpdateWin, String username) {
		this.personalInfoUpdateWin = personalInfoUpdateWin;
		this.username = username;
		this.userSelectModel = new UserSelectModel();
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		try {
			// ��ѯ��ǰ��¼���û�
			User user = userSelectModel.selectUserByUsername(username);
			// ϲ���Ǹ������û������е�����������û���Ϣ
			personalInfoUpdateWin.getUsernameField().setText(user.getUsername());
			personalInfoUpdateWin.getPasswordField().setText(user.getPassword());
			personalInfoUpdateWin.getEmailField().setText(user.getEmail());
			personalInfoUpdateWin.getTelephoneField().setText(user.getTelephone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
