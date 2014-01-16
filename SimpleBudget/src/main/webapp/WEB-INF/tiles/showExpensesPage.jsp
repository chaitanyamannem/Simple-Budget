<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="col-md-8 col-md-offset-2">Expenses</h1>
<div class="col-md-8 col-md-offset-2">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Edit Expense</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							
							<div class="col-md-5">
								<form role="form" action="getExpenseData">
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
											<button type="button" value="other"
												class="btn btn-success btn-lg active">
												<span class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;Other&nbsp;&nbsp;
											</button>
										</div>
										<input type="hidden" id="categoryValue" name="category"
											value="">

									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="amount">Amount</label> <input type="text"
												class="form-control" name="amount" placeholder="Enter Value">
										</div>

										<div class="form-group">
											<label for="tags">Tags</label> <input type="text" id="tags"
												name="tags" class="tm-input form-control"
												placeholder="Enter Tags">
										</div>

										<div class="form-group">
											<label for="date">Edited on</label> <input
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

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input class="btn btn-primary" id="saveExpense" type="submit"
											value="Save">
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<table id="box-table-a">
		<thead>
			<tr>
				<th>Tags</th>
				<th>Amount</th>

			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty expenses}">
				<c:forEach var="expense" items="${expenses}">
					<tr class="record">
						<td>${expense.getTags()}</td>
						<td>&#8377;&nbsp;${expense.getAmount()}</td>
						<td style="display: none;">${expense.getId()}</td>
					</tr>
				</c:forEach>
			</c:if>
		<tfoot>
			<tr>
				<td>Total</td>
				<td>&#8377;&nbsp;${total}</td>
			</tr>
		</tfoot>
		</tbody>

	</table>
</div>
<script>
	$(".record").click(function() {
		$('#myModal').modal();
		var id = $(".record :last");
		console.log(id);

	});
</script>