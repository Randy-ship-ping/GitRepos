package cn.triom.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.triom.bean.User;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.PersonalInfoUpdateWin;

/**
 * 个人信息修改窗体事件类
 * 
 * @author triom
 *
 */
public class PersonalInfoUpdateActionEvent implements ActionListener {
	// 个人信息修改窗体
	private PersonalInfoUpdateWin personalInfoUpdateWin;
	private UserUpdateModel userUpdateModel;

	public PersonalInfoUpdateActionEvent(PersonalInfoUpdateWin personalInfoUpdateWin) {
		this.personalInfoUpdateWin = personalInfoUpdateWin;
		this.userUpdateModel = new UserUpdateModel();
	}

	public void actionPerformed(ActionEvent e) {
		// 获取输入框的内容
		String username = personalInfoUpdateWin.getUsernameField().getText();
		String password = personalInfoUpdateWin.getPasswordField().getText();
		String email = personalInfoUpdateWin.getEmailField().getText();
		String telephone = personalInfoUpdateWin.getTelephoneField().getText();
		// 异常处理
		if (username.equals("") || password.equals("") || email.equals("") || telephone.equals("")) {
			JOptionPane.showMessageDialog(null, "存在空值", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取user对象
		User user = new User(username, password, email, telephone, 1);
		try {
			// 更新数据
			userUpdateModel.updateUserByUsername(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 提示
		JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		// 释放窗体
		personalInfoUpdateWin.dispose();
	}

}
