<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-4 sidebar-section-body sidebar-sticky">
	<div class="box">

		<!--Widget About Us Start-->
		<aside class="widget widget_about_us">
			<div class="widget-title">
				<h3>About Me</h3>
			</div>
			<div class="widget-detail">
				<div class="widget-about-img img-div-cover">
					<img src="<c:url value="/resources/img/me.jpg" />" alt="about me" />
				</div>
				<div class="widget-about-detail">
					<p>I'm a man...I'm a son...I'm a food lover...I'm a software developer but more importantly I'm an athlete. This blog is built
					around my fitness carrier where I post my feelings and training on a daily basis.</p>
					<div class="widget-about-social">
						<ul>
							<li><a href="#" target="_blank"><i
									class="fa fa-facebook"></i></a></li>
							<li><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#" target="_blank"><i class="fa fa-google"></i></a></li>
							<li><a href="#" target="_blank"><i
									class="fa fa-pinterest-p"></i></a></li>
							<li><a href="#" target="_blank"><i
									class="fa fa-instagram"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</aside>
		<!--Widget About Us End-->

		<!--Widget Instagram Start-->
		<aside class="widget widget_instagram">
			<div class="widget-title">
				<h3>Follow Instagram</h3>
			</div>
			<div class="widget-detail">
				<ul id="instafeed">
				</ul>
			</div>
		</aside>
		<!--Widget Instagram End-->

		<!--Widget Login Start-->
		<sec:authorize access="isAnonymous()">
			<aside class="widget widget_follow_subscribe">
				<div class="widget-title">
					<h3>Sign in</h3>
				</div>
				<div class="widget-detail">
					<p>Sign in with your username or with your preferred social system</p>
					<c:url value="/j_spring_security_check" var="loginUrl" />
					<form:form class="subscribe" method="post" action="${loginUrl}">
						<input class="subscribe-email" type="email" name="username"
							placeholder="Email address" required /> 
						<input class="subscribe-email" type="password" name="password"
							placeholder="Password" /> 
						<input class="subscribe-button" type="submit" value="Login" />
					</form:form>
					<div class="follow-subscribe-social">
						<c:url value="/signin/facebook" var="facebookSigninUrl" />
						<ul>
							<li>
								<form:form id="fbLogin" class="fb-form" name="socialSigninForm"
									action="${facebookSigninUrl}">
									<input type="hidden" name="scope" value="email" />
									<button id="fbLoginButton" type="submit"><i class="fa fa-facebook"></i></button>
								</form:form>
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
			</aside>
		</sec:authorize>
		<!--Widget Login End-->

		<!--Widget Tag Cloud Start-->
		<aside class="widget widget_tag_cloud">
			<div class="widget-title">
				<h3>Popular tags</h3>
			</div>
			<div class="widget-detail">
				<div class="tagcloud">
					<c:forEach items="${popularTags}" var="tag">
						<a href="${pageContext.servletContext.contextPath}/blog/tag/${tag.title}">${tag.title}</a>
					</c:forEach>
				</div>
			</div>
		</aside>
		<!--Widget Tag Cloud End-->
		
		<!-- Archives Start -->
		<aside class="widget widget_categories">
			<div class="widget-title">
				<h3>Archives</h3>
			</div>
			<div class="widget-detail">
				<ul>
				<c:forEach items="${archives}" var="archive">
					<li>
						<a href="${pageContext.servletContext.contextPath}/blog/archive/${archive.name}">
							<fmt:formatDate value="${archive.date}" pattern="MMM '<span>'yyyy'</span>'" />
						</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</aside>
		<!-- Archives End -->

	</div>
</div>