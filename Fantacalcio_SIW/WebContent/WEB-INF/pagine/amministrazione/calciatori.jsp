<%@page import="model.Calciatore"%>
<%@page import="java.util.List" %>
<jsp:useBean id="calciatori" type="List<model.Calciatore>" scope="request"></jsp:useBean>
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
					<h3>Calciatori</h3>
				</div>
			</header>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
<%
	if(calciatori.size() > 0) {
%>
					<table class="table">
						<thead>
							<tr>
								<th>Codice</th>
								<th>Nome</th>
								<th>Squadra</th>
								<th>Data nascita</th>
								<th>Ruolo</th>
								<th>Costo</th>
							</tr>
						</thead>
						<tbody>
<%
		for(Calciatore c : calciatori) {
%>
							<tr>
								<td><%= c.getCodice() %></td>
								<td><%= c.getNome() %></td>
								<td><%= c.getSquadra() %></td>
								<td><%= c.getDataNascitaStringa() %></td>
								<td><%= c.getRuolo() %></td>
								<td><%= c.getCosto() %></td>
							</tr>
<%
		}
%>
						</tbody>
					</table>
<%
	} else {
%>
					<h5>Non sono presenti giocatori.</h5>
<%
	}
%>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>