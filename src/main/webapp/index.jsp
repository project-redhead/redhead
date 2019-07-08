<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 01/07/2019
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Redhead</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/_redhead.css" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
    <div>
        <div>
            <h3 rh-js="username"></h3>
        </div>
        <div class="rh-widget">
            <div class="rh-widget-header">
                Wetten
            </div>
            <div class="rh-widget-content">
                Lädt...
            </div>
        </div>

        <div class="rh-widget">
            <div class="rh-widget-header">
                Kummerkasten
            </div>
            <div class="rh-widget-content">
                <input type="text" id="inputFeedback" />
                <a class="rh-button" href="#" onclick="submitFeedback(this)">Absenden</a>
                <a class="rh-button" href="/request?type=SuggestionBean&format=bean&redirect=/suggestions.jsp">Einsendungen</a>
            </div>
        </div>
        <div class="rh-widget">
            <div class="rh-widget-header">
                Stundenplan
            </div>
            <div class="rh-widget-content">
                <a class="rh-button" href="/schedule.jsp">Mein Stundenplan</a>
            </div>
        </div>
        <div class="rh-widget">
            <div class="rh-widget-header">
                Fahrplan
            </div>
            <div class="rh-widget-content">
                <a class="rh-button" href="/bahn.jsp">Meine Bahnverbindung</a>
            </div>
        </div>
    </div>

    <script src="/assets/js/common/http-service.js"></script>
    <script src="/assets/js/index.js"></script>
</body>
</html>
