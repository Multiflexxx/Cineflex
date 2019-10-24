<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand mb-0 h1" href="index.jsp">Cineflex</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Standort</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Programm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Specials</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Snacks</a>
                </li>
                <li class="nav-item dropdown">
                    <%
                        if (session.getAttribute("email") == null ) {
                            out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownAccount\" role=\"button\"\n" +
                                    "                       data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Account</a>");
                        } else {
                            out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownAccount\" role=\"button\"\n" +
                                    "                       data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" + session.getAttribute("name") + "</a>");
                        }
                    %>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <%
                            if (session.getAttribute("email") == null) {
                                out.println("<a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#loginDialog\">Login</a>");
                                out.println("<a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#registerDialog\">Register</a>");
                            } else {
                                out.println("<a class=\"dropdown-item\" href=\"logout.jsp\">Logout</a>");
                            }
                        %>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Cineflex News</a>
                    </div>
                </li>
            </ul>
            <button class="btn btn-outline-success my-2 my-sm-0" type="button" data-toggle="modal"
                    data-target="#filterDialog">Suche
            </button>
        </div>
    </nav>
</header>



<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>

<div class="container">
    <div class="card mt-3 mb-3">
        <div class="row">
            <div class="col-lg-5">
                <img src="img/error/multiflexTeam.jpg" class="card-img" alt="It´s a triple">
            </div>
            <div class="col-lg-7">
                <div class="card-body">
                    <h5 class="card-title">Error 500</h5>
                    <p class="card-text"><small class="text-muted">Oh Baby! It´s a 500</small></p>
                    <p class="card-text mrb-justify">Hier ist etwas schief gelaufen! Dein Multiflex-Team kümmert sich um das Problem.</p>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="elements/footer.jsp"/>
</body>
</html>