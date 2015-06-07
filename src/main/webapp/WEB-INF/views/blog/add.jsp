<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>The WODster</title>
	
	<link rel="stylesheet" href="../resources/css/main.css">
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,400italic,600,600italic,800,800italic" >
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,300,700" >
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="shortcut icon" type="image/png" href="../resources/images/favicon.png">
	
	<script src="../resources/js/jquery-1.9.1.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/bootstrap-wysiwyg.js"></script>
	<script src="../resources/js/jquery.hotkeys.js"></script>
	<script src="../resources/js/init-editor.js"></script>
	<script type="text/javascript">
		function loadVal(){
			var desc = $("#editor").html();
			document.postForm.content.value = desc;
		}
	</script>

	<jsp:include page="../login.jsp" />
</head>

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
			
				<c:url var="saveAction" value="/blog/post"></c:url>
			
				<form:form  name="postForm" action="${saveAction}" modelAttribute="post" onsubmit="loadVal();">
				
					<div class="form-group">
						<jsp:include page="../toolbar.jsp" />
						<div contenteditable="true" id="editor" ></div>
						<textarea id="content" name="content" style="display:none;"></textarea>
					</div>
					
					<div class="actions"> 
						<input type="submit" value="Save" class="btn btn-primary">
						<a href="#" class="btn btn-default">Cancel</a> 
					</div>
				
				</form:form>
				
			</div>

			<jsp:include page="../sidebar.jsp" />

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