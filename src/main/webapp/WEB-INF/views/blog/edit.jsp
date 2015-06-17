<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/placeholders.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/wow.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/custom.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-wysiwyg.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.hotkeys.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/init-editor.js" />"></script>
	<script type="text/javascript">
		function loadVal(){
			var desc = $("#editor").html();
			document.postForm.content.value = desc;
		}
	</script>

	<jsp:include page="../login.jsp" />
</head>

<body>

	<body id="home">
		<jsp:include page="../menu.jsp" />
		
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
			
			<!-- Cover -->
			<section id="hero" class="light-typo">
				<div id="cover-image" class="image-bg animated fadeIn"></div>
				<div class="container welcome-content">
					<div class="middle-text">
						<h1>${post.title}</h1>
						<h2></h2>
					</div>
				</div>
			</section>

			<div id="start" class="container content">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
					
						<c:url var="saveAction" value="/blog/post"></c:url>
			
						<form:form  name="postForm" action="${saveAction}" modelAttribute="post" onsubmit="loadVal();">
						
							<input name="id" type="hidden" value="${post.id}"/>
							<input name="date" type="hidden" value="<fmt:formatDate value="${post.date}" pattern="yyyy-MM-dd" />"/>
							<div class="form-group">
								<input class="form-control input-lg requiredField" type="text" name="title" placeholder="New title" value="${post.title}"/>
							</div>
							<div class="form-group">
								<jsp:include page="../toolbar.jsp" />
								<div contenteditable="true" id="editor">${post.content}</div>
								<textarea id="content" name="content" style="display:none;"></textarea>
							</div>
							
							<div class="actions"> 
								<input type="submit" value="Save" class="btn btn-primary"/>
								<a href="${pageContext.servletContext.contextPath}/blog" class="btn outline">Cancel</a> 
							</div>
						
						</form:form>

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