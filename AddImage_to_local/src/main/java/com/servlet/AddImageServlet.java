package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.CallableStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize=100*1024*1024)   // File Size 100MB
public class AddImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger=LogManager.getLogger(AddImageServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Part> parts = (List<Part>) request.getParts();
            for (Part part : parts) { // here usage of foreach loop for to store select multiple images or file into database 
                String imagepath = part.getSubmittedFileName(); // Get the filename of the selected image path
                if(imagepath !=null && !imagepath.isEmpty()) {
                String uploadPath = "C:/Users/lokeshwarans/eclipse-workspace/New Eclipse IDE 2023-09/AddImage_to_local/src/main/webapp/Images/" + imagepath;
                try (FileOutputStream writefile = new FileOutputStream(uploadPath)) {
					InputStream readfile= part.getInputStream(); //Read the file from part and store it in is object
           //  byte[] data = new byte[readfile.available()];
//                readfile.read(data);
//                writefile.write(data);
					PrintWriter out = response.getWriter();
					out.println("Image file uploaded Successfully..");
            // out.println("byteData: "+data);
					out.println("Selected image path: "+imagepath);
					out.println("ReadFile :"+readfile);
					out.println("WriteFile :"+writefile);
									
            //Load Driver
					String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
					String DB_URL = "jdbc:mysql://localhost:3306/demo";
					String DB_UserName = "root";
					String DB_Password = "root@12345";
					
					String UploadImage="{call UploadImage(?)}";

					Class.forName(DB_DRIVER);
					try (Connection con = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password)) {
					    try (CallableStatement cs = (CallableStatement) con.prepareCall(UploadImage)) {
					        cs.setString(1, imagepath);
					        out.println("Upload image to database successfully..");
					        logger.info("Upload image to database successfully");
					        out.println("----------------------");
					        cs.executeUpdate();
					    }
					}
				}
            }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
