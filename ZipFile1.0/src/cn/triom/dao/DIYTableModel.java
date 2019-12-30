package cn.triom.dao;

import javax.swing.table.DefaultTableModel;

/**
 * 自定义的DIYTableModel
 * 
 * @author Administrator
 *
 */
public class DIYTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public DIYTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
