<%--
  Created by IntelliJ IDEA.
  User: benno
  Date: 22.09.2019
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Date</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script>
        window.onload=function() {
            var d = new Date();
            var date = d.getFullYear() + "-" + ("0" + d.getMonth()).slice(-2)+ "-" + ("0" + d.getDate()).slice(-2);
            document.getElementById("dateInput").value = date;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <div class="md-form">
                        <form action="./SearchHandler"  method="get">
                            <input type="text" name="searchText">
                            <input type="date" id="dateInput" name="date"
                                   value="0000-00-00"
                                   min="0000-00-00" max="9999-12-31">
                            <input name="time" type="time" value="08:00" step="3000">

                            <select id="fskInput" name="fsk">
                                <option value="18">Nicht gewählt</option>
                                <option value="0">FSK 0</option>
                                <option value="6">FSK 6</option>
                                <option value="12">FSK 12</option>
                                <option value="16">FSK 16</option>
                                <option value="18">FSK 18</option>
                            </select>
                            <input type="submit" value="Submit" />
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
