<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Utente"%>
<%@ page import="model.Lega"%>
<%@ page import="model.Squadra"%>
<jsp:useBean id="lega" scope="request" type="model.Lega"></jsp:useBean>
<jsp:useBean id="squadre" scope="request" type="List<Squadra>"></jsp:useBean>
<jsp:useBean id="possiedoSquadra" scope="request" type="java.lang.Boolean"></jsp:useBean>
<jsp:useBean id="campionatoIniziato" scope="request" type="java.lang.Boolean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<title>Pannello di controllo</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
%>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<header class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3>Dettagli Lega</h3>
			</div>
		</header>
		<div class="row">
			<div class="col-lg-6 col-md-6 col-xs-12">
				<form action="${pageContext.request.contextPath}/dettagliLega" method="POST">
					<div class="form-group">
						<label for="nomeLega1">Nome</label>
						<input value="<%= lega.getNome() %>" name="nome" type="text" class="form-control" id="nomeLega1" placeholder="Nome nuova lega">
					</div>
					<div class="form-group">
						<label for="descrizioneLega1">Descrizione</label>
						<textarea name="descrizione" class="form-control" id="descrizioneLega1" rows="5"><%= lega.getDescrizione() %></textarea>
					</div>
					<div class="form-group">
						<label for="budgetLega1">Budget Iniziale</label>
						<input value="<%= lega.getBudgetIniziale() %>" name="budget" type="text" class="form-control" id="budgetLega1" placeholder="Budget iniziale" readonly>
					</div>
					<input type="hidden" name="id" value="<%= lega.getId() %>">
					<button type="submit" class="btn btn-primary">Aggiorna</button>
				</form>
			</div>
			<div class="col-lg-6 col-md-6 col-xs-12">
				<h5>Classifica</h5>
<%
	if(squadre.size() > 0) {
%>
				<div class="list-group">
<%
		for(Squadra d : squadre) {
			if(d.getFkUtente().equals(((Utente)session.getAttribute("utente")).getId())){
				%>
				<a href="${pageContext.request.contextPath}/dettagliSquadra?lega=<%= d.getFkLega() %>&utente=<%= d.getFkUtente() %>" class="list-group-item"><%= d.getNome() +"  | punti: "+d.getPunteggio() +" My Team" %></a>
				<%}else { %>
					<a style="background: grey;" href="${pageContext.request.contextPath}/dettagliSquadra?lega=<%= d.getFkLega() %>&utente=<%= d.getFkUtente() %>" class="list-group-item"><%= d.getNome() +"  | punti: "+d.getPunteggio() %></a>
<%
		}
		}
%>
				</div>
<%
	} else {
%>
				<p>Non ci sono squadre in questa lega.</p>
<%
	}
%>				
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/invitaUtente?id=<%= lega.getId() %>">Invita utente</a>			
<%
	if(!possiedoSquadra.equals(Boolean.TRUE)) {
%>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/creazioneSquadra?lega=<%= lega.getId() %>">Crea squadra</a>
<%
	}
%>
<%
	if(!campionatoIniziato.equals(Boolean.TRUE) && utente.getId().equals(lega.getFkUtente())){
%>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/iniziaCampionato?lega=<%= lega.getId() %>">Inizia campionato</a>
<%
	} 
%>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>   
</body>
</html>