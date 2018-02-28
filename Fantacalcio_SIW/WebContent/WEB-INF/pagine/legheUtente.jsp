<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Lega"%>
<jsp:useBean id="leghe" scope="request" type="List<Lega>" />
<jsp:useBean id="leghePartecipa" scope="request" type="List<Lega>" />
<!DOCTYPE html>
<html>
<head>
	<title>Le tue Leghe</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin-bottom: 2rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3>Le tue Leghe</h3>
			</div>
		</div>
		<div class="row" style="margin-bottom: 2rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
<%
	if(leghe.size() > 0) {
%>
				<table class="table">
					<thead>
						<tr>
							<td>Nome</td>
							<td>Dettagli</td>
						</tr>
					</thead>
					<tbody>
<%
		for(Lega lega: leghe) {
%>
						<tr>
							<td><%= lega.getNome() %></td>
							<td>
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/dettagliLega?id=<%= lega.getId() %>">Dettagli</a>
							</td>
						</tr>
<%
		}
%>
					</tbody>
				</table>
<%
	} else {
%>
				<p>Non hai ricevuto alcun invito.</p>
<%
	}
%>
			</div>
		</div>
		<div class="row" style="margin-bottom: 2rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3>Leghe a cui partecipi</h3>
			</div>
		</div>
		<div class="row" style="margin-bottom: 2rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
<%
	if(leghePartecipa.size() > 0) {
%>
				<table class="table">
					<thead>
						<tr>
							<td>Nome</td>
							<td>Dettagli</td>
						</tr>
					</thead>
					<tbody>
<%
		for(Lega lega: leghePartecipa) {
%>
						<tr>
							<td><%= lega.getNome() %></td>
							<td>
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/dettagliLega?id=<%= lega.getId() %>">Dettagli</a>
							</td>
						</tr>
<%
		}
%>
					</tbody>
				</table>
<%
	} else {
%>
				<p>Non partecipi a nessuna lega.</p>
<%
	}
%>
			</div>
		</div>
		<div class="row" style="margin-bottom: 1rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/creaLega">Crea Lega</a>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
</body>
</html>