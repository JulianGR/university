<?php
session_start();

if (isset($_POST["OID_CA"]) && isset($_POST["OID_AS"])) {
	$calificacion["OID_CA"] = $_POST["OID_CA"];
	$calificacion["OID_AS"] = $_POST["OID_AS"];
	$calificacion["VALOR_CA"] = $_POST["VALOR_CA"];

	$_SESSION["calificacion"] = $calificacion;

	$_SESSION["asignaturaDespues"] = $_POST["OID_AS"];

	if (isset($_POST["grabar"])) {
		Header("Location: validacionCalificacion.php");

	} else {
		Header("Location: gestionDeCalificaciones.php");
	}

} else
	Header("Location: gestionDeCalificaciones.php");
?>
