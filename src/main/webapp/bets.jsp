<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 09/07/2019
  Time: 09:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Redhead - Wetten</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/_redhead.css" />
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:useBean id="bean" class="xyz.dommi.beans.BetGameBean" scope="request"/>

    <div>
        <h1>Wetten</h1>
        <ul id="bets_list" class="rh-listview">

        </ul>
    </div>

    <script>
      var gameList = JSON.parse('<jsp:getProperty name="bean" property="gameList"/>');
      console.log('Game list fetched', gameList);

      if (gameList.status != "OK") {
        alert("Ein Fehler ist aufgetreten");
      }

      gameList.value.forEach(game => {

        let date = new Date(game.date.$date);

        $('#bets_list').append(
          `<li rh-id="${game._id}">
            <small class="date">${date.toLocaleString()}</small><br/>
            ${game.description}
            <hr/>
            <small class="footnote">${game.bets.length} Leute haben bereits darauf gewettet</small>
          </li>
        `);
      });
    </script>
</body>
</html>
