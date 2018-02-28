<%@page import="model.Utente" %>
<%@page import="model.Amministratore" %>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>                        
      		</button>
      		<a class="navbar-brand" href="${pageContext.request.contextPath}/index">FANTACALCIO</a>
    	</div>
    	<div class="collapse navbar-collapse" id="myNavbar">
      		<ul class="nav navbar-nav">
        		<li class="active"><a href="#">Home</a></li>
        		<li class="dropdown">
          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Info <span class="caret"></span></a>
          			<ul class="dropdown-menu">
             			<li><a href="${pageContext.request.contextPath}/tutorial">TutorialGioco</a></li>
            			<li><a href="${pageContext.request.contextPath}/doveSiamo">DoveSiamo</a></li>
          			</ul>
        		</li>
 			</ul>
      
<%
	if(session.getAttribute("utente") == null && session.getAttribute("amministratore") == null) { 
%>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="${pageContext.request.contextPath}/registrazione">
						<span class="glyphicon glyphicon-user"></span>Registrati
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/login" >
						<span class="glyphicon glyphicon-log-in"></span>Accedi
					</a>
				</li>
			</ul>
<%
	} else if(session.getAttribute("amministratore") == null){
%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"	data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span> 
							<%=((Utente)session.getAttribute("utente")).getUsername() %><b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/pannelloUtente" >Profilo</a></li>
						<li><a href="${pageContext.request.contextPath}/logout" id="logout">Logout</a></li>
					</ul>
				</li>
			</ul>
			<% 
	} else {
			%>
		<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"	data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span> 
							<%=((Amministratore)session.getAttribute("amministratore")).getUsername() %><b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/admin/index" id="logout">Pannello Amministratore</a></li>
						<li><a href="${pageContext.request.contextPath}/logout" id="logout">Logout</a></li>
					</ul>
				</li>
			</ul>	
			
<%
	}
%>
		</div>
	</div>
</nav>