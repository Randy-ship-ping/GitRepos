package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*8
 * 退出程序事件
 */
public class ExitActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
