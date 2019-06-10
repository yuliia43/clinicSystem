package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AuthorisationGetController implements Controller {
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        return "pages/authorisation.jsp";
    }
}
