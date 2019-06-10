package servlets;

import controller.AdminPageGetController;
import controller.Controller;
import factories.ControllerFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DispatcherServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(DispatcherServlet.class);

    private void executeRequest(HttpServletRequest req, HttpServletResponse resp, Controller controller) throws ServletException, IOException {
        String page;
        try {
            page = controller.execute(req);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            page = "errorPages/SQlError.jsp";
        }
        req.getRequestDispatcher(page)
                .forward(req, resp);
    }

    private String getUri(HttpServletRequest req) {
        return req.getRequestURI()
                    .substring(req.getContextPath().length()+1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);

        Controller controller = ControllerFactory.chooseGetMethodController(uri);
        executeRequest(req, resp, controller);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);

        Controller controller = ControllerFactory.choosePostMethodController(uri);
        executeRequest(req, resp, controller);
    }
}
