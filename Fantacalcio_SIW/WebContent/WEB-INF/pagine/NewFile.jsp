<div class="container well">
	
		 <form action="IscrizioneUtente" class="form horizontal" role="form" method="post"> 
			<div class="well" id="registration" style="text-align: center;">
				<h2>Registrazione</h2>
			</div>
			<div class="form-group" id="nameForm">
			<!-- il tag label fornisce un miglioramento dell'usabilità per gli utenti del mouse,
			 perché se l'utente fa clic sul testo all'interno dell'elemento <label>, attiva il controllo. -->
				<label for="nome" class="col-sm-3 col-md-3 col-lg-3 control-label" id="nome">Nome
					<span> (Necessario)</span>
				</label>
				<div class="col-sm-9 col-md-9 col-lg-9">
					<input type="text" class="form-control" name="nome"
						id="inputName" placeholder="Name" onblur="validateInputString(this.value,this.id)" required> <span
						id="glyphiconFormName"> </span> <span class="help-block">Es.
						Francesco<small>[Minimum 3 and maximum 15 letter]</small></span>
					<div id="errName"></div>
				</div>
			</div>
			<div class="form group">
				<label for="cognome" class="col-sm-3 col-md-3 col-lg-3 control-label" id="cognome">Cognome
					<span> (Necessario)</span>
				</label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="text" name="cognome" id="cognome" onblur="validateInputString(this.value,this.id)" 
						placeholder="Surname" class="form-control" required> <span
						class="help-block">Es. Verdi<small>[Minimum 3 and maximum 15 letter]</small></span>
				</div>
			</div>
			<div class="form-group" id="emailForm">
				<label for="email" class="col-sm-3 col-md-3 col-lg-3 control-label">E-mail <span>
						(Necessario)</span></label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="text" name="email" id="email" placeholder="E-mail"
						class="form-control" onblur="validateEmail(this.value,this.id)" required> <span class="help-block">Es.
						francescoVerdi@dominio.it</span>
				</div>
			</div>
			<div class="form-group" id="usernameForm">
				<label for="username" class="col-sm-3 col-md-3 col-lg-3 control-label" id="username">Username
					<span> (Necessario)</span>
				</label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="text" class="form-control" name="username"
						id="inputusername" placeholder="username"
						onblur="validateInputString(this.value,this.id),checkUsername(this.value);" required> <span
						id="glyphiconFormusername"> </span>
					<div id="errusername"></div>
				</div>
			</div>
			<div class="form-group" id="passwordForm">
				<label for="password" class="col-sm-3 col-md-3 col-lg-3 control-label" id="password">Password
					<span> (Necessario)</span></label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="password" name="password" id="inputPassword"
						placeholder="Password" class="form-control" onblur="validateInputString(this.value,this.id),checkPassword()" required> <span
						id="glyphiconFormPassword"></span>
					<div id="errPassword"></div>
				</div>
			</div>
			<div class="form-group" id="repeatPasswordForm">
				<label for="repeatPassword" class="col-sm-3 col-md-3 col-lg-3 control-label" id="repeatPassword">RipetiPassword 
					<span> (Necessario)</span></label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="password" name="repeatPassword"
						id="inputRepeatPassword" placeholder="RepeatPassword"
						class="form-control" onblur="validateInputString(this.value,this.id),checkPassword()" required> <span
						id="glyphiconFormRepeatPassword"></span>
					<div id="errRepeatPassword"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-md-9 col-lg-9 col-sm-offset-3 col-md-offset-3 col-lg-offset-3">
					<button type="submit" id="submitForm" value="register"
						class="btn btn-primary btn-block">Registrazione<span
						id="glyphiconSubmitForm"> </span></button>
					<div id="errSubmitForm"></div>
				</div>
			</div>
			
		</form>
	</div>

	
		<div class="containerFooter">
		<hr>
		
		
		<footer class="container-fluid text-center">
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright by Ros2 &copy; 2017/2018</p>
				</div>
			</div>
		</footer>
		</div>
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
	                	console.log("Error into Utente_jsp.jsp (AJAX - checkUsername(username) ))-->" + textStatus);
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
			 		console.log("Error into Utente_jsp.jsp (AJAX - checkPassword() ))-->" + textStatus);
			 	}
                
			});
		}
			 
	}
</script>
</body>
</html>