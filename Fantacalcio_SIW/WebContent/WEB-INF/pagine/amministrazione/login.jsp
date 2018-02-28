<%@page import="model.Amministratore"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<title>Login</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
		<div class="container">
			<div class="row" id="content">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-xs-8 col-xs-offset-2 col-lg-4 col-lg-offset-4">
						<div class="well">
							<h1 class="text-center">Admin</h1>
							<form action="${pageContext.request.contextPath}/loginAmministratore" method="POST">
								<div class="form-group">
									<label for="username">Username</label>
									<input class="form-control" type="text" placeholder="username..." required name="username" id="username" />
									<p></p>
								</div>
								<div class="form-group">
									<label for="username">Password</label>
									<input class="form-control" type="password" placeholder="password..." name ="password" required id="password" />
									<p></p>
								</div>
								<button type="submit" class="btn btn-primary btn-lg btn-block">Entra</button>
							</form>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	</body>
</html>