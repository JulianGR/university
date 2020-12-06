<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "admin")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");
}
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
$asignaturas = consultarAsignaturasProfesor($conexion);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>

		<script>
			$(document).ready(function() {
				validateSeleccionarAsignatura();
			});
		</script>

	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>

		<div class="row">
			<div class="col-sm">
				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<fieldset class="border">
					<form method="post" action="validacionSeleccionarAsignatura.php">

						<div class="form-group">
							<label for="asignatura">Asignatura:</label>
							<select class="custom-select" required name="asignatura" id="asignatura">
								<?php

								foreach ($asignaturas as $asignatura) {
									$oidAsig = $asignatura["OID_AS"];

									$nombreAs = $asignatura["NOMBRE_AS"];

									if ($asignatura["OID_AS"] == $oidAsig) {
										echo "<option value='$oidAsig' label='$nombreAs' selected/>$nombreAs</option>";
									} else {

										echo "<option value='$oidAsig' label='$nombreAs'/>$nombreAs</option>";
									}
								}
								?>
							</select>
						</div>
						<br>

						<button type="submit" id="seleccionaAsig" class="btn btn-primary" value="Seleccionar asignatura">
							Seleccionar Asignatura
						</button>
					</form>
				</fieldset>
				<br>
				<br>
				<br>
				<br>
			</div>

		</div>

		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
		?>
	</body>
</html>