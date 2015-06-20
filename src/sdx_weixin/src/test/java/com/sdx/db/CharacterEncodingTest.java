
package com.sdx.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试数据库编码
 * @author 田广文
 * @date 2014年5月30日-上午11:07:45
 */
public class CharacterEncodingTest
{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		Connection con = null;
		String sql;
		PreparedStatement pre;
		ResultSet rs;

		try
		{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			String url = "jdbc:mysql://11.0.37.174:3306/umapp?useUnicode=true&amp;characterEncoding=utf-8";
//			String url = "jdbc:mysql://localhost:3306/umapp?useUnicode=true&amp;characterEncoding=utf-8";
			con = DriverManager.getConnection(url, "root", "root");
//			con.createStatement().execute("SET NAMES utf8");

			pre = con.prepareStatement("insert test (username, password) values (?,?)");
			pre.setString(1, "测试");
//			pre.setBytes(2, "测试密码".getBytes("utf-8"));
			pre.setString(2, new String("测试密码".getBytes("latin1"), "latin1"));
			pre.execute();

			sql = "select t_id,username,password from test";
			pre = con.prepareStatement(sql);

			rs = pre.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String username = new String(rs.getString(2).getBytes("utf-8"));
				String password = rs.getString(3);

				System.out.println("id=" + id + ";username=" + username + ";password=" + password);
			}
			con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}