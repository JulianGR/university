<?php
session_start();

if (isset($_SESSION['login'])) {
	$_SESSION['login'] = null;
}

session_unset();
session_destroy();
header("Location: index.php");
?>
