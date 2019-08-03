<%--
  Created by IntelliJ IDEA.
  User: d.klysch
  Date: 08.07.2019
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Stundenplan"/>
</jsp:include>

<body>
    <div>
        <div bp="grid gap-none">
            <div bp="1" id="sidebar"></div>
            <div bp="11">
                <div class="rh-widget">
                    <div class="rh-widget-header">
                        Stundenplan
                    </div>
                    <div class="rh-widget-content">
                        <embed src="https://rapla.dhbw-karlsruhe.de/rapla?page=calendar&user=eisenbiegler&file=TINF18B4" style="height: 100%; width: 100%">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="partials/footer.jsp" />
</body>
</html>
