<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Calciatore" %>
<jsp:useBean id="calciatori" scope="request" type="List<Calciatore>"></jsp:useBean>
<html>
	<head lang="it">
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/menu.jsp"></jsp:include>
		<div class="container">
		<%
		for(Calciatore c : calciatori) {
		%>
			<div class="row">
				<div class="col-sm">
					<%= c.getNome() %>
				</div>
				<div class="col-sm">
					<%= c.getRuolo() %>
				</div>
				<div class="col-sm">
					<%= c.getSquadra() %>
				</div>
				<div class="col-sm">
					<%= c.getCosto() %>
				</div>
			</div>
		<%
		}
		%>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>