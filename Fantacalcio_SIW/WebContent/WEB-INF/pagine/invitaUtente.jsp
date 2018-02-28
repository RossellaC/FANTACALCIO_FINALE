<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="model.Invito" %>
<%@ page import="model.Utente"%>
<jsp:useBean id="lega" scope="request" type="model.Lega"></jsp:useBean>
<jsp:useBean id="inviti" scope="request" type="List<Invito>"></jsp:useBean>

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
					<h3>Invita utente</h3>      
					<p>Invita un altro utente a far parte di questa lega.</p>
<%
	if(inviti.size() > 0) {
%>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Email</th>
								<th scope="col">Stato</th>
							</tr>
						</thead>
						<tbody>
<%
				for(Invito l : inviti) {
%>
							<tr>
								<td><%= l.getFkUtenteRiceve() %></td>
								<td><%= l.isAccettazione() ? "Accettato" : "Non Accettato" %></td>
							</tr>
<%
				}
%>
						</tbody>
					</table>
				</div>
<%
			} else {
%>
				<p>Non hai invitato nessuno per questa lega.</p>
<%
			}
%>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="row">
						<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-xs-12">
							<h5>Invita un altro utente</h5>
							<form action="${pageContext.request.contextPath}/invitaUtente" method="POST">
								<div class="form-group">
									<label for="nomeLega1">Username</label>
									<input value="" name="nome" type="text" class="form-control" id="nomeLega1" placeholder="Nome utente da invitare">
								</div>
								<input type="hidden" name="lega" value="<%= lega.getId() %>" >
								<button type="submit" class="btn btn-primary">Invita</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>