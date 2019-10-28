function setCookieUrl(city, plz) {
    var d = new Date();
    d.setTime(d.getTime() + (60*60*24*365));
    var expires = "expires="+ d.toUTCString();
    document.cookie = "city=" + city + ";" + expires + ";path=/";
    document.cookie = "plz=" + plz + ";" + expires + ";path=/";
    document.getElementById("navbarDropdownCity").innerHTML = city;
    window.location.reload();
}