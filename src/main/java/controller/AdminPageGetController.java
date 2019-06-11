package controller;

import commonlyUsedStrings.PageName;

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
        return PageName.ADMIN_PAGE;
    }
}
