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
					<div class="col-md-6 col-md-offset-3">

						<c:url value="/j_spring_security_check" var="loginUrl" />
			
						<form:form name="loginForm" action="${loginUrl}">
							<img id="profile-img" class="img-responsive img-circle about-portrait" width="200" height="200" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png">
							<p id="profile-name" class="profile-name-card"></p>
						
							<div class="form-group">
								<input id="username" name="username" placeholder="Username or email address" class="form-control input-lg requiredField" data-error-empty="Enter name" type="text">
							</div>
		
							<div class="form-group">
								<input id="password" name="password" placeholder="Password" class="form-control input-lg requiredField" data-error-empty="Enter name" type="password">
							</div>
		
							<div class="form-group">
								<button name="submit" type="submit" class="btn btn-block">Login</button>
							</div>
		
							<div class="text-danger" id="form-error"></div>
						
						</form:form>
						<c:url value="/signin/test" var="facebookLoginUrl" />
						<a href="${facebookLoginUrl}" class="btn btn-block">Login with FB</a>

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