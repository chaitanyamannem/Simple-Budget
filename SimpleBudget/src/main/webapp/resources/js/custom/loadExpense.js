$(".record").click(
		function() {
			var tagInput = $('#tags');
			var amountFieldInput = $("#expenseAmount");
			var expenseInputField = $("#expenseIdInput");
			var existingTags;
			var expenseRecord = this.childNodes;
			var categoryFieldName;
			for ( var i = 0; i < expenseRecord.length; i++) {
				console.log(expenseRecord[i].className);
				if (expenseRecord[i].className === "categoryField") {
					categoryFieldName = expenseRecord[i].innerHTML;
					for ( var j = 0; j < btns.length; j++) {
						if (disabledButton) {
							disabledButton.className = activeButton;
						}
						if (btns[j].value == categoryFieldName) {
							btns[j].className = disableButton;
							disabledButton = btns[j];
							break;
						}

					}
				}
				if (expenseRecord[i].className === "tagsField") {
					existingTags = expenseRecord[i].innerHTML;
					console.log("existingTags = " + existingTags);
					var strippedTags = existingTags.substring(1,
							(existingTags.length - 1));
					console.log("strippedTags = " + strippedTags);
					var strippedTagsArray = strippedTags.split(",");
					tagInput.tagsManager('empty');
					for ( var k = 0; k < strippedTagsArray.length; k++) {
						tagInput.tagsManager('pushTag', strippedTagsArray[k]);
					}

				}
				if (expenseRecord[i].className === "amountField") {
					var amountValueStr = expenseRecord[i].innerHTML;
					console.log(amountValueStr);
					var index = amountValueStr.lastIndexOf(";");
					var amount = amountValueStr.substring(index + 1);
					console.log(amountFieldInput.val());
					amountFieldInput.val(amount);
				}

				if (expenseRecord[i].className === "expenseIdField") {
					var expenseId = expenseRecord[i].innerHTML;
					expenseInputField.val(expenseId);
				}

			}

			$('#myModal').modal();
		});