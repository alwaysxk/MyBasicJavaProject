package com.simplemvc.db;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static DataSource ds = null;
	
	// 数据源初始化
	static{
		ds = new ComboPooledDataSource("simplemvc");
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}

	public static void realseConnection(Connection connection) {
		// TODO Auto-generated method stssub
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
