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
                <form name="LoginForm" action="LoginHandler" method="post">
                    <div class="form-group">
                        <label for="inputEmail">E-Mail / Benutzername</label>
                        <input type="text" class="form-control" id="inputEmail" name="inputEmail" aria-describedby="emailHelp" placeholder="E-Mail Adresse">
                        <small id="emailHelp" class="form-text text-muted">Wir geben deine E-Mail Adresse nicht weiter.</small>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">Passwort</label>
                        <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Passwort">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="stayLoggedIn">
                        <label class="form-check-label" for="stayLoggedIn">Angemeldet bleiben?</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-primary" onclick="submitLogin()">Einloggen</button>
            </div>
        </div>
    </div>
</div>

<script src="javascript/login_registration.js" crossorigin="anonymous"></script>