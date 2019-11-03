var countChoosenSeats = 0;
var choosenSeatsMap = new Map();
choosenSeatsMap.set("P", 0);
choosenSeatsMap.set("L", 0);
var choosenReihe = null;
var preistyp = [];
var preisMultiplikator = [];
var url = "Penis";

function chooseSeat(id, row_length) {
    var seat = document.getElementById(id);
    if (choosenReihe == null || choosenReihe == id.charAt(0)) {
        if (seat.style.backgroundColor == "#ff0000") {
            return;
        }

        if (seat.style.backgroundColor == "green") {
            if (!areBoothNeighborChoosen(id, row_length)) {
                seat.style.backgroundColor = '#4a9be8';

                for (let i = 0; i < preisMultiplikator.length; i++) {
                    for (let j = 0; j < 2; j++) {
                        countChoosenSeats += preisMultiplikator[i][j];
                        preisMultiplikator[i][j] = 0;
                    }
                    document.getElementById("h5" + (i + 1)).innerHTML = "0 €";
                    document.getElementById("span" + (i + 1)).innerHTML = 0;
                }
                if (countChoosenSeats > 0) {
                    var seats_nr = 0;
                    if (seat.getAttribute("seat_cat") == "B" || seat.getAttribute("seat_cat") == "P") {
                        seats_nr = choosenSeatsMap.get("P");
                        seats_nr--;
                        choosenSeatsMap.set("P", seats_nr);
                    } else {
                        seats_nr = choosenSeatsMap.get("L");
                        seats_nr--;
                        choosenSeatsMap.set("L", seats_nr);
                    }
                    countChoosenSeats--;
                }

                if (countChoosenSeats == 0) {
                    choosenReihe = null;
                }
                document.getElementById("span" + (0)).innerHTML = countChoosenSeats;
            }
        } else {
            if (countChoosenSeats < 8 && (choosenReihe == null || isChoosenNeighbor(id, row_length))) {
                seat.style.backgroundColor = "green";
                choosenReihe = id.charAt(0);
                var seats_nr = 0;
                if (seat.getAttribute("seat_cat") == "B" || seat.getAttribute("seat_cat") == "P") {
                    seats_nr = choosenSeatsMap.get("P");
                    seats_nr++;
                    choosenSeatsMap.set("P", seats_nr);
                } else {
                    seats_nr = choosenSeatsMap.get("L");
                    seats_nr++;
                    choosenSeatsMap.set("L", seats_nr);
                }
                countChoosenSeats++;
            }
        }
        btndclickable(countChoosenSeats);
        setCounterUI()
    }
    console.log("C: " +choosenSeatsMap.get("P"), choosenSeatsMap.get("L"));
}

