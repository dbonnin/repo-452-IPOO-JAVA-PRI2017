<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Cajero</title>
	</head>
	<header>
		<p>Transferencia</p>
	</header>
	<div>
		<c:choose>
			<c:when test="${ ! empty mensaje }">
				${mensaje}
			</c:when>
		</c:choose>
		<form href="transferencia" method="post">
			<ul>
				<li>
					<label for="cuentaOrigen">Cuenta Origen: </label>
					<select name="cuentaOrigen">
						<option value="0">-- Seleccione una cuenta --</option>
					</select>
				</li>
				<li>
					<label for="cuentaDestino">Cuenta Destino: </label>
					<input type="text" name="cuentaDestino">
				</li>
				<li>
					<label for="importe">Importe: </label>
					<input type="number" name="importe">
				</li>				
				<input type="submit" value="Transferir" />
			</ul>
		</form>
	</div>
	<footer>
	</footer>
</html>