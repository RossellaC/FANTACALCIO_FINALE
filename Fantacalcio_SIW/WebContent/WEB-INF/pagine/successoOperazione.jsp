<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Complimenti</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<header class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<h3>Complimenti</h3>
				</div>
			</header>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<p>
						Operazione eseguita con successo!
						<a href="${pageContext.request.contextPath}/pannelloUtente">Torna all'area personale</a>
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>