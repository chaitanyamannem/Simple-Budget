//get all the buttons 
var btns = document.querySelectorAll(".category .btn");
var category = document.getElementById("categoryValue");
var disabledButton;
var disableButton = "btn btn-success btn-lg disabled col-md-4";
var activeButton = "btn btn-success btn-lg active col-md-4";
for (var i = 0; i < btns.length; i++) {
	btns[i].onclick = function(){
		if(disabledButton){
			disabledButton.className = activeButton;
		}
		//disable the button and store value
		
		disabledButton = this;
		this.className = disableButton;
		console.log(this.value);
		category.value = this.value;
	};
	
}