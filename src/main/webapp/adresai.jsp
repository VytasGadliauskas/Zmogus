<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.data.Zmogus"%>
<%@page import="lt.bit.zmones.data.ZmogusRepo"%>
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
        <title>Zmongaus adresai</title>
    </head>
    <body>
    <%
       int id = 0;
       SaugumoPatikrinimas saugumop = new SaugumoPatikrinimas("idpatikrinimas", request);
       if (saugumop.Atsakymas()){
           id = Integer.parseInt(request.getParameter("id"));
           Zmogus zmogus = ZmogusRepo.getById(id);
           List<Adresas> adresai = AdresasRepo.getAdresai(id);
           %>
                <div class="menu">
                     <div class="addc">
                       <form action="addAdresas" method="POST">
                        <input type="hidden" name="id" value="<%=zmogus.getId()%>" readonly="readonly"><br>
                        <label for="valstybe">Valstybe:</label><input type="text" name="valstybe"><br>
                        <label for="miestas">Miestas:</label><input type="text" name="miestas"><br>
                        <label for="adresas">Adresas:</label><input type="text" name="adresas"><br>
                        <label for="pastokodas">Pasto k:</label><input type="text" name="pastokodas"><br>
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
                         <td><%=alga%></td>
                      </tr>
                   </table>
                   <h2 class="menu">Zmogaus adresai</h2>
                   <table class="lentele">
                      <thead>
                        <tr>
                           <th></th>
                           <th>&nbsp Nr &nbsp</th>
                           <th>&nbsp&nbsp Valstybe &nbsp&nbsp</th>
                           <th>&nbsp&nbsp Miestas &nbsp&nbsp</th>
                           <th>&nbsp&nbsp Adresas &nbsp&nbsp</th>
                           <th>&nbsp&nbsp Pasto kodas &nbsp&nbsp</th>
                        </tr>
                      </thead>
                         <% int eilesNr = 0;
                           for (Adresas adresas: adresai) { %>
                             <tr><td><a href="deleteAdresas?id=<%=id%>&aid=<%=adresas.getId()%>">
                                    <img src="img/remove.png" alt="Delete" width="40" height="42"></a>&nbsp
                                 <a href="editadresas.jsp?id=<%=id%>&aid=<%=adresas.getId()%>">
                                    <img src="img/edit.png" alt="Edit" width="40" height="42"></a></td>
                             <td><%= ++eilesNr %></td>
                             <td><%= adresas.getValstybe()  %></td>
                             <td><%= adresas.getMiestas()  %></td>
                             <td><%= adresas.getAdresas()  %></td>
                             <td><%= adresas.getPastoKodas()  %></td></tr>

                             </tr>
                           <% } %>
                      </table>
         <%
        } else { %>
        <h2 class="menu"> Zmogus nerastas </h2>
        <% }%>
        <div class="menu">
           <a href="index.jsp"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
           <a href="adresai.jsp?id=<%=id%>"><img src="img/refresh.png" alt="Refresh" width="45" height="45"></a>
        </div>
        </div>
    </body>
</html>
