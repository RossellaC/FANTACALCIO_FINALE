<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Crea nuova lega</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<header class="row" style="margin-bottom: 2rem;">
				<div class="col-lg-8 col-md-12 col-xs-12 col-lg-offset-2">
					<h3>Creazione Lega</h3>
				</div>
			</header>
			<div class="row">
				<div class="col-lg-8 col-md-12 col-xs-12 col-lg-offset-2">
					<form action="${pageContext.request.contextPath}/creaLega" method="POST">
						<div class="form-group">
							<label for="nomeLega1">Nome</label>
							<input name="nome" type="text" class="form-control" id="nomeLega1" placeholder="Nome nuova lega">
						</div>
						<div class="form-group">
							<label for="descrizioneLega1">Descrizione</label>
							<textarea name="descrizione" class="form-control" id="descrizioneLega1" rows="10"></textarea>
						</div>
						<div class="form-group">
							<label for="budgetLega1">Budget Iniziale</label>
							<input name="budget" type="text" class="form-control" id="budgetLega1" placeholder="Budget iniziale">
						</div>
						<button type="submit" class="btn btn-primary">Salva</button>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>