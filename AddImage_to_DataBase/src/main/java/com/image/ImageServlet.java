package com.image;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig(maxFileSize = 100*1024*1024) //File Size 100MB 
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String DbManager = "com.mysql.cj.jdbc.Driver";
    private String DbUrl = "jdbc:mysql://localhost:3306/demo";
    private String DbUName = "root";
    private String DbPass = "root@12345";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String FirstName = request.getParameter("firstName");
        String LastName = request.getParameter("lastName");
        List<Part> parts = (List<Part>) request.getParts();
        System.out.println(FirstName);
    	System.out.println(LastName);
        for (Part part : parts) {
                System.out.println(part.getName());
                System.out.println(part.getSize());
                System.out.println(part.getSubmittedFileName());
                System.out.println(part.getClass());
                InputStream inputStream = part.getInputStream();
                try {
                    Class.forName(DbManager);
                    Connection con = DriverManager.getConnection(DbUrl, DbUName, DbPass); 
                    String query = "insert into image values (?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, FirstName);
                    ps.setString(2, LastName);
                    if (inputStream != null) {
                        ps.setBlob(3, inputStream);
                    } 
                   int uploaded= ps.executeUpdate();
                    if (uploaded > 0) {
                        System.out.println("File Uploaded Successfully..");
                        System.out.println("-----------------------------");
                    } else {
                        System.out.println("File Upload Failed..");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