function isChoosenNeighbor(id, row_length) {
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

function areBoothNeighborChoosen(id, row_length) {
    var rv = false;
    var leftId = id.charAt(0) + (parseInt(id.slice(1)) - 1);
    var rightId = id.charAt(0) + (parseInt(id.slice(1)) + 1);
    if (parseInt(id.slice(1)) == 1 || parseInt(id.slice(1)) == row_length) {
        rv = false;
    } else {
        if (document.getElementById(leftId.toString()).style.backgroundColor == "green" && document.getElementById(rightId).style.backgroundColor == "green") {
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
    for (let i = 0; i < tableLength; i++) {
        preisMultiplikator[i] = [];
        for (let j = 0; j < 2; j++) {
            preisMultiplikator[i][j] = 0;
        }
    }
    var body = document.getElementById("tickets");
    var table = document.createElement("TABLE");
    table.setAttribute("class", "table table-dark");
    table.createTBody();
    for (var i = 0; i <= tableLength; i++) {
        var tr = table.insertRow();
        tr.setAttribute("id", "ticketcat" + i);
        for (var j = 0; j < 4; j++) {
            var td = tr.insertCell();
            if (i == 0) {
                if (j == 0) {
                    td.setAttribute("class", "pay_info");
                } else if (j == 1) {
                    var h5 = document.createElement("H5");
                    h5.innerHTML = "Nicht zugewiesen";
                    td.appendChild(h5);
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
                    var h5 = document.createElement("H5");
                    h5.innerHTML = preistyp[i - 1].beschreibung;
                    td.appendChild(h5);
                } else if (j == 2) {
                    var btn1 = document.createElement("BUTTON");
                    btn1.setAttribute("class", "btn btn-outline-light btn-sm btn-plus-minus text-center");
                    btn1.setAttribute("onclick", "ticket_minus(" + i + ", " + preistyp[i - 1].preis + ", " + preistyp[i - 1].preisL + ")");
                    btn1.innerHTML = "-";
                    var span = document.createElement("SPAN");
                    span.setAttribute("class", "span_price_select")
                    span.setAttribute("id", "span" + i);
                    span.setAttribute("idPreis", preistyp[i - 1].id);
                    span.innerHTML = "0";
                    var btn2 = document.createElement("BUTTON");
                    btn2.setAttribute("onclick", "ticket_plus(" + i + ", " + preistyp[i - 1].preis + ", " + preistyp[i - 1].preisL + ")");
                    btn2.setAttribute("class", "btn btn-outline-secondary btn-sm btn-plus-minus");
                    btn2.innerHTML = "+";
                    td.appendChild(btn1);
                    td.appendChild(span);
                    td.appendChild(btn2);
                } else {
                    var h5 = document.createElement("H5");
                    h5.setAttribute("id", "h5" + i);
                    h5.innerHTML = preisMultiplikator[i - 1][0] * preistyp[i - 1].preis + preisMultiplikator[i - 1][1] * preistyp[i - 1].preisL + "€";
                    td.appendChild(h5);
                }
            }
        }
    }
    body.appendChild(table);
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
}


function ticket_plus(i, preis, preisL) {
    if (countChoosenSeats > 0) {
        var increase = true;
        for (let j=0 ; j<preisMultiplikator.length; j++) {
            if ((choosenSeatsMap.get("P") <= preisMultiplikator[j][0])) {
                increase = false;
            }
        }
        if (increase) {
            preisMultiplikator[i - 1][0] += 1;
        } else {
            increase = true;
            for (let j=0 ; j<preisMultiplikator.length; j++) {
                if ((choosenSeatsMap.get("L") <= preisMultiplikator[j][1])) {
                    increase = false;
                }
            }
            if (increase) {
                preisMultiplikator[i - 1][1] += 1;
            }
        }
        countChoosenSeats -= 1;
        document.getElementById("h5" + i).innerHTML = preisMultiplikator[i - 1][0] * preis + preisMultiplikator[i - 1][1] * preisL + "€";
        document.getElementById("span" + i).innerHTML = preisMultiplikator[i - 1][0] + preisMultiplikator[i - 1][1];
        document.getElementById("span0").innerHTML = countChoosenSeats;
    }
    btndclickable(countChoosenSeats);
    console.log("C: " + choosenSeatsMap.get("P"), choosenSeatsMap.get("L"));
    console.log("M: " +preisMultiplikator[i-1][0], preisMultiplikator[i-1][1]);
}

function ticket_minus(i, preis, preisL) {
    if (preisMultiplikator[i - 1][0] > 0) {
        preisMultiplikator[i - 1][0] -= 1;
        countChoosenSeats += 1;
    } else if (preisMultiplikator[i - 1][1] > 0) {
        preisMultiplikator[i - 1][1] -= 1;
        countChoosenSeats += 1;
    }
    document.getElementById("h5" + i).innerHTML = preisMultiplikator[i - 1][0] * preis + preisMultiplikator[i - 1][1] * preisL + "€";
    document.getElementById("span" + i).innerHTML = preisMultiplikator[i - 1][0] + preisMultiplikator[i - 1][1];
    document.getElementById("span0").innerHTML = countChoosenSeats;
    btndclickable(countChoosenSeats);
    console.log(choosenSeatsMap.get("P"), choosenSeatsMap.get("L"));
}


function btndclickable(countChoosenSeats) {
    var btn_res = document.getElementById("btn_res");
    var btn_buc = document.getElementById("btn_buc");
    if (countChoosenSeats == 0) {
        // What
        if (false) {

        } else {
            btn_res.disabled = false;
            btn_res.classList.add("btn-outline-primary");
            btn_res.classList.remove("btn-outline-secondary");
            btn_buc.disabled = false;
            btn_buc.classList.add("btn-outline-primary");
            btn_buc.classList.remove("btn-outline-secondary");
        }
    } else {
        btn_res.disabled = true;
        btn_res.classList.remove("btn-outline-primary");
        btn_res.classList.add("btn-outline-secondary");
        btn_buc.disabled = true;
        btn_buc.classList.remove("btn-outline-primary");
        btn_buc.classList.add("btn-outline-secondary");
    }
}

function onClickReservieren(vID , fID) {
    /*var preiscat = [];
    var seatcat = [];
    for (let i = 0; i < preisMultiplikator.length; i++) {
        for (let j = 0; j<2; j++) {
            var ctr = preisMultiplikator[i][j];
            var id = preistyp[i].id;
            var cat ="";
            if (j == 0) {
                cat = "P";
            } else {
                cat = "L";
            }
            for (let k = 0; k < ctr; k++) {
                preiscat.push(id);
                seatcat.push(cat);
            }
        }
    }
    var preisInput = preiscat.join(",");
    var seatcatInput = seatcat.join(",");
    console.log(preisInput)
    console.log(seatcatInput);*/

    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "reservierungsHandler.jsp");

    var inputs = document.getElementsByClassName("seat");
    var seats = [];
    for (var j = 0; j < inputs.length; j++) {
        if (inputs[j].style.backgroundColor == "green") {
            seats.push(inputs[j].getAttribute("uniqueID"));
        }
    }
    var seatsInput = seats.join(",");

    var preiscat = [];
    // var seatcat = [];
    for (let i = 0; i < preisMultiplikator.length; i++) {
        for (let j = 0; j<2; j++) {
            var ctr = preisMultiplikator[i][j];
            var id = preistyp[i].id;
            var cat ="";
            if (j == 0) {
                cat = "P";
            } else {
                cat = "L";
            }
            for (let k = 0; k < ctr; k++) {
                preiscat.push(id);
                // seatcat.push(cat);
            }
        }
    }
    var preisInput = preiscat.join(",");
    // var seatcatInput = seatcat.join(",");
    var hiddenField0 = document.createElement("input");
    hiddenField0.setAttribute("type", "hidden");
    hiddenField0.setAttribute("name", "vorstellungs_id");
    hiddenField0.setAttribute("value", vID);
    var hiddenField1 = document.createElement("input");
    hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "seats_data");
    hiddenField1.setAttribute("value", seatsInput);
    var hiddenField2 = document.createElement("input");
    hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "tickets_data");
    hiddenField2.setAttribute("value", preisInput);
    var hiddenField3 = document.createElement("input");
    hiddenField3.setAttribute("type", "hidden");
    hiddenField3.setAttribute("name", "film_id");
    hiddenField3.setAttribute("value", fID);
    var hiddenField4 = document.createElement("input");
    hiddenField4.setAttribute("type", "hidden");
    hiddenField4.setAttribute("name", "url");
    hiddenField4.setAttribute("value", url);
    form.appendChild(hiddenField0);
    form.appendChild(hiddenField1);
    form.appendChild(hiddenField2);
    form.appendChild(hiddenField3);
    form.appendChild(hiddenField4);

    document.body.appendChild(form);
    form.submit();
}


