<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="search-overlay overlay-hugeinc">
	<div class="container">

		<!--Search Close Start-->
		<div class="overlay-close-wrapper">
			<div class="overlay-close"></div>
		</div>
		<!--Search Close End-->

		<!--Search Input Start-->
		<div class="search-input">
			<div class="search-form">
				<c:url var="searchAction" value="/blog/search"></c:url>
				<form:form name="searchForm" action="${searchAction}" method="GET">
					<input type="search" name="criteria"
						placeholder="Search and hit enter">
				</form:form>
			</div>
		</div>
		<!--Search Input Close-->

	</div>
</div>