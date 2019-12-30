package cn.triom.test;

import java.sql.Connection;

import cn.triom.util.JDBCUtil;

public class Test {
	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		System.out.println(connection);
	}
}
