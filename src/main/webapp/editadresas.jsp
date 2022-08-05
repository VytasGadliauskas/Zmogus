<%@page import="java.util.List"%>
<%@page import="lt.bit.zmones.data.Zmogus"%>
<%@page import="lt.bit.zmones.data.Adresas"%>
<%@page import="lt.bit.zmones.data.AdresasRepo"%>
<%@page import="lt.bit.zmones.components.SaugumoPatikrinimas"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Adresas edit</title>
    </head>
    <body>
    <%
      int id = 0;
      int aid = 0;
      SaugumoPatikrinimas saugumop = new SaugumoPatikrinimas("idpatikrinimas", request);
      if (saugumop.Atsakymas()){
         id = Integer.parseInt(request.getParameter("id"));
         aid = Integer.parseInt(request.getParameter("aid"));
         Adresas adresas = AdresasRepo.getById(aid);
         %>
               <div class="menu">
                   <div class="edit">
                     <form action="editAdresas" method="POST">
                         <input type="hidden" name="id" value="<%=id%>" readonly="readonly"><br>
                         <input type="hidden" name="aid" value="<%=adresas.getId()%>" readonly="readonly"><br>
                         <label for="valstybe">Valstybe:</label><input type="text" name="valstybe" value="<%=adresas.getValstybe()%>" required><br>
                         <label for="miestas">Miestas:</label><input type="text" name="miestas" value="<%=adresas.getMiestas()%>" required><br>
                         <label for="adresas">Adresas:</label><input type="text" name="adresas" value="<%=adresas.getAdresas()%>" required><br>
                         <label for="pastokodas">Pasto kodas:</label><input type="text" name="pastokodas" value="<%=adresas.getPastoKodas()%>" required><br>
                         <input type="image" src="img/submit-user.png" alt="Add" width="40" height="42">
                     </form>
                   </div>
                </div>
        <%
           } else { %>
              <h2 class="menu"> Adresas nerastas </h2>
        <% } %>
        <a class="menu" href="adresai.jsp?id=<%=id%>"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
        </div>

    </body>
</html>
