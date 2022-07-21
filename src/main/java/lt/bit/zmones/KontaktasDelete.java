package lt.bit.zmones;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "KontaktasDelete", urlPatterns = {"/deleteKontaktas"})
public class KontaktasDelete extends HttpServlet {

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
        String zids = request.getParameter("zid");
        String kids = request.getParameter("kid");

        try {
            int zid = Integer.parseInt(zids);
            int kid = Integer.parseInt(kids);
            Zmogus z = Db.getById(zid);
            if (z != null) {
                List<Kontaktas> kontaktai = Db.getListKontaktaibyZmogusId(zid);
                for (int i = 0; i < kontaktai.size(); i++) {
                    if(kid == kontaktai.get(i).getId()){
                        kontaktai.remove(kontaktai.get(i));
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            response.sendRedirect("index.jsp");
        } finally {
            response.sendRedirect("kontaktai.jsp?id="+zids);
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
