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
if (!isset($_SESSION["crear_up"])) {
	$crear_up['nick_up'] = "";
	$crear_up['pass'] = "";
	$crear_up['confirmpass'] = "";
	$crear_up['nombre_up'] = "";
	$crear_up['apellidos_up'] = "";
	$crear_up['email_up'] = "";
	$crear_up['foto_up'] = "";

	$_SESSION["crear_up"] = $crear_up;
} else {
	$crear_up = $_SESSION["crear_up"];
}

$_SESSION["haPasadoPorGestion"] = true;

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
	</head>

	<body>

		<script>
			$(document).ready(function() {
				validateBorraUsuarioProfesor();
				validateModificarUsuarioProfesor();
				validateUsuarioProfesor();
				passwordValidation();
				passwordConfirmation();
			});
		</script>

		<?php
		include_once ("reutilizables/cabeceraAdmin.php");
		?>

		<div class="row">
			<div class="col-sm">
				<h1 class="text-center">Gestión de usuarios profesor</h1>
				<br>

				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php
				//Mensaje de creación
				if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-success' role='alert'>El usuario profesor se ha creado correctamente</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
					unset($_SESSION["crear_up"]);
					$crear_up['nick_up'] = "";
					$crear_up['pass'] = "";
					$crear_up['confirmpass'] = "";
					$crear_up['nombre_up'] = "";
					$crear_up['apellidos_up'] = "";
					$crear_up['email_up'] = "";
					$crear_up['foto_up'] = "";

					$_SESSION["crear_up"] = $crear_up;
				} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-danger' role='alert'>El usuario profesor no se ha podido crear. Le recordamos que no puede repetir ni el nick ni el email</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
				}

				//Mensaje de borrado
				else if (isset($_SESSION["haPasadoPorBorrar"]) && isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-success' role='alert'>El usuario profesor se ha borrado correctamente</div>";
					unset($_SESSION["haSidoBorrado"]);
					unset($_SESSION["haPasadoPorBorrar"]);
				} else if (isset($_SESSION["haPasadoPorBorrar"]) && !isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-danger' role='alert'>El usuario profesor no se ha podido borrar. Le recordamos que no puede borrar profesores con avisos, asignaturas o clases asignadas</div>";
					unset($_SESSION["haSidoBorrado"]);
					unset($_SESSION["haPasadoPorBorrar"]);

				}
				?>
			</div>
		</div>

		<div class="row">
			<div class="col-sm">

				<fieldset class="border p-2">
					<legend class="w-auto">
						Crear Usuario Profesor
					</legend>

					<form id="profeAForm" method="post" action="validacionUsuarioProfesor.php">

						<div class="form-group">
							<label for="nick_up">Nick:</label>
							<input class="form-control" id="nick_up" name="nick_up" type="text" value="<?php echo $crear_up['nick_up']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="pass">Contraseña:</label>
							<input id="pass" name="pass" type="password" class="form-control" oninput="passwordValidation();" value="<?php echo $crear_up['pass']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="confirmpass">Confirmar Contraseña: </label>
							<input type="password" name="confirmpass" id="confirmpass" class="form-control" oninput="passwordConfirmation();" required/>
						</div>

						<div class="form-group">
							<label for="nombre_up">Nombre:</label>
							<input id="nombre_up" name="nombre_up" type="text" class="form-control"  value="<?php echo $crear_up['nombre_up']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="apellidos_up">Apellidos:</label>
							<input id="apellidos_up" name="apellidos_up" type="text" class="form-control"  value="<?php echo $crear_up['apellidos_up']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="email_up">Email:</label>
							<input id="email_up" name="email_up" type="email" class="form-control"  value="<?php echo $crear_up['email_up']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="foto_up">URL Foto Perfil:</label>
							<input class="form-control"  id="foto_up" name="foto_up" type="text" value="" />
							<br>
						</div>

						<button type="submit" class="btn btn-primary"  id="profeA" value="Crear profesor">
							Crear usuario profesor
						</button>
					</form>
				</fieldset>
			</div>
			<br>
			<br>

			<div class="col-sm">

				<fieldset class="border p-2">
					<legend class="w-auto">
						Modificar usuario profesor
					</legend>

					<form id="modificarProfesorForm" method="post" action="validacionModificarUsuarioProfesor.php">

						<div class="form-group">
							<label for="profesor">Profesor:</label>
							<select class="custom-select" name="profesor" id="profesor" required>
								<?php
								$profesores = listarProfesores($conexion);
								foreach ($profesores as $profesor) {
									$oidProfesor = $profesor["OID_UP"];
									$nombreApellidosProfesor = $profesor["NOMBRE_UP"] . " " . $profesor["APELLIDOS_UP"];

									echo "<option value='$oidProfesor' label='$nombreApellidosProfesor'/>$nombreApellidosProfesor</option>";
								}
								?>
							</select>
						</div>

						<button type="submit"  id="modificarProfesor" class="btn btn-primary" value="Modificar Profesor">
							Modificar Profesor
						</button>
					</form>
				</fieldset>

				<br>
				<br>

				<fieldset class="border p-2">
					<legend class="w-auto">
						Borrar Profesor
					</legend>

					<form id="borrarProfesorForm" method="post" action="validacionBorrarUsuarioProfesor.php">
						<?php
$profesoresCon0AvisosYCon0ClasesYCon0Asignaturas = cuentaCuantosProfesoresHayCon0AvisosYCon0AsignaturasYCon0Clases($conexion);

if ($profesoresCon0AvisosYCon0ClasesYCon0Asignaturas==0){
echo "<div class='alert alert-info' role='alert'>No hay profesores que se puedan borrar puesto que todos tienen asignaturas o clases o avisos asociadas</div>";
}else{

$profesoresCon0AvisosYCon0ClasesYCon0Asignaturas = queCuantosProfesoresHayCon0AvisosYCon0AsignaturasYCon0Clases($conexion);

						?>
						<div class="form-group">
							<p>
								<em>Le recordamos que, puesto que solo se pueden borrar profesores sin alumnos asignados, solo aparecerán los profesores que tienen 0 avisos, 0 asignaturas y 0 clases asignadas</em>

							</p>
						</div>
						<div class="form-group">
							<label for="profesor">Profesor:</label>
							<select class="custom-select" name="profesor" id="profesor" required>
								<?php

								foreach ($profesoresCon0AvisosYCon0ClasesYCon0Asignaturas as $profesor) {
									$oidProfesor = $profesor["OID_UP"];
									$nombreApellidosProfesor = obtenerNombreApellidosDeUnProfesor($conexion, $oidProfesor);

									echo "<option value='$oidProfesor' label='$nombreApellidosProfesor'/>$nombreApellidosProfesor</option>";
								}
								?>
							</select>
						</div>

						<button type="submit" id="borrarProfesor" class="btn btn-primary" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" value="Borrar Profesor">
							Borrar Profesor
						</button>
						<?php } ?>
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
