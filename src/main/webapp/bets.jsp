<%-- Created by IntelliJ IDEA. User: GinoMessmer Date: 09/07/2019 Time: 09:16 To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Wetten"/>
  </jsp:include>

  <body>
    <jsp:useBean
      id="bean"
      class="xyz.dommi.beans.BetGameBean"
      scope="request"
    />

    <div bp="grid gap-none">
      <div bp="1" id="sidebar"></div>
      <div bp="11">
        <div rh-js="wip"></div>
        <div class="rh-content">
          <h1 style="margin:0px 10px">Wetten</h1>

          <div>
            <div class="rh-widget">
              <div class="rh-widget-header">
                Eine neue Wette erstellen
              </div>
              <div class="rh-widget-content">
                <div>
                  <div>
                    <label for="description">Wie lautet die Wette?</label>
                    <input
                      id="newGameDescriptionInput"
                      type="text"
                      class="block"
                      name="description"
                      placeholder="Wird morgen jemand zu spät erscheinen?"
                    />
                  </div>

                  <div class="rh-card" style="margin: 16px 0px">
                    <div class="rh-card-content">
                      <label for="options">Füge ein paar Optionen hinzu:</label>

                      <div rh-id="options_container"></div>

                      <a id="btnAddOption" class="rh-link-button" href="#">
                        <small style="width:100%;text-align:right"
                          >Neue Option</small
                        >
                      </a>

                      <script>
                        $("#btnAddOption").click(handler => {
                          $("[rh-id=options_container]").append(
                            `<div game-option-container>
                                      <input game-option class="block" type="text">
                                    </div>`
                          );
                        });
                      </script>
                    </div>
                  </div>

                  <div>
                    <a id="btnCreateBet" class="rh-button" href="#"
                      >Wette eröffnen</a
                    >
                    <script>
                      $("#btnCreateBet").click(async handler => {
                        // Get options
                        let options = $("input[game-option]").toArray();
                        let optionValues = [];
                        options.forEach(element => {
                          console.log(options);
                          optionValues.push($(element).val());
                        });

                        let description = $("#newGameDescriptionInput").val();

                        // Submit game
                        let response = await postGame(
                          description,
                          optionValues
                        );
                        if (response.status == "OK") {
                          Swal.fire("Deine Wette wurde eröffnet");
                        } else {
                          Swal.fire({
                            text:
                              "Ein Fehler ist aufgetreten. Bitte versuche es erneut.",
                            type: "error"
                          });
                        }

                        // Clean up
                        $("[game-option-container]").remove();
                        $("#newGameDescriptionInput").val("");
                      });
                    </script>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <ul id="bets_list" class="rh-listview" style="margin: 0px 10px"></ul>
        </div>

        <script src="/assets/js/common/http-service.js"></script>
        <script>
          var gameList;

          function initList() {
            // Hide all options
            $("[rh-id-options]").hide();

            // Add event listeners
            $("li[rh-id]").click(handler => {
              let id = $(handler.target).attr("rh-id");

              $(handler.target).toggleClass("interactive");
              $(`[rh-id-options="${id}"]`).animate({
                height: "toggle",
                opacity: "toggle"
              });
            });

            // Submit answer
            $(".btnSelectAnswer[rh-game-id]").click(async handler => {
              // Get game ID
              let gameId = $(handler.target).attr("rh-game-id");

              // Get available options from game
              let game = await getGame(gameId);
              let gameOptions = game.value.options;

              // Ask user for final answer
              const { value: selectIndex } = await Swal.fire({
                title: "Lösung veröffentlichen",
                text: "Wähle die richtige Antwort aus",
                input: "select",
                inputOptions: gameOptions,
                showCancelButton: true
              });

              // Submit to API
              if (selectIndex) {
                let res = await postAnswer(gameId, selectIndex);
                if (res.status == "OK") {
                  location.reload();
                }
              }
            });

            $("[rh-bet-option]").click(async handler => {
              // Gather properties
              let optionElement = handler.target;

              let gameId = $(optionElement).attr("rh-game");
              let gameOption = $(optionElement).attr("rh-bet-option");
              let gameOptionId = $(optionElement).attr("rh-bet-option-id");

              // Get current user meta data
              let user = await getUser();
              let userTotalPoints = user.value.points;

              // Prepare dialog
              let dialogResult = await Swal.fire({
                type: "question",
                title: `Für "${gameOption}" wetten`,
                text: "Wie hoch ist Dein Wetteinsatz?",
                input: "text",
                showCancelButton: true,
                confirmButtonText: "Wette einreichen",

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
                  Swal.fire("Deine Wette wurde eingereicht");
                else
                  Swal.fire({
                    type: "error",
                    text: betResult.value
                  });
              }
            });
          }

          async function fetchList() {
            // Clear
            $("#bets_list")
              .children()
              .remove();

            // Init
            gameList = JSON.parse(
              '<jsp:getProperty name="bean" property="gameList"/>'
            );
            console.log("Game list fetched", gameList);

            if (gameList.status != "OK") {
              alert("Ein Fehler ist aufgetreten");
            }

            let user = await getUser();

            for (const game of gameList.value.reverse()) {
              let isCreator = user.value._id == game.creator;

              let date = new Date(game.date.$date);
              let creatorUser = (await getUser(game.creator)).value;

              let optionsHtml = "";
              let optionId = 0;

              game.options.forEach(option => {
                optionsHtml += `<a href="#" rh-bet-option="${option}" rh-bet-option-id="${optionId}" rh-game="${
                  game._id
                }" class="rh-button small">
                      ${option}
                    </a>`;

                optionId++;
              });

              $("#bets_list").append(
                `<li rh-id="${game._id}" style="cursor: pointer">
                    <small class="date">
                      ${date.toLocaleString()} ${
                  isCreator ? "(von dir!)" : "von " + creatorUser.name
                }
                    </small><br/>

                    ${game.description}

                    <div class="rh-card" rh-id-options="${game._id}">
                      <div class="rh-card-content">
                        ${
                          game.answer === null
                            ? `${
                                !isCreator
                                  ? `<small>Wette abgeben:</small>`
                                  : `<small><a class="rh-link-button btnSelectAnswer" rh-game-id="${
                                      game._id
                                    }" href="#">Lösung veröffentlichen &raquo;</a></small>`
                              }
                          <div class="rh-button-bar">${optionsHtml}</div>`
                            : `<small>Lösung: ${
                                game.options[game.answer]
                              }</small>`
                        }
                      </div>
                    </div>
                    <small class="footnote">${
                      game.bets.length
                    } Leute wetteten bereits darauf</small>
                  </li>
                `
              );
            }

            initList();
          }

          $().ready(() => fetchList());
        </script>
      </div>
    </div>

    <script src="/assets/js/_wip.js"></script>

    <script src="https://kit.fontawesome.com/76ef5d807b.js"></script>
  </body>
</html>
