// Insert your Dropbox app key here:
var DROPBOX_APP_KEY = 'bdaafy67hddjyo9';

// Exposed for easy access in the browser console.
var client = new Dropbox.Client({
	key : DROPBOX_APP_KEY
});
console.log(client);
var expenseTable;

$(function() {
	// Insert a new expense record into the table.
	function insertExpense(amountValue, categoryValue) {
		expenseTable.insert({
			amount : amountValue,
			category : categoryValue,
			created : new Date()
		});
	}

	// The login button will start the authentication process.
	$('#loginButton').click(function(e) {
		e.preventDefault();
		// This will redirect the browser to OAuth login.
		client.authenticate();
	});

	// Try to finish OAuth authorization.
	client.authenticate({
		interactive : false
	}, function(error) {
		if (error) {
			alert('Authentication error: ' + error);
		}
	});

	if (client.isAuthenticated()) {
		// Client is authenticated. Display UI.
		$('#login').hide();
		$('#main').show();

		client.getDatastoreManager().openDefaultDatastore(
				function(error, datastore) {
					if (error) {
						alert('Error opening default datastore: ' + error);
					}

					expenseTable = datastore.getTable('expenses');

					// Populate the expenses list.
					showExpenses();

					// Ensure that future changes update the list.
					datastore.recordsChanged.addListener(showExpenses);
				});
	}

	// Render the HTML for a single task.
	function showExpenses() {
		var records = expenseTable.query();
		for(var i = 0; i < records.length; i++){
			var record = records[i];
			console.log("#############");
			console.log(record.get('amount'));
			console.log(record.get('category'));
			console.log("#############");
		}
		
	}

	// TODO Register event listeners to handle completing and deleting.
	function addListeners() {

	}

	// Hook form submit and add the new task.
	$('#addForm').submit(function(e) {
		e.preventDefault();
		insertExpense($('#expenseAmount').val(), $('#expenseCategory').val());
		return false;
	});

	
});
