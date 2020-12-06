<?php
session_start();
unset($_SESSION["asignatura"]);
unset($_SESSION["asignaturaDespues"]);
header("Location: seleccionarAsignatura.php");
?>