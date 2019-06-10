package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public interface Controller {
    String execute(HttpServletRequest request) throws SQLException;
}
