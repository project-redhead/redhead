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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:useBean id="bean" class="xyz.dommi.beans.BetGameBean" scope="request"/>

    <div>
        <h1>Wetten</h1>
        <ul id="bets_list" class="rh-listview">

        </ul>
    </div>

    <script src="/assets/js/common/http-service.js"></script>
    <script>
      function initList() {
        // Hide all options
        $('[rh-id-options]').hide();

        // Add event listeners
        $('[rh-id]').click(handler => {
          let id = $(handler.target).attr('rh-id');

          $(`[rh-id-options="${id}"]`).slideToggle();
        });

        $('[rh-bet-option]').click(handler => {
          let optionElement = handler.target;

          let gameId = $(optionElement).attr('rh-game');
          let gameOption = $(optionElement).attr('rh-bet-option');

          Swal.fire({
            type: 'question',
            title: `Für "${gameOption}" wetten`,
            text: 'Wie hoch ist Dein Wetteinsatz?',
            input: 'text',
            showCancelButton: true,
            confirmButtonText: 'Wette einreichen'
          });
        });
      }

      async function fetchList() {
        // Init
        var gameList = JSON.parse('<jsp:getProperty name="bean" property="gameList"/>');
        console.log('Game list fetched', gameList);

        if (gameList.status != "OK") {
          alert("Ein Fehler ist aufgetreten");
        }

        for (const game of gameList.value) {
          let date = new Date(game.date.$date);
          let user = (await getUser(game.creator)).value;

          let optionsHtml = '';
          game.options.forEach(option => {
            optionsHtml +=
              `<a href="#" rh-bet-option="${option}" rh-game="${game._id}" class="rh-button small">
                ${option}
              </a>`;
          });

          $('#bets_list').append(
            `<li rh-id="${game._id}">
              <small class="date">${date.toLocaleString()} von ${user.name}</small><br/>
              ${game.description} <br/>
              <div rh-id-options="${game._id}">
                <hr/>
                <small>Wette abgeben:</small>
                <div class="rh-button-bar">${optionsHtml}</div>
              </div>
              <hr/>
              <small class="footnote">${game.bets.length} Leute haben bereits darauf gewettet</small>
            </li>
          `);
        }

        initList();
      }

      $().ready(() => fetchList());
    </script>
</body>
</html>
