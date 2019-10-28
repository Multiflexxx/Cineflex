function onClickProfilBearbeiten() {
    document.getElementById("bearbeitenButton").style.display = "none";
    document.getElementById("abbrechenButton").style.display = "block";
    document.getElementById("submitButton").style.display = "block";
    document.getElementById("formVorname").readOnly = false;
    document.getElementById("formNachname").readOnly = false;
    document.getElementById("inputEmail").readOnly = false;
    document.getElementById("inputPasswort").readOnly = false;
    document.getElementById("inputStrasse").readOnly = false;
    document.getElementById("inputHausnummer").readOnly = false;

}

function onClickProfilAbbrechen() {
    document.getElementById("bearbeitenButton").style.display = "block";
    document.getElementById("abbrechenButton").style.display = "none";
    document.getElementById("submitButton").style.display = "none";
    document.getElementById("formVorname").readOnly = true;
    document.getElementById("formNachname").readOnly = true;
    document.getElementById("inputEmail").readOnly = true;
    document.getElementById("inputPasswort").readOnly = true;
    document.getElementById("inputStrasse").readOnly = true;
    document.getElementById("inputHausnummer").readOnly = true;

}

function onSubmit() {
    if(document.getElementById("inputPasswort").value.length < 6){
        alert('Das Passwort muss mindestens 6 Zeichen lang sein!');
    }else {
        document.ProfilForm.submit();
    }
}