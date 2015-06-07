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
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/main.css">
	<link rel="stylesheet" href="resources/css/icons.css">
	<link rel="stylesheet" href="resources/css/animate.min.css">
	
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/placeholders.min.js"></script>
	<script type="text/javascript" src="resources/js/wow.min.js"></script>
	<script type="text/javascript" src="resources/js/custom.js"></script>

	<jsp:include page="login.jsp" />
</head>

<c:url var="firstUrl" value="/pages/1" />
<c:url var="lastUrl" value="/pages/${posts.totalPages}" />
<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />

<body>

	<body id="home">
		<div id="menu" class="menu-right">
			<ul>
				<form class="menu-search" >
					<div class="form-group header">
						<i class="icon-search searchico"></i>
						<input type="text" placeholder="Blog Search">
						<a href="#" class="close-menu"><i class="icon-close"></i></a>
					</div>
				</form>
				<li><a href="${pageContext.servletContext.contextPath}/"><i class="icon-lime"></i>Home</a></li>
				<li><a href="about.html"><i class="icon-user"></i>About</a></li>
				<li class="submenu">
					<a href="#"><i class="icon-books"></i>Categories<b class="caret"></b></a>
					<ul class="submenu-list">
						<li><a href="category.html">Blogroll <span class="badge golden">2</span></a></li>
						<li><a href="category.html">Quotes<span class="badge">4</span></a></li>
						<li><a href="category.html">Travel<span class="badge red">12</span></a></li>
						<li><a href="category.html">Writing<span class="badge blue">7</span></a></li>
					</ul>		
				</li>
				<li class="submenu submenu-open">
					<a href="#"><i class="icon-file"></i>Pages<b class="caret"></b></a>
					<ul class="submenu-list">
						<li><a href="post-image.html">Post Image</a></li>
						<li><a href="post-audio.html">Post Audio</a></li>
						<li><a href="post-video.html">Post Video</a></li>						
						<li><a href="post-typography.html">Typography</a></li>
						<li><a href="author.html">Author</a></li>
						<li><a href="search.html">Search</a></li>
						<li><a href="404.html">404 Error</a></li>
					</ul>		
				</li>
				<li class="submenu">
					<a href="#"><i class="icon-calendar"></i>Archives <b class="caret"></b></a>
					<ul class="submenu-list">
						<li><a href="archives.html">June<span>2014</span></a></li>
						<li><a href="archives.html">May<span>2014</span></a></li>
						<li><a href="archives.html">January<span>2014</span></a></li>
						<li><a href="archives.html">October<span>2013</span></a></li>
					</ul>	
				</li>
				<li><a href="contact.html"><i class="icon-envelope"></i>Contact</a></li>
			</ul>
		</div>
		<div id="wrap">
			<div id="main-nav" class="">
				<div class="container">
					<div class="nav-header">
							<a class="nav-brand" href="${pageContext.servletContext.contextPath}/"><i class="icon-lime"></i>Wodster</a>
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
					
			<section id="hero" class="light-typo">
				<div id="cover-image" class="image-bg animated fadeIn"></div>
				<div class="container welcome-content">
					<div class="middle-text">
						<h1>HELLO, I AM THE WODSTER</h1>
						<h2>Don't follow heroes, be one of them</h2>
						<a class="btn smooth-scroll" href="#start">Get Stated</a>
					</div>
				</div>
			</section>

			<div id="start" class="container content">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
					
						<c:if test="${!empty posts}">
						
							<c:forEach items="${posts.content}" var="post">
							
								<article class="clearfix">
									<div class="post-date">
										<fmt:formatDate value="${post.date}"
									pattern="yyyy-MM-dd" /> | <a href="">${post.user.name} </a> <span><a href="">0 Comments</a></span>
									</div>		
									<h2><a href="#">${post.title}</a></h2>
									<p>${post.content}</p>
								</article>
							
							</c:forEach>
						
						</c:if>

						<div class="paging clearfix">
							<c:choose>
								<c:when test="${currentIndex == deploymentLog.totalPages}">
									<a href="${nextUrl}" class="btn pull-left"><i class="icon-arrow-left2 left"></i><span>Older</span><span class="hidden-xs"> Posts</span></a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn pull-left"><i class="icon-arrow-left2 left"></i><span>Older</span><span class="hidden-xs"> Posts</span></a>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${currentIndex != 1}">
									<a href="${prevUrl}" class="btn pull-right"><span>Newer</span><span class="hidden-xs"> Posts</span><i class="icon-arrow-right2 right"></i></a>
								</c:when>
							</c:choose>

						</div>

					</div>	
				</div><!-- end row -->
			</div>
				<footer>
					<div class="footer">
						<div class="container">
							<div class="row">
	
								<!-- Footer placeholder -->
							</div>
						</div>
					</div>
				</footer>
		</div>		

	</body>
</html>