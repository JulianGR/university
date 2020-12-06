<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<meta name="description" content="Mashup project por AISS">
<meta name="author" content="AISSBerg">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>AISSBerg</title>
</head>


<!-- empieza el body -->
<body>
	<img class="fotoPrincipalAfter" src="./images/IconoPNG.png"
		height="10%" width="10%" alt="AISSBerg">
	<h1 class="titulo">
		AISSBerg:
		<c:out value="${param.locality}" />
	</h1>


	<!-- Layout -->
	<div class="container">




		<div class="row">
			<!-- Columna de la izquierda -->
			<div class="col-md-6">

				<!-- bandera y extracto -->
				<div class="card mb-3">
					<img src="<c:out value="${pais.flag}"/>" width="10%" height="10%"
						class="card-img" alt="flag">
					<div class="card-body">

						<p class="card-text">
							<c:out value="${wikiResults.extract}" />
						</p>
						<p class="card-text">
							<small class="text-muted">Información de Wikipedia</small>
						</p>
					</div>
				</div>


				<div class="card bg-light mb-3" style="max-width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">Prefijo telefónico y moneda</h5>
						<p class="card-text">
							+
							<c:out value="${pais.callingCodes}" />
							,
							<c:out value="${pais.currencies.get(0).code}" />
							,
							<c:out value="${pais.currencies.get(0).name}" />
							,
							<c:out value="${pais.currencies.get(0).symbol}" />
						</p>
					</div>
				</div>


				<!-- cerramiento de la columna izq -->
			</div>
			<!-- Columna de la derecha -->
			<div class="col-md-6">


				<div class="card bg-light mb-3" style="width: 18rem;">
					<img src="./images/miniaturaGMaps.png" class="card-img-top"
						alt="Miniatura google maps">
					<div class="card-body">
						<p class="card-text">
						<form action='/MapasController' method='post'>

							<input id="city" name="locality" type="hidden"
								value="<c:out value="${param.locality}" />"> <input
								id="lat" name="lat" type="hidden"
								value="<c:out value="${wikiResults.coordinates.lat}"/>">
							<input id="lng" name="lng" type="hidden"
								value="<c:out value="${wikiResults.coordinates.lon}"/>">
							<input id="country" name="country" type="hidden"
								value="<c:out value="${param.country}" />"> <input
								id="countryShort" name="countryShort" type="hidden"
								value="<c:out value="${param.countryShort}" />">
							<button type='submit' class="btn btn-warning" name='searchBtn'
								title='Mapa' value='Mapa'>Mapa de ciudad</button>
						</form>
						</p>
					</div>
				</div>

				</br>
				<div class="card bg-light mb-3" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">
							El tiempo:
							<c:out value="${tiempo.currently.summary}" />
						</h5>
						<p class="card-text">
							<c:out value="${tiempo.currently.temperature}" />
							ºC
						<p id="icono" hidden>
							<c:out value="${tiempo.currently.icon}" />
						</p>
						<img id="wicon" src="" height="107" width="98"
							alt="<c:out value="${tiempo.currently.icon}" />">

						<script>
							var iconoTiempo = document.getElementById("icono").innerHTML
									.trim()
							document.getElementById("wicon").src = "/images/iconosWeather/"
									+ iconoTiempo + ".png";
						</script>
						</p>
						<p class="card-text">
							<small class="text-muted">Información de DarkSky</small>
						</p>
					</div>
				</div>


			</div>
		</div>
	</div>

	</br>
	</br>
	</br>


	<script
		src="https://maps.googleapis.com/maps/api/js?key=xxx&libraries=places&callback=initMap"
		async defer></script>
	<footer>
		<div class="footer-content">
			Proyecto para la asignatura de AISS. Hecho con ❤️ desde Sevilla, por
			el grupo de desarrollo AISSBerg. <a
				href="https://aissberg-238915.appspot.com/docs/index.html">Nuestra
				API</a>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>