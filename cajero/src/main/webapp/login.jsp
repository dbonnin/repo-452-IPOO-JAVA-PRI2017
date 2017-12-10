<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Respuestos</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	</head>
	<header>
		<p>Iniciar Sesión</p>
	</header>
	<div>
		<c:choose>
			<c:when test="${ ! empty mensaje }">
				${mensaje}
			</c:when>
		</c:choose>
		<form href="login" method="post">
			<ul>
				<li>
					<label for="tipoDoc">Tipo Documento: </label>
					<input type="text" name="tipoDoc">
				</li>
				<li>
					<label for="nroDoc">Nro. Documento: </label>
					<input type="text" name="nroDoc">
				</li>
				<li>
					<label for="password">Contraseña: </label>
					<input type="password" name="password">
				</li>				
				<input type="submit" value="Ingresar" />
			</ul>
		</form>
	</div>
	<footer>
	</footer>
</html>