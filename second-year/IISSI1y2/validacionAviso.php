<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_av"])) {

	$crearAviso["titulo_av"] = $_POST["titulo_av"];
	$crearAviso["descripcion_av"] = $_POST["descripcion_av"];
	$crearAviso["fechaSuceso"] = $_POST["fechaSuceso"];
	$crearAviso["profesor_av"] = $_POST["profesor_av"];

	if (trim($_POST["archivoURL_av"]) == "" || $_POST["archivoURL_av"] == null || strlen($_POST["archivoURL_av"]) == 0) {
		$crearAviso["archivoURL_av"] = "URLDePrueba";
	} else {
		$crearAviso["archivoURL_av"] = $_POST["archivoURL_av"];
	}

	$_SESSION["crear_av"] = $crearAviso;

} else {
	Header("Location: crearAviso.php");
}

$conexion = crearConexionBD();
$errores = validarDatosAviso($conexion, $crearAviso);
cerrarConexionBD($conexion);

if ((count($errores) > 0)) {
	$_SESSION["errores"] = $errores;
	Header('Location: crearAviso.php');
} else {
	Header('Location: accion_crear.php');
}

function validarDatosAviso($conexion, $crearAviso) {
	$errores = array();

	if (!isset($crearAviso["titulo_av"]) || trim($crearAviso["titulo_av"]) == "" || $crearAviso["titulo_av"] == null || strlen($crearAviso["titulo_av"]) == 0) {
		$errores[] = "<p>El título no puede estar vacío</p>";
	}
	if (!isset($crearAviso["descripcion_av"]) || trim($crearAviso["descripcion_av"]) == "" || $crearAviso["descripcion_av"] == null || strlen($crearAviso["descripcion_av"]) == 0) {
		$errores[] = "<p>La descripción no puede estar vacía</p>";
	}

	if (!isset($crearAviso["fechaSuceso"]) || trim($crearAviso["fechaSuceso"]) == "" || $crearAviso["fechaSuceso"] == null || strlen($crearAviso["fechaSuceso"]) == 0 || strtotime($crearAviso["fechaSuceso"]) < strtotime('now')) {
		$errores[] = "<p>La fecha de suceso no puede estar vacía ni puede ser anterior a ahora</p>";
	}

	return $errores;
}
?>

