<%@ page import="java.util.List"%>
<%@ page import="model.InvitoLega"%>
<jsp:useBean id="inviti" scope="request" type="List<InvitoLega>" />
<%
	if(inviti.size() > 0) {
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
		for(InvitoLega il : inviti) {
%>
						<tr>
							<td><%= il.getMittente() %></td>
							<td><%= il.getNomeLega() %></td>
							<td>
								<form action="${pageContext.request.contextPath}/accettaInvito" method="POST">
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
				<p>Non hai ricevuto alcun invito.</p>
<%
	}
%>