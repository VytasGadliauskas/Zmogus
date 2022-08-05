package lt.bit.zmones.servlets;

import lt.bit.zmones.components.PasswordEncryptDecrypt;

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
public class Login extends HttpServlet {

    public Login() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;
        String password = null;
        String page = "login.jsp?msg=invalid";
        if (request.getParameter("username") != null && request.getParameter("password") != null) {
            userName = request.getParameter("username").trim();
            password = request.getParameter("password").trim();
            HttpSession session = request.getSession();
            Connection conn = (Connection) request.getAttribute("conn");
            if (conn != null) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(
                        "SELECT password, role FROM Vartotojai WHERE `username`= ?;")) {
                    preparedStatement.setString(1, userName);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        if (PasswordEncryptDecrypt.encryptString(password).equals(rs.getString("password"))) {
                            session.setAttribute("userName", userName);
                            int role = rs.getInt("role");
                            session.setAttribute("roleName", role);
                            page = "index.jsp";
                        } else {
                            page = "login.jsp?msg=invalid";
                        }
                    }
                    rs.close();
                } catch (SQLException e) {
                    page = "login.jsp?msg=invalid";
                }
            } else {
                System.out.println("conn = null");
            }
        }
        response.sendRedirect(page);
    }
}