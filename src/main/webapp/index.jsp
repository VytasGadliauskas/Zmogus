<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.zmones.Zmogus"%>
<%@page import="lt.bit.zmones.ZmogusRepo"%>
<%@page import="lt.bit.zmones.KontaktasRepo"%>
<%@page import="lt.bit.zmones.AdresasRepo"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Zmoniu sarasas</title>
    </head>
    <body>
    <%
      String userName = "";
      int roleName = 0;
      if(session.getAttribute("userName")!=null){
        roleName = (Integer) session.getAttribute("roleName");
        userName = (String) session.getAttribute("userName");
      }%>

    <div id="mySidenav" class="sidenav" >
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">
          <img src="img/cancel.png" alt="Cancel" width="30" height="32">
          </a>

          <div class="menusmall">
               <a href="addzmogus.jsp"><img srcset="img/add-user.png" alt="Add User" width="40" height="42"></a>
          </div>

         <div class="find">
            <form action="findZmogus" method="POST">
               <h3>Surasti zmogu</h3>
               Vardas:  <input name="vardas" required><br>
               Pavarde: <input name="pavarde" required><br>
               <input type="image" src="img/find-user.png" alt="Find" width="40" height="42">
            </form>
         </div>
         <div class="find">
            <form action="findZmogusByContact" method="POST">
               <h3>Surasti zmogu pagal kontakta</h3>
               Tipas:  <input name="tipas" required><br>
               Reiksme: <input name="reiksme" required><br>
               <input type="image" src="img/find-user.png" alt="Find" width="40" height="42">
            </form>
         </div>
         <div class="findadresas">
            <form action="findZmogusByAdresas" method="POST">
               <h3>Surasti zmogu pagal adresa</h3>
               Valstybe: <input name="valstybe" required><br>
               Miestas: <input name="miestas" required><br>
               Adresas: <input name="adresas" required><br>
               Pasto k: <input name="pastokodas" required><br>
               <input type="image" src="img/find-user.png" alt="Find" width="40" height="42">
            </form>
         </div>
    </div>
       <div class="menutop">
           <div class="columns">
               <div><input type="image" onclick="openNav()" src="img/menu.png" alt="Open menu" width="40" height="42"></div>
               <div>Open Menu</div>
           </div>

          <% if (roleName ==1) { %>
           <div class="columns">
                <div><input type="image" onclick="showAdminNustatymai()" src="img/admin-settings.png" alt="Admin Settings" width="40" height="42"></div>
                <div>Nustatymai role: <%= userName%></div>
           </div>
           <% } %>

           <div class="columns">
                 <div><input type="image" onclick="showNustatymai()" src="img/settings.png" alt="Settings" width="40" height="42"></div>
                 <div>Nustatymai</div>
           </div>

           <div class="columns">
               <form action="logout" method="post">
               <div><input type="image" src="img/logout.png" alt="Logout" width="40" height="42"></div>
               </form>
               <div>Logout: <%= userName%></div>
           </div>
       </div>

       <div class="menu">
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
             <th>&nbsp Adresai &nbsp</th>
           </tr>
         </thead>
           <tr>
             <% String filterVardas = null;
                String filterPavarde = null;
                String filterTipas = null;
                String filterReiksme = null;
                String filterValstybe = null;
                String filterMiestas = null;
                String filterAdresas = null;
                String filterPastokodas = null;
                String sortBy = null;
                String order = null;
                if (request.getParameter("filterVardas") != null){
                   filterVardas = request.getParameter("filterVardas");
                }
                if (request.getParameter("filterPavarde") != null){
                   filterPavarde = request.getParameter("filterPavarde");
                }
                if (request.getParameter("filterTipas") != null){
                   filterTipas = request.getParameter("filterTipas");
                }
                if (request.getParameter("filterReiksme") != null){
                   filterReiksme = request.getParameter("filterReiksme");
                }
                if (request.getParameter("filterValstybe") != null){
                   filterValstybe = request.getParameter("filterValstybe");
                }
                if (request.getParameter("filterMiestas") != null){
                   filterMiestas = request.getParameter("filterMiestas");
                }
                if (request.getParameter("filterAdresas") != null){
                   filterAdresas = request.getParameter("filterAdresas");
                }
                if (request.getParameter("filterPastokodas") != null){
                   filterPastokodas = request.getParameter("filterPastokodas");
                }
                if (request.getParameter("sortBy") != null){
                   sortBy = request.getParameter("sortBy").trim();
                }
                if (request.getParameter("order") != null){
                   order = request.getParameter("order").trim();
                }
                List<Zmogus> zmones =  ZmogusRepo.getZmones();
                if (sortBy != null && order != null) {
                   zmones = ZmogusRepo.getListSorted(sortBy,order);
                }
                if (filterVardas !=null && filterPavarde != null) {
                    zmones = ZmogusRepo.filteredZmones(filterVardas,filterPavarde);
                }
                if (filterTipas !=null && filterReiksme != null) {
                    zmones = KontaktasRepo.getZmonesByKontaktas(filterTipas,filterReiksme);
                }
                if (filterValstybe !=null && filterMiestas != null && filterAdresas !=null && filterPastokodas != null) {
                    zmones = AdresasRepo.getZmonesByAdresas(filterValstybe,filterMiestas,filterAdresas,filterPastokodas);
                }

                for (Zmogus zmogus: zmones) {
                  %>
                    <tr>
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
                     <% int kontaktaiSk = 0;
                        if (KontaktasRepo.getKontaktai(zmogus.getId()) != null) { kontaktaiSk = KontaktasRepo.getKontaktai(zmogus.getId()).size();}
                          if ( kontaktaiSk > 0 ) { %>
                               <td><a href="kontaktai.jsp?id=<%=zmogus.getId()%>">
                                  <img src="img/contact.png" alt="Kontaktai" width="40" height="42"></a></td>
                          <% } else { %>
                               <td><a href="kontaktai.jsp?id=<%=zmogus.getId()%>">
                                  <img src="img/add.png" alt="Kontaktai" width="40" height="42"></a></td>
                          <% } %>
                     <% int adresaiSk = 0;
                        if (AdresasRepo.getAdresai(zmogus.getId()) != null) { adresaiSk = AdresasRepo.getAdresai(zmogus.getId()).size();}
                           if ( adresaiSk > 0 ) { %>
                               <td><a href="adresai.jsp?id=<%=zmogus.getId()%>">
                                  <img src="img/address.png" alt="Adresai" width="40" height="42"></a></td>
                           <% } else { %>
                               <td><a href="adresai.jsp?id=<%=zmogus.getId()%>">
                                  <img src="img/add.png" alt="Adresai" width="40" height="42"></a></td>
                        <% } %>
                 </tr>
             <% } %>
           </tr>
        </table>
        </div>
        <div class="menu">
             <a href="index.jsp"><img src="img/refresh.png" alt="Refresh" width="45" height="45"></a>
        </div>


        <!------------------------------------------ Modal langas Nustatymai -->
        <div id="myModalNustatymai" class="modal">
          <div id="modal-content-Nustatymai" class="modal-content">
            <span class="closeNustatymai">&times;</span>
              <div>
                 <h3>Nustatymai</h3>
                 <div class="login">
                    <form action="ChangePasswd" method="POST">
                       <input type="hidden" name="username"><br>
                       <label for="oldpassword">Senas Slaptazodis:</label><input type="password" name="oldpassword"><br>
                       <label for="newpassword1">Naujas Slaptazodis:</label><input type="password" name="newpassword1"><br>
                       <label for="newpassword2">Pakartoti Slaptazodi:</label><input type="password" name="newpassword2"><br>
                       <input type="image" src="img/ok.png" alt="Ok" width="48" height="48">
                    </form>
                  </div>
                 <p>Fonai:</p>
                 <div class="footer-fonai">
                       <img onclick="setFonas('img/fonas/1.jpg')" src="img/fonas/1.jpg" alt="fonas1" width="60" height="60">
                       <img onclick="setFonas('img/fonas/2.jpg')" src="img/fonas/2.jpg" alt="fonas2" width="60" height="60">
                       <img onclick="setFonas('img/fonas/3.jpg')" src="img/fonas/3.jpg" alt="fonas3" width="60" height="60">
                       <img onclick="setFonas('img/fonas/4.jpg')" src="img/fonas/4.jpg" alt="fonas4" width="60" height="60">
                       <img onclick="setFonas('img/fonas/5.jpg')" src="img/fonas/5.jpg" alt="fonas5" width="60" height="60">
                       <img onclick="setFonas('img/fonas/6.jpg')" src="img/fonas/6.jpg" alt="fonas6" width="60" height="60">
                       <img onclick="setFonas('img/fonas/7.jpg')" src="img/fonas/7.jpg" alt="fonas7" width="60" height="60">
                   </div>
              </div>
          </div>
        </div>

         <!------------------------------------------ Modal langas Admin Nustatymai -->
                <div id="myModalAdminNustatymai" class="modal">
                  <div id="modal-content-AdminNustatymai" class="modal-content">
                    <span class="closeAdminNustatymai">&times;</span>
                      <div>
                         <h3>Admin Nustatymai</h3>
                         <p>Vartotojai:</p>
                         <div class="footer-fonai">

                         </div>
                      </div>
                  </div>
                </div>

        <script src="js/script.js"></script>
        <script>
        function openNav() {
          document.getElementById("mySidenav").style.width = "400px";
        }

        function closeNav() {
          document.getElementById("mySidenav").style.width = "0";
        }
        </script>

    </body>
</html>
