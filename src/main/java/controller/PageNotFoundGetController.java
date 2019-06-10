package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class PageNotFoundGetController implements Controller {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "errorPages/404.jsp";
    }
}
