<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "usuarioProfesor")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");
}

if (!isset($_SESSION["clase"])) {
	Header("Location:seleccionarClase.php");

} else {
	$claseSeleccionada = $_SESSION["clase"];
	$_SESSION["clase"] = $claseSeleccionada;
}

if ($_POST["OID_AL"]) {

	$crear_al["OID_AL"] = $_POST["OID_AL"];
	$crear_al["nombre_al"] = $_POST["nombre_al"];
	$crear_al["apellidos_al"] = $_POST["apellidos_al"];
	$crear_al["fechaNacimiento"] = $_POST["fechaNacimiento"];
	$crear_al["tutor"] = $_POST["tutor"];
	$crear_al["clase"] = $_POST["clase"];

	$_SESSION["crear_al"] = $crear_al;

} else {
	$crear_al = $_SESSION["crear_al"];
}

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();

$tutores = listarTutores($conexion);
$clases = listarClases($conexion);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>

		<script>
			$(document).ready(function() {
				validacionGestionarAlumnos();
			});
		</script>

	</head>

	<body>
		<?php

		include_once ("reutilizables/cabeceraAdmin.php");
		?>

		<div class="row">
			<div class="col-sm">

				<h1 class="text-center">Modificando alumnos</h1>

				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php

				//Mensaje de modificación: aquí solo entra si da fallo
				if (isset($_SESSION["haPasadoPorModificar"]) && !isset($_SESSION["haSidoModificado"])) {
					echo "<div class='alert alert-danger' role='alert'>El alumno no se ha podido modificar</div>";
					unset($_SESSION["haSidoModificado"]);
					unset($_SESSION["haPasadoPorModificar"]);
				}
				?>
			</div>
		</div>

		<div class="row">
			<div class="col-sm">

				<fieldset class="border">
					<legend class="w-auto">
						Modificar Alumno
					</legend>
					<form id="modificarAlumnoForm" method="post" action="validacionAlumno.php">
						<input id="OID_AL" name="OID_AL" type="hidden" value="<?php echo $crear_al['OID_AL']; ?>"/>

						<div class="form-group">
							<label for="nombre_al">Nombre</label>
							<input class="form-control" id="nombre_al" name="nombre_al" type="text" value="<?php echo $crear_al['nombre_al']; ?>" required/>
						</div>

						<div class="form-group">
							<label for="apellidos_al">Apellidos</label>
							<input class="form-control" id="apellidos_al" name="apellidos_al" type="text" value="<?php echo $crear_al['apellidos_al']; ?>" required/>
						</div>

						<div class="form-group">
							<label for="fechaNacimiento">Fecha Nacimiento</label>
							<input class="form-control" id="fechaNacimiento" name="fechaNacimiento" type="date" max="date-local" placeholder="dd/mm/yyyy" value="" required/>
						</div>

						<?php

						if (isset($_POST["fechaNacimiento"])) {
							$fecha = $crear_al["fechaNacimiento"];
							$dia = substr($fecha, 0, 2);
							$mes = substr($fecha, 3, 2);
							$anyo = substr($fecha, 6, 2);

						} else {
							$fecha = $crear_al["fechaNacimiento"];

							$dia = substr($fecha, 8, 2);
							$mes = substr($fecha, 5, 2);
							$anyo = substr($fecha, 0, 4);

						}

						//esto es para rellenar la fecha en el formulario
						//http://jsfiddle.net/GZ46K/
						//las fechas son un lio gordo: si el dia es el 1, esto peta y si el mes es el 12, tambien

						$dia = $dia + 1;
						$mes = $mes - 1;
						if ($anyo >= 50) {
							$anyo = "19" . $anyo;
						} else {
							$anyo = "20" . $anyo;
						}
						?>

						<script>
							(function() {
var date = new Date(<?php echo $anyo; ?>,<?php echo $mes; ?>,<?php echo $dia ?>
	).
	toISOString().substring(0, 10),
	field = document.querySelector('#fechaNacimiento');
	field.value = date;

	})()
						</script>

						<div class="form-group">
							<label for="tutor">Tutor</label>
							<select class="custom-select" name="tutor" id="tutor" required>
								<?php

								foreach ($tutores as $tutor) {
									$oidTutor = $tutor["OID_UF"];
									$nombreApellidoTutor = $tutor["NOMBRE_UF"] . " " . $tutor["APELLIDOS_UF"];

									if ($crear_al["tutor"] == $oidTutor) {
										echo "<option value='$oidTutor' label='$nombreApellidoTutor' selected>$nombreApellidoTutor</option>";
									} else {
										echo "<option value='$oidTutor' label='$nombreApellidoTutor'>$nombreApellidoTutor</option>";
									}

								}
								?>
							</select>
						</div>

						<div class="form-group">
							<label for="clase">Clase</label>
							<select class="custom-select" name="clase" id="clase" required>
								<?php

								foreach ($clases as $clase) {
									$oidClase = $clase["OID_CL"];
									$cursoGrupo = $clase["CURSO"] . " " . $clase["GRUPO"];

									if ($crear_al["clase"] == $oidClase) {
										echo "<option value='$oidClase' label='$cursoGrupo' selected>$cursoGrupo</option>";
									} else {
										echo "<option value='$oidClase' label='$cursoGrupo'>$cursoGrupo</option>";
									}

								}
								?>
							</select>
						</div>

						<input name="modificar" id="modificar" type="hidden" value="true"/>
						<button type="submit" id="modificarAlumnos" class="btn btn-primary" value="Modificar alumno">
							Modificar alumno
						</button>
					</form>
				</fieldset>
			</div>

		</div>
		<br>
		<br>

		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
		?>
	</body>

</html>