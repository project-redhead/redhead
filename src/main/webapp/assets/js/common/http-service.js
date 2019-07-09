async function getHttp(url) {
    var result = await fetch(url, {
        method: 'GET',
        cache: "no-cache"
    });

    return result;
}

async function postHttp(url, body) {
    var result = await fetch(url, {
        method: 'POST',
        cache: "no-cache",
        body: body
    });

    return result;
}

// User methods
async function getUser(id = '') {
    var res = await getHttp(`/request?type=UserInfo&id=${id}`);

    if (!res.ok) {
        alert('Ein Fehler ist aufgetreten beim Laden des Nutzers.');
        return;
    }

    var user = await res.json();
    console.log(user);
    return user;
}



async function postSuggestion(content) {
    var escapedSuggestionContent = escape(content);
    var res = await postHttp('/request?type=CreateSuggestion&content=' + escapedSuggestionContent, { });

    if (!res.ok) {
        alert('Beim Übermitteln ist ein Fehler aufgetreten.');
        return false;
    }

    return true;
}