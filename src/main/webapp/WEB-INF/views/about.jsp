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

</head>

<c:set var="userPrincipal" scope="page" value="${pageContext['request'].userPrincipal}" />
<c:set var="tagUrlPrefix" scope="page" value="${pageContext.servletContext.contextPath}/blog/tag"/>

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

					<div class="col-xs-12 nopadding">
                        <div class="box">
                    
                            <div class="content-body">
                            
                                <!--Featured Image Start-->
                                <div class="featured-image img-div-cover">
                                    <img src="<c:url value="/resources/img/athlete.jpg" />" alt="featured image" />
                                </div>
                                <!--Featured Image End-->
                                
                                <!--Content Details Body Start-->
                                <div class="content-details-body">
                                
                                    <div class="page-title title-center">
                                        <h1>Little about me</h1>
                                    </div>
                                    
                                    <p><span class="dropcap">P</span>assionate sport lover since I born. In fact I used to be good in physical training even in elementary school. During this time our teacher asked me whether I want to join to the athletics (track and fields) training group. Besides playing basketball in our freetime we used to practice sprinting, long-jumping and throwing. Thanks to my prematurity I achieved very good results and soon I was going to competitions week by week. I remember these times as very happy era of my life.</p>
                                    
                                    <div data-height="10"></div>
                                    
                                    <blockquote>Literally I couldn't believe it...maybe only after I realized my father how proud of me.</blockquote>
                                    
                                    <div data-height="10"></div>
                                    
                                    <p>Suddenly a new coach appeared who asked me to join to a local sport club called Lelkesed&eacute;s SK. I was full of joy and very excited to be a part of something bigger. I met a lot of new people here who also become my best friends and even my family. This time we went regularly to national or local competitions of athletics. In 2000 we also won the 4x100m sprint relay in the national competition. Literally I couldn't believe it...maybe only after I realized my father how proud of me. Sooner or later my prematurity went down and also my scores got low which made me very unhappy and lost faith in this sport.</p>
                                    
                                    <div data-height="10"></div>
                                    
                                    <p>After athletics I became a regular guy who just going to the gym and working out with machines 3 times a week. Although I liked it at first, I did not give me enough result...or at least how much I expect. To overcome this I was regularly switching gyms, diet, training plan, etc. None of them worked for real.</p>
                                    
                                    <div data-height="10"></div>
                                    
                                    <blockquote>A bell with a kettle? What is going on here?</blockquote>
                                    
                                    <div data-height="10"></div>
                                    
                                    <p>Sooner or later I realized that I'm not doing any sports and I'm not fit at all. This time I hear about a new sport called Kettlebell. A bell with a kettle? What is going on here? I immediately signed up for a new beginner course and I pretty much liked it. Swings, swings and more swings. Literally all the time we were practicing swings beside snatches, cleans or presses. It was challenging and gave me a good knowledge about functional sports. However it was very static. We were always standing in the exact same place for an hour and working out. It was exhausting for sure but not enough for me. So after a while I stopped it. Then after a few months I saw an advertisement of Crossfit on a social website.</p>
                                    
                                    <div data-height="10"></div>
                                    
                                    <blockquote>I became an addict...a WOD addict</blockquote>
                                    
                                    <div data-height="10"></div>
                                    
                                    <p>I have to be honest, I instantly wanted to be quit from Crossfit after the first free training. It was very-very-very exhausting. I couldn't believe how people can like this or doing this all the time. It was killing me. However a friend and colleague of mine told me that he will continue even though he was cracked as well. So we were keep going to the beginner Crossfit course and sooner or later I became an addict...a WOD addict. I was proud of my body, my mentality, my lifestyle and my friends in the game. The community which gave me strength to push myself forward and more forward. And even now I'm an athlete who is doing what he loves...doing Crossfit.</p>
                                    
                                    <div data-height="30"></div>
                                    
                                    <ul class="social-media-share text-center">
                                    	<li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#"><i class="fa fa-google"></i></a></li>
                                        <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                    </ul>
                                    
                                </div>
                                <!--Content Details Body End-->
                            
                            </div>
                    
                        </div>
                    </div>
                
                </div>
                
  				<div class="layout-divider"></div>
            </div>
        </div>
        
         <!--Back to Top Start-->
        <a class="back-to-top" href="#"><i class="fa fa-angle-up"></i></a>
        <!--Back to Top End-->
        
    </div>
    <!--Whole Container End-->
</body>
</html>