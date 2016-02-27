<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<body>

	<!--Pre Loading Start-->
	<div class="preloader-container">
		<div class="preloader-particles-wrapper"></div>
	</div>
	<!--Pre Loading End-->

	<!--Background Start-->
	<div class="bgimage bgimage-cover"
		data-image-src="images/upload/bg-image.jpg">
		<div id="particles"></div>
		<div class="bg-overlay" data-bg-color="#353c40" data-opacity="0.85"></div>
	</div>
	<!--Background End-->

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
					<div class="col-xs-12 nopadding">
						<div class="box">

							<div class="content-body">
                                
                                <!--Content Details Body Start-->
                                <div class="content-details-body">
                                
                                    <div class="page-title title-center">
                                        <h1>Login</h1>
                                    </div>
                                    
                                    
                                    <!--Contact Form Start-->
                                    <c:url value="/j_spring_security_check" var="loginUrl" />
                                    <form:form id="loginForm" class="contact-form comment-input login-form" method="post" action="${loginUrl}">
                                    	<p>Sign in with your username or with your preferred social system</p>
                                    	<div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <input id="username" type="email" name="username" placeholder="Email address" />
                                                </div>
                                                 <div class="box">
                                                    <input id="password" type="password" name="password" placeholder="Password"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-md-4">
                                            	<div class="box">
                                                    <input id="login" type="submit" value="Login"/>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                    <!--Contact Form End-->
                                    
                                    <!--Social Media Login-->
                                    <div class="social-media-share2">
                                        <ul>
                                            <li><span>
                                            	<c:url value="/signin/facebook" var="facebookSigninUrl" />
                                            	<form:form id="fbLogin" class="fb-form" name="socialSigninForm"
													action="${facebookSigninUrl}">
													<input type="hidden" name="scope" value="email" />
													<button id="fbLoginButton" type="submit"><i class="fa fa-facebook"></i></button>
												</form:form></span>
                                            </li>
                                            <li><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
											<li><a href="#" target="_blank"><i class="fa fa-linkedin"></i></a></li>
											<li><a href="#" target="_blank"><i class="fa fa-google"></i></a></li>
											<li><a href="#" target="_blank"><i class="fa fa-pinterest-p"></i></a></li>
											<li><a href="#" target="_blank"><i class="fa fa-dribbble"></i></a></li>
											<li><a href="#" target="_blank"><i class="fa fa-instagram"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <!--Content Details Body End-->
							
							</div>
						</div>
						
					</div>
				</div>
				<!--Content End-->
                
                <div class="layout-divider"></div>

			 </div>
        </div>
        <!--Content Wrapper End-->
        
        <!-- Search script should be loaded after render phase -->
	    <script type="text/javascript"
			src="<c:url value="/resources/js/search.js" />"></script>
        
        <!--Back to Top Start-->
        <a class="back-to-top" href="#"><i class="fa fa-angle-up"></i></a>
        <!--Back to Top End-->
        
    </div>
    <!--Whole Container End-->
</body>
</html>