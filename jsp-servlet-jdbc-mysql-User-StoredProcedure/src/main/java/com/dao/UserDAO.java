package com.dao;

import com.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO{
    private static final String INSERT_USER_SQL="{call InsertUser(?, ?, ?, ?, ?, ?,?)}";   //CALL Statement is used to execute stored Procedure
    private static final String SELECT_USER_BY_ID="{call SelectUserById(?)}";   //Private access modifier is used to we can access within the class
    private static final String SELECT_ALL_USER="{call SelectAllUsers()}";
    private static final String DELETE_USER_SQL="{call DeleteUserById(?)}";
    private static final String UPDATE_USER_SQL="{call UpdateUser(?, ?, ?, ?, ?, ?, ?,?)}";
    
    private static final String Email_Exists="{call UserExists_UserForm(?)}"; 
    private static final Logger log=LogManager.getLogger(UserDAO.class);

    public UserDAO() {
    	getConnection(); 	
    }   
	private Connection connection=null; 
    
    public Connection getConnection() {
    	InputStream is=UserDAO.class.getClassLoader().getResourceAsStream("UserForm.properties"); //Read Data from properties file
    	Properties prop=new Properties();
    	try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}  
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(prop.getProperty("DB_Driver1"));  //Load Driver
        config.setJdbcUrl(prop.getProperty("DB_Conn1"));
        config.setUsername(prop.getProperty("DB_Uname1"));
        config.setPassword(prop.getProperty("DB_Pass1"));
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(15);
       HikariDataSource dataSource = new HikariDataSource(config);
			try {
				connection=dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return connection; //here connection is return to connection pool
}
          	
   //Insert New User
	public void insertUser(User user) {
		try (CallableStatement cs = connection.prepareCall(INSERT_USER_SQL)) {   //CallableStatement is interface used to call stored procedure and functions
			cs.setString(1, user.getName());  										//PrepareCall Method is used to create object for callable Statement
			cs.setString(2, user.getEmail());
			cs.setString(3, user.getCountry());   
			cs.setString(4, user.getContact()); 
			cs.setString(5, user.getGender());
			cs.setString(6, user.getAreaOfInterest());
			cs.setString(7, user.getImageName());
			cs.executeUpdate();           //ExecuteUpdate method which is used for insert,delete,update operations
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}
	
	//Delete User
	public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (CallableStatement cs=connection.prepareCall(DELETE_USER_SQL)) {
            cs.setInt(1, id);
            rowDeleted = cs.executeUpdate() > 0;
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
        return rowDeleted;
    }

	//Update User Details
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (CallableStatement cs = connection.prepareCall(UPDATE_USER_SQL)) {
        	cs.setInt(1, user.getId());
            cs.setString(2, user.getName());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getCountry());
            cs.setString(5, user.getContact());
            cs.setString(6, user.getGender());
            cs.setString(7, user.getAreaOfInterest());
            cs.setString(8, user.getImageName());
            log.info("Updating User: " + user); 
            rowUpdated = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    //Select User By id
    public User selectUser(int id) {
        User user = null;
        try (CallableStatement cs=connection.prepareCall(SELECT_USER_BY_ID)) {
            cs.setInt(1, id);        //Set() method is used to setValues in preparedStatement or callableStatement before Executing it
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");    //get() method is used to retrive or read the values from ResultSet
                String email = rs.getString("email");  
                String country = rs.getString("country");   
                String contact = rs.getString("contact");
                String gender = rs.getString("gender");
                String areaOfInterest = rs.getString("areaOfInterest");
                String ImageName=rs.getString("ImageName");
                user = new User(id, name, email, country, contact, gender, areaOfInterest,ImageName);
            }
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
        return user;
    }

    //Select All Users 
    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        try (CallableStatement cs = connection.prepareCall(SELECT_ALL_USER)) {
            ResultSet rs = cs.executeQuery();     //ExecuteQuery which is used for select operations
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String contact = rs.getString("contact");
                String gender = rs.getString("gender");
                String areaOfInterest = rs.getString("areaOfInterest");
                String ImageName=rs.getString("ImageName");
                userList.add(new User(id, name, email, country, contact, gender, areaOfInterest,ImageName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    } 
    
    //User Email and Contact Validation
	public boolean validateuseremail(String email){
		boolean emailExists=false;
		try{
			CallableStatement cs=connection.prepareCall(Email_Exists);
			cs.setString(1,email);
			ResultSet rs=cs.executeQuery();
			emailExists=rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return emailExists;
	}
}