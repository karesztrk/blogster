<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>

<div class="container">

	<!--Logo Start -->
	<div class="logo">
		<h1><a href="<c:url value="/blog" />">Wodster</a></h1>
	</div>
	<!--Logo End -->

				<!--Navigation Menu Start-->
	<div class="nav-menu">

		<!--Open Close Menu-->
		<div class="burger-menu">
			<i class="fa fa-bars"></i>
		</div>

		<!--Search Icon-->
		<div id="trigger-overlay" class="search-open">
			<i class="fa fa-search"></i>
		</div>

		<!--Menu Start-->
		<ul>
			<li><a href="<c:url value="/blog" />">Home</a></li>
			<li><a href="<c:url value="/blog" />">Blog</a>
				<ul class="dropdown">
					<li><a href="<c:url value="/blog/add" />">Write new</a></li>
				</ul>
			</li>
			<li><a href="<c:url value="/about" />">About me</a></li>
		</ul>
		<!--Menu End-->

	</div>
	<!--Navigation Menu End-->

</div>