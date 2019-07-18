function submitFeedback() {
    var feedbackContent = $('#inputFeedback').val();

    if (feedbackContent == '' || feedbackContent === null) {
        Swal.fire({text: 'Bitte beschreibe Dein Anliegen zuerst'});
        return;
    }

    postSuggestion(feedbackContent).then(success => {
        if (success) {
            Swal.fire({
                text: 'Dein Anliegen wurde in den Kummerkasten eingeworfen'
            });
        }
    });
}

async function claimReward(){

    var body = await postReward();
    if(body.status == "OK"){
        Swal.fire({
            text: "Du hast "+body.value + " Punkte erhalten"
        });
    }else{
        Swal.fire({
            text: body.value,
            type: 'error'
        });
    }
}

function init() {
    getUser().then(u => {
        $('[rh-js=username]').text(u.value.name);
        $('[rh-js=points]').text(`${u.value.points} Punkte`);
    });

    getGameList().then(l => {
        let latestGame = l.value.reverse()[0];

        getUser(latestGame.creator).then(u => {
            $('[rh-js=game-hero]').html(`
                <div>
                    <div>
                        <b>Aktuelle Wette von ${u.value.name}:</b>
                        <br /> ${latestGame.description}
                    </div>
                    <br />
                    <a class="rh-button small">Zu den Wetten</a>
                </div>
            `);
        })
    })
}

init();
