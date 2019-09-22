<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <jsp:include page="elements/head.jsp" />
  <body>
  <jsp:include page="elements/header.jsp" />

  <jsp:include page="login.jsp" />
  <jsp:include page="registration.jsp" />
  <jsp:include page="filter.jsp" />

    <!-- Karussell-->
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
            <img src="img/banner/ouatih.jpg" class="d-block w-100" alt="Once Upon A Time In Hollywood">
            <div class="carousel-caption d-none d-md-block">
              <h5>Once Upon a Time in Hollywood</h5>
              <p>"Bester Film EU WEST BRUDA"- Alle</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="img/banner/popcorn.jpg" class="d-block w-100" alt="Popcorn">
            <div class="carousel-caption d-none d-md-block">
              <h5>Angebot</h5>
              <p>1 Packung Popcorn zum Preis von 2</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="img/banner/filme.jpg" class="d-block w-100" alt="Filme">
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

  <%--
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

     --%>
  <jsp:include page="elements/footer.jsp" />
  </body>
</html>
