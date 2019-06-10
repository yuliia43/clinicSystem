package controller;

import javax.servlet.http.HttpServletRequest;

public class AdminPageGetController implements Controller {
    @Override
    public String execute(HttpServletRequest request) {
        return "pages/adminPage.jsp";
    }
}
