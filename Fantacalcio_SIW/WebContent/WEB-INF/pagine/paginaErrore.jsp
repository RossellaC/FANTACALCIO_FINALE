<jsp:useBean id="message" type="model.Messaggio" scope="request"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Errore</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>
<body>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin-bottom: 3rem;">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<h3><%= message.getTitolo() %></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<p><%= message.getTesto() %></p>
				<p>Torna a <a href="<%= message.getLink() %>"><%= message.getTestoLink() %></a></p>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
</body>
</html>


