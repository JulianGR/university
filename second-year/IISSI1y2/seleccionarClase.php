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
	$clase["OID_CL"] = "";
	$_SESSION["clase"] = $clase;

} else {
	$clase = $_SESSION["clase"];
}

if (!isset($_SESSION["crear_al"])) {
	$crear_al['nombre_al'] = "";
	$crear_al['apellidos_al'] = "";
	$crear_al['fechaNacimiento'] = "";
	$crear_al['familia_al'] = "";
	$crear_al['clase_al'] = "";

	$_SESSION["crear_al"] = $crear_al;

} else {
	$crear_al = $_SESSION["crear_al"];
}
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
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
				<h1 class="text-center">Selecciona una clase o crea un alumno</h1>
				<br>
				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php
				//Mensaje de creación
				if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-success' role='alert'>El alumno se ha creado correctamente</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
					unset($_SESSION["crear_al"]);
					$crear_al['nombre_al'] = "";
					$crear_al['apellidos_al'] = "";
					$crear_al['fechaNacimiento'] = "";
					$crear_al['familia_al'] = "";
					$crear_al['clase_al'] = "";
					$_SESSION["crear_al"] = $crear_al;
				} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-danger' role='alert'>El alumno no se ha podido crear</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
				}
				?>
			</div>
		</div>
		<br>
		<div class="row">

			<!-- columna de la izquierda-->
			<div class="col-sm">
				<fieldset class="border">
					<legend class="w-auto">
						Selecciona una clase
					</legend>
					<form id="seleccionarClaseForm" method="post" action="validacionSeleccionarClase.php">

						<div class="form-group">
							<label for="clase">Clase:</label>
							<select class="custom-select" name="clase" id="clase" required>
								<?php

								foreach ($clases as $clase) {
									$oidClase = $clase["OID_CL"];

									$cursoGrupo = $clase["CURSO"] . $clase["GRUPO"];

									if ($clase["OID_CL"] == $clase) {
										echo "<option value='$oidClase' label='$cursoGrupo' selected/>$cursoGrupo</option>";
									} else {

										echo "<option value='$oidClase' label='$cursoGrupo'/>$cursoGrupo</option>";
									}
								}
								?>
							</select>
						</div>
						<br>

						<button type="submit" id="seleccionarClase" class="btn btn-primary" value="Seleccionar Clase">
							Seleccionar Clase
						</button>
					</form>
				</fieldset>
				<br>

			</div>

			<!-- columna de la derecha-->
			<div class="col-sm">
				<fieldset class="border">
					<legend class="w-auto">
						Crear un alumno
					</legend>

					<form id="crearAlumnoForm" method="post" action="validacionAlumno.php">

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
							<input class="form-control" id="fechaNacimiento" name="fechaNacimiento" type="date" max="date-local" placeholder="dd/mm/yyyy" value="<?php echo $crear_al['fechaNacimiento']; ?>" required/>

						</div>

						<div class="form-group">
							<label for="tutor">Tutor</label>
							<select class="custom-select" name="tutor" id="tutor" required>
								<?php
								$tutores = listarTutores($conexion);

								foreach ($tutores as $tutor) {
									$oidTutor = $tutor["OID_UF"];
									$nombreApellidoTutor = $tutor["NOMBRE_UF"] . " " . $tutor["APELLIDOS_UF"];

									echo "<option value='$oidTutor' label='$nombreApellidoTutor'>$nombreApellidoTutor</option>";
								}
								?>
							</select>
						</div>

						<div class="form-group">
							<label for="clase">Clase</label>
							<select class="custom-select" name="clase" id="clase" required>
								<?php
								$clases = listarClases($conexion);

								foreach ($clases as $clase) {
									$oidClase = $clase["OID_CL"];
									$cursoGrupo = $clase["CURSO"] . " " . $clase["GRUPO"];
									echo "<option value='$oidClase' label='$cursoGrupo'>$cursoGrupo</option>";
								}
								?>
							</select>
						</div>

						<button type="submit" id="crearAlumno" class="btn btn-primary" value="Crear alumno">
							Crear alumno
						</button>
					</form>
				</fieldset>

			</div>

		</div>

		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
		?>
	</body>
</html>