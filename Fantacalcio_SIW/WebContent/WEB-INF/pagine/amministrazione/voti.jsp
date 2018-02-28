<%@page import="model.GiocaCalciatore"%>
<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<jsp:useBean id="squadre" type="List<String>" scope="request"></jsp:useBean>
<jsp:useBean id="giornata" type="model.Giornata" scope="request"></jsp:useBean>
<jsp:useBean id="giocaCalciatori" type="List<model.GiocaCalciatore>" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head lang="it">
		<title>Amministrazione</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<header class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<h3>Voti</h3>
				</div>
			</header>
			<jsp:include page="/WEB-INF/pezzi/messaggioIncorporato.jsp"></jsp:include>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-xs-12">
					<form action="${pageContext.request.contextPath}/admin/voti" method="POST">
						<div class="form-goup">
							<label for="squadra">Squadra</label>
							<select id="squadra" name="squadra" class="form-control">
<%
	for(String s : squadre) {
%>
								<option><%= s %></option>
<%
	}
%>
							</select>
						</div>
						<div class="form-group">
							<label for="giocatore">Giocatore</label>
							<select id="giocatore" name="giocatore" class="form-control">
							</select>
						</div>
						<div class="form-group">
							<label for="voto">Voto</label>
							<select id="voto" name="voto" class="form-control">
								<option>0</option>
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
								<option>8</option>
								<option>9</option>
								<option>10</option>
							</select>
						</div>
						<input name="giornata" type="hidden" value="<%= giornata.getId() %>">
						<button type="submit" class="btn btn-primary">Vota</button>
					</form>
				</div>
				<div class="col-lg-8 col-md-8 col-xs-12">
<%
	if(giocaCalciatori.size() > 0) {
%>
					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Squadra</th>
								<th>Voto</th>
								<th>Modifica</th>
							</tr>
						</thead>
						<tbody>
<%
		for(GiocaCalciatore gc : giocaCalciatori) {
%>
							<tr>
								<td><%= gc.getNome() %></td>
								<td><%= gc.getSquadra() %></td>
								<td><%= gc.getVoto() %></td>
								<td>
									<a href="#" class="btn btn-primary">Modifica</a>
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
					<h5>Non &egrave; stato inserito nessun voto.</h5>
<%
	}
%>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
		<script type="text/javascript">
			$j = jQuery.noConflict();
			$j('#squadra').on('change', function() {
				$j.get(context + "/getCalciatoriPerSquadra",
						{'squadra': $j("#squadra option:selected").text()}, 
						function( data ) {
					var res = JSON.parse(data);
					console.log(res);
					$j('#giocatore').empty();
					for(let i = 0; i < res.length; i++) {
						$j('#giocatore').append(new Option(res[i]['nome'], res[i]['codice'], true, true));
					}
				});
			});
		</script>
	</body>
</html>