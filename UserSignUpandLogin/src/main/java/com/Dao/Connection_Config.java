package com.Dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Connection_Config {
		private static final Connection_Config object=new Connection_Config();
		private HikariDataSource ds;
		private Connection con;
		
		private Connection_Config() {
			datasource_initialize();
			connection_initialize();
		}
		public static final synchronized Connection_Config getInstance() {
			return object;
		}
		private void connection_initialize() {
			try {
				con=ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		public Connection getConnection() {
			return con;
		}
		private void datasource_initialize() {
			HikariConfig config=new HikariConfig();
			config.setJdbcUrl(DB_Conn);
			config.setDriverClassName(DB_Driver);
			config.setUsername(DB_Uname);
			config.setPassword(DB_Pass);
			config.setMaximumPoolSize(10);
			config.setMinimumIdle(2);
			ds=new HikariDataSource(config);
		}
		private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
	    private static final String DB_Conn = "jdbc:mysql://localhost:3306/demo";
	    private static final String DB_Uname = "root";
	    private static final String DB_Pass = "root@12345";
}