<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<jsp:useBean id="squadre" type="List<String>" scope="request"></jsp:useBean>
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
	<button type="submit" class="btn btn-primary">Vota</button>
</form>