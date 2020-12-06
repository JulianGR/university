<?php
session_start();
unset($_SESSION["clase"]);
unset($_SESSION["crear_al"]);
header("Location: seleccionarClase.php");
?>