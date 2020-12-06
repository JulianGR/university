<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["familia_a_borrar"])) {

	$seleccionarFamiliaABorrar = $_POST["familia_a_borrar"];

	$_SESSION["familia_a_borrar"] = $seleccionarFamiliaABorrar;

} else {
	Header("Location: gestionDeUsuarioFamilia.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarFamiliaABorrar);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeUsuarioFamilia.php');
} else {
	Header('Location: accion_borrar.php');
}

function validarDatos($conexion, $seleccionarFamiliaABorrar) {
	$errores = array();

	if (!isset($seleccionarFamiliaABorrar) || trim($seleccionarFamiliaABorrar) == "" || $seleccionarFamiliaABorrar == null || strlen($seleccionarFamiliaABorrar) == 0 || !is_numeric($seleccionarFamiliaABorrar)) {
		$errores[] = "<p>La familia a borrar no puede estar vacía</p>";
	}

	return $errores;
}
?>

