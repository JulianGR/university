<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_POST["profesor"])) {

	$seleccionarProfesorABorrar = $_POST["profesor"];

	$_SESSION["profesor"] = $seleccionarProfesorABorrar;

} else {
	Header("Location: gestionDeUsuarioProfesor.php");
}

$conexion = crearConexionBD();
$errores = validarDatos($conexion, $seleccionarProfesorABorrar);
cerrarConexionBD($conexion);

if (count($errores) > 0) {
	$_SESSION["errores"] = $errores;
	Header('Location: gestionDeUsuarioProfesor.php');
} else {
	Header('Location: accion_borrar.php');
}

function validarDatos($conexion, $seleccionarProfesorABorrar) {
	$errores = array();

	if (!isset($seleccionarProfesorABorrar) || trim($seleccionarProfesorABorrar) == "" || $seleccionarProfesorABorrar == null || strlen($seleccionarProfesorABorrar) == 0 || !is_numeric($seleccionarProfesorABorrar)) {
		$errores[] = "<p>LEl profesor a borrar no puede estar vacía</p>";
	}

	return $errores;
}
?>

