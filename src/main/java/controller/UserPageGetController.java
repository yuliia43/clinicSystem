package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class UserPageGetController implements Controller {
    /**
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "pages/userPage.jsp";
    }
}
