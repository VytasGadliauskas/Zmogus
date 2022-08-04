package lt.bit.zmones;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ArrayList<String> ALLOWED_PATHS = new ArrayList<>();
        ALLOWED_PATHS.add("/zmones_web/");
        ALLOWED_PATHS.add("/zmones_web/css/style.css");
        ALLOWED_PATHS.add("/zmones_web/login");
        ALLOWED_PATHS.add("/zmones_web/logout");
        ALLOWED_PATHS.add("/zmones_web/login.jsp");


        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uriPath = httpRequest.getRequestURI();

        System.out.println("------ "+ uriPath);
        System.out.println(httpRequest.getSession(false) +" " +ALLOWED_PATHS.contains(uriPath));

        if (httpRequest.getSession(false) != null) {
            if (httpRequest.getSession(false).getAttribute("userName") == null) {
                if(!ALLOWED_PATHS.contains(uriPath)) {
                    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                    System.out.println("username=null " + uriPath);
                    httpResponse.sendRedirect("/zmones_web/login.jsp");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
           if(!ALLOWED_PATHS.contains(uriPath)) {
               HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
               System.out.println("sesija=null " + uriPath);
               httpResponse.sendRedirect("/zmones_web/login.jsp");
           } else {
               filterChain.doFilter(servletRequest, servletResponse);
           }
        }

    }

    @Override
    public void destroy() {

    }
}
