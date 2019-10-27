<%
    String filename = "pdf" + session.getAttribute("KID") + request.getParameter("BID") + ".pdf";
    String filepath = "/usr/local/tomcat/buchungsbelege_pdf/";
    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

    java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath + filename);

    int i;
    while ((i=fileInputStream.read()) != -1) {
        out.write(i);
    }
    fileInputStream.close();
%>
