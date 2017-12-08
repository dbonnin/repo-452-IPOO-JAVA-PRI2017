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
	</div>
	<footer>
	</footer>
</html>