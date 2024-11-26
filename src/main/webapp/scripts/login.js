
   $('#login-form').on('submit', function(e) {
		e.preventDefault();
		$.ajax({
			url: '/ProjetoGTD/login',
			method: 'POST',
			data: {
				email: $('#email').val(),
				senha: $('#senha').val()
			},
			success: function(response) {
				console.log(response);
				if (response.status === 'success') {
					window.location.href = 'pages/dashboard.html';  
				}
			},
			error: function(xhr) {
				console.log(xhr	);
				$('#error-message').text(xhr.responseJSON.message).show();
			}
		});
	});