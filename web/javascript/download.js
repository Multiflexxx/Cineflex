function download_buchung(BID) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "downloadBuchung.jsp");
    var hiddenField0 = document.createElement("input");
    hiddenField0.setAttribute("type", "hidden");
    hiddenField0.setAttribute("name", "BID");
    hiddenField0.setAttribute("value", BID);

    form.appendChild(hiddenField0);

    document.body.appendChild(form);
    form.submit();
}

function download_reservierung(RID) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "downloadReservierung.jsp");
    var hiddenField0 = document.createElement("input");
    hiddenField0.setAttribute("type", "hidden");
    hiddenField0.setAttribute("name", "RID");
    hiddenField0.setAttribute("value", RID);

    form.appendChild(hiddenField0);

    document.body.appendChild(form);
    form.submit();
}