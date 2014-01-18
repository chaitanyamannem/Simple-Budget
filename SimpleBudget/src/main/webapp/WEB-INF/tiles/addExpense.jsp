<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/tagmanager.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/datepicker.css" />" />
	<link rel="stylesheet"
	href="<c:url value="/resources/css/custom/autocomplete.css" />" />

<div class="container">
	<div class="row">
		<h1 class="col-md-12 col-md-offset-1">Add An Expense</h1>
		<div class="col-md-10">
			<form role="form" action="getExpenseData">
				<div class="col-md-6 category pull-right">
					<label class="col-md-12">Category</label>
					<div class="btn-group col-md-12">
						<button type="button" value="food"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-cutlery"></span>&nbsp;&nbsp;Food&nbsp;&nbsp;&nbsp;
						</button>
						<button type="button" value="movies"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-film"></span>&nbsp;&nbsp;Movies&nbsp;
						</button>
						<button type="button" value="petrol"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-tint"></span>&nbsp;&nbsp;Petrol&nbsp;
						</button>
					</div>
					<div class="btn-group col-md-12">
						<button type="button" value="house"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;House&nbsp;&nbsp;
						</button>
						<button type="button" value="shopping"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Shopping
						</button>
						<button type="button" value="beverages"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-glass"></span>&nbsp;Beverages
						</button>
					</div>
					<div class="btn-group col-md-12">
					<button type="button" value="bills"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-paperclip"></span>&nbsp;Bills
						</button>
						<button type="button" value="other"
							class="btn btn-success btn-lg active">
							<span class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;Other&nbsp;&nbsp;
						</button>
					</div>
					<input type="hidden" id="categoryValue" name="category" value="">

				</div>
				<div class="col-md-4  pull-left">
					<div class="form-group">
						<label for="amount">Amount</label> <input type="text"
							class="form-control" name="amount" placeholder="Enter Value">
					</div>

					<div class="form-group">
						<label for="tags">Tags</label> <input type="text" id="tags" name="tags"
							class="tm-input form-control" placeholder="Enter Tags">
					</div>

					<div class="form-group">
						<label for="date">Spent on</label> <input
							class="datepicker form-control" data-format="dd/mm/yyyy"
							name="date" size="16" type="text" value=""
							placeholder="Choose a date"> <span class="add-on"><i
							class="icon-th"></i></span>
					</div>

					<input class="btn btn-default" id="saveExpense" type="submit"
						value="submit">



				</div>

			</form>
		</div>
	</div>
</div>


<script src="<c:url value="/resources/js/custom/tagmanager.js" />"></script>
<script src="<c:url value="/resources/js/custom/category.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.js" />"></script>
<script src="<c:url value="/resources/js/custom/autocomplete.js" />"></script>
<script>
	$(document).ready(function() {
		$(".tm-input").tagsManager();
		// $("#saveExpense").click(function() {
		//	var tags = $(".tm-input").tagsManager('tags');			
		//}); 
		$(function() {
			$(".datepicker").datepicker({
				format : 'dd/mm/yyyy',
				autoclose : true
			});
		});

	});
</script>
