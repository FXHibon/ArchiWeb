package fr.epsi.tp.ws.security;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fx on 09/03/2015.
 * Forbid access to rest api when user is not authenticated
 * initParam => "public" contains ";" separated rest methods name that should be public
 */
@WebFilter(filterName = "ApiFilter", urlPatterns = {"/rest/*"}, initParams = {@WebInitParam(name = "public", value = "/connect")})
public class ApiFilter implements Filter {

    private static Logger logger = Logger.getLogger(ApiFilter.class);

    private List<String> publicMethods;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String path = httpServletRequest.getPathInfo();

        logger.info("API accessed: " + path);

        // public method?
        if (!publicMethods.contains(path)) {
            if (isAuthenticated(httpServletRequest)) {
                chain.doFilter(req, resp);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    /**
     * @param httpServletRequest
     * @return
     */
    private boolean isAuthenticated(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        return session != null && session.getAttribute("token") != null;
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
