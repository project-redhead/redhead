<%--
  Created by IntelliJ IDEA.
  User: GinoMessmer
  Date: 03/08/2019
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Hilfe"/>
</jsp:include>
<body>
<div>
    <div bp="grid gap-none">
        <div bp="1" id="sidebar"></div>
        <div bp="11" class="rh-content">
            <h2>Hi, <br> hier findest Du alles was Du wissen musst, um Dich auf Deiner Kursseite zurechtzufinden.</h2>
            <p>
            <h3>Grundlayout</h3>
            Auf der linken Seite befindet sich die globale Navbar
            Es gibt eine Seitenüberschrift mit dem entsprechenden Seitennamen.
            Der Rest der Seite ist mit verschiedenen Widgets gefüllt, Durch die verschiedene Aktionen möglich sind.
            </p>

            <p>
                <h3>Startseite</h3>
                Die einzelnen Widgets kurz erklärt:
                <table>
                    <tr>
                        <td>Wetten:</td>
                        <td>Zeigt die letzte eingestellte Wette an und bietet einen Butten um zur Übersicht der Wetten zu kommen</td>
                    </tr>
                    <tr>
                        <td>Kummerkasten: </td>
                        <td>Eingabemöglichkeit für ihr Problem und Button um dieses abzusenden</td>
                    </tr>
                    <tr>
                        <td>Stundenplan: </td>
                        <td>"Mein Stundenplan"-Button leitet zum Stundenplan wei-ter</td>
                    </tr>
                    <tr>
                        <td>Fahrplan: </td>
                        <td>"Meine BahnverbinDung"-Button leitet weiter auf die Fahrplan-Seite</td>
                    </tr>
                    <tr>
                        <td>Tägliche Belohnung:</td>
                        <td>"Einlösen"-Button kann alle 24 Stunden genutzt werden um eine tägliche Belohnung, in Form von Punkten für das Wettspiel, freizuschalten.
            Sollte das letzte Betätigen des Buttons keine 24 Stunden her sein, wird ein Alert-Text ausgegeben, der ausgibt, dass die letzte Betätigung keine 24 Stunden her ist.
            </td>
                    </tr>
                    <tr>
                        <td>Mensa</td>
                        <td>Zeigt an was es heute in der Mensa der DHBW Karlsruhe zu essen gibt.</td>
                    </tr>
                </table>
            </p>
            <p>
                <h3>Wetten-Seite</h3>
                <p>
                    <h4>"Neue Wette erstellen":</h4>
                    <ol>
                        <li>Eingabemöglichkeit für den Namen der Wette. -> Name der Wette eingeben.</li>
                        <li>Wettoptionen einfügen. Durch "Neue Option" kann eine weiter Option eingefügt werden.-> Für neue Wettoption "Neue Option" auswählen (Mehr als eine Wettoption angeben, an-sonsten kann keine Wette erstellt werden).</li>
                        <li>"Wette eröffnen"-Button stellt die Wette online. -> Button betätigen.</li>
                    </ol>
                </p>
                <p>
                    <h4>Wetten-Übersicht:</h4>
                Wette:
                <ol>
                    <li>Erstellungsdatum der Wette + Ersteller der Wette.</li>
                    <li>Name der Wette.</li>
                    <li>Auswahl der Wett-Optionen. Durch wählen einer Option öffnet sich eine Eingabemöglich-keit, in der man die Anzahl der Punkte angeben kann, die gewettet werden sollen. Durch den "Wette einreichen"-Button kann die Wette abgeschickt werden. Durch den "Cancel"-Button wird der Vorgang abgebrochen.</li>
                    <li>Anzahl der Leute, die auf die Wette gewettet haben. Unterhalb der beiden Buttons wird die verfügbare Anzahl an Punkten für diesen Account angegeben.</li>
                </ol>

                </p>
            </p>
            <p>
                    <h3>Kummerkasten Seite</h3>
                <p>
                    <h4>Problem einreichen:</h4>
                    <ol>
                        <li>Auf der Startseite im Kummerkasten-Widget das Problem eingeben.</li>
                        <li>"Absenden"-Button betätigen.</li>
                        <li>Nun wird das Problem auf der Kummerkasten-Seite angezeigt und kann explizit von den Kurssprechern angegangen werden.</li>
                    </ol>
                </p>
                <p>
                    <h4>Problem:</h4>
                    <ol>
                        <li>Erstellungsdatum des Problems (Anonym).</li>
                        <li>Problemtext</li>
                    </ol>
                </p>
            </p>
            <p>
                    <h3>Stundenplan Seite</h3>
                    Der Rapla der DHBW Karlsruhe wird angezeigt, der Umgang damit ist wie bei dem „normalen“ Rapla auch.
            </p>
            <p>
                    <h3>Fahrplan Seite</h3>
                    Durch Embedded Tags wird hat man auf dieser Seite die Möglichkeit, sich eine VerbinDung anzeigen zu lassen. Der Umgang damit ist gleich dem KVV Navigator.
            </p>
        </div>
    </div>
</div>
<jsp:include page="partials/footer.jsp" />
</body>
</html>
