package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
    	 String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
         String DB_URL = "jdbc:mysql://localhost:3306/demo";
         String DB_UserName = "root";
         String DB_Password = "root@12345";
         
         PrintWriter pw=response.getWriter();
    	int id = Integer.parseInt(request.getParameter("id"));
    	
        int imgId=0;
        String imageFileName=null;
        try {
            Class.forName(DB_DRIVER);
            Connection connection=DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
            String query = "SELECT * FROM upload_image WHERE image_id=?";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if (rs.getInt("image_id") == id) {
					imgId = rs.getInt("image_id");
					imageFileName = rs.getString("images_fName");
				}
			}  
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        if(imageFileName!=null){
        request.setAttribute("id", String.valueOf(imgId));
        request.setAttribute("img", imageFileName);       
        request.getRequestDispatcher("DisplayImage.jsp").forward(request, response);
    }else {
    	 pw.println("File Not Found....");
    }
    }   
}
