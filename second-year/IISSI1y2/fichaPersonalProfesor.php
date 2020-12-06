<?php
session_start();

require_once ("reutilizables/gestionBD.php");
	require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "admin")) {
	session_unset(); 
	session_destroy();	
	Header("Location:index.php");
	
} else {


	$conexion = crearConexionBD();


		$ficha = consultarFichaPersonalProfesor($conexion);


	cerrarConexionBD($conexion);
}
?>

<!DOCTYPE html>
<html lang = "es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>
		<h1>Tu ficha personal</h1>

		<?php
		if (is_array($ficha) || is_object($ficha)) {
			if ($ficha["FOTOURL"] == "" || $ficha["FOTOURL"] == null || !isset($ficha["FOTOURL"])) {

				$foto = "./images/defaultPic.jpg";

			} else {

				$foto = $ficha['FOTOURL'];
			}

			$nombreCom = $ficha['NOMBRE_UP'] . ' ' . $ficha['APELLIDOS_UP'];
			$email = $ficha['EMAIL_UP'];

		} else {
			echo "<div class=\"alert alert-info\" role=\"alert\">No tienes ficha personal</div>";
		}
		?>

		<div class="card fichaPersonalProfesor">
			<img src=" <?php echo $foto ?>" class="card-img-top">
			<div class="card-body">
				<h5 class="card-title"><?php echo $nombreCom ?></h5>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<?php  echo $email ?>
				</li>			

			</ul>

		</div>

		<?php
			include_once ("reutilizables/pie.php");
			?>
	</body>

</html>