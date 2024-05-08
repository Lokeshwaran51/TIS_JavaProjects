package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Dao.UserDao;
import com.Model.userModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UserDao userDAO;
    private static final Logger logger=LogManager.getLogger(RegisterServlet.class);

    public void init() {
        userDAO =new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            if (userDAO.userExistsByEmail(email)) {           	
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User with this email is already exists. Please login to continue.');");
                out.println("window.location.replace('Login.jsp');");
                out.println("</script>");
//                out.println("User with this email is already exists. Please login to continue.");
//                request.getRequestDispatcher("Register.jsp").forward(request, response);

              logger.info("User with this email is already exists. Please login to continue.");
                return; // Return to prevent further execution
            }
            else {
                userModel usermodel = new userModel(firstName, lastName, contact, address, email, password);
                userDAO.insert(usermodel);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User Registered Successfully. Please login to continue..');");
                out.println("window.location.replace('Login.jsp');");
                out.println("</script>");
//                out.println("User Registered Successfully. Please login to continue..");
//                request.getRequestDispatcher("Login.jsp").include(request, response);

               logger.info("User Registered Successfully. Please login to continue..");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
