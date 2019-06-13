package servlets;

import commonlyUsedStrings.PageName;
import controller.Controller;
import exceptions.UnAuthorisedException;
import factories.ControllerFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class DispatcherServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * @param req
     * @param resp
     * @param controller
     * @throws ServletException
     * @throws IOException
     */
    private void executeRequest(HttpServletRequest req, HttpServletResponse resp, Controller controller) throws ServletException, IOException {
        String page;
        try {
            page = controller.execute(req);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            page = PageName.SQL_ERROR;
        }
        catch (UnAuthorisedException e){
            page = PageName.NOT_AUTHORISED_ERROR;
        }
        req.getRequestDispatcher(page)
                .forward(req, resp);
    }

    /**
     * @param req
     * @return
     */
    private String getUri(HttpServletRequest req) {
        return req.getRequestURI()
                .substring(req.getContextPath().length() + 1);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);

        Controller controller = ControllerFactory.chooseGetMethodController(uri);
        executeRequest(req, resp, controller);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);

        Controller controller = ControllerFactory.choosePostMethodController(uri);
        executeRequest(req, resp, controller);
    }
}
