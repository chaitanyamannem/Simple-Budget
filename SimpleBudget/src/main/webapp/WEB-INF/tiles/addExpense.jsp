<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/tagmanager.css" />" />

<div class="container">
<div class="row">
<h1 class="col-md-4 col-md-offset-4">Add An Expense</h1>
      <form role="form" action="getExpenseData">
        <div class="col-md-4 col-md-offset-4" >
         <div class="form-group">
           <label for="amount">Amount</label>
           <input type="text" class="form-control" name="amount"
           placeholder="Enter Value">
         </div>
         <div class="form-group">
           <label for="tags">Tags</label>
           <input 
           type="text" name="tags" class="tm-input form-control" placeholder="Enter Tags">
         </div>
         
         <input class="btn btn-default" id="saveExpense" type="submit" value="submit" >



       </div>
     </form>
    </div>
     </div>


<script src="<c:url value="/resources/js/custom/tagmanager.js" />"></script>
<script>
	$(document).ready(function() {
		$(".tm-input").tagsManager();
		// $("#saveExpense").click(function() {
		//	var tags = $(".tm-input").tagsManager('tags');			
		//}); 
	});
</script>
