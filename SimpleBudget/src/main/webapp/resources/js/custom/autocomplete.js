var options, tagsInput;
var tagInputField = $('#tags');
tagsInput = $('#tags').autocomplete({
	serviceUrl : '../autocomplete/tags',
	onSelect: function(){ 
		console.log(this.value);
		tagInputField.tagsManager('pushTag', this.value);
	}
// lookup: ['January', 'February', 'March', 'April', 'May']

});