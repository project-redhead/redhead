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

function init() {
    getUser().then(u => {
        $('[rh-js=username]').text(u.value.name);
    });
}

init();
