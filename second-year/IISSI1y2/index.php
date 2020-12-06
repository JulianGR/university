<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (!isset($_SESSION["nick"])) {
	$nick = "";
	$_SESSION["nick"] = $nick;

} else {
	$nick = $_SESSION["nick"];
}

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}
?>
<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>

		<script>
			$(document).ready(function() {
				validateIndex();
			});
		</script>

	</head>

	<body>
		<div class="container">
			<div class="row">

				<div class="col-sm">
					<h1 class="display-1 text-center"><strong><em>Los arboles</em></strong></h1>
				</div>
			</div>

			<div class="row">
				<div class="col">

					<div id="carouselIndicators" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
							<li data-target="#carouselIndicators" data-slide-to="1"></li>
							<li data-target="#carouselIndicators" data-slide-to="2"></li>
							<li data-target="#carouselIndicators" data-slide-to="3"></li>
							<li data-target="#carouselIndicators" data-slide-to="4"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img class="d-block w-100" src="./images/children-cute-drawing-159823.jpg?auto=yes&bg=777&fg=555&text=FirstSlide" alt="First slide">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="./images/celebration-children-daytime-1206101.jpg?auto=yes&bg=666&fg=444&text=SecondSlide" alt="Second slide">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="./images/boy-chair-children-1001914.jpg?auto=yes&bg=555&fg=333&text=ThirdSlide" alt="Third slide">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="./images/blur-child-classroom-256468.jpg?auto=yes&bg=666&fg=444&text=FourthSlide" alt="Fourth slide">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="./images/apple-blur-book-stack-256520.jpg?auto=yes&bg=666&fg=444&text=FifthSlide" alt="Fifth slide">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Anterior</span> </a>
						<a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span><span class="sr-only">Siguiente</span> </a>
					</div>
				</div>
			</div>
			<br>
			<br>

			<div class="row">

				<div class="col-sm">
					<h2 class="text-center"><strong>Qué es <em>Los arboles</em></strong></h2>
					<br>
					<p class="font-weight-light text-justify">

						 El grupo de desarrollo <em>GestionColegio</em> ha desarrollado una aplicación web dinámica con el objetivo de facilitar el contacto entre las familias y el profesorado. Gracias a esta aplicación web, los usuarios familia podrán ver los avisos que cuelguen los profesores, así como las calificaciones de sus hijos, también gestionadas por el profesorado.

					</p>
				</div>

				<div class="col-sm">

					<?php

					if (isset($_SESSION["noExiste"])) {
						echo "<div id=\"div_errores\" role=\"alert\" class=\"alert alert-danger\">";
						echo "Error en la contraseña o no existe el usuario";
						echo "</div>";
					}
					?>
					<br>
					<?php
					include_once ("reutilizables/errorFormulario.php");
					?>

					<br>
					<fieldset class="border p-2">

					<form id="loginForm" action="validacionIndex.php" method="post">
					<div class="form-group">
					<label for="nick">Nick: </label>
					<input  class="form-control" aria-describedby="ayudaNick" value="<?php echo $nick; ?>" placeholder="Introduce tu usuario" type="text" name="nick" id="nick" required/>
					<small id="ayudaNick" class="form-text text-muted">Introduce el usuario que se te facilitó al principio de curso</small>
					</div>

					<div class="form-group">
					<label for="contrasenya">Contraseña: </label>
					<input type="password" class="form-control" placeholder="Introduce tu contraseña" name="contrasenya" id="contrasenya" required/>
					</div>

					<label for="usuarioFamilia" id="perfil">Perfil: </label>

					<div class="form-check">
					<input class="form-check-input" id="usuarioFamilia" name="perfil" type="radio" value="usuarioFamilia" checked>
					<label class="form-check-label" for="usuarioFamilia"> Usuario Familia </label>
					</div>
					<div class="form-check">
					<input class="form-check-input" type="radio" name="perfil" id="usuarioProfesor" value="usuarioProfesor">
					<label class="form-check-label" for="usuarioProfesor"> Usuario Profesor </label>
					</div>
					<div class="form-check">
					<input class="form-check-input" type="radio" name="perfil" id="admin" value="admin">
					<label class="form-check-label" for="admin"> Admin </label>
					</div>
					<br>
					<button name="submit" type="submit" value="Adelante"  id="enviar" class="btn btn-primary">
					Adelante
					</button>
					</form>
					</fieldset>
					</div>

				</div>
			</div>

			<br>
			<br>
			<div>
				<?php
				include_once ("reutilizables/pie.php");
				?>
	</body>
</html>
