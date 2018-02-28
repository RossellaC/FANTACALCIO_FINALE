<!DOCTYPE html>
<html>
	<head lang="it">
		<title>Gioco Fantacalcio</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<header class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<h3>Amministrazione</h3>
				</div>
			</header>
			<div class="col-xs-6 col-md-4 col-lg-4">
    			<a href="${pageContext.request.contextPath}/admin/giornate" class="thumbnail">
      				<img src="${pageContext.request.contextPath}/images/inviti.jpg" alt="Inviti">
      				<div class="caption">
      					<h5 class="text-center">Giornate</h5>
      				</div>
    			</a>
  			</div>
			<div class="col-xs-6 col-md-4 col-lg-4">
    			<a href="${pageContext.request.contextPath}/admin/giocatori" class="thumbnail">
      				<img src="${pageContext.request.contextPath}/images/inviti.jpg" alt="Inviti">
      				<div class="caption">
      					<h5 class="text-center">Calciatori</h5>
      				</div>
    			</a>
  			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>