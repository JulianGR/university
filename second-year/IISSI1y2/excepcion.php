<?php
session_start();

if (!isset($_SESSION["excepcion"])) {
	Header("Location:index.php");
} else {
	$excepcion = $_SESSION["excepcion"];
	unset($_SESSION["excepcion"]);
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
			<h1 class="titulo">¡Ups! ¡Ocurrió un problema con respecto a la base de datos!</h1>

			<div class="row">
				<div class="col-sm">
					<p>
						No se preocupe. Si el problema persiste, contacte con la directora y dele esta información:
					</p>

					<ul class="list-group">

						<p>

							<?php

							$inicio = strpos($excepcion, "ORA-");

							// echo "<li class=\"list-group-item\">Código de error SQL:  " . substr($excepcion, $inicio, 9) . "</li>";

							echo "<li class=\"list-group-item\">";
							if (strpos($excepcion, "ORA-00942")) {
								echo "El problema está relacionado con un error en las tablas de la base de datos.";
							} elseif (strpos($excepcion, "ORA-24415")) {
								echo "El problema está relacionado con un error en los usuarios de la base de datos.";
							} elseif (strpos($excepcion, "ORA-00600")) {
								echo "El problema está relacionado con un error interno la base de datos.";
							} elseif (strpos($excepcion, "ORA-01017")) {
								echo "El problema está relacionado con una mala combinación de usuario y contraseña para acceder a la base de datos.";
							} elseif (strpos($excepcion, "ORA-01407")) {
								echo "El problema está relacionado con intentar eliminar alguna entidad que tenía asociadas otras entidades.";
							} elseif (strpos($excepcion, "ORA-02291")) {
								echo "El problema está relacionado con operaciones sobre entidades que no existen en la base de datos.";
							} elseif (strpos($excepcion, "ORA-02290")) {
								echo "El problema está relacionado con valores fuera de sus limites.";
							} elseif (strpos($excepcion, "ORA-00001")) {
								echo "El problema está relacionado con entidades únicas en la BD que tiene valores repetidos.";
							} else {
								echo "El error fue inesperado.";
							}
							echo "</li>";
							?>
						</p>
						<!--
						<div class='excepcion'>
						<?php echo "Información relativa al problema: $excepcion;"
						?>
						</div> -->

						<p>
							Pulse <a href="logout.php">aquí</a> para volver a la página principal.
						</p>
				</div>
			</div>

			<?php
			include_once ("reutilizables/pie.php");
			?>
	</body>
</html>