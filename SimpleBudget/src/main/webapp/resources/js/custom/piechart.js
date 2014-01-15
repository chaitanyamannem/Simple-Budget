
$.getJSON('../autocomplete/showpie', function(data) {

	var testDatas = [];
	$.each(data, function(key, value) {
		console.log(key + ": " + value);
		var pieElement = {};
		pieElement.key = key;
		pieElement.y = value;
		console.log(pieElement);
		testDatas.push(pieElement);
		console.log("****");
		console.log(testDatas);

	});
	console.log("###");
	console.log(testDatas);
	nv.addGraph(function() {
		var width = 500, height = 500;

		var chart = nv.models.pieChart().x(function(d) {
			return d.key
		}).y(function(d) {
			return d.y
		}).color(d3.scale.category10().range()).width(width).height(height);

		d3.select("svg").datum(testDatas).transition().duration(1200).attr(
				'width', width).attr('height', height).call(chart);

		chart.dispatch.on('stateChange', function(e) {
			nv.log('New State:', JSON.stringify(e));
		});

		return chart;
	});
});

var testdata = [ {
	key : "One",
	y : 5
}, {
	key : "Two",
	y : 2
}, {
	key : "Three",
	y : 9
}, {
	key : "Four",
	y : 7
}, {
	key : "Five",
	y : 4
}, {
	key : "Six",
	y : 3
}, {
	key : "Seven",
	y : .5
} ];
