
/////////////////////////////////////////////////   Modal Nustatymai kodas

    let modalNustatymai = document.getElementById("myModalNustatymai");
    function showNustatymai() {
      modalNustatymai.style.display = "block";
    }

    let spanNustatymai = document.getElementsByClassName("closeNustatymai")[0];
    spanNustatymai.onclick = function() {
      modalNustatymai.style.display = "none";
    }

/////////////////////////////////////////////////   Modal Admin Nustatymai kodas

    let modalAdminNustatymai = document.getElementById("myModalAdminNustatymai");
    function showAdminNustatymai() {
      modalAdminNustatymai.style.display = "block";
    }

    let spanAdminNustatymai = document.getElementsByClassName("closeAdminNustatymai")[0];
    spanAdminNustatymai.onclick = function() {
      modalAdminNustatymai.style.display = "none";
    }

//////////////////////////////////////// Isvaizdos Temos

function setCSS(css_failas) {
  // document.cookie = `todo_css_file=${css_failas}; expires=${new Date(new Date().getTime()+1000*60*60*24*365).toGMTString()}; path=/`;
  // window.location.reload();
}

function setFonas(fonas) {
  // document.cookie = `todo_fono_file=${fonas}; expires=${new Date(new Date().getTime()+1000*60*60*24*365).toGMTString()}; path=/`;
  document.body.style.backgroundImage = `url('${fonas}')`;
  document.body.style.color = 'white';
  let modalNustatymaiFonas = document.getElementById('modal-content-Nustatymai');
  modalNustatymaiFonas.style.backgroundImage = `url('${fonas}')`;
  let modalAdminNustatymaiFonas = document.getElementById('modal-content-AdminNustatymai');
  modalAdminNustatymaiFonas.style.backgroundImage = `url('${fonas}')`;
}