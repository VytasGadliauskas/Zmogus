<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.Zmogus"%>
<%@page import="lt.bit.zmones.Kontaktas"%>
<%@page import="lt.bit.zmones.Db"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Zmogus edit</title>
    </head>
    <body>
    <%
      int id = 0;
      if (request.getParameter("zid") != null){
       try {
             id = Integer.parseInt(request.getParameter("zid"));
             int kid = Integer.parseInt(request.getParameter("kid"));
             Zmogus zmogus = Db.getById(id);

             if (zmogus == null) {
                 response.sendRedirect("index.jsp");
                 return;
             }

             %>
               <div class="menu">
                   <div class="edit">
                     <form action="editKontaktas" method="POST">
                     <% for (Kontaktas kontaktas : Db.getListKontaktaibyZmogusId(id)) {
                              if(kid == kontaktas.getId()){ %>
                                 <label for="zid">Zmogaus ID:</label><input class="marked" type="text" name="zid" value="<%=zmogus.getId()%>" readonly="readonly"><br>
                                 <label for="kid">Kontakto ID:</label><input class="marked" type="text" name="kid" value="<%=kontaktas.getId()%>" readonly="readonly"><br>
                                 <label for="tipas">Vardas:</label><input type="text" name="tipas" value="<%=kontaktas.getTipas()%>" required><br>
                                 <label for="reiksme">Pavarde:</label><input type="text" name="reiksme" value="<%=kontaktas.getReiksme()%>" required><br>
                                 <input type="image" src="img/submit-user.png" alt="Add" width="40" height="42">
                              <% break;
                              }
                         } %>
                     </form>
                   </div>
                </div>
        <% } catch (NumberFormatException nfe) {
           }
        }%>
        <a href="kontaktai.jsp?id=<%=id%>"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
        </div>

    </body>
</html>
