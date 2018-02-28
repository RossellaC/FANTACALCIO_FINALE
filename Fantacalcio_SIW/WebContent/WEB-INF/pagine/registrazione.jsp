<%@page import="model.Utente"%>
<%@page import="forms.FormRegistrazione"%>
<jsp:useBean id="form" type="forms.FormRegistrazione" scope="request"></jsp:useBean>
<!-- <jsp:useBean id="messaggio" type="model.Messaggio" scope="request"></jsp:useBean> -->

 <!--             -->
<!DOCTYPE html>  
<html>

	<head>
	<script src="js/validateInput.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="js/jquery-3.1.1.js"></script>
		<title>Registrazione</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<div class="row" id="registration" >
				<div class="col-lg-8 col-md-10 col-xs-12 col-lg-offset-2 col-md-offset-1">
					<h3>Registrazione utente</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-md-10 col-xs-12 col-lg-offset-2 col-md-offset-1">
					<p><%= messaggio.getTesto() != null ? messaggio.getTesto() : "" %></p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-md-10 col-xs-12 col-lg-offset-2 col-md-offset-1">
					<form action="" method="POST">
						<div class="form-group" id="nameForm">
							<label for="nome">Nome
							<span> (Necessario)</span>
							</label>
							<input name="nome" value="<%= form.getNome() != null ? form.getNome() : "" %>" type="text" class="form-control" id="inputName" placeholder="Nome..." name="nome"
						placeholder="Name" onblur="validateInputString(this.value,this.id)" required> <span
						id="glyphiconFormName"> </span> <span class="help-block">Es.
						Francesco<small>[Minimum 3 and maximum 15 letter]</small></span>
					<div id="errName"></div>
						</div>
						<div class="form-group" id="emailForm">
							<label for="cognome">Cognome
							<span> (Necessario)</span>
							</label>
							<input name="cognome" value="<%= form.getCognome() != null ? form.getCognome() : "" %>" type="text" class="form-control" id="cognome" placeholder="Cognome..." onblur="validateInputString(this.value,this.id)" required>
						<span class="help-block">Es. Verdi<small>[Minimum 3 and maximum 15 letter]</small></span>
						</div>
						<div class="form-group" id="emailForm">
							<label for="email">Email
							<span>(Necessario)</span></label>
							
							</label>
							<input name="email" value="<%= form.getEmail() != null ? form.getEmail() : "" %>" type="email" class="form-control" id="email" placeholder="Email..."
							onblur="validateEmail(this.value,this.id)" required>
							<span class="help-block">Es.francescoVerdi@dominio.it</span>
						</div>
						<div class="form-group" id="usernameForm">
							<label for="username">Username
							<span> (Necessario)</span>
							</label>
							<input name="username" value="<%= form.getUsername() != null ? form.getUsername() : "" %>" type="text" class="form-control" id="username" placeholder="Username..."
							onblur="validateInputString(this.value,this.id),checkUsername(this.value);" required>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="sesso" id="maschio" value="M" <%= form.getSesso()!=null && form.getSesso().equals("M") ? "checked" : "" %>>Maschio
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="sesso" id="femmina" value="F" <%= form.getSesso()!=null && form.getSesso().equals("F") ? "checked" : "" %>>Femmina
							</label>
						</div>
						<div class="form-group" id="passwordForm">
							<label for="password">Password
							<span> (Necessario)</span></label>
							</label>
							<input class="form-control" type="password" name="password" id="password" placeholder="Password..." value=""
							onblur="validateInputString(this.value,this.id),checkPassword()" required>
							<span id="glyphiconFormPassword"></span>
							<div id="errPassword"></div>
							
						</div>
						<div class="form-group" id="repeatPasswordForm">
							<label for="repeatPassword">Ripeti Password
							<span> (Necessario)</span></label>
							</label>
							<input class="form-control" type="password" name="repeatPassword" id="repeatPassword" placeholder="Password..." value=""
							onblur="validateInputString(this.value,this.id),checkPassword()" required>
							<span id="glyphiconFormRepeatPassword"></span>
					<div id="errRepeatPassword"></div>
						</div>
						<div class="form-group">
							<button class="form-control btn btn-primary" type="submit">Registrati<span
						id="glyphiconSubmitForm"></span></button>
						<div id="errSubmitForm"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
		
		<script type="text/javascript">

	function checkUsername(username){
		if(username!=""){
				$.ajax({
					type: "POST",
					url: "CheckUtenteController",
					data: {username:username},
					
	                success: function( data, status) {
	                	var result = data;
	                	
	                	if(result == "success"){
	                		
	                		$("#usernameForm").addClass("form-group has-success has-feedback");
	                		$("#glyphiconFormusername").addClass("glyphicon glyphicon-ok form-control-feedback");
	                		$("#submitForm").unbind();
	                		$("#submitForm").css("background-color","green");
	                	}
	                	else if(result == "error"){
							$("#usernameForm").addClass("form-group has-error has-feedback");
							$("#glyphiconFormusername").addClass("glyphicon glyphicon-remove form-control-feedback");
							$("#submitForm").bind("click",function (event) {
							    event.preventDefault();
							});
							$("#submitForm").css("background-color","red");
							$("#errSubmitForm")
							.append("<div id='invalidSubmit' class='alert alert-danger'>"
							+ "<strong>ATTENZIONE!</strong> alcuni dei campi non sono stati riempiti, correggerli per potersi registrare.</div>");
							
							$("#inputusername").prop("disabled",true);
							$("#errusername")
							.append(
									"<div id='existusername' class='alert alert-danger'>"
											+ "<strong>Errore!</strong> username non disponibile, riprova</div>");
							window.setTimeout(function() {
								$("#errusername").fadeTo(50, 0).slideUp(
										50, function() {
											$("#errusername").empty();
											$("#errusername").removeAttr("style");
										});
								$("#inputusername").val("");
								$("#usernameForm").removeClass();
								$("#glyphiconFormusername").removeClass();
								$("#inputusername").prop("disabled",false);
							}, 3000);
	                	}
	                },
	                
	                error: function(jqXHR, textStatus, errorThrown){
	                	console.log("Error into /WEB-INF/pagine/registrazione.jsp (AJAX - checkUsername(username) ))-->" + textStatus);
	                }
	                
				});
		}
		else{
			$("#usernameForm").addClass("form-group has-warning has-feedback");
			$("#glyphiconFormusername").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
			$("#inputusername").prop("disabled",true);
			$("#submitForm").bind("click",function (event) {
			    event.preventDefault(); 
			    
			});
			$("#submitForm").css("background-color","red");
			$("#errusername").append(
					"<div id='existusername' class='alert alert-warning'>"
							+ "<strong>Warning!</strong> username required!!</div>");
			window.setTimeout(function() {
				$("#errusername").fadeTo(500, 0).slideUp(
						500, function() {
							$("#errusername").empty();
							$("#errusername").removeAttr("style");
						});
				$("#inputusername").val("");
				$("#usernameForm").removeClass();
				$("#glyphiconFormusername").removeClass();
				$("#inputusername").prop("disabled",false);
			}, 3000);
		}
	}

	function checkPassword(){
		var passw1=$("#inputRepeatPassword").val();
		var passw2=$("#inputPassword").val();
		
		if( (( passw1 && passw2 ) != ( null)) && ( ( passw1 && passw2 ) != "" ) ){
			
			$.ajax({
				type: "POST",
				url: "PasswordController",
				data: {
					password:passw1,
					repeatPassword:passw2
					},
				
                success: function( data ){ 
                	var result = data;
                	
                	if( result=="success" ){
                		
                		$("#submitForm").unbind();
            			$("#submitForm").css("background-color","green");
            			$("#passwordForm").addClass("form-group has-success has-feedback");
                		$("#glyphiconFormPassword").addClass("glyphicon glyphicon-ok form-control-feedback");
                		$("#repeatPasswordForm").addClass("form-group has-success has-feedback");
                		$("#glyphiconFormRepeatPassword").addClass("glyphicon glyphicon-ok form-control-feedback");
                	}
                	
                	else if( result=="warning" ){
                		$("#submitForm").bind("click",function (event) {
            			    event.preventDefault(); 
            			});
            			$("#submitForm").css("background-color","red");
            			$("#errSubmitForm").empty();
            			$("#errSubmitForm")
						.append("<div id='invalidSubmit' class='alert alert-danger'>"
						+ "<strong>ATTENZIONE!</strong> alcuni dei campi non sono stati riempiti, correggerli per potersi registrare.</div>");
						
            			$("#passwordForm").addClass("form-group has-error has-feedback");
                		$("#glyphiconFormPassword").addClass("glyphicon glyphicon-remove form-control-feedback");
                		$("#repeatPasswordForm").addClass("form-group has-error has-feedback");
                		$("#glyphiconFormRepeatPassword").addClass("glyphicon glyphicon-remove form-control-feedback");
                		$("#inputPassword").prop("disabled",true);
                		$("#inputRepeatPassword").prop("disabled",true);
                		$("#errRepeatPassword").append(
            					"<div id='matchPasswords' class='alert alert-danger'>"
            							+ "<strong>ATTENTION!!!</strong> The passwords mismatch!</div>");
            			window.setTimeout(function() {
            				$("#errRepeatPassword").fadeTo(500, 0).slideUp(
            						500, function() {
            							$("#errRepeatPassword").empty();
            							$("#errRepeatPassword").removeAttr("style");
            						});  
            				$("#inputPassword").val("");            				
            				$("#passwordForm").removeClass();
            				$("#glyphiconFormPassword").removeClass();
            				$("#inputRepeatPassword").val("");
            				$("#repeatPasswordForm").removeClass();
            				$("#glyphiconFormRepeatPassword").removeClass();
            				$("#inputRepeatPassword").prop("disabled",false);
            				$("#inputPassword").prop("disabled",false);
            				
            			}, 3000);
                	}
                		                		
                },
                
			 	error: function(jqXHR, textStatus, errorThrown){
			 		console.log("Error into /WEB-INF/pagine/registrazione.jsp (AJAX - checkPassword() ))-->" + textStatus);
			 	}
                
			});
		}
			 
	}
</script>
	</body>
</html>	