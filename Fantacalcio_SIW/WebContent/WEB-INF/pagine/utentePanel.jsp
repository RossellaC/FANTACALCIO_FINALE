<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Utente"%>
<%@ page import="model.Lega"%>
<%@ page import="model.InvitoLega"%>
<jsp:useBean id="leghe" scope="request" type="List<Lega>" />
<jsp:useBean id="inviti" scope="request" type="List<InvitoLega>" />
<jsp:useBean id="invitiSquadre" scope="request" type="List<InvitoLega>" />
<!DOCTYPE html>
<html lang="it">

<head>
	<title>Pannello di controllo</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>

<body>

	<%
		if (request.getSession().getAttribute("utente") != null) {
	%>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="jumbotron">
		<h1>Benvenuto</h1>
		<p>Inizia a giocare</p>
	</div>
	<%
		if (inviti.size() > 0) {
	%>
	<h2>Inviti ricevuti</h2>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Mittente</th>
				<th scope="col">Lega</th>
				<th scope="col">Accettazione</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (InvitoLega il : inviti) {
			%>
			<tr>
				<td><%=il.getMittente()%></td>
				<td><%=il.getNomeLega()%></td>
				<td>
					<form action="${pageContext.request.contextPath}/accettaInvito"
						method="POST">
						<input type="hidden" name="idLega" value="<%=il.getFkLega()%>">
						<button type="submit" class="btn btn-primary">Accetta</button>
					</form>
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
	<h2>Non hai nessun invito da accettare</h2>
	<%
		}
	%>
	<%
		if (invitiSquadre.size() > 0) {
	%>
	<h2>Squadre da creare</h2>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Mittente</th>
				<th scope="col">Lega</th>
				<th scope="col">Accettazione</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (InvitoLega il : invitiSquadre) {
			%>
			<tr>
				<td><%=il.getMittente()%></td>
				<td><%=il.getNomeLega()%></td>
				<td><a class="btn btn-primary"
					href="${pageContext.request.contextPath}/creazioneSquadra?id=<%= il.getFkLega() %>"
					role="button">Crea</a></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
	<div id="sidebar-wrapper">
		<h5>Le mie leghe</h5>
		<%
			if (leghe.size() > 0) {
		%>
		<div class="list-group">
			<%
				for (Lega l : leghe) {
			%>
			<a
				href="${pageContext.request.contextPath}/dettagliLega?id=<%= l.getId() %>"
				class="list-group-item list-group-item-action"><%=l.getNome()%></a>
			<%
				}
			%>
		</div>
		<%
			} else {
		%>
		<p>Non hai accesso a nessuna lega.</p>
		<%
			}
		%>
		<ul class="sidebar-nav">
			<li class="sidebar-brand"><a
				href="${pageContext.request.contextPath}/creaLega">Crea una Lega</a></li>
			<li class="sidebar-brand"><a> ListaCalciatori </a></li>
			<li><a href="#">Crae una Rosa</a></li>
		</ul>

	</div>
	<!-- /#sidebar-wrapper -->
	<%
		} else {
	%>

	<jsp:include page="/WEB-INF/pagine/paginaErrore.jsp"></jsp:include>
	<%
		}
	%>

</body>

</html>