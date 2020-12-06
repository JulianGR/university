<?php
session_start();

require_once ("reutilizables/gestionBD.php");

if (isset($_SESSION["crear_up"])) {

	$nuevoUsuarioProfesor["nombre_up"] = $_POST["nombre_up"];
	$nuevoUsuarioProfesor["nick_up"] = $_POST["nick_up"];
	$nuevoUsuarioProfesor["apellidos_up"] = $_POST["apellidos_up"];
	$nuevoUsuarioProfesor["email_up"] = $_POST["email_up"];
	$nuevoUsuarioProfesor["pass"] = $_POST["pass"];
	$nuevoUsuarioProfesor["confirmpass"] = $_POST["confirmpass"];

	if (isset($_POST["foto_up"])) {
		$nuevoUsuarioProfesor["foto_up"] = $_POST["foto_up"];
	}

	if (isset($_POST["OID_UP"])) {
		$nuevoUsuarioProfesor["OID_UP"] = $_POST["OID_UP"];
	}

	$_SESSION["crear_up"] = $nuevoUsuarioProfesor;

	if (isset($_POST["modificar"])) {
		$_SESSION["modificar"] = true;
	} else {
		$_SESSION["modificar"] = false;
	}

} else {
	Header("Location: gestionDeUsuarioProfesor.php");
}
$conexion = crearConexionBD();
$errores = validarDatosUsuario($conexion, $nuevoUsuarioProfesor);
cerrarConexionBD($conexion);

if ($_SESSION["modificar"] == true) {

	if (count($errores) > 0) {
		$_SESSION["errores"] = $errores;
		Header('Location: modificarUsuarioProfesor.php');
	} else {
		Header('Location: accion_modificar.php');
	}
} else {
	if ((count($errores) > 0) && $_SESSION["modificar"] == false) {
		$_SESSION["errores"] = $errores;
		Header('Location: gestionDeUsuarioProfesor.php');
	} else {
		Header('Location: accion_crear.php');
	}
}

function validarDatosUsuario($conexion, $nuevoUsuario) {
	$errores = array();

	if (!isset($nuevoUsuario["nick_up"]) || trim($nuevoUsuario["nick_up"]) == "" || $nuevoUsuario["nick_up"] == null || strlen($nuevoUsuario["nick_up"]) == 0) {
		$errores[] = "<p>El nick no puede estar vacío</p>";
	}

	if (!isset($nuevoUsuario["pass"]) || trim($nuevoUsuario["pass"]) == "" || $nuevoUsuario["pass"] == null || strlen($nuevoUsuario["pass"]) < 8) {
		$errores[] = "<p>Contraseña no válida: debe tener al menos 8 caracteres</p>";
	} elseif (!isset($nuevoUsuario["confirmpass"]) || trim($nuevoUsuario["confirmpass"]) == "" || $nuevoUsuario["confirmpass"] == null || strlen($nuevoUsuario["confirmpass"]) < 8) {
		$errores[] = "<p>Contraseña no válida: debe tener al menos 8 caracteres</p>";
	} else if (!preg_match("/[a-z]+/", $nuevoUsuario["pass"]) || !preg_match("/[A-Z]+/", $nuevoUsuario["pass"]) || !preg_match("/[0-9]+/", $nuevoUsuario["pass"])) {
		$errores[] = "<p>Contraseña no válida: debe contener letras mayúsculas y minúsculas y dígitos</p>";
	} else if ($nuevoUsuario["pass"] != $nuevoUsuario["confirmpass"]) {
		$errores[] = "<p>La confirmación de contraseña no coincide con la contraseña</p>";
	}

	if (!isset($nuevoUsuario["nombre_up"]) || trim($nuevoUsuario["nombre_up"]) == "" || $nuevoUsuario["nombre_up"] == null || strlen($nuevoUsuario["nombre_up"]) == 0) {
		$errores[] = "<p>El nombre no puede estar vacío</p>";
	}
	if (!isset($nuevoUsuario["apellidos_up"]) || trim($nuevoUsuario["apellidos_up"]) == "" || $nuevoUsuario["apellidos_up"] == null || strlen($nuevoUsuario["apellidos_up"]) == 0) {
		$errores[] = "<p>Los apellidos no pueden estar vacíos</p>";
	}
	if (!isset($nuevoUsuario["email_up"]) || trim($nuevoUsuario["email_up"]) == "" || $nuevoUsuario["email_up"] == null || strlen($nuevoUsuario["email_up"]) == 0 || !filter_var($nuevoUsuario["email_up"], FILTER_VALIDATE_EMAIL)) {
		$errores[] = "<p>El email no puede estar vacío</p>";
	}
	return $errores;
}
?>