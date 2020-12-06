<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,	shrink-to-fit=no">
<meta name="description" content="Project for UbiqComp">
<meta name="author" content="HueKJ">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/style.css">
<title>HueKJ</title>
</head>


<body>
	<h1 class="titulo">
		HueKJ:
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
				src="https://maps.googleapis.com/maps/api/js?key=XXX&libraries=places&callback=initMap"
				async defer></script>
		</div>





		<div class="row">


			<div class="col-sm">


				</br>

				<div class="card bg-light mb-3">
					</br>
					<h5 class="text-center" class="card-title">Venues from
						Foursquare</h5>

					<p class="text-center" class="card-text">
						<small class="text-muted"><i>Name <b>##</b> Address<b>##</b>
								Rating <b>##</b> ¿Is open right now?
						</i></small>
					</p>




					<div class="card-body">
						<p class="card-text">
						<ul class="list-group">


							<c:forEach items="${requestScope.venuesDetail}"
								var="venuesDetail">

								<form action='/BulbsController2' method='post'>


									<span class="descripcionLugares">
										<li class="list-group-item"><c:out
												value="${venuesDetail.name}" /> <b>##</b> <c:out
												value="${venuesDetail.location.formattedAddress}" /> <b>##</b>
											<c:out value="${venuesDetail.rating}" /> <b>##</b> <c:out
												value="${venuesDetail.hours.isOpen}" /> <input
											type="hidden" name="colorRating" id="colorRating"
											value="<c:out value="${venuesDetail.rating}" />"> <input
											type="hidden" name="colorIsOpen" id="colorIsOpen"
											value="<c:out value="${venuesDetail.hours.isOpen}" />">
											<input type="submit" class="btn	btn-warning" name="searchBtn"
											title="search" value="Change lights"></li>
									</span> <br /> <br />


								</form>
							</c:forEach>

							<br />
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


	<script
		src="https://maps.googleapis.com/maps/api/js?key=XXX&libraries=places&callback=initMap"
		async defer></script>
	<footer>
		<div class="footer-content">Project for Ubiquitous Computing.
			Made with ❤️ from Konstanz, by Klara and Julián</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>