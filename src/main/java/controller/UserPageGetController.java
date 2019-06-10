package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UserPageGetController implements Controller {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "pages/userPage.jsp";
    }
}
