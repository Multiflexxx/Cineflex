function setCookieUrl(city) {
    document.cookie = "city=" + city;
    history.pushState(null, '', (window.location.pathname + "?city=" + city));
}