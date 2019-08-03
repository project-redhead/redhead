<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 07/07/2019
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Kummerkasten"/>
</jsp:include>
<body>
    <jsp:useBean id="bean" class="xyz.dommi.beans.SuggestionBean" scope="request"/>

    <div bp="grid gap-none">
        <div id="sidebar" bp="1">

        </div>

        <div class="rh-content" bp="11">
            <h1>Kummerkasten</h1>
            <ul id="suggestion_list" class="rh-listview">

            </ul>
        </div>
    </div>

    <script>
        var result = JSON.parse('<jsp:getProperty name="bean" property="suggestionlist"/>');
        console.log(result);

        result.value.forEach(element => {
            var date = new Date(element.date.$date);

            $('#suggestion_list').append(
                `<li>
                    <small>${date.toLocaleString()}</small> <br/>
                    ${element.content}
                </li>`
            );
        });
    </script>

    <jsp:include page="partials/footer.jsp" />
</body>
</html>
