package cn.triom.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import cn.triom.bean.User;
import cn.triom.model.UserSelectModel;
import cn.triom.model.UserUpdateModel;
import cn.triom.view.MainWin;

/**
 * 退出事件
 * @author triom
 *
 */
public class ExitActionEvent extends MouseAdapter {
	// 获取主窗体
	private MainWin mainWin;
	// 用户查询类
	private UserSelectModel userSelectModel;
	// 用户更新类
	private UserUpdateModel userUpdateModel;

	public ExitActionEvent(MainWin mainWin) {
		this.mainWin = mainWin;
		this.userSelectModel = new UserSelectModel();
		this.userUpdateModel = new UserUpdateModel();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			// 根据用户名查找用户
			User user = userSelectModel.selectUserByUsername(mainWin.getUsername());
			// 将用户状态设为0，表示退出
			user.setState(0);
			// 更新用户数据
			userUpdateModel.updateUserByUsername(user);
			// 退出程序
			System.exit(0);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
