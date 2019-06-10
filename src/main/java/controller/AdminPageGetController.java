package controller;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminPageGetController implements Controller {


    /**
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        return "pages/adminPage.jsp";
    }
}
