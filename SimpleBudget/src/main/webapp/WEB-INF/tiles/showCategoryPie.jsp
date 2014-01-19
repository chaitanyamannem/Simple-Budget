<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/nv.d3.css" />" />
<div>
<h1>Pie chart</h1>
<h2 class="total"></h2>
	<svg style="height: 600px" />
</div>


<script src="<c:url value="/resources/js/d3.v3.js" />"></script>
<script src="<c:url value="/resources/js/nv.d3.js" />"></script>
<script src="<c:url value="/resources/js/custom/piechart.js" />"></script>