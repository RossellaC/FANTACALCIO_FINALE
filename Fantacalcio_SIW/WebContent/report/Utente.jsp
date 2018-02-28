<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Registrazione</title>

</head>

<body>

	<div class="container well">
		<form action="${pageContext.request.contextPath}/IscrizioneUtente" class="form horizontal" role="form"
			method="post">
			<div class="well" id="registration" style="text-align: center;">
				<h2>Registrazione</h2>
			</div>
			<div class="form-group" id="nameForm">
				<label for="name" class="col-sm-3 col-md-3 col-lg-3 control-label" id="name">Nome
					<span> (Necessario)</span>
				</label>
				<div class="col-sm-9 col-md-9 col-lg-9">
					<input type="text" class="form-control" name="name"
						id="inputName" placeholder="Name" onblur="validateInputString(this.value,this.id)" required> <span
						id="glyphiconFormName"> </span> <span class="help-block">Es.
						Francesco<small>[Minimum 3 and maximum 15 letter]</small></span>
					<div id="errName"></div>
				</div>
			</div>
			<div class="form group">
				<label for="surname" class="col-sm-3 col-md-3 col-lg-3 control-label" id="surname">Cognome
					<span> (Necessario)</span>
				</label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="text" name="surname" id="surname" onblur="validateInputString(this.value,this.id)" 
						placeholder="Surname" class="form-control" required> <span
						class="help-block">Es. Verdi<small>[Minimum 3 and maximum 15 letter]</small></span>
				</div>
			</div>
			<div class="form-group" id="emailForm">
				<label for="e-mail" class="col-sm-3 col-md-3 col-lg-3 control-label">E-mail <span>
						(Necessario)</span></label>
				<div class="col-sm-9 col-md-9 col-lg-9 ">
					<input type="text" name="email" id="e-mail" placeholder="E-mail"
						class="form-control" onblur="validateEmail(this.value,this.id)" required> <span class="help-block">Es.
						francescoverdi@dominio.it</span>
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

</body>
</html>