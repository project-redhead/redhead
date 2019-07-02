function submitFeedback() {
    var content = $('#inputFeedback').val();
    alert(content);
}

function init() {
    getUser().then(u => {
        $('[rh-js=username]').text(u.value.name);
    });
}

init();
