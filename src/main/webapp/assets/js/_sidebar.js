$(document).ready(() => {
	// Attach sidebar
	$('#sidebar').append(`
		<div class="rh-sidebar">
			<div class="rh-sidebar-action-item" rh-link="/request?type=BetGameBean&format=bean&redirect=/bets.jsp">
				<span class="border"></span>

				<i class="rh-icon fas fa-trophy fa-2x"></i>
			</div>

			<div class="rh-sidebar-action-item" rh-link="/">
				<span class="border"></span>

				<i class="rh-icon fas fa-home fa-2x"></i>
			</div>

			<div class="rh-sidebar-action-item" rh-link="/request?type=SuggestionBean&format=bean&redirect=/suggestions.jsp">
				<span class="border"></span>

				<i class="rh-icon fas fa-inbox fa-2x"></i>
			</div>
		</div>
	`);

	$('.rh-sidebar-action-item').click(handler => {
		let link = $(handler.target).attr('rh-link');
		document.location.href = link;
	});
});
