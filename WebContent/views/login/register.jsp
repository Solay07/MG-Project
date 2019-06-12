<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="author" content="Kodinger">
	<title>Registro</title>
	<link rel="stylesheet"
		  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		  crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../../css/my-login.css">
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body class="my-login-page col-6 offset-3">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="../../img/logo.png">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Registro</h4>
							<form method="POST">
							 
								<div class="form-group">
									<label for="name">Nombre</label>
									<input id="name" type="text" class="form-control" name="name" required autofocus>
								</div>

								<div class="form-group">
									<label for="email">E-Mail</label>
									<input id="email" type="email" class="form-control" name="email" required>
								</div>

								<div class="form-group">
									<label for="password">Contraseña</label>
									<input id="password" type="password" class="form-control" name="password" required data-eye>
								</div>

								<div class="form-group">
									<label>
										<input type="checkbox" name="aggree" value="1"> Acepto los términos y condiciones
									</label>
								</div>

								<div class="form-group no-margin">
									<button type="submit" class="btn btn-primary btn-block">
										Registrase
									</button>
								</div>
								<div class="margin-top20 text-center">
									¿Ya tienes una cuenta? <a href="../../index.jsp">Ingresar</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; MG Project 2019
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- jQuery CDN -->
	<script src="js/vendor/jquery.js"></script>

	<!-- Bootstrap JS -->
	<script src="js/vendor/bootstrap.js"></script>

	<!-- Bootstrap JS -->
	<script src="js/app/lib/my-login.js"></script>

	<%-- footer--%>
	<jsp:include page="../components/footer.jsp"/>