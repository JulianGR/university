<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["asignatura"])) {

	$seleccionarAsignatura = $_POST["asignatura"];

	$_SESSION["asignatura"] = $seleccionarAsignatura;

} else {
	Header("Location: seleccionarAsignatura.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarAsignatura);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: seleccionarAsignatura.php');
} else {
	Header('Location: gestionDeCalificaciones.php');
}

function validarDatos($conexion, $seleccionarAsignatura) {
	$errores = array();

	if (!isset($seleccionarAsignatura) || trim($seleccionarAsignatura) == "" || $seleccionarAsignatura == null || strlen($seleccionarAsignatura) == 0 || !is_numeric($seleccionarAsignatura)) {
		$errores[] = "<p>La asignatura no puede estar vacía</p>";
	}

	return $errores;
}
?>

