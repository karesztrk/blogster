<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/j_spring_security_check" var="loginUrl" />

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
	aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- 
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
 			-->
			<div class="modal-body">
				<div class="omb_login">
					<h3 class="omb_authTitle">Login</h3>
					<div class="row omb_row-sm-offset-3 omb_socialButtons">
						<c:url value="/signin/facebook" var="facebookSigninUrl" />
			    	    <div class="col-xs-6 col-sm-4">
			    	    	<form:form name="socialSigninForm" action="${facebookSigninUrl}">
								<input type="hidden" name="scope" value="email" />
								<i class="fa fa-facebook visible-xs"></i>
								<button type="submit" class="btn btn-lg btn-block omb_btn-facebook">Facebook</button>
							</form:form>
					        
				        </div>
			        	<div class="col-xs-6 col-sm-4">
					        <a href="#" class="btn btn-lg btn-block omb_btn-twitter">
						        <i class="fa fa-twitter visible-xs"></i>
						        <span class="hidden-xs">Twitter</span>
					        </a>
				        </div>		
					</div>
					
					<div class="row omb_row-sm-offset-3 omb_loginOr">
						<div class="col-xs-12 col-sm-8">
							<hr class="omb_hrOr">
							<span class="omb_spanOr">or</span>
						</div>
					</div>
			
					<div class="row omb_row-sm-offset-3">
						<div class="col-xs-12 col-sm-8">	
						    <form id="loginForm" method="post" class="form-horizontal" action="${loginUrl}">
								<div>
									<input type="email" class="form-control" name="username" id="username" placeholder="Email address">
								</div>
								<span class="help-block"></span>
													
								<div>
									<input type="password" class="form-control" name="password" id="password" placeholder="Password">
								</div>
			                    <span class="help-block" id="form-error"></span>
			
								<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
							</form>
						</div>
			    	</div>
					

				</div>
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

