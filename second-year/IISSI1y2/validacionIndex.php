<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");
define("CONTRA_ADMIN", "a");
define("USUARIO_ADMIN", "a");
$conexion = crearConexionBD();

if (isset($_POST["nick"]) && isset($_POST["contrasenya"]) && isset($_POST["perfil"])) {

	$nick = $_POST["nick"];
	$contrasenya = $_POST["contrasenya"];
	$perfil = $_POST["perfil"];

} else {
	Header("Location: index.php");
}

$errores = validarDatos($conexion, $nick, $contrasenya, $perfil);

if ((count($errores) > 0)) {
	$_SESSION["errores"] = $errores;
	Header('Location: index.php');
} else {

	if ($perfil == "usuarioFamilia") {
		$num_usuarios = consultarUsuarioFamilias($conexion, $nick, $contrasenya);
		$localizacion = "Location:indiceFamilia.php";
	} elseif ($perfil == "usuarioProfesor") {
		$num_usuarios = consultarUsuarioProfesores($conexion, $nick, $contrasenya);
		$localizacion = "Location:indiceProfesor.php";
	} elseif ($perfil == "admin") {
		if (($contrasenya == CONTRA_ADMIN) && ($nick == USUARIO_ADMIN)) {
			$num_usuarios = 1;
			$localizacion = "Location:gestionesAdmin.php";
		} else {
			$num_usuarios = 0;
		}

	}

	if ($num_usuarios != 0) {
		$_SESSION["login"] = $nick;
		$_SESSION["perfil"] = $perfil;
		Header($localizacion);
	} else {
		$_SESSION["noExiste"] = true;
		header("Location: index.php");

	}

}

cerrarConexionBD($conexion);

function validarDatos($conexion, $nick, $contrasenya, $perfil) {
	$errores = array();

	if (!isset($nick) || trim($nick) == "" || $nick == null || strlen($nick) == 0) {
		$errores[] = "<p>El nick no puede estar vacío</p>";
	}
	if (!isset($contrasenya) || trim($contrasenya) == "" || $contrasenya == null || strlen($contrasenya) == 0) {
		$errores[] = "<p>La contraseña no puede estar vacía</p>";
	}

	if (!isset($perfil) || trim($perfil) == "" || $perfil == null || strlen($perfil) == 0) {
		$errores[] = "<p>El perfil no puede estar vacío</p>";
	}

	return $errores;
}
?>