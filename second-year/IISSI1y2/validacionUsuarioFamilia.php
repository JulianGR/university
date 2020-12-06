<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_uf"])) {

	$nuevoUsuarioFamilia["nick_uf"] = $_POST["nick_uf"];
	$nuevoUsuarioFamilia["pass"] = $_POST["pass"];
	$nuevoUsuarioFamilia["confirmpass"] = $_POST["confirmpass"];
	$nuevoUsuarioFamilia["nombre_uf"] = $_POST["nombre_uf"];
	$nuevoUsuarioFamilia["apellidos_uf"] = $_POST["apellidos_uf"];
	$nuevoUsuarioFamilia["email_uf"] = $_POST["email_uf"];

	if (isset($_POST["OID_UF"])) {
		$nuevoUsuarioFamilia["OID_UF"] = $_POST["OID_UF"];
	}

	$_SESSION["crear_uf"] = $nuevoUsuarioFamilia;

	if (isset($_POST["modificar"])) {
		$_SESSION["modificar"] = true;
	} else {
		$_SESSION["modificar"] = false;
	}

} else {
	Header("Location: gestionDeUsuarioFamilia.php");
}
$conexion = crearConexionBD();
$errores = validarDatosUsuario($conexion, $nuevoUsuarioFamilia);
cerrarConexionBD($conexion);

if ($_SESSION["modificar"] == true) {

	if (count($errores) > 0) {
		$_SESSION["errores"] = $errores;
		Header('Location: modificarUsuarioFamilia.php');
	} else {
		Header('Location: accion_modificar.php');
	}
} else {
	if ((count($errores) > 0)) {
		$_SESSION["errores"] = $errores;
		Header('Location: gestionDeUsuarioFamilia.php');
	} else {
		Header('Location: accion_crear.php');
	}
}

function validarDatosUsuario($conexion, $nuevoUsuario) {
	$errores = array();

	if (!isset($nuevoUsuario["nick_uf"]) || trim($nuevoUsuario["nick_uf"]) == "" || $nuevoUsuario["nick_uf"] == null || strlen($nuevoUsuario["nick_uf"]) == 0) {
		$errores[] = "<p>El nick no puede estar vacío</p>";
	}

	if (!isset($nuevoUsuario["pass"]) || trim($nuevoUsuario["pass"]) == "" || $nuevoUsuario["pass"] == null || strlen($nuevoUsuario["pass"]) < 8) {
		$errores[] = "<p>Contraseña no válida: no debe estar vacía y debe tener al menos 8 caracteres</p>";
	} elseif (!isset($nuevoUsuario["confirmpass"]) || trim($nuevoUsuario["confirmpass"]) == "" || $nuevoUsuario["confirmpass"] == null || strlen($nuevoUsuario["confirmpass"]) < 8) {
		$errores[] = "<p>Confirmar contraseña no válida: no debe estar vacía y debe tener al menos 8 caracteres</p>";
	} else if (!preg_match("/[a-z]+/", $nuevoUsuario["pass"]) || !preg_match("/[A-Z]+/", $nuevoUsuario["pass"]) || !preg_match("/[0-9]+/", $nuevoUsuario["pass"])) {
		$errores[] = "<p>Contraseña no válida: debe contener letras mayúsculas y minúsculas y dígitos</p>";
	} else if ($nuevoUsuario["pass"] != $nuevoUsuario["confirmpass"]) {
		$errores[] = "<p>La confirmación de contraseña no coincide con la contraseña</p>";
	}

	if (!isset($nuevoUsuario["nombre_uf"]) || trim($nuevoUsuario["nombre_uf"]) == "" || $nuevoUsuario["nombre_uf"] == null || strlen($nuevoUsuario["nombre_uf"]) == 0) {
		$errores[] = "<p>El nombre no puede estar vacío</p>";
	}
	if (!isset($nuevoUsuario["apellidos_uf"]) || trim($nuevoUsuario["apellidos_uf"]) == "" || $nuevoUsuario["apellidos_uf"] == null || strlen($nuevoUsuario["apellidos_uf"]) == 0) {
		$errores[] = "<p>Los apellidos no pueden estar vacíos</p>";
	}
	if (!isset($nuevoUsuario["email_uf"]) || trim($nuevoUsuario["email_uf"]) == "" || $nuevoUsuario["email_uf"] == null || strlen($nuevoUsuario["email_uf"]) == 0 || !filter_var($nuevoUsuario["email_uf"], FILTER_VALIDATE_EMAIL)) {
		$errores[] = "<p>El email no puede estar vacío</p>";
	}
	return $errores;
}
?>