<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.Zmogus"%>
<%@page import="lt.bit.zmones.Db"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Zmoniu sarasas</title>
    </head>
    <body>

       <div class="menu">
          <div class="add">
            <form action="addZmogus" method="POST">
             <label for="vardas">Vardas:</label><input type="text" name="vardas"><br>
             <label for="pavarde">Pavarde:</label><input type="text" name="pavarde"><br>
             <label for="gdata">Gimimo data:</label>
               <input type="date" name="gdata" placeholder="yyyy-mm-dd"><br>
             <label for="alga">Alga:</label><input type="number" name="alga" min="1" step="any" ><br>
             <input type="image" src="img/add-user.png" alt="Add" width="40" height="42">
            </form>
          </div>
          <div class="find">
            <form action="findZmogus" method="POST">
               Vardas:  <input name="vardas" required><br>
               Pavarde: <input name="pavarde" required><br>
               <input type="image" src="img/find-user.png" alt="Find" width="40" height="42">
            </form>
         </div>
       </div>
       <div>
        <table class="lentele">
         <thead>
           <tr>
             <th></th>
             <th>&nbsp ID &nbsp</th>
             <th>&nbsp <a href="index.jsp?sortBy=vardas&order=asc"><img srcset="img/sortasc.png" alt="SortAsc" width="15" height="15"></a>
             Vardas <a href="index.jsp?sortBy=vardas&order=desc"><img srcset="img/sortdesc.png" alt="SortDesc" width="15" height="15"></a> &nbsp</th>
             <th>&nbsp <a href="index.jsp?sortBy=pavarde&order=asc"><img srcset="img/sortasc.png" alt="SortAsc" width="15" height="15"></a>
             Pavarde <a href="index.jsp?sortBy=pavarde&order=desc"><img srcset="img/sortdesc.png" alt="SortDesc" width="15" height="15"></a> &nbsp</th>
             <th>&nbsp Gimimo data &nbsp</th>
             <th>&nbsp Alga [Eu] &nbsp</th>
             <th>&nbsp Kontaktai &nbsp</th>
           </tr>
         </thead>
           <tr>
             <% int marked = 0;
                String sortBy = null;
                String order = null;
                if (request.getParameter("marked") != null){
                   marked = Integer.parseInt(request.getParameter("marked"));
                }
                if (request.getParameter("sortBy") != null){
                   sortBy = request.getParameter("sortBy").trim();
                }
                if (request.getParameter("order") != null){
                   order = request.getParameter("order").trim();
                }
                List<Zmogus> zmones =  Db.getList();
                if (sortBy != null && order != null) {
                zmones = Db.getListSorted(sortBy,order);
                }
                for (Zmogus zmogus: zmones) {
                  if (marked == zmogus.getId()){ %>
                     <tr class="marked">
                <%  } else { %>
                    <tr>
                <% }  %>
                     <td><a href="deleteZmogus?id=<%= zmogus.getId() %>">
                              <img src="img/delete-user.png" alt="Delete" width="40" height="42"></a>&nbsp
                         <a href="editzmogus.jsp?id=<%= zmogus.getId() %>">
                              <img src="img/edit-user.png" alt="Edit" width="40" height="42"></a></td>
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
                     <% if (zmogus.getKontaktai().size() > 0) { %>
                        <td><a href="kontaktai.jsp?id=<%=zmogus.getId()%>">
                              <img src="img/contact.png" alt="Kontaktai" width="40" height="42"></a></td>
                        <% } else { %>
                        <td><a href="kontaktai.jsp?id=<%=zmogus.getId()%>">
                             <img src="img/add.png" alt="Kontaktai" width="40" height="42"></a></td>
                       <%  } %>
                 </tr>
             <% } %>
           </tr>
        </table>
            <div class="menu">
                 <a href="index.jsp"><img src="img/refresh.png" alt="Refresh" width="45" height="45"></a>

            </div>
        </div>
    </body>
</html>
