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

    window.currentUser = user;

    return user;
}


// Suggestions / Kummerkasten
async function postSuggestion(content) {
    var escapedSuggestionContent = escape(content);
    var res = await postHttp('/request?type=CreateSuggestion&content=' + escapedSuggestionContent, { });

    if (!res.ok) {
        alert('Beim Übermitteln ist ein Fehler aufgetreten.');
        return false;
    }

    return true;
}


// Games
async function getGameList() {
    let url = `/request?type=GameList`;
    let res = await getHttp(url);

    let body = await res.json();
    return body;
}

async function getGame(id) {
    let url = `/request?type=GameInfo&id=${id}`;
    let res = await getHttp(url);

    let body = await res.json();
    return body;
}

async function postGame(description, options) {
    let optionString = Array(options).join(',');
    let url = `/request?type=CreateGame&description=${description}&options=${optionString}`;
    let res = await postHttp(url);

    let body = await res.json();
    return body;
}


async function postAnswer(gameId, optionIndex) {
    let url = `/request?type=SetAnswer&id=${gameId}&value=${optionIndex}`;
    let res = await postHttp(url);

    let body = await res.json();
    return body;
}


// Bets
async function postBet(gameId, points, optionIndex) {
    let res = await postHttp(`/request?type=CreateBet&gameId=${gameId}&amount=${points}&option=${optionIndex}`);

    let body = await res.json();

    if (body.status == "OK")
        return true;
    else
        return body;
}// Reward
async function postReward() {
    let res = await postHttp(`/request?type=ClaimReward`);

    let body = await res.json();
    return body;
}