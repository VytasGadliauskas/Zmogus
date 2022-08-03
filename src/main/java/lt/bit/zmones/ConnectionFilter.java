package lt.bit.zmones;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebFilter(filterName = "ConnectionFilter", urlPatterns = {"/login.jsp"})
public class ConnectionFilter implements Filter {
    private static final String URL = "jdbc:mysql://localhost:3306/Adresu_knyga";
    private static final String USER = "user";
    private static final String PASS = "user123";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
        ) {
            request.setAttribute("conn", conn);
            chain.doFilter(request, response);
        } catch (SQLException ex) {
            throw new ServerException("Failed to connect to DB", ex);
        }
    }
    @Override
    public void destroy() {
    }
}