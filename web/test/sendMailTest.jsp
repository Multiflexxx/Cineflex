<%@ page import="send_mail.Email_Sender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body class="d-flex flex-column h-100">

<h1>SendMail Test</h1>
<%
    out.println("Try sending Text Mail<br><br>");
    Email_Sender.sendMail("xivese@3dmail.top", "Test", "TestString");
    out.println("Done!<br><br>");

    out.println("Try sending File Attachement<br><br>");
    Email_Sender.sendMultipartMail("xivese@3dmail.top", "Test", "TestString", "/usr/local/tomcat/buchungsbelege_pdf/<FILENAME.END>");
    out.println("Done!");
%>

</body>
</html>