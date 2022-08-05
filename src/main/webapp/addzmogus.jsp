<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.data.Zmogus"%>
<%@page import="lt.bit.zmones.data.Db"%>

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
             <label for="alga">Alga:</label><input type="number" name="alga" min="0" step="any" ><br>
             <input type="image" src="img/add-user.png" alt="Add" width="40" height="42">
            </form>
          </div>
       </div>
       <div>
         <div class="menu">
              <a href="index.jsp"><img src="img/cancel.png" alt="Cancel" width="45" height="45"></a>
         </div>
        </div>
    </body>
</html>