function onClickBuchen(vID , fID) {
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

    var preiscat = [];
    // var seatcat = [];
    for (let i = 0; i < preisMultiplikator.length; i++) {
        for (let j = 0; j<2; j++) {
            var ctr = preisMultiplikator[i][j];
            var id = preistyp[i].id;
            var cat ="";
            if (j == 0) {
                cat = "P";
            } else {
                cat = "L";
            }
            for (let k = 0; k < ctr; k++) {
                preiscat.push(id);
                // seatcat.push(cat);
            }
        }
    }
    var preisInput = preiscat.join(",");
    // var seatcatInput = seatcat.join(",");
    var hiddenField0 = document.createElement("input");
    hiddenField0.setAttribute("type", "hidden");
    hiddenField0.setAttribute("name", "vorstellungs_id");
    hiddenField0.setAttribute("value", vID);
    var hiddenField1 = document.createElement("input");
    hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "seats_data");
    hiddenField1.setAttribute("value", seatsInput);
    var hiddenField2 = document.createElement("input");
    hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "tickets_data");
    hiddenField2.setAttribute("value", preisInput);
    var hiddenField3 = document.createElement("input");
    hiddenField3.setAttribute("type", "hidden");
    hiddenField3.setAttribute("name", "film_id");
    hiddenField3.setAttribute("value", fID);
    var hiddenField4 = document.createElement("input");
    hiddenField4.setAttribute("type", "hidden");
    hiddenField4.setAttribute("name", "url");
    hiddenField4.setAttribute("value", url);
    form.appendChild(hiddenField0);
    form.appendChild(hiddenField1);
    form.appendChild(hiddenField2);
    form.appendChild(hiddenField3);
    form.appendChild(hiddenField4);

    document.body.appendChild(form);
    form.submit();
}