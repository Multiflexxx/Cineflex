function setCookieUrl(city) {
    document.cookie = "city=" + city; path=/;
    history.pushState(null, '', (window.location.pathname + "?city=" + city));
}