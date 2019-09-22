<%@ page import="db_connector.Connector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="db_connector.QueryBuilder" %>
<%@ page import="java.sql.ResultSet" %>
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

            <div class="dropdown">
                <%
                    Cookie cookie = null;
                    Cookie[] cookies = null;
                    String outputValue = "Standort";

                    cookies = request.getCookies();

                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            if (cookies[i].getName().equals("city")) {
                                cookie = cookies[i];
                                outputValue = cookie.getValue();
                                break;
                            }
                        }
                    }
                    out.println("<a class=\"navbar-brand dropwdown dropdown-toggle\" href=\"#\" id=\"navbarDropdownCity\" role=\"button\"\n" +
                            "                   data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" + outputValue + "</a>");

                %>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <%
                        Connection c = Connector.getConnection();
                        ResultSet rs = Connector.getQueryResult(c, QueryBuilder.showAllCinemas());
                        if (rs != null) {
                            while (rs.next()) {
                                out.println(" <a class=\"dropdown-item\" href=\"javascript:void(0)\" onclick=\"setCookieUrl('" + rs.getString("Ortsname") + "', '" + rs.getString("PLZ") + "')\">" + rs.getString("Ortsname") + "</a>");
                            }
                        }
                        Connector.closeConnection(c);
                    %>
                </div>
            </div>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="programm.html">Programm <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Specials</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Snacks</a>
                </li>
                <li class="nav-item dropdown">
                    <%
                        if (session.getAttribute("email") == null) {
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
                                out.println("<a class=\"dropdown-item\" href=\"javascript:void(0)\">Logout</a>");
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
            <%--            <form class="form-inline my-2 my-lg-0">--%>
            <%--                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">--%>
            <%--                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
            <%--            </form>--%>
        </div>
    </nav>
</header>