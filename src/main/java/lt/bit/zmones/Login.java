package lt.bit.zmones;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet{

    public Login() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = null;
        String password = null;
        if (request.getParameter("username") != null) {
            userName = request.getParameter("username").trim();
        }
        if (request.getParameter("password") != null) {
            password = request.getParameter("password").trim();
        }

        HttpSession session=request.getSession();
        session.setAttribute("userName", userName);

        Connection conn = (Connection) request.getAttribute("conn");
        if(conn != null ) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT password FROM Vartotojai WHERE `username`= ?;")) {
                preparedStatement.setString(1, userName);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    if(password.equals("1234"))
                    {
                        response.sendRedirect("index.jsp");
                    }
                    else
                    {
                        response.sendRedirect("login.jsp?msg=invalid");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                response.sendRedirect("klaida.jsp");
            }
        }
        response.sendRedirect("klaida.jsp");
    }
}