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

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = "index.jsp";
        if (req.getParameter("username") != null && req.getParameter("oldpasswd") != null && req.getParameter("username") != null ) {
            if (!"".equals(req.getParameter("username")) && !"".equals(req.getParameter("oldpasswd")) &&
                    !"".equals(req.getParameter("username"))) {
                String userName = req.getParameter("username").trim();
                String oldpassword = req.getParameter("oldpassword").trim();
                String newpassword = req.getParameter("newpassword").trim();

                HttpSession session = req.getSession();
                Connection conn = (Connection) req.getAttribute("conn");
                if (conn != null) {
                    try (PreparedStatement preparedStatement = conn.prepareStatement(
                            "SELECT password, role FROM Vartotojai WHERE `username`= ?;")) {
                        preparedStatement.setString(1, userName);
                        ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
                            if (PasswordEncryptDecrypt.encryptString(oldpassword).equals(rs.getString("password"))) {
                                int role = rs.getInt("role");
                                String newpasswd = PasswordEncryptDecrypt.encryptString(newpassword);
                                changePassword(conn, userName , newpassword);
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
                    resp.sendRedirect("klaida.jsp");
                }
            } else {
                resp.sendRedirect("klaida.jsp");
            }
        }
        resp.sendRedirect(page);
    }

    private boolean changePassword(Connection conn, String userName, String newpassword){
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE Vartotojai SET `password`=? WHERE `username`= ?;")) {
            preparedStatement.setString(1, PasswordEncryptDecrypt.encryptString(newpassword));
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Klaida "+e.getMessage());
        }
        return false;
    }
}

