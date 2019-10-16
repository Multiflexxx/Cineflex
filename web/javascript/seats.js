var countChoosenSeats = 0;
var choosenReihe = null;
var preistyp = [];
var preisMultiplikator = [];

function chooseSeat(id, row_length) {
    if (choosenReihe == null || choosenReihe == id.charAt(0)) {
        if (document.getElementById(id).style.backgroundColor == "#ff0000") {
            return;
        }

        if (document.getElementById(id).style.backgroundColor == "green") {
            document.getElementById(id).style.backgroundColor = '#4a9be8';
            if (countChoosenSeats > 0) {
                countChoosenSeats--;
            }

            if (countChoosenSeats == 0) {
                choosenReihe = null;
            }

            for (var g = 0; g < preisMultiplikator.length; g++) {
                countChoosenSeats += preisMultiplikator[g];
                preisMultiplikator[g] = 0;
                document.getElementById("h4" + (g + 1)).innerHTML = "0 €";
                document.getElementById("span" + (g + 1)).innerHTML = 0;
            }
            countChoosenSeats--;
            document.getElementById("span" + (0)).innerHTML = countChoosenSeats;
        } else {
            if (countChoosenSeats < 8 && (choosenReihe == null || isChoosen(id, row_length))) {
                document.getElementById(id).style.backgroundColor = "green";
                choosenReihe = id.charAt(0);
                countChoosenSeats++;
            }
        }
        setCounterUI()
    }
}

function isChoosen(id, row_length) {
    var rv = false;
    var leftId = id.charAt(0) + (parseInt(id.slice(1)) - 1);
    var rightId = id.charAt(0) + (parseInt(id.slice(1)) + 1);
    if (parseInt(id.slice(1)) == 1) {
        if (document.getElementById(rightId.toString()).style.backgroundColor == "green") {
            rv = true;
        }
    } else if (parseInt(id.slice(1)) == row_length) {
        if (document.getElementById(leftId.toString()).style.backgroundColor == "green") {
            rv = true;
        }
    } else {
        if (document.getElementById(leftId.toString()).style.backgroundColor == "green") {
            rv = true;
        }

        if (document.getElementById(rightId).style.backgroundColor == "green") {
            rv = true;
        }
    }
    return rv;
}


function setCounterUI() {
    if (countChoosenSeats > 0) {
        document.getElementById("ticket_checkout").style.display = "inherit";
        document.getElementById("span0").innerHTML = countChoosenSeats;
    } else {
        document.getElementById("ticket_checkout").style.display = "none";
    }
}

function createTable(preistyp, tableLength) {
    for (var h = 0; h < tableLength; h++) {
        preisMultiplikator[h] = 0;
    }
    var body = document.getElementById("tickets");
    var table = document.createElement("TABLE");
    table.setAttribute("class", "table table-dark");
    table.createTBody();
    for (var i = 0; i <= tableLength; i++) {
        var tr = table.insertRow();
        tr.setAttribute("id", "ticketcat" + i);
        for (j = 0; j < 4; j++) {
            var td = tr.insertCell();
            if (i == 0) {
                if (j == 0) {
                    td.setAttribute("class", "pay_info");
                } else if (j == 1) {
                    var h4 = document.createElement("H4");
                    h4.innerHTML = "Nicht zugewiesen";
                    td.appendChild(h4);
                } else if (j == 2) {
                    var span = document.createElement("SPAN");
                    span.setAttribute("id", "span" + i);
                    span.innerHTML = "0";
                    // span.setAttribute("id", "span" + i);
                    td.appendChild(span);
                }
            } else {
                if (j == 0) {
                    td.setAttribute("class", "pay_info");
                    if (preistyp[i - 1].tooltip != "null") {
                        var btnInfo = document.createElement("BUTTON");
                        btnInfo.setAttribute("type", "button");
                        btnInfo.setAttribute("class", "btn btn-outline-info");
                        btnInfo.setAttribute("data-toggle", "tooltip");
                        btnInfo.setAttribute("data-html", "true");
                        btnInfo.setAttribute("title", preistyp[i - 1].tooltip);
                        btnInfo.innerHTML = "?";
                        td.appendChild(btnInfo);
                    }
                } else if (j == 1) {
                    var h4 = document.createElement("H4");
                    h4.innerHTML = preistyp[i - 1].beschreibung;
                    td.appendChild(h4);
                } else if (j == 2) {
                    var btn1 = document.createElement("BUTTON");
                    btn1.setAttribute("class", "btn btn-outline-light btn-sm btn-plus-minus text-center");
                    btn1.setAttribute("onclick", "ticket_minus(" + i + ", " + preistyp[i - 1].preis + ")");
                    btn1.innerHTML = "-";
                    var span = document.createElement("SPAN");
                    span.setAttribute("id", "span" + i);
                    span.innerHTML = "0";
                    var btn2 = document.createElement("BUTTON");
                    btn2.setAttribute("onclick", "ticket_plus(" + i + ", " + preistyp[i - 1].preis + ")");
                    btn2.setAttribute("class", "btn btn-outline-secondary btn-sm btn-plus-minus");
                    btn2.innerHTML = "+";
                    td.appendChild(btn1);
                    td.appendChild(span);
                    td.appendChild(btn2);
                } else {
                    var h4 = document.createElement("H4");
                    h4.setAttribute("id", "h4" + i);
                    h4.innerHTML = preisMultiplikator[i - 1] * preistyp[i - 1].preis + " €";
                    td.appendChild(h4);
                }
            }
        }
    }
    body.appendChild(table);
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
}


function ticket_plus(i, preis) {
    if (countChoosenSeats > 0) {
        preisMultiplikator[i - 1] += 1;
        countChoosenSeats -= 1;
        document.getElementById("h4" + i).innerHTML = preisMultiplikator[i - 1] * preis + " €";
        document.getElementById("span" + i).innerHTML = preisMultiplikator[i - 1];
        document.getElementById("span0").innerHTML = countChoosenSeats;
    }


}

function ticket_minus(i, preis) {
    if (preisMultiplikator[i - 1] > 0) {
        preisMultiplikator[i - 1] -= 1;
        countChoosenSeats += 1;
        document.getElementById("h4" + i).innerHTML = preisMultiplikator[i - 1] * preis + " €";
        document.getElementById("span" + i).innerHTML = preisMultiplikator[i - 1];
        document.getElementById("span0").innerHTML = countChoosenSeats;
    }
}

function onClickZurueck() {

}

function onClickReservieren() {

}

function onClickBuchen() {
    // TODO: CHANGE LOOK AT MARCEL KASSENZETTEL TOOL

    // LOOP OVER SEATS
    // CREATE JSON FILE
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "buchungsHandler.jsp");

    var inputs = document.getElementsByClassName("seat"),
        data_inputs = [].map.call(inputs, function (input) {
            var idVal = -1;

            if (input.style.backgroundColor == "green") {
                idVal = input.getAttribute("uniqueID");
            }

            return idVal;

        }).join(",");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "seats_data");
    hiddenField.setAttribute("value", data_inputs);
    form.appendChild(hiddenField);

    document.body.appendChild(form);
    form.submit();
}