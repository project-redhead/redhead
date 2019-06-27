<html>
<body>
<h2>Examples:</h2>
<input name="Userinfo" type="button" value="Userinfo" onclick="requestUser()"/>
<input name="AddGame" type="button" value="AddGame" onclick="addGame()"/>
<input name="AddBet" type="button" value="AddBet" onclick="addBet()"/>
<input name="GameList" type="button" value="GameList" onclick="gameList()"/>
<input name="GameInfo" type="button" value="GameInfo" onclick="gameInfo()"/>
<input name="BetInfo" type="button" value="BetInfo" onclick="betInfo()"/>
<input name="SetAnswer" type="button" value="SetAnswer" onclick="setAnswer()"/>
<script>
    function requestUser() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=UserInfo&id=312279086690992128", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function addGame() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=CreateGame&creatorId=125313392239050752&description=bla&options=yes,no", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function addBet() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=CreateBet&id=125313392239050752&gameId=5d138fad165c1429886120f1&amount=42&option=0", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function gameList() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=GameList", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function gameInfo() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=GameInfo&id=5d0766b194c4a1ba2061033b", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function betInfo() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=BetInfo&gameId=5d0766b194c4a1ba2061033b&id=0", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function setAnswer() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=SetAnswer&id=5d0766b194c4a1ba2061033b&userId=125313392239050752&value=0", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
</script>
</body>
</html>
