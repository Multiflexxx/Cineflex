<%@ page import="oo.Gebaeude" %>
<%@ page import="factory.GebaeudeFactory" %>
<%@ page import="helper.ExceptionHandler" %>
<!-- Location Screen-->
<div class="modal fade" id="locationDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerDialogTitle">W&auml;hlen Sie bitte einen der folgenden Standorte aus!</h5>
            </div>
            <div class="modal-body">
                <table class="table">
                    <tbody>

                    <%
                        try {
                            Gebaeude[] gebäude = GebaeudeFactory.getGebaeude();
                            if (gebäude != null) {
                                for (int i = 0; i < gebäude.length; i++) {
                                    if (i % 2 == 0) {
                                        out.write("<tr>");
                                        out.write("<td><a href=\"javascript:void(0)\" onclick=\"submitLocation('" + gebäude[i].getOrtsname() + "', '" + gebäude[i].getPlz() + "')\">" + gebäude[i].getOrtsname() + "</a></td>");
                                    } else {
                                        out.write("<td><a href=\"javascript:void(0)\" onclick=\"submitLocation('" + gebäude[i].getOrtsname() + "', '" + gebäude[i].getPlz() + "')\">" + gebäude[i].getOrtsname() + "</a></td>");
                                        out.write("</tr>");
                                    }
                                }
                                if (gebäude.length % 2 != 0) {
                                    out.write("<td></td></tr>");
                                }
                            }
                        } catch (Exception e) {
                            out.write(ExceptionHandler.exceptionStackTraceToString(e));
                        }
                    %>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    checkCookie();
</script>