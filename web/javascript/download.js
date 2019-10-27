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