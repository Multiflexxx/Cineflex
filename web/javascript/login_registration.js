function submitLogin () {
    document.LoginForm.submit();
}

function submitRegistration() {

    var inputVorname = document.getElementById("inputVorname").value;
    var inputNachname = document.getElementById("inputNachname").value;
    var inputGeb = document.getElementById("inputGeb").value;
    var inputEmail = document.getElementById("inputEmail").value;
    var inputPasswortReg = document.getElementById("inputPasswordReg").value;
    var inputPasswortRegWdh = document.getElementById("inputPasswordRegWdh").value;

    // check passwords are not equal
    if(inputPasswortReg != inputPasswortRegWdh )
    {
        alert('Die Passwörter stimmen nicht überein!');
        return;
    }

    if(inputPasswortReg.length < 6 || inputPasswortRegWdh.length < 6)
    {
        alert('Das gewählte Passwort muss mindestens 6 Zeichen lang sein');
        return;
    }

    if(inputVorname == "" || inputNachname == "" || inputGeb == "" || inputEmail == "" || inputPasswortReg == "" || inputPasswortRegWdh == "")
    {
        alert('Bitte alle Pflichtfelder ausfüllen!');
        return;
    }

    document.RegistrationForm.submit();
}