package cn.triom.event.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*8
 * �˳������¼�
 */
public class ExitActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
