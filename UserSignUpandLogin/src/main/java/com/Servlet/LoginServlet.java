package com.Servlet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Dao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Use a secure key with sufficient size for HS256
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Header
    private static final long EXPIRATION_TIME_MINUTES = 1; // set expire time 1 minute
    private static final Logger logger=LogManager.getLogger(LoginServlet.class);
    
//    InputStream is=LoginServlet.class.getClassLoader().getResourceAsStream("SecretKey.properties");
//    Properties properties=new Properties();
//    properties.load(is);
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");
        
        UserDao userdao=new UserDao();
        try {
			if (userdao.validateUser(email, password,contact)) {
			    String jwtToken = generateJwtToken(email);  // Generate JWT token
			    
			    // Store expiration time in session attribute
			    Instant expirationTime = Instant.now().plus(EXPIRATION_TIME_MINUTES, ChronoUnit.MINUTES); //ChronoUnit is Enum class(enum is a group of unchangeble variables)
			    request.getSession().setAttribute("expirationTime", expirationTime);
			    
			    //Token Validation
			    if (isTokenExpired(jwtToken)) {
			    	logger.info("Token Expired User Logged Out Successfully..");
			        response.sendRedirect(request.getContextPath() + "/Logout.jsp");
			        return;
			    }
			    response.sendRedirect(request.getContextPath() + "/Success.jsp");
			    logger.info("User Logged in Successfully.");
			    logger.info(SECRET_KEY);			    
			    } else {	
			    request.setAttribute("error", "User entered wrong credentials,please enter correctly....");
			    request.getRequestDispatcher("/Login.jsp").forward(request, response);
			    logger.info("User entered wrong credentials,please enter correctly....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    // Token Generation
    private String generateJwtToken(String email) {  // Generate JWT token using the secure key
        Instant expirationTime = Instant.now().plus(EXPIRATION_TIME_MINUTES, ChronoUnit.MINUTES);
        Date expirationDate = Date.from(expirationTime);
        logger.info("Token Issued At: " + expirationTime);
        // Payload(claims) data
        String JwtToken = Jwts
                .builder() // it is used to build new token
                .setSubject(email) // During the authentication process it often carries a Unique identifier for the user.
                .setIssuedAt(Date.from(expirationTime)) 
                .setExpiration(expirationDate) // set Expire time for token
                .signWith(SECRET_KEY)  //sign token with the specified algorithm and secret key
                .compact();  
        logger.info("Generated Token:" + JwtToken);
        return JwtToken;
    }
    
    // Token Validation
    private boolean isTokenExpired(String jwtToken) {
        try {        
            Instant expire = Jwts   //This class is used for creating JSON Web Tokens (JWTs).
                    .parserBuilder()  
                    .setSigningKey(SECRET_KEY)  //it is used to set key that will be used to verify the signature of the token during the validation.
                    .build()  //it is used to build final instance of the JWTparser
                    .parseClaimsJws(jwtToken) //is used to parse and validate a JWT token's signature 
                    .getBody()  //method is used to retrieve the claims (payload) of a parsed JWT.
                    .getExpiration()  //it is used to return expiration time of the token.
                    .toInstant();  //instant it returns current time.            
            logger.info("Token Expiration Time (parsed): " + expire);
            return expire.isBefore(Instant.now());
        } catch (Exception e) {
            return true;
        }
    }
}