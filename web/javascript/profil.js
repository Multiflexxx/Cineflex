function onClickProfilBearbeiten() {
    document.getElementById("bearbeitenButton").style.display = "none";
    document.getElementById("abbrechenButton").style.display = "block";
    document.getElementById("submitButton").style.display = "block";
    document.getElementById("formVorname").readOnly = false;
    document.getElementById("formNachname").readOnly = false;
    document.getElementById("inputEmail").readOnly = false;
    document.getElementById("inputPasswort").readOnly = false;
    document.getElementById("inputStrasse").readOnly = false;

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

}

function onSubmit() {
    document.ProfilForm.submit();
}