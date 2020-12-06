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
if (!isset($_SESSION["crear_cl"])) {
	$crear_cl['curso_cl'] = "";
	$crear_cl['grupo_cl'] = "";
	$crear_cl['profesor_tutor'] = "";

	$_SESSION["crear_cl"] = $crear_cl;

} else {
	$crear_cl = $_SESSION["crear_cl"];
}
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

		<script>
			$(document).ready(function() {
				validateCreaClase();
				validateBorraClase();
			});
		</script>

	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraAdmin.php");
		?>
		<div class="row">
			<div class="col-sm">
				<h1 class="text-center">Gestión de clases</h1>
				<br>

				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php
				//Mensaje de creación
				if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-success' role='alert'>La clase se ha creado correctamente</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
					unset($_SESSION["crear_cl"]);
					$crear_cl['curso_cl'] = "";
					$crear_cl['grupo_cl'] = "";
					$crear_cl['profesor_tutor'] = "";

					$_SESSION["crear_cl"] = $crear_cl;
				} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-danger' role='alert'>La clase no se ha podido crear</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
				}
				//Mensaje de modificación
				else if (isset($_SESSION["haPasadoPorModificar"]) && isset($_SESSION["haSidoModificado"])) {
					echo "<div class='alert alert-success' role='alert'>La clase se ha modificado correctamente</div>";
					unset($_SESSION["haSidoModificado"]);
					unset($_SESSION["haPasadoPorModificar"]);
					unset($_SESSION["crear_cl"]);
					$crear_cl['curso_cl'] = "";
					$crear_cl['grupo_cl'] = "";
					$crear_cl['profesor_tutor'] = "";

					$_SESSION["crear_cl"] = $crear_cl;
				} else if (isset($_SESSION["haPasadoPorModificar"]) && !isset($_SESSION["haSidoModificado"])) {
					echo "<div class='alert alert-danger' role='alert'>La clase no se ha podido modificar</div>";
					unset($_SESSION["haSidoModificado"]);
					unset($_SESSION["haPasadoPorModificar"]);
				}
				//Mensaje de borrado
				else if (isset($_SESSION["haPasadoPorBorrar"]) && isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-success' role='alert'>La clase se ha borrado correctamente</div>";
					unset($_SESSION["haSidoBorrado"]);
					unset($_SESSION["haPasadoPorBorrar"]);
				} else if (isset($_SESSION["haPasadoPorBorrar"]) && !isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-danger' role='alert'>La clase no se ha podido borrar. Le recordamos que no puede borrar clases con alumnos asignados</div>";
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
						Crear Clase
					</legend>
					<form id="crearClase" method="post" action="validacionClase.php">

						<div class="form-group">
							<label for="curso_cl">Curso:</label>
							<input id="curso_cl" name="curso_cl" type="number" class="numbersPeque" min="1" max="6" required/>
						</div>

						<div class="form-group">
							<label for="grupo_cl">Grupo:</label>
							<input  class="form-control" id="grupo_cl" name="grupo_cl" type="text" required/>
						</div>

						<div class="form-group">
							<label for="profesor_tutor">Profesor Tutor:</label>
							<select class="custom-select" name="profesor_tutor" required id="profesor_tutor" >
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

						<button type="submit" id="crearClaseForm" class="btn btn-primary" value="Crear Clase">
							Crear Clase
						</button>
					</form>
				</fieldset>
			</div>

			<div class="col-sm">
				<fieldset class="border p-2">
					<legend class="w-auto">
						Borrar Clase
					</legend>
					<form id="borrarClase" method="post" action="validacionBorrarClase.php">

						<?php
$clasesSinAlumnosNiAsignaturas = cuentaCuantasClasesHayCon0AlumnosYCon0Asignaturas($conexion);

if ($clasesSinAlumnosNiAsignaturas==0){
echo "<div class='alert alert-info' role='alert'>No hay clases que se puedan borrar puesto que todas tienen alumnos asociados o asignaturas</div>";
}else {
$clasesSinAlumnosNiAsignaturas = queClasesHayCon0AlumnosYCon0Asignaturas($conexion);

						?>

						<div class="form-group">
							<p>
								<em>Le recordamos que, puesto que solo se pueden borrar clases sin alumnos asignados <strong>y</strong> sin asignaturas asociadas, solo aparecerán las clases que tienen 0 alumnos <strong>y</strong> 0 asignaturas</em>

							</p>
						</div>
						<div class="form-group">
							<label for="clase_a_borrar">Clase:</label>
							<select class="custom-select" name="clase_a_borrar" required id="clase_a_borrar" >
								<?php
								foreach ($clasesSinAlumnosNiAsignaturas as $clase) {

									$oidClase = $clase["OID_CL"];
									$cursoGrupo = obtenerCursoGrupoDeUnaClase($conexion, $oidClase);

									echo "<option value='$oidClase' label='$cursoGrupo'>$cursoGrupo</option>";

								}
								?>
							</select>
						</div>

						<button type="submit" id="enviarBorrarClase" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" class="btn btn-primary" value="Borrar clase">
							Borrar clase
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
