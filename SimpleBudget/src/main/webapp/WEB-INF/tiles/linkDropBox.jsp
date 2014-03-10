<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://www.dropbox.com/static/api/dropbox-datastores-1.0-latest.js"></script>
<script src="<c:url value="/resources/js/custom/dropBox.js" />"></script>

<!-- Hide UI until user has authenticated -->
<div id="wrapper" class="col-md-10">


	<div id="main" style="display: none">
		<!-- Empty list that will be populated with tasks -->
		<ul id="expenses"></ul>

		<!-- Form to collect new tasks -->
		<form id="addForm" method="post">
			<input id="expenseAmount" type="text" placeholder="Enter a amount..." />
			<input id="expenseCategory" type="text" placeholder="Enter category..." />
			<button id="addButton" type="submit">Add Expense</button>
		</form>
	</div>

	<div id="login">
		<h1>Link Simple Budget to your DropBox account</h1>
		<p>Simple Budget doesn't store your data on its servers. We
			respect your privacy. So we store your data in your Dropbox account,
			so if you don't have a dropbox account, please get one to proceed.</p>
		<button id="loginButton">Link Simple Budget to Dropbox</button>
	</div>
</div>

