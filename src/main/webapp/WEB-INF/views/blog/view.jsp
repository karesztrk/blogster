<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
	src="<c:url value="/resources/js/search.js" />"></script>
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

<!-- Start of Woopra Code -->
<script>
(function(){
        var t,i,e,n=window,o=document,a=arguments,s="script",r=["config","track","identify","visit","push","call","trackForm","trackClick"],c=function(){var t,i=this;for(i._e=[],t=0;r.length>t;t++)(function(t){i[t]=function(){return i._e.push([t].concat(Array.prototype.slice.call(arguments,0))),i}})(r[t])};for(n._w=n._w||{},t=0;a.length>t;t++)n._w[a[t]]=n[a[t]]=n[a[t]]||new c;i=o.createElement(s),i.async=1,i.src="//static.woopra.com/js/w.js",e=o.getElementsByTagName(s)[0],e.parentNode.insertBefore(i,e)
})("woopra");

woopra.config({
    domain: 'wodster.eu'
});
woopra.track();
</script>
<!-- End of Woopra Code -->

</head>

<c:set var="userPrincipal" scope="page" value="${pageContext['request'].userPrincipal}" />
<c:set var="tagUrlPrefix" scope="page" value="${pageContext.servletContext.contextPath}/blog/tag"/>

<body>

	<!--Pre Loading Start-->
	<div class="preloader-container">
		<div class="preloader-particles-wrapper"></div>
	</div>
	<!--Pre Loading End-->

	<jsp:include page="../background.jsp"/>

	<!--Whole Container Start-->
	<div class="container-wrapper">

		<jsp:include page="../search.jsp"/>

		<!--Header Start-->
		<header>
			<jsp:include page="../menu.jsp"/>
		</header>
		<!--Header End-->

		<!--Content Wrapper Start-->
		<div class="content-wrapper">
			<div class="container">

				<div class="row section-wrapper">

					<!--Content Start-->
                    <div class="col-md-8 content-section-body">
                        <div class="box">
                        	<div class="post-single-content-body">
                            	
                            	<!--Cover Image Start -->
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
                                <!--Cover Image End -->
                                
                                <!--Post Single Details Top Start-->
                                <div class="post-single-details-body">
                                	
                                    <!--Post Title Start-->
                                	<div class="page-title">
                                        <h1>${post.title}</h1>
                                    </div>
                                    <!--Post Title End-->
                                    
                                    <!--Post Meta Start-->
                                    <div class="post-meta">
                                    	<ul>
                                    		<li><a href="#"><fmt:formatDate value="${post.date}" pattern="yyyy-MM-dd" /></a></li>
                                            <li><a href="#">${post.user.name}</a></li>
                                    	</ul>
                                    </div>
                                    <!--Post Meta End-->
                                    
                                     <!--Post Control -->
                                    <div class="post-control">
                                    	<ul>
                                    		<c:if test="${null != userPrincipal}">
												<li><a class="edit-btn" 
													href="${pageContext.servletContext.contextPath}/blog/${post.publicId}/edit"><i class="fa fa-pencil"></i>&nbsp;&nbsp;Edit</a></li>
											</c:if>
                                    	</ul>
                                    </div>
                                    <!--Post Control -->
                                    
                                    <!--Post Text Content Start-->
                                    <div class="post-content">
                                    	${post.content}
                                    </div>
                                    <!--Post Text Content End-->
                                    
                                    <div data-height="17"></div>
                                    
                                    <!--Post Tags Start-->
                                    <div class="post-single-tags">
                                    	<h5>Tags</h5>
                                    	<c:forEach items="${post.tags}" var="tag">
                                    		<a href="${tagUrlPrefix}/${tag.title}">${tag.title}</a>
                                        </c:forEach>
                                    </div>
                                    <!--Post Tags End-->
                                    
                                    <!--Social Media Share Start-->
                                    <div class="social-media-share2">
                                        <ul>
                                            <li><a href="#" target="_blank"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
                                            <li><a href="#" target="_blank"><i class="fa fa-google"></i></a></li>
                                            <li><a href="#" target="_blank"><i class="fa fa-pinterest-p"></i></a></li>
                                            <li><a href="#" target="_blank"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                    <!--Social Media Share End-->
                                    
                                    <!--Post Pagination Start-->
                                    <div class="post-pagination-wrap">
                                    	<c:if test="${previousPost != null}">
	                                        <div class="post-pagination pagination-prev">
	                                            <a href="${pageContext.servletContext.contextPath}/blog/${previousPost.publicId}">
	                                                <span>Previous Post</span>
	                                                <h4>${previousPost.title}</h4>
	                                            </a>
	                                        </div>
                                        </c:if>
                                        
                                        <c:if test="${nextPost != null}">
	                                        <div class="post-pagination pagination-next">
	                                            <a href="${pageContext.servletContext.contextPath}/blog/${nextPost.publicId}">
	                                                <span>Next Post</span>
	                                                <h4>${nextPost.title}</h4>
	                                            </a>
	                                        </div>
                                        </c:if>
                                    </div>
                                    <!--Post Pagination End-->
                                    
                                </div>
                                <!--Post Single Details Top End-->
                            </div>
                        </div>
                    </div>
                    <!--Content End-->
					
					<!--Sidebar Start-->
                    <jsp:include page="../sidebar.jsp"/>
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
    
    <!-- Instagram plugins to load feed -->
	<script type="text/javascript"
		src="<c:url value="/resources/js/instafeed.min.js" />"></script>
	<script type="text/javascript">
	    var feed = new Instafeed({
	    	get: 'user',
	        userId: '1724438033',
        	clientId: 'a5d60980724642f1872a674947176658',
       		accessToken: '1724438033.a5d6098.1a2e6665b1374b11ab9008a1cffc5507',
       		limit: 9,
       		template: '<li><a class="animation" href="{{link}}"><figure><div class="overlay-hover"></div><img src="{{image}}" /></figure></a></li>'
	    });
	    feed.run();
	</script>
</body>
</html>