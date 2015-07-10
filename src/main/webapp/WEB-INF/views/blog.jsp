<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	<jsp:include page="login.jsp" />
</head>

<c:url var="firstUrl" value="">
	<c:param name="page" value="1"/>
</c:url>
<c:url var="lastUrl" value="">
	<c:param name="page" value="${posts.totalPages}"/>
</c:url>
<c:url var="prevUrl" value="">
	<c:param name="page" value="${currentIndex - 1}"/>
</c:url>
<c:url var="nextUrl" value="">
	<c:param name="page" value="${currentIndex + 1}"/>
</c:url>
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
							<sec:authorize access="isAnonymous()">
								<a href="#" class="btn btn-blog outline-white pull-right" data-toggle="modal"
									data-target="#loginModal">Login</a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<a class="btn btn-blog outline-white pull-right"
									href="<c:url value="/j_spring_security_logout" />">Logout</a>
							</sec:authorize>
						</div>
				</div>
			</div>
					
			<jsp:include page="cover.jsp" />

			<div id="start" class="container content">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
					
						<c:choose>
							<c:when test="${numberOfPosts > 0}">
								<c:forEach items="${posts.content}" var="post">
								
									<article class="clearfix">
										<div class="post-date">
											<fmt:formatDate value="${post.date}"
										pattern="yyyy-MM-dd" /> | <a href="">${post.user.name} </a> <span><a href="">0 Comments</a></span>
										</div>		
										<h2><a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}">${post.title}</a></h2>
										<p>${post.content}</p>
									</article>
								
								</c:forEach>
							</c:when>
							<c:otherwise>
								<p class="text-center text-info">
									<i class="icon-sad"></i><span> We couldn't find any match for your search criteria</span>
								</p>
							</c:otherwise>
						</c:choose>

						<div class="paging clearfix">

							<c:if test="${numberOfPosts > 0}">
								<c:choose>
									<c:when test="${currentIndex == posts.totalPages}">
										<div></div>
									</c:when>
									<c:otherwise>
										<a href="${nextUrl}" class="btn pull-left"><i class="icon-arrow-left2 left"></i><span>Older</span><span class="hidden-xs"> Posts</span></a>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${currentIndex != 1}">
										<a href="${prevUrl}" class="btn pull-right"><span>Newer</span><span class="hidden-xs"> Posts</span><i class="icon-arrow-right2 right"></i></a>
									</c:when>
								</c:choose>
							</c:if>

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