<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/j_spring_security_check" var="loginUrl" />

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
	aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Login</h4>
			</div>

			<div class="modal-body">
				<!-- The form is placed inside the body of modal -->
				<form id="loginForm" method="post" class="form-horizontal"
					action="${loginUrl}">
					<div class="form-group">
						<label class="col-xs-3 control-label">Username</label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="username"
								id="username" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-3 control-label">Password</label>
						<div class="col-xs-5">
							<input type="password" class="form-control" name="password"
								id="password" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-5 col-xs-offset-3">
							<button type="submit" class="btn btn-primary">Login</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
						</div>
					</div>

					<div class="text-danger" id="form-error"></div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		var loginForm = $('#loginForm');

		loginForm.submit(function(e) {
			var form = $(this);
			var formData = form.serializeArray();
			$.ajax({
				url : '${loginUrl}',
				method : "POST",
				data : formData,
				beforeSend : function(xhr) {
					xhr.setRequestHeader("X-Ajax-call", "true");
				},
				success : function(response) {
					$('#loginModal').modal('hide');
					location.reload();
				},
				error : function(response) {
					var resp = JSON.parse(response.responseText);
					$('#form-error').html(resp.message);
				}
			});
			// stop the form from submitting the normal way and refreshing the page
			e.preventDefault();
		});

	});
</script>

