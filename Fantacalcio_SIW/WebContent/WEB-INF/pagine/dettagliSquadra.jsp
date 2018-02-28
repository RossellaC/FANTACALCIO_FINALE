<%@page import="java.util.List" %>
<%@page import="model.Squadra"%>
<%@page import="model.Calciatore"%>
<jsp:useBean id="squadra" scope="request" type="model.Squadra"></jsp:useBean>
<jsp:useBean id="calciatori" scope="request" type="List<Calciatore>"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<title>Invita un utente</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<h3>Dettagli squadra</h3> 
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<p>Nome: <%= squadra.getNome() %></p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Ruolo</th>
								<th>Squadra</th>
							</tr>
						</thead>
						<tbody>
<%
	for(Calciatore c : calciatori) {
%>
							<tr>
								<td><%= c.getNome() %></td>
								<td><%= c.getRuolo() %></td>
								<td><%= c.getSquadra() %></td>
							</tr>
<%
	}
%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>