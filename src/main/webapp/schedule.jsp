<%--
  Created by IntelliJ IDEA.
  User: d.klysch
  Date: 08.07.2019
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Redhead - Schedule</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/_redhead.css" />

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="/assets/js/_sidebar.js"></script>
</head>
<body>
    <div>
        <div bp="grid gap-none">
            <div bp="1" id="sidebar">

            </div>
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

    <script src="https://kit.fontawesome.com/76ef5d807b.js"></script>
</body>
</html>
