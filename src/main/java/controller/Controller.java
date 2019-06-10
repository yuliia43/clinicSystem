package controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Controller {
    String execute(HttpServletRequest request) throws SQLException;
}
