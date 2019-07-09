function submitFeedback() {
    var feedbackContent = $('#inputFeedback').val();
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
