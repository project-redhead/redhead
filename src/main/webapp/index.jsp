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
    <script src="/assets/js/_sidebar.js"></script>
</head>
<body>
    <div bp="grid">
        <div id="sidebar" bp="1"></div>
        <div id="widget-area" bp="11">
            <div>
                <h3>
                    <span>Hey </span>
                    <span rh-js="username"></span>
                    <span> 👋</span>
                </h3>
            </div>

            <div bp="grid gap-none">
                <div class="rh-widget" bp="6">
                    <div class="rh-widget-header">
                        Wetten
                    </div>
                    <div class="rh-widget-content">
                        Lädt...
                        <a class="rh-button" href="/request?type=BetGameBean&format=bean&redirect=/bets.jsp">Zur Übersicht</a>
                    </div>
                </div>

                <div class="rh-widget" bp="6">
                    <div class="rh-widget-header">
                        Kummerkasten
                    </div>
                    <div class="rh-widget-content">
                        <input type="text" id="inputFeedback" />
                        <a class="rh-button" href="#" onclick="submitFeedback(this)">Absenden</a>
                        <a class="rh-button" href="/request?type=SuggestionBean&format=bean&redirect=/suggestions.jsp">Einsendungen</a>
                    </div>
                </div>

                <div class="rh-widget" bp="6">
                    <div class="rh-widget-header">
                        Stundenplan
                    </div>
                    <div class="rh-widget-content">
                        <a class="rh-button" href="/schedule.jsp">Mein Stundenplan</a>
                    </div>
                </div>

                <div class="rh-widget" bp="6">
                    <div class="rh-widget-header">
                        Fahrplan
                    </div>
                    <div class="rh-widget-content">
                        <a class="rh-button" href="/bahn.jsp">Meine Bahnverbindung</a>
                    </div>
                </div>

                <div class="rh-widget" bp="6">
                    <div class="rh-widget-header">
                        Tägliche Belohnung
                    </div>
                    <div class="rh-widget-content">
                        <a class="rh-button" href="#" onclick="claimReward()">Einlösen</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="/assets/js/common/http-service.js"></script>
    <script src="/assets/js/index.js"></script>

    <script src="https://kit.fontawesome.com/76ef5d807b.js"></script>
</body>
</html>
