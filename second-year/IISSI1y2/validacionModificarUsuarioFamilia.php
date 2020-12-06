<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["familia"])) {

	$seleccionarFamiliaAModificar = $_POST["familia"];

	$_SESSION["familia"] = $seleccionarFamiliaAModificar;

} else {
	Header("Location: gestionDeUsuarioFamilia.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarFamiliaAModificar);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeUsuarioFamilia.php');
} else {
	Header('Location: modificarUsuarioFamilia.php');
}

function validarDatos($conexion, $seleccionarFamiliaAModificar) {
	$errores = array();

	if (!isset($seleccionarFamiliaAModificar) || trim($seleccionarFamiliaAModificar) == "" || $seleccionarFamiliaAModificar == null || strlen($seleccionarFamiliaAModificar) == 0 || !is_numeric($seleccionarFamiliaAModificar))  {
		$errores[] = "<p>La familia a borrar no puede estar vacía</p>";
	}

	return $errores;
}
?>

