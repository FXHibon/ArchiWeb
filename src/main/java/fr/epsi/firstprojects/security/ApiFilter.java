package fr.epsi.firstprojects.security;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fx on 09/03/2015.
 * Forbid access to rest api when user is not authenticated
 */
@WebFilter(filterName = "ApiFilter", urlPatterns = {"/rest/*"}, initParams = {@WebInitParam(name = "public", value = "/connect")})
public class ApiFilter implements Filter {

    private static Logger logger = Logger.getLogger(ApiFilter.class);

    private List<String> publicMethods;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String path = ((HttpServletRequest) req).getContextPath();
        logger.error(path);

//        if (((HttpServletRequest)req).getContextPath())
        chain.doFilter(req, resp);
    }

    /**
     * "public" init params contains comma separated methods name that should be public
     *
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        logger.error("filter init");
        publicMethods = Arrays.asList(config.getInitParameter("public").split(";"));
    }

}
