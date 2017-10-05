<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Respuestos</title>
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