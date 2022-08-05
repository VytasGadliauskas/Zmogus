<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.data.Zmogus"%>
<%@page import="lt.bit.zmones.data.ZmogusRepo"%>
<%@page import="lt.bit.zmones.components.SaugumoPatikrinimas"%>
<%@page import="lt.bit.zmones.data.Kontaktas"%>
<%@page import="lt.bit.zmones.data.KontaktasRepo"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Zmogaus kontaktai</title>
    </head>
    <body>
    <%
       int id = 0;
       SaugumoPatikrinimas saugumop = new SaugumoPatikrinimas("idpatikrinimas", request);
       if (saugumop.Atsakymas()){
           id = Integer.parseInt(request.getParameter("id"));
           Zmogus zmogus = ZmogusRepo.getById(id);
           List<Kontaktas> kontaktai = KontaktasRepo.getKontaktai(id);
           %>

       <div class="menu">
          <div class="addc">
            <form  action="addKontaktas" method="POST">
             <input type="hidden" name="zmogaus_id" value="<%=zmogus.getId()%>" readonly="readonly"><br>
             <label for="tipas">Tipas:</label><input type="text" name="tipas"><br>
             <label for="reiksme">Reiksme:</label><input type="text" name="reiksme"><br>
             <input type="image" src="img/add.png" alt="Add" width="40" height="42">
            </form>
          </div>
       </div>

        <div>
        <table class="lentele">
         <thead>
           <tr>
             <th>&nbsp ID &nbsp</th>
             <th>&nbsp&nbsp Vardas &nbsp&nbsp</th>
             <th>&nbsp&nbsp Pavarde &nbsp&nbsp</th>
             <th>&nbsp&nbsp Gimimo data &nbsp&nbsp</th>
             <th>&nbsp&nbsp Alga [Eu] &nbsp&nbsp</th>
           </tr>
         </thead>
           <tr>
              <td><%= zmogus.getId() %></td>
              <td><%= zmogus.getVardas()  %></td>
              <td><%= zmogus.getPavarde()  %></td>
              <%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  String gdata;
                  if (zmogus.getGimimoData() != null) {
                     gdata = String.valueOf(sdf.format(zmogus.getGimimoData()));
                  } else {
                     gdata = "-";
                  } %>
              <td><%= gdata  %></td>
              <%  String alga;
                  if (zmogus.getAlga() != null) {
                     alga = String.valueOf(zmogus.getAlga());
                  } else {
                     alga = "0";
                  } %>
              <td><%= alga  %></td>
           </tr>
        </table>
        <h2 class="menu">Zmogaus kontaktai</h2>
        <table class="lentele">
           <thead>
             <tr>
                <th></th>
                <th>&nbsp&nbsp Nr &nbsp&nbsp</th>
                <th>&nbsp&nbsp Tipas &nbsp&nbsp</th>
                <th>&nbsp&nbsp Reiksme &nbsp&nbsp</th>
             </tr>
           </thead>
              <% int eilesNr = 0;
                for (Kontaktas kontaktas: kontaktai) { %>
                  <tr><td><a href="deleteKontaktas?id=<%=zmogus.getId()%>&&kid=<%=kontaktas.getId()%>">
                         <img src="img/remove.png" alt="Delete" width="40" height="42"></a>&nbsp
                      <a href="editkontaktas.jsp?id=<%=zmogus.getId()%>&&kid=<%=kontaktas.getId()%>">
                         <img src="img/edit.png" alt="Edit" width="40" height="42"></a></td>
                  <td><%= ++eilesNr %></td>
                  <td><%= kontaktas.getTipas()  %></td>
                  <td><%= kontaktas.getReiksme()  %></td></tr>
                <% } %>
           </table>
        <% } else {
            response.sendRedirect("klaida.jsp");
        }%>

        <div class="menu">
           <a href="index.jsp"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
           <a href="kontaktai.jsp?id=<%=id%>"><img src="img/refresh.png" alt="Refresh" width="45" height="45"></a>
        </div>
        </div>
    </body>
</html>
