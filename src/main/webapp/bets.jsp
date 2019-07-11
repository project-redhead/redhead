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
        <a href="/">&laquo; Zurück</a>
        <h1>Wetten</h1>

        <div>
          <div class="rh-widget">
              <div class="rh-widget-header">
                  Eine neue Wette erstellen
              </div>
              <div class="rh-widget-content">
                  <div>
                    <div>
                      <label for="description">Wie lautet die Wette?</label>
                      <input id="newGameDescriptionInput" type="text" class="block" name="description" placeholder="Wird morgen jemand zu spät erscheinen?">
                    </div>

                    <hr/>

                    <div>
                      <label for="options">Füge ein paar Optionen hinzu:</label>

                      <div rh-id="options_container">

                      </div>

                      <a id="btnAddOption" class="rh-link-button" href="#">
                        <small style="width:100%;text-align:right">Neue Option</small>
                      </a>

                      <script>
                        $('#btnAddOption').click(handler => {
                          $('[rh-id=options_container]').append(
                            `<div game-option-container>
                              <input game-option class="block" type="text">
                            </div>`
                          );
                        });
                      </script>
                    </div>

                    <hr />

                    <div>
                      <a id="btnCreateBet" class="rh-button" href="#">Wette eröffnen</a>
                      <script>
                        $('#btnCreateBet').click(async handler => {
                          // Get options
                          let options = $('input[game-option]').toArray();
                          let optionValues = [];
                          options.forEach(element => {
                            console.log(options);
                            optionValues.push($(element).val());
                          });

                          let description = $('#newGameDescriptionInput').val();

                          // Submit game
                          let response = await postGame(description, optionValues);
                          if (response.status == "OK") {
                            Swal.fire('Deine Wette wurde eröffnet');
                          }
                          else {
                            Swal.fire({
                              text: 'Ein Fehler ist aufgetreten. Bitte versuche es erneut.',
                              type: 'error'
                            })
                          }

                          // Clean up
                          $('[game-option-container]').remove();
                          $('#newGameDescriptionInput').val('');
                        });
                      </script>
                    </div>
                  </div>
              </div>
          </div>
        </div>

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

        $('[rh-bet-option]').click(async handler => {
          // Gather properties
          let optionElement = handler.target;

          let gameId = $(optionElement).attr('rh-game');
          let gameOption = $(optionElement).attr('rh-bet-option');
          let gameOptionId = $(optionElement).attr('rh-bet-option-id');

          // Get current user meta data
          let user = await getUser();
          let userTotalPoints = user.value.points;

          // Prepare dialog
          let dialogResult = await Swal.fire({
            type: 'question',
            title: `Für "${gameOption}" wetten`,
            text: 'Wie hoch ist Dein Wetteinsatz?',
            input: 'text',
            showCancelButton: true,
            confirmButtonText: 'Wette einreichen',

            footer: `Verfügbare Punkte: ${userTotalPoints}`
          });

          // Evaluate dialog result
          if (dialogResult.value) {
            // Validate
            let points = dialogResult.value;

            // Submit to API
            var betResult = await postBet(gameId, points, gameOptionId);

            // Refresh page
            if (betResult === true)
              Swal.fire('Deine Wette wurde eingereicht');
            else
              Swal.fire({
                type: 'error',
                text: betResult.value
              });
          }
        });
      }

      async function fetchList() {
        // Clear
        $('#bets_list').children().remove();

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
          let optionId = 0;

          game.options.forEach(option => {
            optionsHtml +=
              `<a href="#" rh-bet-option="${option}" rh-bet-option-id="${optionId}" rh-game="${game._id}" class="rh-button small">
                ${option}
              </a>`;

              optionId++;
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
              <small class="footnote">${game.bets.length} Leute wetteten bereits darauf</small>
            </li>
          `);
        }

        initList();
      }

      $().ready(() => fetchList());
    </script>
</body>
</html>
