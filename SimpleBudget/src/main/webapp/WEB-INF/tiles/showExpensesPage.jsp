<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/tagmanager.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/custom/autocomplete.css" />" />
<h1 class="col-md-8 col-md-offset-2">Expenses</h1>
<div class="col-md-8 col-md-offset-2">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			<form role="form" action="edit">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Edit Expense</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">

							<div class="col-md-5">
								
									<div class="col-md-6 category ">
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
										<input type="hidden" id="categoryValue" name="category"
											value="">
											<input type="hidden" id="expenseIdInput" name="expenseId"
											value="">

									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="amount">Amount</label> <input type="text"
												class="form-control" id="expenseAmount" name="amount" value="123">
										</div>

										<div class="form-group">
											<label for="tags">Tags</label> <input type="text" id="tags"
												name="tags" class="tm-input form-control"
												placeholder="Enter Tags">
										</div>







									</div>

								
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input class="btn btn-primary" id="saveExpense" type="submit"
						value="Save">
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<table id="box-table-a">
		<thead>
			<tr>
				<th>Category</th>
				<th>Tags</th>
				<th>Amount</th>

			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty expenses}">
				<c:forEach var="expense" items="${expenses}">
					<tr class="record">
						<td class="categoryField">${expense.getCategoryName()}</td>
						<td class="tagsField">${expense.getTags()}</td>
						<td class="amountField">&#8377;&nbsp;${expense.getAmount()}</td>
						<td class="expenseIdField" style="display: none;">${expense.getId()}</td>
					</tr>
				</c:forEach>
			</c:if>
		<tfoot>
			<tr>
				<td>Total</td>
				<td></td>
				<td>&#8377;&nbsp;${total}</td>
			</tr>
		</tfoot>
		</tbody>

	</table>
</div>

<script src="<c:url value="/resources/js/custom/tagmanager.js" />"></script>
<script src="<c:url value="/resources/js/custom/category.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.js" />"></script>
<script src="<c:url value="/resources/js/custom/autocomplete.js" />"></script>
<script src="<c:url value="/resources/js/custom/loadExpense.js" />"></script>
<script>
	$(document).ready(function() {
		$(".tm-input").tagsManager();

	});
</script>
