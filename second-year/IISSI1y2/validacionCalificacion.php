<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_ca"]) && !isset($_SESSION["calificacion"])) {

	$crearCalificacion["convocatoria"] = $_POST["convocatoria"];
	$crearCalificacion["VALOR_CA"] = $_POST["VALOR_CA"];

	$crearCalificacion["OID_AS"] = $_POST["OID_AS"];
	$crearCalificacion["OID_AL"] = $_POST["OID_AL"];

	$_SESSION["asignaturaDespues"] = $crearCalificacion["OID_AS"];
	$_SESSION["alumnoDespues"] = $crearCalificacion["OID_AL"];

	$_SESSION["crearCalificacion"] = $crearCalificacion;

} elseif (isset($_SESSION["calificacion"])) {
	$modificar = true;
	$crearCalificacion = $_SESSION["calificacion"];
	$_SESSION["crearCalificacion"] = $crearCalificacion;

} else {
	Header("Location: gestionDeCalificaciones.php");
}

$conexion = crearConexionBD();
if ($modificar == true) {
	$errores = validarDatosCalificacionMod($conexion, $crearCalificacion);
} else {
	$errores = validarDatosCalificacion($conexion, $crearCalificacion);
}
cerrarConexionBD($conexion);

if ($modificar == true) {

	if (count($errores) > 0) {
		$_SESSION["errores"] = $errores;
		Header('Location: gestionDeCalificaciones.php');
	} else {
		Header('Location: accion_modificar.php');
	}
} else {
	if ((count($errores) > 0)) {
		$_SESSION["errores"] = $errores;
		Header('Location: crearCalificacion.php');
	} else {
		Header('Location: accion_crear.php');
	}
}

function validarDatosCalificacion($conexion, $crearCalificacion) {
	$errores = array();

	if (!isset($crearCalificacion["convocatoria"]) || trim($crearCalificacion["convocatoria"]) == "" || $crearCalificacion["convocatoria"] == null || strlen($crearCalificacion["convocatoria"]) == 0) {
		$errores[] = "<p>La convocatoria no puede estar vacía</p>";
	}
	if (!isset($crearCalificacion["VALOR_CA"]) || trim($crearCalificacion["VALOR_CA"]) == "" || $crearCalificacion["VALOR_CA"] == null || strlen($crearCalificacion["VALOR_CA"]) == 0 || !is_numeric($crearCalificacion["VALOR_CA"])) {
		$errores[] = "<p>El valor no puede estar vacío</p>";
	}
	if ($crearCalificacion["VALOR_CA"] < 0 || $crearCalificacion["VALOR_CA"] > 10) {
		$errores[] = "<p>El valor debe estar contenido entre 0 y 10</p>";
	}

	return $errores;
}

function validarDatosCalificacionMod($conexion, $crearCalificacion) {
	$errores = array();

	if (!isset($crearCalificacion["VALOR_CA"]) || trim($crearCalificacion["VALOR_CA"]) == "" || $crearCalificacion["VALOR_CA"] == null || strlen($crearCalificacion["VALOR_CA"]) == 0 || !is_numeric($crearCalificacion["VALOR_CA"])) {
		$errores[] = "<p>El valor no puede estar vacío</p>";
	}
	if ($crearCalificacion["VALOR_CA"] < 0 || $crearCalificacion["VALOR_CA"] > 10) {
		$errores[] = "<p>El valor debe estar contenido entre 0 y 10</p>";
	}


	return $errores;
}
?>

