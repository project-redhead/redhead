<%-- Created by IntelliJ IDEA. User: GinoMessmer Date: 01/07/2019 Time: 20:24 To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Home"/>
  </jsp:include>
  <body>
    <div bp="grid gap-none">
      <div id="sidebar" bp="1"></div>
      <div id="widget-area" bp="11">
        <div rh-js="wip"></div>
        <div class="rh-content">
          <div style="margin:0px 10px">
            <h3>
              <span>
                <span>Hey </span>
                <span rh-js="username"></span>
                <span> 👋</span>
              </span>

              <span style="float:right">
                <span rh-js="points"></span>
              </span>
            </h3>
          </div>

          <div bp="grid gap-none">
            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Wetten
              </div>
              <div class="rh-widget-content">
                <div rh-js="game-hero"></div>
              </div>
            </div>

            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Kummerkasten
              </div>
              <div class="rh-widget-content">
                <div>
                  <label for="inputFeedback">Dein Anliegen</label>
                  <input
                    type="text"
                    class="block"
                    id="inputFeedback"
                    name="inputFeedback"
                  />
                </div>

                <br />

                <div>
                  <a
                    class="rh-button small"
                    href="#"
                    onclick="submitFeedback(this)"
                    >Absenden</a
                  >
                  <a
                    class="rh-button small"
                    href="/request?type=SuggestionBean&format=bean&redirect=/suggestions.jsp"
                    >Einsendungen</a
                  >
                </div>
              </div>
            </div>

            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Stundenplan
              </div>
              <div class="rh-widget-content">
                <a class="rh-button" href="/schedule.jsp">Mein Stundenplan</a>
              </div>
            </div>

            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Fahrplan
              </div>
              <div class="rh-widget-content">
                <a class="rh-button" href="/bahn.jsp">Meine Bahnverbindung</a>
              </div>
            </div>

            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Tägliche Belohnung
              </div>
              <div class="rh-widget-content">
                <a class="rh-button" href="#" onclick="claimReward()"
                  >Einlösen</a
                >
              </div>
            </div>

            <div class="rh-widget" bp="6@lg 12@md">
              <div class="rh-widget-header">
                Heute in der Mensa
              </div>
              <div class="rh-widget-content" rh-js="mensa-widget">
                <ul rh-js="mensa-list"></ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


    <script src="/assets/js/index.js"></script>

    <jsp:include page="partials/footer.jsp" />
  </body>
</html>
