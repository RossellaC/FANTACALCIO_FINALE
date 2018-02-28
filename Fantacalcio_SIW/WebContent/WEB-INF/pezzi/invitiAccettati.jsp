<%@ page import="java.util.List"%>
<%@ page import="model.InvitoLega"%>
<jsp:useBean id="accettati" scope="request" type="List<InvitoLega>" />
<%
	if(accettati.size() > 0) {
%>
				<table class="table">
					<thead>
						<tr>
							<td>Mittente</td>
							<td>Lega</td>
							<td>Accettazione</td>
						</tr>
					</thead>
					<tbody>
<%
		for(InvitoLega il : accettati) {
%>
						<tr>
							<td><%= il.getMittente() %></td>
							<td><%= il.getNomeLega() %></td>
							<td>
								<form action="${pageContext.request.contextPath}/creazioneSquadra" method="GET">
									<input type="hidden" name="lega" value="<%= il.getFkLega() %>">
									<button type="submit" class="btn btn-primary">Crea Squadra</button>
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
				<p>Non hai accettato nessun invito.</p>
<%
	}
%>