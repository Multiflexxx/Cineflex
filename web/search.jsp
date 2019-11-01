<!-- Filter Screen -->
<%@ page import="oo.Genre" %>
<%@ page import="factory.GenreFactory" %>

<div class="modal fade" id="filterDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Suche</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="SearchForm" action="searchHandler.jsp" method="get" accept-charset="UTF-8">
                    <div class="form-group">
                        <label for="inputSearchText">Suchtext</label>
                        <input type="text" class="form-control" id="inputSearchText" name="inputSearchText" placeholder="'Schwanzus Longus'">
                    </div>
                    <div class="form-group">
                        <label for="inputDate">Datum</label>
                        <input type="date" class="form-control" id="inputDate" name="inputDate" value="0000-00-00"
                               min="0000-00-00" max="9999-12-31">
                    </div>
                    <div class="form-group">
                        <label for="inputTime">Uhrzeit</label>
                        <input type="time" class="form-control" id="inputTime" name="inputTime" value="08:00" step="60">
                    </div>
                    <div class="form-group">
                        <label for="inputFSK">FSK</label>
                        <select class="form-control" id="inputFSK" name="inputFSK">
                            <option value="18">Nicht gew&#228;hlt</option>
                            <option value="0">FSK 0</option>
                            <option value="6">FSK 6</option>
                            <option value="12">FSK 12</option>
                            <option value="16">FSK 16</option>
                            <option value="18">FSK 18</option>
                        </select>
                    </div>

                    <%
                        Genre genre[] = GenreFactory.getGenre();

                        int arrayLength = genre.length;

                        out.println("<div class=\"form-group\">");
                        out.println("<label for=\"inputGenre\">Genre</label>");
                        out.println("<select class=\"form-control\" id=\"inputGenre\" name=\"inputGenre\">");

                        out.println("<option value=\"-1\">Nicht gew&#228;hlt</option>");

                        for(int i = 0; i < arrayLength; i++)
                        {
                            out.println("<option value=\""+ genre[i].getGenreID() +"\">"+ genre[i].getGenrebezeichnung() +"</option>");
                        }

                        out.println("</select>");
                        out.println("</div>");
                    %>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-primary" onclick="submitFilter()">Filtern</button>
            </div>
        </div>
    </div>
</div>
<script>
    var input = document.getElementById("inputSearchText");

    // Login if Enter is hit
    input.addEventListener("keyup", function(event) {
        // Number 13 is the "Enter" key on the keyboard
        if (event.keyCode === 13) {
            submitFilter();
        }
    });
</script>