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
		height="10%" width="7%" alt="AISSBerg">
	<h1 class="titulo">
		AISSBerg:
		<c:out value="${param.locality}" />
	</h1>


	<div class="container">

		<div class="mapita">

			<div id="map"></div>
			<script type="text/javascript"
				src="http://maps.google.com/maps/api/js?sensor=false"></script>
			<script type="text/javascript">
				var marcadores = [];

				function initMap() {

					var localidades = [
							<c:forEach items="${requestScope.items}" var="item">[
									"<c:out value="${item.venue.name}"/>",
									<c:out value="${item.venue.location.lat}"/>,
									<c:out value="${item.venue.location.lng}"/>,
									<c:out value="${item.venue.location.postalCode}"/>],
							</c:forEach> ];

					var mapa = new google.maps.Map(document
							.getElementById('map'), {
						zoom : 17,
						center : new google.maps.LatLng(localidades[1][1],
								localidades[2][2]),
					});

					var limites = new google.maps.LatLngBounds();

					var infowindow = new google.maps.InfoWindow();

					var marcador, i;

					for (i = 0; i < localidades.length; i++) {
						var marcador = new google.maps.Marker({
							position : new google.maps.LatLng(
									localidades[i][1], localidades[i][2]),
							map : mapa
						});

						marcadores.push(marcador);

						limites.extend(marcador.position);

						google.maps.event.addListener(marcador, 'click',
								(function(marcador, i) {
									return function() {
										infowindow.setContent(localidades[i][0]
												+ "<br/>" + localidades[i][3]
												+ "<br/>");
										infowindow.open(mapa, marcador);
									}
								})(marcador, i));
					}

					mapa.fitBounds(limites);

				}

				google.maps.event.addDomListener(window, 'load', initMap);
			</script>

			<script
				src="https://maps.googleapis.com/maps/api/js?key=xxx&libraries=places&callback=initMap"
				async defer></script>
		</div>
		</br> </br>
		<div class="row">
			<!-- Columna de la izquierda-->

			<div class="col-md-4">
				<div class="card mb-3">
					</br>
					<h5 class="text-center" class="card-title">Festivos</h5>


					<p class="text-center" class="card-text">
						<small class="text-muted"><i>Nombre <b>##</b> fecha <b>##</b>
								descripción
						</i></small>
					</p>

					<div class="card-body">
						<p class="card-text">
						<div class="bloqueFiestas">



							<ul class="list-group">
								<c:forEach items="${requestScope.holidays}" var="holiday">
									<span class="nombreFiesta">
										<li class="list-group-item"><c:out
												value="${holiday.name}" /> <b>##</b> <c:out
												value="${holiday.date.iso}" /> <b>##</b> <c:out
												value="${holiday.description}" /></li> </br>
									</span>

								</c:forEach>
							</ul>



						</div>
						</p>

					</div>
				</div>

				</br>

				<div class="card mb-3">
					<img src="./images/512.png" class="card-img-top">
					<div class="card-body">
						<p class="card-text">
							<a role="button" class="btn btn-warning"
								href="https://foursquare.com/oauth2/authenticate?client_id=XXXXXXXXXXX&response_type=code&redirect_uri=http://aissberg-238915.appspot.com/FoursquareController">Añadir
								a Foursquare</a>
						</p>
					</div>
				</div>

			</div>


			<!-- Columna de la derecha -->

			<div class="col-md-8">


				<div class="card bg-light mb-3">
					</br>
					<h5 class="text-center" class="card-title">Venues de
						Foursquare</h5>

					<p class="text-center" class="card-text">
						<small class="text-muted"><i>Nombre <b>##</b>
								localización
						</i></small>
					</p>




					<div class="card-body">
						<p class="card-text">
						<ul class="list-group">
							<c:forEach items="${requestScope.venues}" var="venue">
								<span class="descripcionLugares">

									<li class="list-group-item"><c:out value="${venue.name}" />
										<b>##</b> <c:out value="${venue.location.formattedAddress}" /></li>

								</span>
								<br />


							</c:forEach>
						</ul>
						</p>
					</div>
				</div>
			</div>



		</div>
	</div>
	</br>
	</br>
	</br>

	<footer>
		<div class="footer-content">
			Proyecto para la asignatura de AISS. Hecho con ❤️ desde Sevilla, por
			el grupo de desarrollo AISSBerg. <a
				href="https://aissberg-238915.appspot.com/docs/index.html">Nuestra API</a>
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
