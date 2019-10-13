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