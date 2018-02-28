<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Utente"%>
<%@ page import="model.Lega"%>
<%@ page import="model.InvitoLega"%>
<jsp:useBean id="leghe" scope="request" type="List<Lega>" />
<jsp:useBean id="inviti" scope="request" type="List<InvitoLega>" />
<jsp:useBean id="invitiSquadre" scope="request" type="List<InvitoLega>" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Area Personale</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<header class="row">
			<div class="col-lg-12 col-ms-12 col">
				<h3>Area Personale</h3>
			</div>
		</header>
		<div class="row">
			<div class="col-xs-6 col-md-4 col-lg-4">
    			<a href="${pageContext.request.contextPath}/inviti" class="thumbnail">
      				<img src="${pageContext.request.contextPath}/images/inviti.jpg" alt="Inviti">
      				<div class="caption">
      					<h5 class="text-center">Inviti</h5>
      				</div>
    			</a>
  			</div>
			<div class="col-xs-6 col-md-4 col-lg-4">
    			<a href="${pageContext.request.contextPath}/leghe" class="thumbnail">
      				<img src="${pageContext.request.contextPath}/images/inviti.jpg" alt="Inviti">
      				<div class="caption">
      					<h5 class="text-center">Le mie Leghe</h5>
      				</div>
    			</a>
  			</div>
<!-- 			<div class="col-xs-6 col-md-4 col-lg-4"> -->
<%--     			<a href="${pageContext.request.contextPath}/squadre" class="thumbnail"> --%>
<%--       				<img src="${pageContext.request.contextPath}/images/inviti.jpg" alt="Inviti"> --%>
<!--       				<div class="caption"> -->
<!--       					<h5 class="text-center">Squadre</h5> -->
<!--       				</div> -->
<!--     			</a> -->
<!--   			</div> -->
		</div>
	</div>


	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
</body>
</html>