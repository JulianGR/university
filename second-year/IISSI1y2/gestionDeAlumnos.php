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

if (!isset($_SESSION["crear_al"])) {
	$crear_al['nombre_al'] = "";
	$crear_al['apellidos_al'] = "";
	$crear_al['fechaNacimiento'] = "";
	$crear_al['familia_al'] = "";

	$_SESSION["crear_al"] = $crear_al;

} else {
	$crear_al = $_SESSION["crear_al"];

}

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
$alumnos = listarAlumnosDeUnaClase($conexion, $claseSeleccionada);
$cuantosAlumnos = cuentaCuantosAlumnosDeUnaClase($conexion, $claseSeleccionada);
$cursoGrupo = obtenerCursoGrupoDeUnaClase($conexion, $claseSeleccionada);
$tutores = listarTutores($conexion);
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
			<h1 class="text-center">Gestión de alumnos de <strong><?php echo $cursoGrupo; ?></strong></h1>
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
				$_SESSION["crear_al"] = $crear_al;
			} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
				echo "<div class='alert alert-danger' role='alert'>El alumno no se ha podido crear</div>";
				unset($_SESSION["haSidoCreado"]);
				unset($_SESSION["haPasadoPorCrear"]);
			}
			//Mensaje de modificación: solo entra si se modifica bien
			else if (isset($_SESSION["haPasadoPorModificar"]) && isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-success' role='alert'>El alumno se ha modificado correctamente</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
				unset($_SESSION["crear_al"]);
				$crear_al['nombre_al'] = "";
				$crear_al['apellidos_al'] = "";
				$crear_al['fechaNacimiento'] = "";
				$crear_al['familia_al'] = "";
				$_SESSION["crear_al"] = $crear_al;
			}
			//Mensaje de borrado
			else if (isset($_SESSION["haPasadoPorBorrar"]) && isset($_SESSION["haSidoBorrado"])) {
				echo "<div class='alert alert-success' role='alert'>El alumno se ha borrado correctamente</div>";
				unset($_SESSION["haSidoBorrado"]);
				unset($_SESSION["haPasadoPorBorrar"]);
			} else if (isset($_SESSION["haPasadoPorBorrar"]) && !isset($_SESSION["haSidoBorrado"])) {
				echo "<div class='alert alert-danger' role='alert'>El alumno no se ha podido borrar</div>";
				unset($_SESSION["haSidoBorrado"]);
				unset($_SESSION["haPasadoPorBorrar"]);

			}
			?>
			</div>
			</div>
			<br>
			
			
			<div class="row">
				<div class="col-sm">
					<ul class="list-group">
						<?php 	
						
					
						
						if($cuantosAlumnos == 0){
							echo "<div class='alert alert-info' role='alert'>Esta clase no tiene alumnos asociados</div>";
						}else{
							
						foreach ($alumnos as $alumno) { ?>

						<li class="list-group-item">		

						<?php
						$oidAl = $alumno["OID_AL"];
						$nombreAl = $alumno["NOMBRE_AL"];
						$apellidosAl = $alumno["APELLIDOS_AL"];
						$fecha = $alumno["FECHANACIMIENTO"];
						$tutorAsoc = $alumno["OID_UF"];

						echo $nombreAl . " " . $apellidosAl;
						?>
			
		
			<div class="float-right">
						
				<form method="post" action="modificarAlumnos.php">							
	
						<input id="clase" name="clase" type="hidden" value="<?php echo $claseSeleccionada; ?>"/>
						<input id="OID_AL" name="OID_AL" type="hidden" value="<?php echo $oidAl; ?>"/>
						<input id="nombre_al" name="nombre_al" type="hidden" value="<?php echo $nombreAl; ?>"/>
						<input id="apellidos_al" name="apellidos_al" type="hidden" value="<?php echo $apellidosAl; ?>"/>
						<input id="fechaNacimiento" name="fechaNacimiento" type="hidden" value="<?php echo $fecha; ?>"/>
						<input id="tutor" name="tutor" type="hidden" value="<?php echo $tutorAsoc; ?>"/>
						
				
					<div class="form-group">		
							<button id="editar" name="editar" type="submit" class="btn btn-outline-primary nohover">
								<img src="./images/001-exchange.png" height="30px" width="30px">
							</button>
					</div>
				</form>	
			
			</div>
				
					
					 
			<div class="float-right">
				<form id="borrarAlumno" method="post" action="accion_borrar.php">				
						<input id="OID_CL" name="OID_CL" type="hidden" value="<?php echo $claseSeleccionada; ?>"/>
						<input id="OID_AL" name="OID_AL" type="hidden" value="<?php echo $oidAl; ?>"/>				
						<div class="form-group">	
						
						<button id="borrar" name="borrar" type="submit" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" class="btn btn-outline-primary nohover">															
							<img src="./images/002-cross.png" height="30px" width="30px" alt="Borrar alumno">
						</button>
					</div>
					</form>				
			</div>
				
	</li>
	<br>
<?php }} ?>

</ul>

<br>
<br>
</div>

<div class="col-sm">

<fieldset class="border">
					<legend class="w-auto">
						Crear alumno en <strong><?php echo $cursoGrupo; ?></strong>
					</legend>

					<form id="altaAlumno" method="post" action="validacionAlumno.php">

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
								foreach ($tutores as $tutor) {
									$oidTutor = $tutor["OID_UF"];
									$nombreApellidoTutor = $tutor["NOMBRE_UF"] . " " . $tutor["APELLIDOS_UF"];

									echo "<option value='$oidTutor' label='$nombreApellidoTutor'>$nombreApellidoTutor</option>";
								}
								?>
							</select>
						</div>

						<input name="clase" id="clase" type="hidden" value="<?php echo $claseSeleccionada; ?>"/>
						<input name="enEstaClase" id="enEstaClase" type="hidden" value="true"/>

						<br>

						<button type="submit" id="crearAlumno" class="btn btn-primary" value="Crear alumno">
							Crear alumno
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