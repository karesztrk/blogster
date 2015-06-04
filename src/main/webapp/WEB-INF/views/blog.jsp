<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/main.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
<c:url var="firstUrl" value="/pages/1" />
<c:url var="lastUrl" value="/pages/${posts.totalPages}" />
<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />

  <body>

    <div class="blog-masthead">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item" href="${pageContext.servletContext.contextPath}/">Home</a>
          <a class="blog-nav-item active" href="#">Blog</a>
          <a class="blog-nav-item" href="#">About me</a>
        </nav>
      </div>
    </div>

    <div class="container">

      <div class="blog-header">
        <h1 class="blog-title">The WODster</h1>
        <p class="lead blog-description">Pass your limits</p>
      </div>

      <div class="row">

        <div class="col-sm-8 blog-main">
        
			<c:if test="${!empty posts}">
				<c:forEach items="${posts.content}" var="post">
                <div class="blog-post">
                	<h2 class="blog-post-title">${post.title}</h2>
                	<p class="blog-post-meta"><fmt:formatDate value="${post.date}" pattern="yyyy-MM-dd HH:mm:ss" /> <a href="#">${post.user.name}</a></p>
                	<p>${post.content}</p>
                </div>
            </c:forEach>
			</c:if>

          <nav>
            <ul class="pager">
            	<c:choose>
            		<c:when test="${currentIndex == deploymentLog.totalPages}">
		        		<li class="previous"><a href="${nextUrl}"><span aria-hidden="true">&larr;</span> Older</a></li>
		        	</c:when>
		        	<c:otherwise>
		        		<li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> Older</a></li>
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
		        		<li class="next"><a href="${prevUrl}">Newer <span aria-hidden="true">&rarr;</span></a></li>
	       			</c:when>
       			</c:choose>
            </ul>
          </nav>

        </div><!-- /.blog-main -->

        <jsp:include page="./sidebar.jsp" />

      </div><!-- /.row -->

    </div><!-- /.container -->

    <footer class="blog-footer">
      <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      <p>
        <a href="#">Back to top</a>
      </p>
    </footer>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
