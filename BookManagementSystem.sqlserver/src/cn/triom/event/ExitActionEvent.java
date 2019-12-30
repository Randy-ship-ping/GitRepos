package cn.triom.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.MainWin;

/**
 * �˳��¼�
 * @author triom
 *
 */
public class ExitActionEvent extends MouseAdapter {
	// ��ȡ������
	private MainWin mainWin;
	// �û���ѯ��
	private UserSelectModel userSelectModel;
	// �û�������
	private UserUpdateModel userUpdateModel;

	public ExitActionEvent(MainWin mainWin) {
		this.mainWin = mainWin;
		this.userSelectModel = new UserSelectModel();
		this.userUpdateModel = new UserUpdateModel();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			// �����û��������û�
			User user = userSelectModel.selectUserByUsername(mainWin.getUsername());
			// ���û�״̬��Ϊ0����ʾ�˳�
			user.setState(0);
			// �����û�����
			userUpdateModel.updateUserByUsername(user);
			// �˳�����
			System.exit(0);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
