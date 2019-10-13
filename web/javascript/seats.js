function chooseSeat(id) {

    if(document.getElementById(id).style.backgroundColor == "#ff0000")
    {
        return;
    }

    console.log(document.getElementById(id).style.backgroundColor);

    if (document.getElementById(id).style.backgroundColor == "green") {
        document.getElementById(id).style.backgroundColor = '#4a9be8';
    } else {
        document.getElementById(id).style.backgroundColor = "green";
    }
}

function onClickZurueck()
{

}

function onClickReservieren()
{

}

function onClickBuchen()
{
    // TODO: CHANGE LOOK AT MARCEL KASSENZETTEL TOOL

    // LOOP OVER SEATS
    // CREATE JSON FILE
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", path);

    var laden = document.getElementById("ladenInput").value;
    var datum = document.getElementById("dateInput").value;
    var zeit = document.getElementById("timeInput").value;

    var fields = laden + "|" + datum + "|" + zeit + "|";

    var inputs = document.getElementsByClassName( "inputfieldclass" ),
        data_inputs  = [].map.call(inputs, function( input )
        {
            return input.value;
        }).join( "|" );

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "daten");
    hiddenField.setAttribute("value", fields + data_inputs);
    form.appendChild(hiddenField);

    document.body.appendChild(form);
    form.submit();
}