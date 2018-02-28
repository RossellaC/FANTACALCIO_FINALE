<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Giornata"%>
<jsp:useBean id="giornate" type="List<Giornata>" scope="request"></jsp:useBean>
<!DOCTYPE html>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
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
					<h3>Giornate di Campionato</h3>
				</div>
			</header>
			<jsp:include page="/WEB-INF/pezzi/messaggioIncorporato.jsp"></jsp:include>
			<div class="row">
				<div class="col-xs-10 col-md-10 col-lg-8 col-xs-offset-1 col-md-offset-1 col-lg-offset-2">
					<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/admin/giornate">
						<div class="form-group">
					    	<label for="inizio" class="col-sm-2 control-label">Inizio</label>
					    	<div class="col-sm-10">
					    		<input type="date" name="inizio" class="form-control" id="inizio" placeholder="Inizio...">
					    	</div>
						</div>
						<div class="form-group">
							<label for="fine" class="col-sm-2 control-label">Fine</label>
							<div class="col-sm-10">
								<input type="date" name="fine" class="form-control" id="fine" placeholder="Fine...">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Inserisci</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-10 col-md-10 col-lg-8 col-xs-offset-1 col-md-offset-1 col-lg-offset-2">
<%
	if(giornate.size() > 0) {
%>
	    			<table class="table">
	    				<thead>
	    					<tr>
	    						<th>ID</th>
	    						<th>Inizio</th>
	    						<th>Fine</th>
	    						<th>Voti</th>
	    					</tr>
	    				</thead>
	    				<tbody>
<%
		for(Giornata g : giornate) {
%>
	    					<tr>
	    						<td><%= g.getId() %></td>
	    						<td><%= sdf.format(g.getDataInizio()) %></td>
	    						<td><%= sdf.format(g.getDataFine()) %></td>
	    						<td>
	    							<a href="${pageContext.request.contextPath}/admin/voti?giornata=<%= g.getId() %>" class="btn btn-primary">Edita</a>
	    						</td>
	    					</tr>
<%
		}
%>
	    				</tbody>
	    			</table>
<%
	}
%>
	  			</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>