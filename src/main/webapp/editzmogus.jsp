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
        <title>Zmogus edit</title>
    </head>
    <body>
    <%
      if (request.getParameter("id") != null){
       try {
             int id = Integer.parseInt(request.getParameter("id"));
             Zmogus zmogus = Db.getById(id);
             %>
               <div class="menu">
                   <div class="edit">
                     <form action="editZmogus" method="POST">
                      <label for="id">ID:</label><input class="marked" type="text" name="id" value="<%=zmogus.getId()%>" readonly="readonly"><br>
                      <label for="vardas">Vardas:</label><input type="text" name="vardas" value="<%=zmogus.getVardas()%>"><br>
                      <label for="pavarde">Pavarde:</label><input type="text" name="pavarde" value="<%=zmogus.getPavarde()%>"><br>
                      <label for="gdata">Gimimo data:</label>
                      <%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                               String gdata;
                                               if (zmogus.getGimimoData() != null) {
                                                  gdata = String.valueOf(sdf.format(zmogus.getGimimoData()));
                                               } else {
                                                  gdata = "";
                                               } %>
                       <input type="date" name="gdata" placeholder="yyyy-mm-dd" value="<%= gdata %>"><br>
                      <% String alga;
                                                 if (zmogus.getAlga() != null) {
                                                     alga = String.valueOf(zmogus.getAlga());
                                                 } else {
                                                     alga = "0";
                                                 } %>
                      <label for="alga">Alga:</label><input type="number" name="alga" min="1" value="<%= alga %>"><br>
                      <input type="image" src="img/submit-user.png" alt="Add" width="40" height="42">
                     </form>
                   </div>
                </div>
        <% } catch (NumberFormatException nfe) {
           }
        }%>
        <a href="index.jsp"><img src="img/cancel.png" alt="Refresh" width="45" height="45"></a>
        </div>

    </body>
</html>
