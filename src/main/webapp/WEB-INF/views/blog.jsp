<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<c:url var="firstUrl" value="/pages/1" />
<c:url var="lastUrl" value="/pages/${posts.totalPages}" />
<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />

<body>

	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav">
				<a class="blog-nav-item"
					href="${pageContext.servletContext.contextPath}/">Home</a> <a
					class="blog-nav-item active" href="#">Blog</a> <a
					class="blog-nav-item" href="#">About me</a>
				<sec:authorize access="isAnonymous()">
					<a class="blog-nav-item" href="#" data-toggle="modal"
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

			<div class="col-sm-8 blog-main">

				<sec:authorize  access="hasRole('ADMINISTRATOR')">
					<a href="${pageContext.servletContext.contextPath}/blog/add">
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							Write new
						</button>
					</a>
				</sec:authorize>

				<c:if test="${!empty posts}">
					<c:forEach items="${posts.content}" var="post">
						<div class="blog-post">
							<h2 class="blog-post-title">${post.title}</h2>
							<p class="blog-post-meta">
								<fmt:formatDate value="${post.date}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								<a href="#">${post.user.name}</a>
							</p>
							<p>${post.content}</p>
						</div>
					</c:forEach>
				</c:if>

				<nav>
					<ul class="pager">
						<c:choose>
							<c:when test="${currentIndex == deploymentLog.totalPages}">
								<li class="previous"><a href="${nextUrl}"><span
										aria-hidden="true">&larr;</span> Older</a></li>
							</c:when>
							<c:otherwise>
								<li class="previous"><a href="#"><span
										aria-hidden="true">&larr;</span> Older</a></li>
							</c:otherwise>
						</c:choose>
						<!-- <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
		            <c:url var="pageUrl" value="/pages/${i}" />
		            <c:choose>
		                <c:when test="${i == currentIndex}">
		                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
		                </c:when>
		                <c:otherwise>
		                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
		                </c:otherwise>
		            </c:choose>
		        </c:forEach> -->
						<c:choose>
							<c:when test="${currentIndex != 1}">
								<li class="next"><a href="${prevUrl}">Newer <span
										aria-hidden="true">&rarr;</span></a></li>
							</c:when>
						</c:choose>
					</ul>
				</nav>

			</div>
			<!-- /.blog-main -->

			<jsp:include page="./sidebar.jsp" />

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

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
