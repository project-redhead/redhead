async function getHttp(url) {
    var result = await fetch(url, {
        method: 'GET',
        cache: "no-cache"
    });

    return result;
}

function postHttp(url, body) {
    // TODO
}

// User methods
async function getUser() {
    var res = await getHttp('/request?type=UserInfo');

    if (!res.ok) {
        alert('Ein Fehler ist aufgetreten beim Laden des Nutzers.');
        return;
    }

    var user = await res.json();
    console.log(user);
    return user;
}
