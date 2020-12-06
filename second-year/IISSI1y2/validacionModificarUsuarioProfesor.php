<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["profesor"])) {

	$seleccionarProfesorAModificar = $_POST["profesor"];

	$_SESSION["profesor"] = $seleccionarProfesorAModificar;

} else {
	Header("Location: gestionDeUsuarioProfesor.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarProfesorAModificar);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeUsuarioProfesor.php');
} else {
	Header('Location: modificarUsuarioProfesor.php');
}

function validarDatos($conexion, $seleccionarProfesorAModificar) {
	$errores = array();

	if (!isset($seleccionarProfesorAModificar) || trim($seleccionarProfesorAModificar) == "" || $seleccionarProfesorAModificar == null || strlen($seleccionarProfesorAModificar) == 0 || !is_numeric($seleccionarProfesorAModificar)) {
		$errores[] = "<p>El profesor a borrar no puede estar vacío</p>";
	}

	return $errores;
}
?>

