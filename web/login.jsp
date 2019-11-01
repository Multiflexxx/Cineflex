<!-- Login Screen-->

<div class="modal fade" id="loginDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger" id="login_failed" role="alert" style="display: none">
                    E-Mail oder Passwort ist falsch!
                </div>
                <form name="LoginForm" action="loginHandler.jsp" method="post">
                    <div class="form-group">
                        <label for="inputEmailLog">E-Mail / Benutzername</label>
                        <input type="text" class="form-control" id="inputEmailLog" name="inputEmailLog"
                               aria-describedby="emailHelp" placeholder="E-Mail Adresse">
                        <small id="emailHelp" class="form-text text-muted">Wir geben deine E-Mail Adresse nicht
                            weiter.</small>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">Passwort</label>
                        <input type="password" class="form-control" id="inputPassword" name="inputPassword"
                               placeholder="Passwort">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" value="true" id="inputCheckLogin" name="inputCheckLogin">
                        <label class="form-check-label" for="inputCheckLogin">Angemeldet bleiben?</label>
                    </div>
                    <input type="hidden" id="inputURL" name="inputURL" value="">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-primary" onclick="submitLogin()">Einloggen</button>
            </div>
        </div>
    </div>
</div>

<script>
    function setInputUrl() {
        document.getElementById("inputURL").value = window.location.href;
    }

    setInputUrl();
</script>
<script>
    var input = document.getElementById("inputPassword");

    // Login if Enter is hit
    input.addEventListener("keyup", function(event) {
        // Number 13 is the "Enter" key on the keyboard
        if (event.keyCode === 13) {
            submitLogin();
        }
    });
</script>


<%
    if (session.getAttribute("loginfailed") == "1") {
%>
<script>
    function loginFailed() {
        document.getElementById("login_failed").style.display = "inherit";
        $('#loginDialog').modal('show');
    }
    loginFailed();
</script>
<%
        session.setAttribute("loginfailed", "0");
    } else if (session.getAttribute("loginfailed") == "0") {
        session.removeAttribute("login");
    }
%>