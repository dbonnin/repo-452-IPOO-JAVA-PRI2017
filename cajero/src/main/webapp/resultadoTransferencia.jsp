<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Transferencia</title>
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
			<c:when test="${ ! empty transferencia }">
				<table>
					<tr>
						<th>Nro. Operacion</th>
						<td>${transferencia.nroOperacion}</td>
					</tr>
					<tr>
						<th>Fecha/Hora</th>
						<td>${transferencia.fechaHora}</td>
					</tr>
					<tr>
						<th>Importe</th>
						<td>${transferencia.importe}</td>
					</tr>
					<tr>
						<th>Moneda</th>
						<td>${transferencia.moneda.nombre}</td>
					</tr>								
					<tr>
						<th>Cuenta Origen</th>
						<td>${transferencia.cuenta.tipo} - ${transferencia.cuenta.nroDeCuenta}</td>
					</tr>
					<tr>
						<th>Cuenta Destino</th>
						<td>${transferencia.cuentaDestino.tipo} - ${transferencia.cuentaDestino.nroDeCuenta}</td>
					</tr>
					<tr>
						<th>Estado</th>
						<td>${transferencia.resultado.estado}</td>
					</tr>								
					<tr>
						<th>Mensaje</th>
						<td>${transferencia.resultado.mensaje}</td>
					</tr>	
				</table>
			</c:when>
			<c:otherwise>
				<p>Operación inexistente</p>
			</c:otherwise>
		</c:choose>
		<p>
			<a href="inicio" class="btn btn-info" role="button">Volver</a>
		</p>
	</div>
	<footer>
	</footer>
</html>