package servlets;

import models.ClinicStaff;
import models.PatientCard;
import org.apache.log4j.Logger;
import repositories.ClinicStaffRepository;
import repositories.PatientCardsRepository;
import repositories.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainPage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainPage.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/mainPage.jsp").
                forward(req,resp);
    }

}
