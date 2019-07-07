<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 07/07/2019
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Redhead - Kummerkasten</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/_redhead.css" />
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:useBean id="bean" class="xyz.dommi.beans.SuggestionBean" scope="request"/>

    <div>
        <h1>Kummerkasten</h1>
        <ul id="suggestion_list" class="rh-listview">

        </ul>
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
</body>
</html>
