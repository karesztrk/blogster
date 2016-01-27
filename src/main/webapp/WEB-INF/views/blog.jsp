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

<link
	href='http://fonts.googleapis.com/css?family=Raleway:400,300,500,600,700,800%7COpen+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic%7CCourgette&amp;subset=latin,latin-ext'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/flexslider.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/owl.carousel.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/settings.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.fancybox.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.fancybox-buttons.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.fancybox-thumbs.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/color.css" />">
<link rel="shortcut icon" href="i#" sizes="32x32" type="image/png" />

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/modernizr.custom.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/classie.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/retina.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.particleground.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.flexslider.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.themepunch.tools.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.themepunch.megafoliopro.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fancybox.pack.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fancybox-buttons.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fancybox-media.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fancybox-thumbs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/isotope.pkgd.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/imagesloaded.pkgd.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fitvids.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.fitvids.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/placeholder.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom.js" />"></script>

</head>

<c:set var="userPrincipal" scope="page" value="${pageContext['request'].userPrincipal}" />

<body>

	<!--Pre Loading Start-->
	<div class="preloader-container">
		<div class="preloader-particles-wrapper"></div>
	</div>
	<!--Pre Loading End-->

	<jsp:include page="background.jsp"/>

	<!--Whole Container Start-->
	<div class="container-wrapper">

		<jsp:include page="search.jsp"/>

		<!--Header Start-->
		<header>
			<jsp:include page="menu.jsp"/>
		</header>
		<!--Header End-->

		<!--Content Wrapper Start-->
		<div class="content-wrapper">
			<div class="container">

				<div class="row section-wrapper">

					<!--Content Start-->
					<div class="col-md-8 content-section-body">
						<div class="box">

							<div class="post-list">
							
								<c:choose>
									<c:when test="${posts.size > 0}">
										<c:forEach items="${posts.content}" var="post">

											<!--Post Entry Start-->
											<article class="post-type-standard">
			
												<!--Post Media Start-->
												<c:choose>
													<c:when test="${not empty post.media and post.media.type == 'IMAGE'}">
													<div class="post-img img-div-cover">
														<a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}">
															<figure>
																<div class="overlay-hover"></div>
																<img src="${post.media.url}" alt="post cover image" />
															</figure>
														</a>
													</div>
													</c:when>
													<c:when test="${not empty post.media and post.media.type == 'AUDIO'}">
														<div class="post-audio">
															<iframe src="${post.media.url}" height="300"></iframe>
														</div>
													</c:when>
													<c:when test="${not empty post.media and post.media.type == 'VIDEO'}">
														<div class="post-video">
															<iframe src="${post.media.url}" height="300"></iframe>
														</div>
													</c:when>
												</c:choose>
												<!--Post Media End-->
			
												<!--Post Entry-->
												<div class="post-entry">
													<h2>
														<a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}">${post.title}</a>
													</h2>
													<div class="post-description">
														<p>${post.content}</p>
													</div>
													<div class="post-meta">
														<ul>
															<li><a href="#"><fmt:formatDate value="${post.date}" pattern="yyyy-MM-dd" /></a></li>
															<li><a href="#">${post.user.name}</a></li>
														</ul>
													</div>
												</div>
			
												<!--Post Share-->
												<div class="post-share">
													<div class="read-more-btn">
														<c:if test="${null != userPrincipal}">
															<a class="edit-btn" 
																href="${pageContext.servletContext.contextPath}/blog/${post.publicId}/edit">Edit</a>
														</c:if>
														<a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}">Read more</a>
													</div>
													<div class="share-btn">Share</div>
													<ul class="share-standard">
														<li><a href="#"><i class="fa fa-facebook"></i></a></li>
														<li><a href="#"><i class="fa fa-twitter"></i></a></li>
														<li><a href="#"><i class="fa fa-google"></i></a></li>
														<li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
													</ul>
												</div>
			
											</article>
											<!--Post Entry End-->

										</c:forEach>
									</c:when>
									<c:otherwise>
									<p class="text-center text-info">
										<i class="icon-sad"></i><span> We couldn't find any match for your search criteria</span>
									</p>
									</c:otherwise>
								</c:choose>
							</div>
							
							 <!--Pagination Start-->
                            <nav class="pagination">
                                <ul>
									<li class="${posts.first? 'disabled' : ''}">
										<c:if test="${not posts.first}">
											<a href="<c:url value=''><c:param name='page' value='1'></c:param></c:url>">&laquo;</a>
										</c:if>
									</li>
									
									<c:forEach items="${posts.items}" var="item">
										<li class="${item.current? 'pagination-active' : ''}">
											<c:if test="${not item.current}">
												<a href="<c:url value=''><c:param name='page' value='${item.number}'></c:param></c:url>">${item.number}</a>
											</c:if>
									     </li>
							         </c:forEach>
							         
      								 <li class="${posts.last? 'disabled' : ''}">
										<c:if test="${not posts.last}">
											<a href="<c:url value=''><c:param name='page' value='${posts.total}'></c:param></c:url>">&raquo;</a>
										</c:if>
									</li>
                                </ul>
                            </nav>
                            <!--Pagination End-->
						</div>
						
						
					</div>
					<!--Content End-->
					
					<!--Sidebar Start-->
                    <jsp:include page="sidebar.jsp"/>
                    <!--Sidebar End-->
				</div>
                
                <div class="layout-divider"></div>

			 </div>
        </div>
        <!--Content Wrapper End-->
        
         <!--Back to Top Start-->
        <a class="back-to-top" href="#"><i class="fa fa-angle-up"></i></a>
        <!--Back to Top End-->
        
    </div>
    <!--Whole Container End-->
    
    <!-- Search script should be loaded after render phase -->
    <script type="text/javascript"
		src="<c:url value="/resources/js/search.js" />"></script>
		
	<!-- Instagram plugins to load feed -->
	<script type="text/javascript"
		src="<c:url value="/resources/js/instafeed.min.js" />"></script>
	<script type="text/javascript">
	    var feed = new Instafeed({
	    	get: 'user',
	        userId: '${igUserId}',
        	clientId: '${igClientId}',
       		accessToken: '${igAccessToken}',
       		limit: 9,
       		template: '<li><a class="animation" href="{{link}}"><figure><div class="overlay-hover"></div><img src="{{image}}" /></figure></a></li>'
	    });
	    feed.run();
	</script>
      
</body>
</html>