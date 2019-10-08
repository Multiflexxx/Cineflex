var url = new URL(window.location.href);
var check = url.searchParams.get("check");
if(check != "false" || check == null) {
    var plz = getCookie("plz");
    if(plz == undefined) {
        window.location.replace("locationPicker.jsp?check=false");
    }
}


function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

