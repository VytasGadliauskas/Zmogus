package lt.bit.zmones;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdresasAdd", urlPatterns = {"/addAdresas"})
public class AdresasAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SaugumoPatikrinimas saugumoPatikrinimas = new SaugumoPatikrinimas("adresasadd", request);
        if (saugumoPatikrinimas.Atsakymas()) {

            String zmogaus_ids = request.getParameter("id");
            String valstybe = request.getParameter("valstybe").trim();
            String miestas = request.getParameter("miestas").trim();
            String adresasp = request.getParameter("adresas").trim();
            String pastokodas = request.getParameter("pastokodas").trim();

            try {
                int zmogaus_id = Integer.parseInt(zmogaus_ids);
                Zmogus z = ZmogusRepo.getById(zmogaus_id);
                if (z != null) {
                    Adresas adresas = new Adresas(zmogaus_id, adresasp, miestas, pastokodas, valstybe);
                    AdresasRepo.addAdresas(adresas);
                }
            } catch (Exception ex) {
                response.sendRedirect("klaida.jsp");
            } finally {
                response.sendRedirect("adresai.jsp?id=" + zmogaus_ids);
            }
        } else {
            response.sendRedirect("klaida.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
