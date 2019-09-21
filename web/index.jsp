<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <jsp:include page="elements/head.jsp" />
  <body>
  <jsp:include page="elements/header.jsp" />

  <jsp:include page="login.jsp" />


<%--  <!-- Karussell-->
  <!-- Bitte nur Bilder der größe 1656x630 einfügen-->
  <div class="karussell">
    <div id="karussellangebote" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#karussellangebote" data-slide-to="0" class="active"></li>
        <li data-target="#karussellangebote" data-slide-to="1"></li>
        <li data-target="#karussellangebote" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="banner/ouatih.jpg" class="d-block w-100" alt="Once Upon A Time In Hollywood">
          <div class="carousel-caption d-none d-md-block">
            <h5>Once Upon a Time in Hollywood</h5>
            <p>"Bester Film EU WEST BRUDA"- Alle</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="banner/popcorn.jpg" class="d-block w-100" alt="Popcorn">
          <div class="carousel-caption d-none d-md-block">
            <h5>Angebot</h5>
            <p>1 Packung Popcorn zum Preis von 2</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="banner/filme.jpg" class="d-block w-100" alt="Filme">
          <div class="carousel-caption d-none d-md-block">
            <h5>Movie Night</h5>
            <p>An diesem Abend zeigen wir Filme.</p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#karussellangebote" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#karussellangebote" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>

  <!--Filme-->
  <div class="container">
    <div class="row">
      <div class="col-sm">
        <div class="card" style="width: 20rem;">
          <img src="covers/onceupon.jpg" class="card-img-top" alt="Once Upon a Time in Hollywood">
          <div class="card-body">
            <h5 class="card-title">Once Upon a Time in Hollywood</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <a href="#" class="btn btn-primary">Zum Film</a>
          </div>
        </div>
      </div>
      <div class="col-sm">
        <div class="card" style="width: 20rem;">
          <img src="covers/johnwick3.jpg" class="card-img-top" alt="John Wick 3">
          <div class="card-body">
            <h5 class="card-title">John Wick 3</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <a href="#" class="btn btn-primary">Zum Film</a>
          </div>
        </div>
      </div>
      <div class="col-sm">
        <div class="card" style="width: 20rem;">
          <img src="covers/lionking.jpg" class="card-img-top" alt="König der Löwen">
          <div class="card-body">
            <h5 class="card-title">König der Löwen</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <a href="#" class="btn btn-primary">Zum Film</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Login Screen-->
  <div class="modal fade" id="loginDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalCenterTitle">Login</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="inputEmail">E-Mail</label>
              <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="E-Mail Adresse">
              <small id="emailHelp" class="form-text text-muted">Wir geben deine E-Mail Adresse nicht weiter.</small>
            </div>
            <div class="form-group">
              <label for="inputPassword">Passwort</label>
              <input type="password" class="form-control" id="inputPassword" placeholder="Passwort">
            </div>
            <div class="form-group form-check">
              <input type="checkbox" class="form-check-input" id="stayLoggedIn">
              <label class="form-check-label" for="stayLoggedIn">Angemeldet bleiben</label>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
          <button type="button" class="btn btn-primary">Einloggen</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Registrieren Screen-->
  <div class="modal fade" id="registerDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="registerDialogTitle">Registrieren</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="inputEmail">E-Mail</label>
              <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="E-Mail Adresse">
              <small id="emailHelp" class="form-text text-muted">Wir werden dir eine Verifizierungsmail schicken.</small>
            </div>
            <div class="form-group">
              <label for="inputPassword">Passwort</label>
              <input type="password" class="form-control" id="inputPasswordReg" placeholder="Passwort">
            </div>
            <div class="form-group">
              <label for="inputPassword">Passwort wiederholen</label>
              <input type="password" class="form-control" id="inputPasswordRegWdh" placeholder="Passwort">
            </div>
            <div class="form-group">
              <label for="formControlRange">Telefonnummer</label>
              <input type="range" class="form-control-range" id="formControlRange" min="0" max="9999999999999" oninput="test()">
              <p id="telefonout"></p>
            </div>
            <div class="form-group form-check">
              <input type="checkbox" class="form-check-input" id="receiveMails">
              <label class="form-check-label" for="stayLoggedIn">Ich möchte tägliche E-Mails und Anrufe zu neuen Angeboten von Multiflex erhalten. Außerdem bin ich damit einverstanden, dass meine Daten an dritte Verkauft werden.</label>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
          <button type="button" class="btn btn-primary">Registrieren</button>
        </div>
      </div>
    </div>
  </div>

  <script type="text/javascript">function test()
  {
    var slider=document.getElementById("formControlRange");
    var output=document.getElementById("telefonout");
    output.innerHTML=slider.value;
  }</script>

  <!--Pages-->
  <nav aria-label="Filmseite auswahl" class="pagination-navigation">
    <ul class="pagination">
      <li class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
  </nav>
  <!--Footer-->
  <div class="jumbotron text-center footer" style="border-top: 4px solid #4a9be8">
    <div class="container">
      <div class="row text-center">
        <div class="col">
          <h5>Unsere Kinos: </h5>
          <p>Augsburg, Berlin, Bremen, Celle, Chemnitz, Hamburg, Hannover, Heidelberg, Ingolstadt, Karlsruhe, Kassel, Köln, Mannheim, München, Potsdam, Stuttgart</p>
        </div>
      </div>
      <div class="row text-center">
        <div class="col">
          <a href="#">Kontakt</a>
        </div>
        <div class="col">
          <a href="#">Impressum</a>
        </div>
        <div class="col">
          <a href="anfahrt.html">Anfahrt</a>
        </div>
      </div>
      <div class="row text-center">
        <div class="col">
          <a>&copy; Cineflex 2019</a>
        </div>
      </div>
    </div>
  </div>




  <form action="LoginHandler" method="post">
    Benutzername :<input type="text" name="username"> <br>
    Passwort :<input type="password" name="password"><br>
    <input type="submit" value="Login">
  </form>--%>
  <jsp:include page="elements/footer.jsp" />
  </body>
</html>
