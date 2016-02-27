<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
<link rel="stylesheet"
	href="<c:url value="/resources/css/select2.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/select2-bootstrap.css" />">

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-wysiwyg.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.hotkeys.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/init-editor.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/select2.min.js" />"></script>
	
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

<script type="text/javascript">
        function loadVal(){
            var desc = $("#editor").html();
            document.postForm.content.value = desc;
        }
        
        $(document).ready(function() {
            $('.select2').select2({
                 tags: [],
                 tokenSeparators: [","]
            });
        });
    </script>
</head>

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
					<div class="col-xs-12 nopadding">
						<div class="box">

							<div class="content-body">
                                
                                <!--Content Details Body Start-->
                                <div class="content-details-body">
                                
                                    <div class="page-title title-center">
                                        <h1>Edit post</h1>
                                    </div>
                                    
                                    <!--Contact Form Start-->
                                    <c:url var="saveAction" value="/blog/post"></c:url>
                                    <form:form name="postForm" class="contact-form comment-input" action="${saveAction}"
                                    	modelAttribute="post" onsubmit="loadVal();">
			                            <input name="publicId" type="hidden" value="${post.publicId}"/>
                                    	<div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <input id="title" type="text" name="title" value="${post.title}" placeholder="Post title" />
                                                </div>
                                                <div class="box">
													<select id="cover-media-type" class="form-control" name="media.type">
                                                		<option value=""></option>
													    <option value="IMAGE" ${post.media.type == 'IMAGE' ? 'selected' : ''}>Image</option>
													    <option value="AUDIO" ${post.media.type == 'AUDIO' ? 'selected' : ''}>Audio</option>
													    <option value="VIDEO" ${post.media.type == 'VIDEO' ? 'selected' : ''}>Video</option>
													</select>
                                                    <input id="cover-media-url" type="text" name="media.url" value="${post.media.url}" placeholder="Cover media url"/>
                                                </div>
                                                <div class="box">
                                                    <jsp:include page="../component/toolbar.jsp" />
                                                    <div contenteditable="true" id="editor">${post.content}</div>
                                					<textarea id="content" name="content" style="display:none;"></textarea>
                                                </div>
                                                <div class="box">
                                                	<form:input path="tags" class="form-control select2 tag-picker" placeholder="Pick tags"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-md-4">
                                            	<div class="box">
                                                    <button id="save-button" type="submit" class="btn-grey">Save</button>
                                                    <a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}">
													    <button id="cancel-button" type="button" class="btn-white">Cancel</button>
													</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                    <!--Contact Form End-->
                                    
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
        
         <!--Back to Top Start-->
        <a class="back-to-top" href="#"><i class="fa fa-angle-up"></i></a>
        <!--Back to Top End-->
        
    </div>
</body>
</html>