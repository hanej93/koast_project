var SkillScore = {
	AxCall : function(func, args, callbackFunc, callbackErrorFunc) {
		
		var request_json = JSON.stringify({
			func : func,
			args : args
		});
		
		/*$.ajax({ 
			// ==== @@@@ ====
			// ajax call 구현
			// target url : ../ax/skillscore.php
			// method : POST
			// dataType : json
			// data : request_json
			// 성공 : callbackFunc 호출
			// 실패 : callbackErrorFunc 호출
			// ==== @@@@ ====
		});*/
		
		
		$.ajax({
			type:"POST", 
			url:"/ax/skillscore.php",
			data:request_json,
			contentType:"application/json;charset=utf-8", 
			dataType:"json" 
		}).done(function(resp){ 
			callbackFunc();
		}).fail(function(error){ 
			callbackErrorFunc();
		});

	},
	GetDiff : function(args, cbFunc, cbErrorFunc) {
		SkillScore.AxCall('diff', args, function(json) {
			if(json.result != 'OK')
			{
				if(typeof cbErrorFunc != 'undefined' && cbErrorFunc!=null) cbErrorFunc(json.code+" : "+json.message);
				return;
			}
			cbFunc(args, json.data);
		}, cbErrorFunc );
	}
}

function error_message_box(e)
{
	alert(e);
}

function get_title_from_sidebar_items(k, val)
{
	var title = "";
	$.each(scorecard_sidebar_items[k].items, function(i, lv1) {
		if(typeof lv1.items == 'undefined')
		{
			if(lv1.value == val)
			{
				title = lv1.title;
				return false;
			}
		}
		else
		{
			$.each(lv1.items, function(i2, lv2) {
				if(lv2.value == val)
				{
					title = lv2.title;
					return false;
				}
			});
		}
	});
	return title;
}

function get_group_title_from_sidebar_items(par_prefix)
{
	var title = "";
	$.each(scorecard_sidebar_items['par'].items, function(i, lv1) {
		if(lv1.par_prefix == par_prefix)
		{
			title = lv1.title;
			return false;
		}
	});
	return title;
}

function draw_scorecard(args, data)
{
	init_scorecard(args);
	fill_scorecard(args, data);
}

function init_scorecard(args)
{
	$('#tb_diff').html('');
	// 
	var tr = $('<tr />');
	tr.append($('<td />', { colspan:'3' }));

	$.each(args.dom, function(i, dom_cd) {
		var title = get_title_from_sidebar_items('dom', dom_cd);
		var td = $('<th />', {
			class:'dom',
			text:title,
			colspan:args.s.length
		});
		tr.append(td);
	});
	$('#tb_diff').append(tr);

	//
	var tr = $('<tr />');
	tr.append($('<td />', { colspan:'3' }));
	$.each(args.dom, function(i, dom_cd) {
		$.each(args.s, function(is, s_v) {
			var title = get_title_from_sidebar_items('s', s_v);
			var td = $('<th />', { 
				text : title,
				class : 'd'
			});
			tr.append(td);
		});
	});
	$('#tb_diff').append(tr);

	//
	var prefixes = [];
	$.each(args.par, function(ip, par_cd) {
		if(prefixes.indexOf(par_cd[0])<0) prefixes.push(par_cd[0]);
		var par_nm = get_group_title_from_sidebar_items(par_cd[0]);
		$.each(args.sc, function(isc, sc_cd) {
			if(par_cd[0]=='w' && sc_cd=='ccaf') return true;
			var sc_nm = get_title_from_sidebar_items('sc', sc_cd);
			var hpa = par_cd;
			var tr = $('<tr />');
			var td = $('<th />', { td_type:'par_nm', text:par_nm, par_prefix:par_cd[0], class:'par' });
			
			if(par_cd=='mslp') hpa = '';
			else hpa = par_cd.substr(1).replace('hpa','hPa');
			tr.append(td);

			tr.append($('<th />', { td_type:'par_hpa', text:hpa, par:par_cd, class:'hpa' } ));
			tr.append($('<th />', {text:sc_nm, class:'sc'}));
			
			$.each(args.dom, function(idom, dom_cd) {
				$.each(args.s, function(is, s_v) {
					var td = $('<td />', {
						sc : sc_cd,
						s : s_v,
						dom : dom_cd,
						par : par_cd,
						class : 'val_box'
					});
					tr.append(td);
				});
			});
			$('#tb_diff').append(tr);
		});
	});

	//
	// 셀 병합

	// ==== @@@@ ====
	// th 중 예측요소 열 병합
	// ==== @@@@ ====
	// th 중 예측요소 고도 열 병합
	// ==== @@@@ ====

}

function fill_scorecard(args, data)
{
	
	$.each(args.sc, function(i, sc){
		
		$.each(args.s, function(i2, s){
			
			$.each(args.dom, function(i3, dom){
				
				$.each(args.par, function(i4, par){
					
					var ratio = data[dom][par][sc][args.t][s].ratio;					
					var $td = $("td[dom='" + dom + "'][par='" + par + "'][sc='" + sc + "'][s='" + s + "']");
														
					// ==== @@@@ ====
					// 차이를 아이콘으로 테이블에 채움
					// 차이 표시 아이콘 : <i class='fas fa-아이콘종류'></i>
					//   20% 이상 우위 : angle-double-up
					//   10% 이상 우위 : chevron-up
					//    3% 이상 우위 : caret-up
					//    3% 미만 차이 : minus
					//    3% 이상 열위 : caret-down
					//   10% 이상 열위 : chevron-down
					//   20% 이상 열위 : angle-double-down
					// ==== @@@@ ====
					// 셀 배경색 선정
					//   비율 값 없음 : rgb(200,200,200)
					//   우위 : rgb(255*비율, 255, 255*비율)
					//   열위 : rgb(255, 255*비율, 255*비율)
					// ==== @@@@ ====
					
				});
			});
		});
	});
}

function call_scorecard()
{
	var args = {};
	$('#sidebar_form .vrfy_args').each(function(idx) {
		if(typeof $(this).attr('k')=='undefined') return true;
		args[ $(this).attr('k') ] =  $(this).val();
	});
	SkillScore.GetDiff(args, draw_scorecard, error_message_box);
}


$(document).ready( function ()
{
	$('#sidebar_form #btn_query').click(function() {
		call_scorecard();
	});
	call_scorecard();
});