<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_cl"])) {

	$crearClase["curso_cl"] = $_POST["curso_cl"];
	$crearClase["grupo_cl"] = $_POST["grupo_cl"];
	$crearClase["profesor_tutor"] = $_POST["profesor_tutor"];

	$_SESSION["crear_cl"] = $crearClase;

} else {
	Header("Location: gestionDeClases.php");
}

$conexion = crearConexionBD();
$errores = validarDatosClase($conexion, $crearClase);
cerrarConexionBD($conexion);

if ((count($errores) > 0)) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeClases.php');
} else {
	Header('Location: accion_crear.php');
}

function validarDatosClase($conexion, $crearClase) {
	$errores = array();

	if (!isset($crearClase["curso_cl"]) || trim($crearClase["curso_cl"]) == "" || $crearClase["curso_cl"] == null || strlen($crearClase["curso_cl"]) == 0 || $crearClase["curso_cl"] < 0 || $crearClase["curso_cl"] > 6) {
		$errores[] = "<p>El curso no puede estar vacío</p>";
	}
	if (!isset($crearClase["grupo_cl"]) || trim($crearClase["grupo_cl"]) == "" || $crearClase["grupo_cl"] == null || strlen($crearClase["grupo_cl"]) != 1) {
		$errores[] = "<p>El grupo no puede estar vacío y debe tener un caracter</p>";
	}

	if (!isset($crearClase["profesor_tutor"]) || trim($crearClase["profesor_tutor"]) == "" || $crearClase["profesor_tutor"] == null || strlen($crearClase["profesor_tutor"]) == 0 || !is_numeric($crearClase["profesor_tutor"])) {
		$errores[] = "<p>El profesor asociado no puede estar vacía</p>";
	}

	return $errores;
}
?>

