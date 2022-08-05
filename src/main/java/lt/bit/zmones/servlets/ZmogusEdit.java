package lt.bit.zmones.servlets;


import lt.bit.zmones.components.SaugumoPatikrinimas;
import lt.bit.zmones.data.Zmogus;
import lt.bit.zmones.data.ZmogusRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ZmogusEdit", urlPatterns = {"/editZmogus"})
public class ZmogusEdit extends HttpServlet {

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
            throws ServletException, IOException {
        SaugumoPatikrinimas saugumoPatikrinimas = new SaugumoPatikrinimas("zmogusadd", request);
        if (saugumoPatikrinimas.Atsakymas()) {
            String ids = request.getParameter("id");
            try {
                int id = Integer.parseInt(ids);
                Zmogus z = ZmogusRepo.getById(id);
                if (z != null) {
                    if (!"".equals(request.getParameter("vardas")) && !"".equals(request.getParameter("pavarde"))) {
                        String vardas = request.getParameter("vardas");
                        z.setVardas(vardas);
                        String pavarde = request.getParameter("pavarde");
                        z.setPavarde(pavarde);
                        if (!"".equals(request.getParameter("gdata"))) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date gimimoData = sdf.parse(request.getParameter("gdata"));
                                z.setGimimoData(gimimoData);
                            } catch (ParseException e) {
                                response.sendRedirect("klaida.jsp");
                            }
                        }
                        if (!"".equals(request.getParameter("alga"))) {
                            BigDecimal alga = new BigDecimal(request.getParameter("alga"));
                            z.setAlga(alga);
                        }
                        ZmogusRepo.updateZmogus(z);
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                }
            } catch (Exception ex) {
                // ignore
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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