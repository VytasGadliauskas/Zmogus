<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/script.js"></script>
        <title>Login</title>
    </head>
<body>
 <div class="columns">
    <img class="imgLogo" src="img/peoples.png" alt="logo"  width="600" height="400">

    <div class="login">
       <%
         String msg=request.getParameter("msg");
         if("invalid".equals(msg)) { %>
            <p style="color:red; font-weight: bold;"> Neteisingas vartotojas arba slaptazodis. </p>
         <%} %>
       <form action="login" method="POST">
          <label for="username">Vartotojas:</label><input type="text" name="username"><br>
          <label for="password">Slaptazodis:</label><input type="password" name="password"><br>
          <input type="image" src="img/ok.png" alt="Ok" width="48" height="48">
       </form>

    </div>
 </div>
</body>
</html>