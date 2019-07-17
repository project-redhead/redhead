<%--
  Created by IntelliJ IDEA.
  User: d.klysch
  Date: 16.07.2019
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Redhead - Transaktionen</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/_redhead.css" />
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="/assets/js/_sidebar.js"></script>
    <script src="/assets/js/common/http-service.js"></script>
</head>

<body>
<div bp="grid gap-none">
    <div id="sidebar" bp="1">

    </div>

    <div class="rh-content" bp="11">
        <h1>Transaktionen</h1>
        <ul id="transaction_list" class="rh-listview">

        </ul>
    </div>
</div>

<script>
    //TODO add to Sidebar, make it look better ^^, highlight nagative numbers
    getUser().then(u => {
        u.value.transactions.forEach(transaction => {
    $('#transaction_list').append(
        `<li>
                    <small>${transaction._id}</small> <br/>
                    ${transaction.description + " : " + transaction.value}
                </li>`
    );
    });
    });
</script>

<script src="https://kit.fontawesome.com/76ef5d807b.js"></script>
</body>
</html>
