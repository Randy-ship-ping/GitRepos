package cn.triom.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.MainWin;

/**
 * 
 * ������ر��¼���
 * 
 * @author triom
 * 
 */
public class MainWinCloseActionEvent extends WindowAdapter {
	// ��ȡ������
	private MainWin mainWin;
	// �û���ѯ��
	private UserSelectModel userSelectModel;
	// �û�������
	private UserUpdateModel userUpdateModel;

	public MainWinCloseActionEvent(MainWin mainWin) {
		this.mainWin = mainWin;
		this.userSelectModel = new UserSelectModel();
		this.userUpdateModel = new UserUpdateModel();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			// �����û��������û�
			User user = userSelectModel.selectUserByUsername(mainWin.getUsername());
			// ���û�״̬��Ϊ0����ʾ�˳�
			user.setState(0);
			// �����û�����
			userUpdateModel.updateUserByUsername(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
