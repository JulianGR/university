<?php
session_start();

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "usuarioProfesor")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");

}
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
	</head>

	<body>
		<div class="container">

			<img class="fotoPrincipal" src="./images/logoPNG.png" height="20%" width="20%" alt="Losarboles">
			<h3> Bienvenido Admin. ¿Qué quieres hacer? </h3>

			<br>
			<br>
			<div class="row">
				<div class="col-sm-3">
					<div class="card border-primary mb-3 gestionesAdmin">
						<img class="card-img-top" src="./images/003-cap.png">
						<div class="card-body">
							<a class="btn btn-primary" href="irASeleccionarClase.php">Gestión de Alumnos</a>
						</div>
					</div>
				</div>
				<br>

				<div class="col-sm-3">
					<div class="card border-primary mb-3 gestionesAdmin" >
						<img class="card-img-top" src="./images/004-family-room.png">
						<div class="card-body">
							<a class="btn btn-primary" href="irAGestionDeUsuarioFamilia.php">Gestión de Usuarios Familia</a>
						</div>
					</div>
				</div>
				<br>

				<div class="col-sm-3">
					<div class="card border-primary mb-3 gestionesAdmin" >
						<img class="card-img-top" src="./images/001-professor.png">
						<div class="card-body">
							<a class="btn btn-primary" href="irAGestionDeUsuarioProfesor.php">Gestión de Usuarios Profesor</a>
						</div>
					</div>
				</div>
				<br>

				<div class="col-sm-3">
					<div class="card border-primary mb-3 gestionesAdmin" >
						<img class="card-img-top" src="./images/002-education.png">
						<div class="card-body">
							<a class="btn btn-primary" href="irAGestionDeClases.php">Gestión de Clases</a>
						</div>
					</div>
				</div>
				<br>

			</div>
			<?php

			include_once ("reutilizables/pie.php");
			?>
	</body>

</html>