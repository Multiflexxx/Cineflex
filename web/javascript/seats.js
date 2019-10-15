var countChoosenSeats = 0;
var choosenReihe = null;

function chooseSeat(id) {
    if (choosenReihe == null || choosenReihe == id.charAt(0)) {
        if (document.getElementById(id).style.backgroundColor == "#ff0000") {
            return;
        }

        console.log(document.getElementById(id).style.backgroundColor);

        if (document.getElementById(id).style.backgroundColor == "green") {
            document.getElementById(id).style.backgroundColor = '#4a9be8';
            countChoosenSeats--;
            if (countChoosenSeats == 0) {
                choosenReihe = null;
            }
        } else {
            var boolean = isChoosen(id);
            if (countChoosenSeats < 8 && (choosenReihe == null || boolean)) {
                document.getElementById(id).style.backgroundColor = "green";
                choosenReihe = id.charAt(0);
                console.log(choosenReihe);
                countChoosenSeats++;
            }
        }
        setCounterUI()
    }
}

function isChoosen(id) {
    var leftId = id.charAt(0) + (parseInt(id.slice(1)) - 1);
    var rightId = id.charAt(0) + (parseInt(id.slice(1)) + 1);
    var rv= false;
    if (document.getElementById(leftId.toString()).style.backgroundColor == "green") {
        rv = true;
    }

    if (document.getElementById(rightId).style.backgroundColor == "green") {
        rv = true;
    }
    return rv;
}

function setCounterUI() {
    if (countChoosenSeats != 0) {
        document.getElementById("ticket_checkout").style.display = "inherit";
        //document.getElementById("ticket_checkout").style.visibility="visible";
        document.getElementById("span0").innerHTML = countChoosenSeats;
    } else {
        //document.getElementById("ticket_checkout").style.visibility="collapse";
        document.getElementById("ticket_checkout").style.display = "none";
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
                idVal = input.id;
            }

            return idVal;

        }).join("|");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "seats_data");
    hiddenField.setAttribute("value", data_inputs);
    form.appendChild(hiddenField);

    document.body.appendChild(form);
    form.submit();
}