package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.dao.UserDAO;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/")
@MultipartConfig(maxFileSize=100*1024*1024)
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UserDAO userDAO;
    private static final Logger log=(Logger) LogManager.getLogger(UserServlet.class);

    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/display image":
                	DisplayImage(request,response);
                	break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
    
    //Display Image from Database
	private void DisplayImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String u_id=request.getParameter("id");
        int id=Integer.parseInt(u_id);
        
        InputStream input=UserServlet.class.getClassLoader().getResourceAsStream("UserForm.properties");  //Reading information from properties file
        Properties prop=new Properties();
        prop.load(input);
        
        String ImageName=null;       
        try {
            Class.forName(prop.getProperty("DB_Driver1"));
            Connection connection=DriverManager.getConnection(prop.getProperty("DB_Conn1"),prop.getProperty("DB_Uname1"),prop.getProperty("DB_Pass1"));
            String query="select ImageName from user_form where id=?";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                ImageName=rs.getString("ImageName");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }      
        if (ImageName!=null) {
            request.setAttribute("ImageName", ImageName);       
            request.getRequestDispatcher("ImageDisplay.jsp").forward(request,response);
        } else {
            PrintWriter out=response.getWriter();
            out.println("Image Not Found...");
        }
    }
      
     //Insert New User
	private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        String contact=request.getParameter("contact");
        String gender=request.getParameter("gender");
        String[] areaOfInterest=request.getParameterValues("areaOfInterest");
        String areaOfInterest1=(areaOfInterest != null) ? String.join(", ",areaOfInterest):null;
        
        if(userDAO.validateuseremail(email)){
            request.setAttribute("error", "Email or contact is already exists. Please try with another...");
            request.getRequestDispatcher("user-form.jsp").forward(request, response);
        }else{
            List<String> imageName=new ArrayList<>(); // List to store image filenames
            Collection<Part> parts=request.getParts(); // Using Collection for type compatibility
            for(Part part:parts){
                String imagepath=part.getSubmittedFileName(); // Get the filename of the selected image path
                if (imagepath!=null && !imagepath.isEmpty()) {
                    String uploadPath="C:/Users/lokeshwarans/Desktop/BackUp/26-04-2024/jsp-servlet-jdbc-mysql-User-StoredProcedure/src/main/webapp/WEB-INF/Files/" + imagepath;
                    try (FileOutputStream writefile=new FileOutputStream(uploadPath)) {
                        InputStream readfile=part.getInputStream(); 
                        byte[] data=new byte[readfile.available()];
                        readfile.read(data);
                        writefile.write(data);
                        imageName.add(imagepath);
                    }
                }
            }
            String imageName1=String.join(", ", imageName);
            User newUser=new User(name, email, country, contact, gender, areaOfInterest1, imageName1);
            userDAO.insertUser(newUser);
            log.info("New User Record Inserted Successfully...");
            response.sendRedirect("list");
        }
    }

    //Update Existing User Details
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        String contact=request.getParameter("contact");
        String gender=request.getParameter("gender");
        String[] areaOfInterest=request.getParameterValues("areaOfInterest");
        String areaOfInterest1=(areaOfInterest != null) ? String.join(", ", areaOfInterest):null;
        String[] ImageName=request.getParameterValues("ImageName");
        String ImageName1=(ImageName!=null)?String.join(", ",ImageName):null;
       
        User user = new User(id, name, email, country, contact, gender, areaOfInterest1,ImageName1);
        userDAO.updateUser(user);
        log.info("User id :"+id+" Record Updated Successfully...");
        response.sendRedirect("list");
    }

    //Delete User
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
   
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        List<User> listUser=userDAO.selectAllUser();
        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        User existingUser=userDAO.selectUser(id);   
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }
}
