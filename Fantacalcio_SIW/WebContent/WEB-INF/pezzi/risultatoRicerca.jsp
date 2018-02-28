<%@ page import="java.util.List"%>
<%@ page import="model.Calciatore"%>
<jsp:useBean id="risultato" type="List<Calciatore>" scope="request"></jsp:useBean>
[
<%
	for(Calciatore c : risultato) {
%>
{value:"<%= c.getCodice() %>", label:"<%= c.getNome() %>"} 
<%
	}
%>
]