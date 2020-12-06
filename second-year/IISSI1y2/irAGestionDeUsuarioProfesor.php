<?php
session_start();

$login = $_SESSION["login"];
$perfil = $_SESSION["perfil"];

session_unset();

$_SESSION["login"] = $login;
$_SESSION["perfil"] = $perfil;

header("Location: gestionDeUsuarioProfesor.php");
?>