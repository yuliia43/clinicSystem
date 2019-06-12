package controller;

import exceptions.UnAuthorisedException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public interface Controller {
    String execute(HttpServletRequest request) throws SQLException, UnsupportedEncodingException, UnAuthorisedException;
}
