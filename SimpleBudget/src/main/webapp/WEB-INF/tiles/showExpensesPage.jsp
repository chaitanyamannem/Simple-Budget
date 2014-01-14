<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <div>
	<h1>Expenses</h1>

	<c:if test="${not empty expenses}">

		<ul>
			<c:forEach var="expense" items="${expenses}">
				<li>${expense.getAmount()}</li>
			</c:forEach>
		</ul>

	</c:if>



</div> --%>

<h1 class="col-md-8 col-md-offset-2">Expenses</h1>
<div class="col-md-8 col-md-offset-2">
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
					<tr>
						<td>${expense.getTags()}</td>
						<td>&#8377;&nbsp;${expense.getAmount()}</td>

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