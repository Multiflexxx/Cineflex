function setCookieUrl(city) {
    var d = new Date();
    d.setTime(d.getTime() + (60*60*24*365));
    var expires = "expires="+ d.toUTCString();
    document.cookie = "city=" + city; + expires + ";path=/";
    history.pushState(null, '', (window.location.pathname + "?city=" + city));
    document.getElementById("navbarDropdownCity").innerHTML = city;
}