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
 * 注册事件类
 * 
 * @author triom
 *
 */
public class SignActionEvent implements ActionListener {
	// 注册窗体
	private SignWin signWin;
	// 用户插入类
	private UserInsertModel userInsertModel;
	// 用户查询类
	private UserSelectModel userSelectModel;

	public SignActionEvent(SignWin signWin) {
		this.signWin = signWin;
		this.userInsertModel = new UserInsertModel();
		this.userSelectModel = new UserSelectModel();
	}

	public void actionPerformed(ActionEvent arg0) {
		// 获取用户输入的用户名
		String username = signWin.getUsernameField().getText();
		// 获取用户输入的密码
		String password = signWin.getPasswordField().getText();
		// 获取用户输入的email
		String email = signWin.getEmailField().getText();
		// 获取用户输入的电话
		String telephone = signWin.getTelephoneField().getText();
		// 异常处理
		if (username.equals("") || password.equals("") || email.equals("") || telephone.equals("")) {
			JOptionPane.showMessageDialog(null, "存在空值", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			// 根据用户输入的用户名来查询用户
			User user = userSelectModel.selectUserByUsername(username);
			// 若用户不为空，则证明已经存在该用户了
			if (user != null) {
				JOptionPane.showMessageDialog(null, "已存在该用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// 根据用户输入的信息，来创建用户类
				user = new User(username, password, email, telephone, 0);
				// 插入用户数据
				userInsertModel.insertUser(user);
				//提示
				JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 销毁注册窗体
				signWin.dispose();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
