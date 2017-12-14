<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Cajero</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		
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
						<c:forEach items="${cuentas}" var="c">
							<option value="${c.nroDeCuenta}">${c.tipo} - ${c.denominacion} - ${c.moneda.nombre} - ${c.saldoDisponible} </option>
						</c:forEach>
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
				<a href="transferencia" class="btn btn-info" role="button">Cancelar</a>
			</ul>
		</form>
	</div>
	<footer>
	</footer>
</html>