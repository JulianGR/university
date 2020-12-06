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

<body id="bodyDelInicio">
	<div>
		<br> <br>
		<h1 class="titulo">HueKJ</h1>
	</div>
	<div class="container">




		<div class="row">
			<div class="col-sm">
				<br> <br> <br>
				<h2>General operations with bulbs:</h2>

				<form action='/BulbsController' method='post'>
					<br>
					<h4>Choose the bulb:</h4>
					<input class="form-check-input" type="radio" name="bulb" id="bulb1"
						value="bulb1" required> <span class="checkmark"></span> <label
						class="form-check-label" for="bulb1">Bulb 1</label><br> <input
						class="form-check-input" type="radio" name="bulb" id="bulb2"
						value="bulb2" required> <label class="form-check-label"
						for="bulb2">Bulb 2</label><br> <br>
					<h4>Choose the color:</h4>
					<input class="form-check-input" type="radio" name="color" id="blue"
						value="blue"> <label class="form-check-label" for="blue">Blue</label><br>
					<input class="form-check-input" type="radio" name="color"
						id="green" value="green"> <label class="form-check-label"
						for="green">Green</label><br> <input class="form-check-input"
						type="radio" name="color" id="red" value="red"> <label
						class="form-check-label" for="red">Red</label><br> <input
						class="form-check-input" type="radio" name="color" id="orange"
						value="orange"> <label class="form-check-label"
						for="orange">Orange</label><br> <input
						class="form-check-input" type="radio" name="color" id="yellow"
						value="yellow"> <label class="form-check-label"
						for="yellow">Yellow</label><br> <input
						class="form-check-input" type="radio" name="color" id="purple"
						value="purple"> <label class="form-check-label"
						for="purple">Purple</label><br> <br>
					<h4>Turn the bulbs off:</h4>
					<input class="form-check-input" type="radio" name="color" id="off"
						value="off" required> <label class="form-check-label"
						for="off">OFF</label> <input type="submit" class="btn btn-warning"
						name="searchBtn" title="search" value="Submit">

				</form>

				<div>
					<br>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm">
				<div id='searchDiv'>
					<br>
					<h2 align:center>Choose the city:</h2>
					<br> <br> <br>
					<form class='barraBusqueda' action='/MapasController' method='post'>
						<input id="searchMapInput" name="searchQuery" class="mapControls"
							type="text" placeholder="Introduce a place" required> <input
							id="city" name="locality" type="hidden"> <input
							id="country" name="country" type="hidden"> <input
							id="countryShort" name="countryShort" type="hidden"> <input
							id="latitude" name="latitude" type="hidden"> <input
							id="longitude" name="longitude" type="hidden"> <input
							type="submit" class="btn btn-warning" name="searchBtn"
							title="search" value="Submit">
					</form>
					<br> <br> <br> <br> <br> <br> <br>
					<br> <br> <br> <br> <br> <br> <br>
					<br> <br> <br> <br> <br> <br> <br>
				</div>

				<script>
					var autocomplete;
					function initMap() {
						var input = document.getElementById('searchMapInput');
						var options = {
							types : [ '(regions)' ],
						};
						autocomplete = new google.maps.places.Autocomplete(
								input, options);
						autocomplete.setFields([ 'address_component' ]);
						autocomplete
								.addListener('place_changed', fillInAddress);
					}
					function fillInAddress() {
						var place = autocomplete.getPlace();
						var town = extractFromAdress(place.address_components,
								"locality");
						var country = extractFromAdress(
								place.address_components, "country");
						var countryShort = extractFromAdress2(
								place.address_components, "country");

						document.getElementById('city').value = town;
						document.getElementById('country').value = country;
						document.getElementById('countryShort').value = countryShort;

					}
					function extractFromAdress(components, type) {
						for (var i = 0; i < components.length; i++)
							for (var j = 0; j < components[i].types.length; j++)
								if (components[i].types[j] == type)
									return components[i].long_name;
						return "";
					}

					function extractFromAdress2(components, type) {
						for (var i = 0; i < components.length; i++)
							for (var j = 0; j < components[i].types.length; j++)
								if (components[i].types[j] == type)
									return components[i].short_name;
						return "";
					}
				</script>
				<script
					src="https://maps.googleapis.com/maps/api/js?key=XXX&libraries=places&callback=initMap"
					async defer></script>
			</div>
		</div>


	</div>

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


	<video id='video' autoplay='autoplay' muted loop>
		<source src="./videos/bgVideo.mp4" /></source>
	</video>
</body>
</html>