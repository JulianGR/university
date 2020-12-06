<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["clase_a_borrar"])) {

	$seleccionarClaseABorrar = $_POST["clase_a_borrar"];

	$_SESSION["clase_a_borrar"] = $seleccionarClaseABorrar;

} else {
	Header("Location: gestionDeClases.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarClaseABorrar);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeClases.php');
} else {
	Header('Location: accion_borrar.php');
}

function validarDatos($conexion, $seleccionarClaseABorrar) {
	$errores = array();

	if (!isset($seleccionarClaseABorrar) || trim($seleccionarClaseABorrar) == "" || $seleccionarClaseABorrar == null || strlen($seleccionarClaseABorrar) == 0 || !is_numeric($seleccionarClaseABorrar)) {
		$errores[] = "<p>LEl profesor a borrar no puede estar vacía</p>";
	}

	return $errores;
}
?>

