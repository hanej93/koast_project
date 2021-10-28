
var scorecard_sidebar_items = {
	'dom': {
		multiple: true,
		multiple_max: 5,
		items: [
			{ value: 'nhem', title: '북반구', selected: true },
			{ value: 'shem', title: '남반구', selected: true },
			{ value: 'asia', title: '아시아' },
			{ value: 'tropics', title: '적도지방', selected: true }
		]
	},
	's': {
		multiple: true,
		multiple_max: 10,
		items: [
			{ value: 24, title: '1일', selected: true },
			{ value: 48, title: '2일' },
			{ value: 72, title: '3일', selected: true },
			{ value: 96, title: '4일' },
			{ value: 120, title: '5일', selected: true },
			{ value: 144, title: '6일' },
			{ value: 168, title: '7일', selected: true },
			{ value: 192, title: '8일' },
			{ value: 216, title: '9일', selected: true },
			{ value: 240, title: '10일' }
		]
	},
	'par': {
		multiple: true,
		multiple_max: 15,
		items: [
			{
				par_prefix: 'm', title: 'MSLP', items: [
					{ value: 'mslp', title: 'MSLP', selected: true }
				]
			},
			{
				par_prefix: 'r', title: '상대습도', items: [
					{ value: 'r700hpa', title: '700hPa 습도', selected: true }
				]
			},
			{
				par_prefix: 'w', title: '풍속', items: [
					{ value: 'w250hpa', title: '250hPa 풍속', selected: true },
					{ value: 'w500hpa', title: '500hPa 풍속', selected: true },
					{ value: 'w850hpa', title: '850hPa 풍속', selected: true },
				]
			},
			{
				par_prefix: 'z', title: '지위고도', items: [
					{ value: 'z250hpa', title: '250hPa 지위고도', selected: true },
					{ value: 'z500hpa', title: '500hPa 지위고도', selected: true },
					{ value: 'z850hpa', title: '850hPa 지위고도', selected: true }
				]
			},
			{
				par_prefix: 't', title: '기온', items: [
					{ value: 't250hpa', title: '250hPa 기온', selected: true },
					{ value: 't500hpa', title: '500hPa 기온', selected: true },
					{ value: 't850hpa', title: '850hPa 기온', selected: true }
				]
			}
		]
	},
	'sc': {
		multiple: true,
		multiple_max: 2,
		items: [
			{ value: 'rmse', title: 'RMSE', selected: true },
			{ value: 'ccaf', title: 'ANO.CORR.', selected: true }
		]
	}
};

function print_items() {
	$.each(scorecard_sidebar_items, function(k, lv1) {
		$('#sidebar_form #ss_' + k).empty();

		if (lv1.multiple == true) {
			$('#sidebar_form #ss_' + k).attr('multiple', 'multiple');
			$('#sidebar_form #ss_' + k).attr('data-max-options', lv1.multiple_max);
		}

		$.each(lv1.items, function(i, lv2) {
			if (typeof lv2.items == 'undefined') {
				// ==== @@@@ ====
				// 해당 셀렉트박스에 lv2 의 내용으로 option 추가
				// ==== @@@@ ====
				var $option = $('<option>'+lv2.title+'</option>');
                $option.attr('value', lv2.value);
                $option.attr('title', lv2.title);
                if(lv2.selected == true){
                    $option.attr('selected', 'selected');
                }
                $('#sidebar_form #ss_' + k).append($option);

			}
			else {
				var optgrp = $('<optgroup>', { label: lv2.title });
				if (k == 'par') optgrp.attr('par_prefix', lv2.par_prefix);

				$.each(lv2.items, function(i2, lv3) {
					// ==== @@@@ ====
					// 해당 셀렉트박스의 optgroup에 lv3 의 내용으로 option 추가
					// ==== @@@@ ====
					 var $option = $('<option>'+lv3.title+'</option>');
                    $option.attr('value', lv3.value);
                    $option.attr('title', lv3.title);
                    if(lv3.selected == true){
                        $option.attr('selected', 'selected');
                    }
                    $('#sidebar_form #ss_' + k).append($option);
				});
				$('#sidebar_form #ss_' + k).append(optgrp);
			}
		});
	});

	$('#sidebar_form .vrfy_args').selectpicker();
}


$(document).ready(function() {
	print_items();
});