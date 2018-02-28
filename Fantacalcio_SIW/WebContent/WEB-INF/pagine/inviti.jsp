<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inviti Ricevuti</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3>Inviti Ricevuti</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<jsp:include page="/WEB-INF/pezzi/invitiRicevuti.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3>Inviti Accettati</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<jsp:include page="/WEB-INF/pezzi/invitiAccettati.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
</body>
</html>