package lt.bit.zmones.servlets;

import lt.bit.zmones.components.SaugumoPatikrinimas;
import lt.bit.zmones.data.Zmogus;
import lt.bit.zmones.data.ZmogusRepo;

import java.io.IOException;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ZmogusAdd", urlPatterns = {"/addZmogus"})
public class ZmogusAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        SaugumoPatikrinimas saugumoPatikrinimas = new SaugumoPatikrinimas("zmogusadd", request);
        if (saugumoPatikrinimas.Atsakymas()) {
            /// Gauti ID
            String vardas = request.getParameter("vardas");
            String pavarde = request.getParameter("pavarde");
            Zmogus zmogus = new Zmogus(vardas, pavarde);
            if (!"".equals(request.getParameter("gdata"))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date gimimoData = sdf.parse(request.getParameter("gdata"));
                    zmogus.setGimimoData(gimimoData);
                } catch (ParseException e) {
                    //  skip
                }
            }
            if (!"".equals(request.getParameter("alga"))) {
                BigDecimal alga = new BigDecimal(request.getParameter("alga"));
                zmogus.setAlga(alga);
            }
            ZmogusRepo.addZmogus(zmogus);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("klaida.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
