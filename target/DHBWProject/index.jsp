<html>
<body>
<h2>Hello:</h2>
<jsp:useBean id="user" class="xyz.dommi.beans.UserBean" scope="request"/>
<jsp:getProperty name="user" property="name"/>
<jsp:getProperty name="user" property="id"/>

<h2>Add Game:</h2>
<form method="post" action="request">
    <input type="text" name="type" value="addGame">
    <input type="text" name="id" value="">
    <input type="text" name="des" value="">
    <input type="text" name="redirect" value="">
    <input type="submit" />
</form>
<h2>Add Bet:</h2>
<form method="post" action="request">
    <input type="text" name="type" value="addBet">
    <input type="text" name="id" value="id">
    <input type="text" name="gameid" value="gameid">
    <input type="text" name="amount" value="amount">
    <input type="text" name="option" value="option">
    <input type="text" name="redirect" value="">
    <input type="submit" />
</form>
</body>
</html>
