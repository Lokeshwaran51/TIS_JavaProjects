package com.Dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.Model.userModel;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class UserDao {
	
	 public UserDao() {
	    	getConnection(); 	
	    }  
	Connection con; 
	//Connection Initialization
	public Connection getConnection() {  
		try {
			InputStream inputStream=UserDao.class.getClassLoader().getResourceAsStream("DB.properties");
			Properties properties=new Properties();
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}	
			HikariConfig config=new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.Url"));
			config.setDriverClassName(properties.getProperty("db.Driver"));
			config.setUsername(properties.getProperty("db.Uname"));
			config.setPassword(properties.getProperty("db.Pass"));
			config.setMaximumPoolSize(10);
			config.setMinimumIdle(2);
			HikariDataSource ds = new HikariDataSource(config);
				con=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;	  //Here Connection is return to Connection pool
	}
	
    private static final String INSERT_USER = "{call InsertUserSignUpandLogin(?,?,?,?,?,?)}";
    private static final String USER_EXISTS = "{call UserExists(?)}";
    private static final String LoginUser = "{call userlogin(?,?,?)}";
    
    // Insert user data
	public void insert(userModel usermodel) throws SQLException {
		try (Connection con=getConnection();
				CallableStatement cs = con.prepareCall(INSERT_USER)) {
			cs.setString(1, usermodel.getFirstName());
			cs.setString(2, usermodel.getLastName());
			cs.setString(3, usermodel.getContact());
			cs.setString(4, usermodel.getAddress());
			cs.setString(5, usermodel.getEmail());
			cs.setString(6, usermodel.getPassword());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

    // Check if user exists by email, password, and contact while register
	public boolean userExistsByEmail(String email) throws SQLException {
		boolean userExists = false;
		try (Connection con=getConnection();
				CallableStatement ps = con.prepareCall(USER_EXISTS)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				userExists = rs.next(); // If there is a next row, user exists
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return userExists;
	}

    // Validate user details while login
	public boolean validateUser(String email, String password, String contact) throws SQLException {
		try (Connection con=getConnection();
				CallableStatement cs = con.prepareCall(LoginUser)) {
			cs.setString(1, email);
			cs.setString(2, password);
			cs.setString(3, contact);
			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}