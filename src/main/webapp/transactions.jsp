<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 03/08/2019
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Transaktionen"/>
</jsp:include>
<body>
<div>
    <div bp="grid gap-none">
        <div bp="1" id="sidebar"></div>
        <div bp="11" class="rh-content">
            <div>
                <h1>Transaktionen</h1>
                Das hier ist die Liste aller vergangenen Transaktionen, die Deinem Account zugeordnet wurden.

                <div>
                    <ul id="transactions" class="rh-listview"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    getUser().then(user => {
        user.value.transactions.forEach(t => {

            let value = t.value + 0 > 0 ? `<span class="positive">${t.value}</span>` : `${t.value}`;

            $('#transactions').append(
                `<li>
                    <small>#${t._id}</small> <br />
                    ${t.description} <b>(${value})</b>
                </li>`);
        });
    });
</script>

<jsp:include page="partials/footer.jsp" />
</body>
</html>
