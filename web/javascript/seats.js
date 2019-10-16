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

            for (var g = 0; g < preisMultiplikator.length; g++) {
                countChoosenSeats += preisMultiplikator[g];
                preisMultiplikator[g] = 0;
                document.getElementById("h4" + (g + 1)).innerHTML = "0 €";
                document.getElementById("span" + (g + 1)).innerHTML = 0;
            }
            if (countChoosenSeats > 0) {
                countChoosenSeats--;
            }

            if (countChoosenSeats == 0) {
                choosenReihe = null;
            }
            document.getElementById("span" + (0)).innerHTML = countChoosenSeats;
        } else {
            if (countChoosenSeats < 8 && (choosenReihe == null || isChoosen(id, row_length))) {
                document.getElementById(id).style.backgroundColor = "green";
                choosenReihe = id.charAt(0);
                countChoosenSeats++;
            }
        }
        btndclickable(countChoosenSeats);
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
                    span.setAttribute("class", "span_price_select")
                    span.setAttribute("id", "span" + i);
                    span.setAttribute("idPreis", preistyp[i - 1].id);
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
    btndclickable(countChoosenSeats);


}

function ticket_minus(i, preis) {
    if (preisMultiplikator[i - 1] > 0) {
        preisMultiplikator[i - 1] -= 1;
        countChoosenSeats += 1;
        document.getElementById("h4" + i).innerHTML = preisMultiplikator[i - 1] * preis + " €";
        document.getElementById("span" + i).innerHTML = preisMultiplikator[i - 1];
        document.getElementById("span0").innerHTML = countChoosenSeats;
    }
    btndclickable(countChoosenSeats);
}


function btndclickable(countChoosenSeats) {
    var btn_res = document.getElementById("btn_res");
    var btn_buc = document.getElementById("btn_buc");
    if (countChoosenSeats == 0) {
        btn_res.disabled = false;
        btn_res.classList.add("btn-outline-primary");
        btn_res.classList.remove("btn-outline-secondary");
        btn_buc.disabled = false;
        btn_buc.classList.add("btn-outline-primary");
        btn_buc.classList.remove("btn-outline-secondary");
    } else {
        btn_res.disabled = true;
        btn_res.classList.remove("btn-outline-primary");
        btn_res.classList.add("btn-outline-secondary");
        btn_buc.disabled = true;
        btn_buc.classList.remove("btn-outline-primary");
        btn_buc.classList.add("btn-outline-secondary");
    }
}

function onClickReservieren() {
    var inputs = document.getElementsByClassName("seat");
    var seats = [];
    for (var j = 0; j < inputs.length; j++) {
        if (inputs[j].style.backgroundColor == "green") {
            seats.push(inputs[j].getAttribute("uniqueID"));
        }
    }
    var seatsInput = seats.join(",");

    console.log(seatsInput);
}

function onClickBuchen(vID) {
    // LOOP OVER SEATS
    // CREATE JSON FILE
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "buchungsHandler.jsp");

    var inputs = document.getElementsByClassName("seat");
    var seats = [];
    for (var j = 0; j < inputs.length; j++) {
        if (inputs[j].style.backgroundColor == "green") {
            seats.push(inputs[j].getAttribute("uniqueID"));
        }
    }
    var seatsInput = seats.join(",");

    var preis = [];
    for (var i = 0; i < preisMultiplikator.length; i++) {
        var ctr = preisMultiplikator[i];
        var id = preistyp[i].id;
        for (var j = 0; j < ctr; j++) {
            preis.push(id);
        }
    }
    var preisInput = preis.join(",");
    var hiddenField0 = document.createElement("input");
    hiddenField0.setAttribute("type", "hidden");
    hiddenField0.setAttribute("name", "vorstellungsid");
    hiddenField0.setAttribute("value", vID);
    var hiddenField1 = document.createElement("input");
    hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "seats_data");
    hiddenField1.setAttribute("value", seatsInput);
    var hiddenField2 = document.createElement("input");
    hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "tickets_data");
    hiddenField2.setAttribute("value", preisInput);
    form.appendChild(hiddenField0);
    form.appendChild(hiddenField1);
    form.appendChild(hiddenField2);

    document.body.appendChild(form);
    form.submit();
}