var IS_WIP = false;

function initWip() {
	if (!IS_WIP) return;

	$('[rh-js=wip]').html(`<div class="alert warning">
		<center>Work in Progress</center>
	</div>`);
}

initWip();
