<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>The WODster</title>
	
	<link rel="stylesheet" href="resources/css/main.css">
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,400italic,600,600italic,800,800italic" >
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,300,700" >
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="shortcut icon" type="image/png" href="resources/images/favicon.png">
	
	<script src="resources/js/jquery-1.9.0.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>

	<jsp:include page="login.jsp" />
</head>

<body>

	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav">
				<a class="blog-nav-item active" href="#">Home</a>
				<a class="blog-nav-item" href="${pageContext.servletContext.contextPath}/blog">Blog</a> 
				<a class="blog-nav-item" href="#">About me</a>
				<sec:authorize access="isAnonymous()">
					<a href="#" class="blog-nav-item" data-toggle="modal"
						data-target="#loginModal">Login</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<a class="blog-nav-item"
						href="<c:url value="/j_spring_security_logout" />">Logout</a>
				</sec:authorize>
			</nav>
		</div>
	</div>

	<div class="container">

		<div class="blog-header">
			<h1 class="blog-title">The WODster</h1>
			<p class="lead blog-description"></p>
		</div>

		<div class="row">

			<div class="col-sm-8 blog-main"></div>

			<jsp:include page="./sidebar.jsp" />

		</div>

	</div>

	<footer class="blog-footer">
		<p>
			Blog template built for <a href="http://getbootstrap.com">Bootstrap</a>
			by <a href="https://twitter.com/mdo">@mdo</a>.
		</p>
		<p>
			<a href="#">Back to top</a>
		</p>
	</footer>

</body>
</html>
