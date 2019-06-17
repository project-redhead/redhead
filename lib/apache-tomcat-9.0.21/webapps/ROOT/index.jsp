<html>
<body>
<h2>Examples:</h2>
<input name="Userinfo" type="button" value="Userinfo" onclick="requestUser()"/>
<input name="AddGame" type="button" value="AddGame" onclick="addGame()"/>
<input name="AddBet" type="button" value="AddBet" onclick="addBet()"/>
<input name="GameList" type="button" value="GameList" onclick="gameList()"/>
<input name="GameInfo" type="button" value="GameInfo" onclick="gameInfo()"/>
<input name="BetInfo" type="button" value="BetInfo" onclick="betInfo()"/>
<script>
    function requestUser() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=UserInfo&id=125313392239050752", false );
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
        xmlHttp.open( "POST", "http://localhost:8080/request?type=CreateBet&id=125313392239050752&gameId=5d07398f94c47b1c2f5ef168&amount=42&option=0", false );
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
        xmlHttp.open( "POST", "http://localhost:8080/request?type=GameInfo&id=5d07398f94c47b1c2f5ef168", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
    function betInfo() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=BetInfo&gameId=5d07398f94c47b1c2f5ef168&id=0", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
</script>
</body>
</html>
