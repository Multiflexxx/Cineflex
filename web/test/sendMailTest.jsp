<%@ page import="send_mail.Email_Sender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body class="d-flex flex-column h-100">

<h1>SendMail Test</h1>
<%
    out.println("Try sending");
    Email_Sender.sendMail("sendmailtest123456@puppetmail.de", "Test", "TestString");
%>

</body>
</html>