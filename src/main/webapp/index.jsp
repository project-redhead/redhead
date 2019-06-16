<html>
<body>
<h2>Get User Data:</h2>
    <input name="Userinfo" type="button" onclick="request()"/>
<script>
    function request() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "http://localhost:8080/request?type=UserInfo&id=125313392239050752", false );
        xmlHttp.send();
        alert(xmlHttp.responseText);
    }
</script>
</body>
</html>
