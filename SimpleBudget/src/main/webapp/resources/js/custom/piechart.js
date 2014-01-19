$.getJSON('../autocomplete/showpie', function(data) {

	var testDatas = [];
	var total = 0;
	$.each(data, function(key, value) {
		var pieElement = {};
		pieElement.key = key;
		pieElement.y = value;
		total = total + value;
		testDatas.push(pieElement);
	});
	$(".total").html("Total : &#8377;&nbsp;" + total.toFixed(2));

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
