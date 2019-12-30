package cn.triom.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.view.PersonalInfoUpdateWin;

/**
 * 个人信息修改窗口打开事件
 * 
 * @author triom
 *
 */
public class PersonalInfoUpdateWinOpenActionEvent extends WindowAdapter {
	// 个人信息更新窗口
	private PersonalInfoUpdateWin personalInfoUpdateWin;
	// 用户查询类
	private UserSelectModel userSelectModel;
	// 当前登录的用户名
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
			// 查询当前登录的用户
			User user = userSelectModel.selectUserByUsername(username);
			// 喜爱那个更新用户窗体中的输入框设置用户信息
			personalInfoUpdateWin.getUsernameField().setText(user.getUsername());
			personalInfoUpdateWin.getPasswordField().setText(user.getPassword());
			personalInfoUpdateWin.getEmailField().setText(user.getEmail());
			personalInfoUpdateWin.getTelephoneField().setText(user.getTelephone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
