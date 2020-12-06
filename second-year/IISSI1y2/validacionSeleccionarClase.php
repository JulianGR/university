<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["clase"])) {

	$login = $_SESSION["login"];
	$perfil = $_SESSION["perfil"];
	$seleccionarClase = $_POST["clase"];

	session_unset();

	$_SESSION["login"] = $login;
	$_SESSION["perfil"] = $perfil;
	$_SESSION["clase"] = $seleccionarClase;

} else {
	Header("Location: seleccionarClase.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarClase);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: seleccionarClase.php');
} else {
	Header('Location: gestionDeAlumnos.php');
}

function validarDatos($conexion, $seleccionarClase) {
	$errores = array();

	if (!isset($seleccionarClase) || trim($seleccionarClase) == "" || $seleccionarClase == null || strlen($seleccionarClase) == 0 || !is_numeric($seleccionarClase)) {
		$errores[] = "<p>La clase no puede estar vacía</p>";
	}

	return $errores;
}
?>

