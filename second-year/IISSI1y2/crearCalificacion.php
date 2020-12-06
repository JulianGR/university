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

if (isset($_POST["OID_AL"]) && isset($_POST["OID_AS"])) {
	$oidAs = $_POST["OID_AS"];
	$oidAl = $_POST["OID_AL"];

} else {
	if (isset($_SESSION["crearCalificacion"])) {

		$crearCalificacion = $_SESSION["crearCalificacion"];

		$oidAs = $crearCalificacion["OID_AS"];
		$oidAl = $crearCalificacion["OID_AL"];

		unset($_SESSION["crearCalificacion"]);

	} else {
		Header("Location:seleccionarAsignatura.php");

	}

}

if (!isset($_SESSION["crear_ca"])) {
	$crear_ca['VALOR_CA'] = "";
	$crear_ca['convocatoria'] = "";

	if (isset($_POST["OID_AL"]) && isset($_POST["OID_AS"])) {

		$crear_ca['OID_AL'] = $_POST["OID_AL"];
		$crear_ca['OID_AS'] = $_POST["OID_AS"];
	} else {
		$crearCalificacion = $_SESSION["crearCalificacion"];
		$crear_ca['OID_AL'] = $crearCalificacion["OID_AL"];
		$crear_ca['OID_AS'] = $crearCalificacion["OID_AS"];

	}

	$_SESSION["crear_ca"] = $crear_ca;

} else {
	$crear_ca = $_SESSION["crear_ca"];
}
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
$nombreAl = getNombreAlumno($conexion, $oidAl);
$nombreAs = consultarAsignatura($conexion, $oidAs);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
		
		<script>
			$(document).ready(function() {
				validateCreaCalificacion();
			});
		</script>
		
	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>

		

		<div class="row">
			<div class="col-sm">
			<h1>Gestión de las calificaciones de  <strong><?php echo $nombreAl; ?></strong>  de <strong><?php echo $nombreAs; ?></strong></h1>
					<br>	
						
			<?php
			include_once ("reutilizables/errorFormulario.php");
			?>

			<?php
			//Mensaje de creación
			if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
				echo "<div class='alert alert-success' role='alert'>La calificación se ha creado correctamente</div>";
				unset($_SESSION["haSidoCreado"]);
				unset($_SESSION["haPasadoPorCrear"]);
				unset($_SESSION["crear_ca"]);
				$crear_ca['VALOR_CA'] = "";
				$crear_ca['convocatoria'] = "";
				if (isset($_POST["OID_AL"]) && isset($_POST["OID_AS"])) {

					$crear_ca['OID_AL'] = $_POST["OID_AL"];
					$crear_ca['OID_AS'] = $_POST["OID_AS"];

				} else {
					$crearCalificacion = $_SESSION["crearCalificacion"];
					$crear_ca['OID_AL'] = $crearCalificacion["OID_AL"];
					$crear_ca['OID_AS'] = $crearCalificacion["OID_AS"];

				}

				$_SESSION["crear_ca"] = $crear_ca;

			} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
				echo "<div class='alert alert-danger' role='alert'>La calificación no se ha podido borrar</div>";
				unset($_SESSION["haSidoCreado"]);
				unset($_SESSION["haPasadoPorCrear"]);
			}
			?>
			</div>
			</div>
			<br>

<div class="row">
			<div class="col-sm">
		
		<fieldset class="border p-2">
			<legend class="w-auto">
				Crear calificacion
			</legend>
			<form id="crearCalificacion" method="post" action="validacionCalificacion.php">

				<div class="form-group">
					<label for="convocatoria">Convocatoria:</label>
					<input class="form-control" id="convocatoria" name="convocatoria" type="text" value="<?php echo $crear_ca['convocatoria']; ?>" required/>
				</div>

				<div class="form-group">
					<label for="VALOR_CA">Valor:</label>
					<input type="number" class="form-control" id="VALOR_CA" name="VALOR_CA"  max="10" min="0" value="<?php echo $crear_ca['VALOR_CA']; ?>" required/>
				</div>

				<input class="form-control" id="OID_AS" name="OID_AS" type="hidden" value="<?php echo $oidAs; ?>"/>

				<input class="form-control" id="OID_AL" name="OID_AL" type="hidden" value="<?php echo $oidAl; ?>"/>

				<br>

				<button type="submit" id="enviar" class="btn btn-primary" value="Crear calificacion">
					Crear calificación
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
