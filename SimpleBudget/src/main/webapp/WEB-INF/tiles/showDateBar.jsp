<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<c:url value="/resources/css/nv.d3.css" />" />
<style>
body {
	overflow-y: scroll;
}

text {
	font: 12px sans-serif;
}

svg {
	display: block;
}

#chart1 svg {
	height: 500px;
	min-width: 100px;
	min-height: 100px;
	/*
  margin: 10px;
  Minimum height and width is a good idea to prevent negative SVG dimensions...
  For example width should be =< margin.left + margin.right + 1,
  of course 1 pixel for the entire chart would not be very useful, BUT should not have errors
*/
}
</style>
<div id="chart1">
	<h1>Bar Graph</h1>
	<svg></svg>
</div>


<script src="<c:url value="/resources/js/d3.v3.js" />"></script>
<script src="<c:url value="/resources/js/nv.d3.js" />"></script>
<script src="<c:url value="/resources/js/custom/barGraph.js" />"></script>