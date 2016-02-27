<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>

<div id="menu" class="menu-right">
	<ul>
		<form class="menu-search">
			<div class="form-group header">
				<i class="icon-search searchico"></i> <input type="text"
					placeholder="Blog Search"> <a href="#" class="close-menu"><i
					class="icon-close"></i></a>
			</div>
		</form>
		<li><a href="${pageContext.servletContext.contextPath}/"><i class="icon-evil"></i>Home</a></li>
		<sec:authorize  access="hasRole('ADMINISTRATOR')">
			<li><a href="${pageContext.servletContext.contextPath}/blog/add"><i class="icon-pencil2"></i>Write new</a></li>
			
			<c:choose>
				<c:when test="${null != post}">
					<li><a href="${pageContext.servletContext.contextPath}/blog/${post.publicId}/edit"><i class="icon-pencil2"></i>Edit post</a></li>
				</c:when>
			</c:choose>
		</sec:authorize>
		<li><a href="about.html"><i class="icon-user"></i>About</a></li>
		<li class="submenu"><a href="#"><i class="icon-calendar"></i>Archives
				<b class="caret"></b></a>
			<ul class="submenu-list">
				<li><a href="archives.html">June<span>2014</span></a></li>
				<li><a href="archives.html">May<span>2014</span></a></li>
				<li><a href="archives.html">January<span>2014</span></a></li>
				<li><a href="archives.html">October<span>2013</span></a></li>
			</ul></li>
		<li><a href="contact.html"><i class="icon-envelope"></i>Contact</a></li>
	</ul>
</div>