package filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PaginationFilter implements Filter {
    int startingIndex;

    /**
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        startingIndex = Integer
                .parseInt(filterConfig.getInitParameter("itemsCountStartingIdx"));
    }

    /**
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String idx = req.getParameter("startIdx");
        if (idx != null) {
            startingIndex = Integer.parseInt(idx);
        }
        req.setAttribute("startIdx", startingIndex);
        req.setAttribute("endIdx", startingIndex + 10);
        chain.doFilter(req, resp);
    }

    /**
     *
     */
    @Override
    public void destroy() {

    }
}
