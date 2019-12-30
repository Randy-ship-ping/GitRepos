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
 * 登录事件类
 * 
 * @author triom
 *
 */
public class LoginActionEvent implements ActionListener {
	// 获取登录窗体
	private LoginWin loginWin;
	// 用户查询类
	private UserSelectModel userSelectModel;
	// 用户更新类
	private UserUpdateModel userUpdateModel;

	public LoginActionEvent(LoginWin loginWin) {
		this.loginWin = loginWin;
		this.userSelectModel = new UserSelectModel();
		this.userUpdateModel = new UserUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {

		// 获取用输入的用户名
		String username = loginWin.getUsernameField().getText();
		// 获取用户输入的密码
		String password = new String(loginWin.getPasswordField().getPassword());

		// 异常处理
		if (username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "用户名或密码为空", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			// 根据用户名查找用户
			User user = userSelectModel.selectUserByUsername(username);
			// 若用户为空则没有该用户
			if (user == null) {
				JOptionPane.showMessageDialog(null, "用户名不存在", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 若用户的状态为1,表示已登录，不能再次登录
			if (user.getState() == 1) {
				JOptionPane.showMessageDialog(null, "用户已经登录,请退出后重新登陆", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// 判断输入的密码与数据库查询的用户的密码是否相等
			if (user.getPassword().equals(password)) {
				// 将用户状态设为1,表示处于登录状态
				user.setState(1);
				// 更新用户
				userUpdateModel.updateUserByUsername(user);
				// 创建新窗体
				MainWin mainWin = new MainWin();
				mainWin.setUsername(user.getUsername());
				// 释放资源
				loginWin.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
