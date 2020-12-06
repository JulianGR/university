<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_al"])) {

	$crearAlumno["nombre_al"] = $_POST["nombre_al"];
	$crearAlumno["apellidos_al"] = $_POST["apellidos_al"];
	$crearAlumno["fechaNacimiento"] = $_POST["fechaNacimiento"];
	$crearAlumno["tutor"] = $_POST["tutor"];
	$crearAlumno["clase"] = $_POST["clase"];

	if (isset($_POST["enEstaClase"])) {
		$_SESSION["enEstaClase"] = true;

	} else {
		$_SESSION["enEstaClase"] = false;
	}

	if (isset($_POST["modificar"])) {
		$modificar = true;
		$crearAlumno["OID_AL"] = $_POST["OID_AL"];
	} else {
		$modificar = false;
	}

	$_SESSION["crear_al"] = $crearAlumno;

} else {
	Header("Location: seleccionarClase.php");
}

$conexion = crearConexionBD();
$errores = validarDatosUsuario($conexion, $crearAlumno);
cerrarConexionBD($conexion);

if ($modificar == true) {

	if (count($errores) > 0) {
		$_SESSION["errores"] = $errores;
		Header('Location: modificarAlumnos.php');
	} else {
		Header('Location: accion_modificar.php');
	}
} else {
	if ($_SESSION["enEstaClase"] == true) {
		if ((count($errores) > 0)) {
			$_SESSION["errores"] = $errores;
			Header('Location: gestionDeAlumnos.php');
		} else {
			Header('Location: accion_crear.php');
		}

	} else {
		if ((count($errores) > 0) && $modificar == false) {
			$_SESSION["errores"] = $errores;
			Header('Location: seleccionarClase.php');
		} else {
			Header('Location: accion_crear.php');
		}
	}
}

function validarDatosUsuario($conexion, $nuevoUsuario) {
	$errores = array();

	if (!isset($nuevoUsuario["nombre_al"]) || trim($nuevoUsuario["nombre_al"]) == "" || $nuevoUsuario["nombre_al"] == null || strlen($nuevoUsuario["nombre_al"]) == 0) {
		$errores[] = "<p>El nombre no puede estar vacío</p>";
	}

	if (!isset($nuevoUsuario["apellidos_al"]) || trim($nuevoUsuario["apellidos_al"]) == "" || $nuevoUsuario["apellidos_al"] == null || strlen($nuevoUsuario["apellidos_al"]) == 0) {
		$errores[] = "<p>Los apellidos no pueden estar vacíos</p>";
	}

	if (!isset($nuevoUsuario["fechaNacimiento"]) || trim($nuevoUsuario["fechaNacimiento"]) == "" || $nuevoUsuario["fechaNacimiento"] == null || strtotime($nuevoUsuario["fechaNacimiento"]) > strtotime('now') || strlen($nuevoUsuario["fechaNacimiento"]) == 0) {
		$errores[] = "<p>La fecha de nacimiento no puede estar vacía <strong>ni puede ser posterior a ahora</strong></p>";
	}

	if (!isset($nuevoUsuario["tutor"]) || trim($nuevoUsuario["tutor"]) == "" || $nuevoUsuario["tutor"] == null || strlen($nuevoUsuario["tutor"]) == 0) {
		$errores[] = "<p>El tutor no puede estar vacío</p>";
	}

	if (!isset($nuevoUsuario["clase"]) || trim($nuevoUsuario["clase"]) == "" || $nuevoUsuario["clase"] == null || strlen($nuevoUsuario["clase"]) == 0) {
		$errores[] = "<p>La clase no puede estar vacía</p>";
	}

	return $errores;
}
?>

