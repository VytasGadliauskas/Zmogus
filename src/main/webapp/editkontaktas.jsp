<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.data.Zmogus"%>
<%@page import="lt.bit.zmones.data.ZmogusRepo"%>
<%@page import="lt.bit.zmones.data.Kontaktas"%>
<%@page import="lt.bit.zmones.data.KontaktasRepo"%>
<%@page import="lt.bit.zmones.components.SaugumoPatikrinimas"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Zmogus kontaktas edit</title>
    </head>
    <body>
    <%
      int id = 0;
      int kid = 0;
      SaugumoPatikrinimas saugumop = new SaugumoPatikrinimas("idpatikrinimas", request);
      if (saugumop.Atsakymas()){
        id = Integer.parseInt(request.getParameter("id"));
        kid = Integer.parseInt(request.getParameter("kid"));
        Zmogus zmogus = ZmogusRepo.getById(id);
        Kontaktas kontaktas = KontaktasRepo.getById(kid);
             %>
               <div class="menu">
                   <div class="edit">
                     <form action="editKontaktas" method="POST">
                         <input type="hidden" name="id" value="<%=zmogus.getId()%>" readonly="readonly"><br>
                         <input type="hidden" name="kid" value="<%=kontaktas.getId()%>" readonly="readonly"><br>
                         <label for="tipas">Vardas:</label><input type="text" name="tipas" value="<%=kontaktas.getTipas()%>" required><br>
                         <label for="reiksme">Pavarde:</label><input type="text" name="reiksme" value="<%=kontaktas.getReiksme()%>" required><br>
                         <input type="image" src="img/submit-user.png" alt="Add" width="40" height="42">
                     </form>
                   </div>
                </div>
        <% } else { %>
            <h2 class="menu"> Zmogus nerastas </h2>
        <% } %>
        <a class="menu" href="kontaktai.jsp?id=<%=id%>"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
        </div>

    </body>
</html>
