<%@page import="model.Messaggio"%>
<jsp:useBean id="message" type="model.Messaggio" scope="request"></jsp:useBean>
<%
	if(message.isAttivo()) {
		String tipo;
		switch(message.getTipo()) {
		case Messaggio.TIPO_AVVISO:
			tipo = "alert-warning";
			break;
		case Messaggio.TIPO_ERRORE:
			tipo = "alert-danger";
			break;
		case Messaggio.TIPO_INFO:
			tipo = "alert-info";
			break;
		case Messaggio.TIPO_SUCCESSO:
			tipo = "alert-success";
			break;
		default:
			tipo = "alert-info";
			
		}
%>

<div class="alert <%= tipo %> alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
  <strong><%= message.getTitolo() %></strong> <%= message.getTesto() %> <a href="<%= message.getLink() %>"><%= message.getTestoLink() %></a>
</div>
<%
	}
%>
