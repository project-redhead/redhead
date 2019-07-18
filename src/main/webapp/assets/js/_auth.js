function getCookie(name) {
	// Stolen from https://stackoverflow.com/questions/10730362/get-cookie-by-name
	// because no one uses 🍪 anymore except for marketing shenanigangs
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}

function handleCheckAuth() {
	let token = getCookie('token');
	if (!token) {
		document.location.href = '/login';
	}
}

handleCheckAuth();
