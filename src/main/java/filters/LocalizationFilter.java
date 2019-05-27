package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";

    private static String setLocale;
    private static String setBundle;

    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        setLocale = filterConfig.getInitParameter(LOCALE);
        setBundle = filterConfig.getInitParameter(BUNDLE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String bundle = request.getParameter(BUNDLE);
        if (bundle == null) {
            String session = (String) request.getSession().getAttribute(BUNDLE);
            if (session != null) {
                setBundle = session;
            }
        } else {
            setBundle = bundle;
        }
        request.getSession().setAttribute(BUNDLE, setBundle);

        String locale = request.getParameter(LOCALE);
        if (locale == null) {
            String session = (String) request.getSession().getAttribute(LOCALE);
            if (session != null) {
                setLocale = session;
            }
        } else {
            setLocale = locale;
        }
        request.getSession().setAttribute(LOCALE, setLocale);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("Filter done!" + " locale: " + setLocale + " bundle " + setBundle);
        } catch (IOException e) {
            logger.error("IOException occured" + e.getStackTrace());
            throw e;
        } catch (ServletException e) {
            logger.error("ServletException occured" + e.getStackTrace());
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}
