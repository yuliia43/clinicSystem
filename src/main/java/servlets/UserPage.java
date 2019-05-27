package servlets;

import models.ClinicStaff;
import org.apache.log4j.Logger;
import repositories.ClinicStaffRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserPage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserPage.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String email = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");
        logger.info("Request with email: " + email + " password " + password);
        if(email != null && password != null){
            ClinicStaffRepository repository = new ClinicStaffRepository();
            ClinicStaff staff = null;
            try {
                staff = repository.checkAuthorization(email, password);
            } catch (SQLException e) {
                req.getRequestDispatcher("errorPages/SQlError.html").forward(req,resp);
            }
            if(staff != null){
                req.getSession().setAttribute("user", staff);
                req.getRequestDispatcher("pages/userPage.jsp")
                        .forward(req, resp);
            }
            else {
                req.setAttribute("fail", true);
            }
        }*/
        if(req.getSession().getAttribute("user") != null){
            req.getRequestDispatcher("pages/userPage.jsp")
                    .forward(req, resp);
        }
        else{
            req.getRequestDispatcher("errorPages/notAuthorisedError.jsp")
                    .forward(req, resp);
        }
    }
}
