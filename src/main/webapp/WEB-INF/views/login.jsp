<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>The WODster</title>
	
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,700|Merriweather:400,400italic,700italic" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/icons.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/animate.min.css" />">
	<link rel="shortcut icon" href="i#" sizes="32x32" type="image/png"/>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/placeholders.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/wow.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/custom.js" />"></script>

	<jsp:include page="component/loginPopup.jsp" />
</head>

<c:set var="numberOfPosts" scope="page" value="${posts.totalElements}"/>

<body>

	<body id="home">
		<jsp:include page="menu.jsp" />
		
		<div id="wrap">
			<div id="main-nav" class="">
				<div class="container">
					<div class="nav-header">
						<a class="nav-brand" href="${pageContext.servletContext.contextPath}/">Wodster</a>
						<a class="menu-link nav-icon" href="#"><i class="icon-menu2"></i></a>
					</div>
				</div>
			</div>
					
			<section id="hero" class="light-typo">
				<div id="cover-image" class="image-bg animated fadeIn"></div>
				<div class="container welcome-content">
					<div class="middle-text">
						<h1>Login</h1>
					</div>
				</div>
			</section>

			<div class="container content">
				
				<div class="row">
					
					<div class="omb_login">
						
						<img id="profile-img" class="img-responsive img-circle about-portrait" width="200" height="200" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png">
			   			<p id="profile-name" class="profile-name-card"></p>
						
						<div class="row omb_row-sm-offset-3">
							<div class="col-xs-12 col-sm-8">	
								<c:url value="/j_spring_security_check" var="loginUrl" />
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
				    	
				    	<div class="row omb_row-sm-offset-3 omb_loginOr">
							<div class="col-xs-12 col-sm-8">
								<hr class="omb_hrOr">
								<span class="omb_spanOr">or</span>
							</div>
						</div>
				    	
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
						
					</div>
				
				</div><!-- end row -->		
			</div><!--
				<footer>
					<div class="footer">
						<div class="container">
							<div class="row">
	
								 Footer placeholder
							</div>
						</div>
					</div>
				</footer> -->
		</div>		

	</body>
</html>