<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="테스트1">
<meta name="author" content="테스트">

<title>ScoreCard</title>
<!-- -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/icon/fontawesome/css/solid.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/icon/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-select/css/bootstrap-select.min.css">
<!-- -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scorecard.css">
<!-- -->
</head>

<body>

	<div class="container">
		<h3>검증 결과 비교</h3>
		<div class="row">
			<div class="col-md-3">
				<form id='sidebar_form'>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="ss_centre1">예측기관1</label> 
							<select class='form-control vrfy_args show-tick' id='ss_centre1' k="centre1" data-size=8 data-container="body">
								<option data-subtext="Europe" value="ecmf" selected='selected'>ECMF</option>
								<option data-subtext="USA" value="kwbc">NCEP</option>
							</select>
						</div>

						<div class="form-group col-md-6">
							<label for="ss_d1">예측연월1</label> 
							<select class='form-control vrfy_args show-tick' id='ss_d1' k="d1" data-size=8 data-container="body">
								<option data-subtext="" value="2018-06-01" selected='selected'>2018-06</option>
								<option data-subtext="" value="2017-06-01">2017-06</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="ss_centre2">예측기관2</label> 
							<select class='form-control vrfy_args show-tick' id='ss_centre2' k="centre2" data-size=8 data-container="body">
								<option data-subtext="Europe" value="ecmf" selected='selected'>ECMF</option>
								<option data-subtext="USA" value="kwbc">NCEP</option>
							</select>
						</div>

						<div class="form-group col-md-6">
							<label for="ss_d2">예측연월2</label> 
							<select class='form-control vrfy_args show-tick' id='ss_d2' k="d2" data-size=8 data-container="body">
								<option data-subtext="" value="2018-06-01">2018-06</option>
								<option data-subtext="" value="2017-06-01" selected='selected'>2017-06</option>
							</select>
						</div>
					</div>


					<div class="form-group">
						<label for="ss_t">예측 기준시간</label> 
						<select class='form-control vrfy_args show-tick' id='ss_t' k="t" data-size=8 data-container="body">
							<option data-subtext="" value="0">00 UTC</option>
							<option data-subtext="" value="12" selected='selected'>12 UTC</option>
						</select>
					</div>

					<div class="form-group">
						<label for="ss_par">예보요소/고도</label> 
						<select class='form-control vrfy_args show-tick' id='ss_par' k="par" data-size=8 data-container="body">
						</select>
					</div>

					<div class="form-group">
						<label for="ss_dom">예측지역</label> 
						<select class='form-control vrfy_args show-tick' id='ss_dom' k="dom" data-size=8 data-container="body">
						</select>
					</div>

					<div class="form-group">
						<label for="ss_s">예측시간</label> 
						<select class='form-control vrfy_args show-tick' id='ss_s' k="s" data-size=8 data-container="body">
						</select>
					</div>

					<div class="form-group">
						<label for="ss_sc">검증기법</label> 
						<select class='form-control vrfy_args show-tick' id='ss_sc' k="sc" data-size=8 data-container="body">
						</select>
					</div>

					<div class="form-group">
						<button type='button' class='btn btn-primary btn-block' id='btn_query'>조회</button>
					</div>

				</form>
			</div>
			<div class="col-md-9">
				<div class="card">
					<div class="card-body">
						<table id='tb_diff' class='samp'>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->

	<!-- -->
	<script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/bootstrap-select/js/bootstrap-select.min.js"></script>
	<!-- -->
	<script src="${pageContext.request.contextPath}/js/sidebar.scorecard.js"></script>
	<script src="${pageContext.request.contextPath}/js/scorecard.js"></script>
	<!-- -->

</body>
</html>