package servlets;

import dtos.ClinicStaffWithPasswords;
import org.apache.log4j.Logger;
import repositories.ClinicStaffRepository;
import services.ClinicStaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Register extends HttpServlet {
    private static final ClinicStaffService clinicStaffService =
            new ClinicStaffService();
    private static final Logger logger = Logger.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("firstName");
            String surname = req.getParameter("lastName");
            String email = req.getParameter("email");
            String title = req.getParameter("title");
            String password = req.getParameter("password");
            if(!password.equals(req.getParameter("confirmedPassword"))){
                req.setAttribute("notEqual", true);
                req.getRequestDispatcher("pages/registration.jsp")
                        .forward(req,resp);
            }
            else if(name.isEmpty() || surname.isEmpty() || email.isEmpty()
                    || title == null || password.isEmpty()){
                req.setAttribute("fail", true);
                req.getRequestDispatcher("pages/registration.jsp")
                        .forward(req,resp);
            }
            else{
                ClinicStaffWithPasswords clinicStaff = new ClinicStaffWithPasswords();
                clinicStaff.setName(name);
                clinicStaff.setSurname(surname);
                clinicStaff.setTitle(title);
                clinicStaff.setEmail(email);
                clinicStaff.setPassword(password);
                clinicStaffService.add(clinicStaff);
                logger.info("Clinic staff " + name + " " + surname + " added");
                req.getRequestDispatcher("pages/successfulRegistration.jsp").forward(req,resp);
            }
        }catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQLError.jsp")
                    .forward(req,resp);
        }
    }
}
