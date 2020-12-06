<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");
require_once ("reutilizables/paginacionConsulta.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "admin") || ($_SESSION["perfil"] == "usuarioFamilia")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");

}

if (isset($_SESSION['paginacion'])) {
	$paginacion = $_SESSION['paginacion'];
}

$paginaSeleccionada = isset($_GET['PAG_NUM']) ? (int)$_GET['PAG_NUM'] : (isset($paginacion) ? (int)$paginacion['PAG_NUM'] : 1);
$pagTam = isset($_GET['PAG_TAM']) ? (int)$_GET['PAG_TAM'] : (isset($paginacion) ? (int)$paginacion['PAG_TAM'] : 5);
if ($paginaSeleccionada < 1) {
	$paginaSeleccionada = 1;
}
if ($pagTam < 1) {
	$pagTam = 5;
}

unset($_SESSION['paginacion']);
$conexion = crearConexionBD();

$avisos = "SELECT * FROM AVISOS";

$totalRegistros = totalConsulta($conexion, $avisos);
$totalPaginas = (int)($totalRegistros / $pagTam);
if ($totalRegistros % $pagTam > 0) {
	$totalPaginas++;
}

if ($paginaSeleccionada > $totalPaginas) {
	$paginaSeleccionada = $totalPaginas;
}

$paginacion['PAG_NUM'] = $paginaSeleccionada;
$paginacion['PAG_TAM'] = $pagTam;
$_SESSION['paginacion'] = $paginacion;

$avisosPaginados = consultaPaginada($conexion, $avisos, $paginaSeleccionada, $pagTam);

cerrarConexionBD($conexion);
?>
<!DOCTYPE HTML>
<html lang='es'>
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
	</head>
	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>

		<div class="row">
			<div class="col-sm">
				<div>
					<h1 class="text-center">Avisos</h1>
					<br>
					<?php

					if($totalRegistros==0){
echo "<div class='alert alert-info' role='alert'>No hay avisos</div>";
}
					else{

					foreach ($avisosPaginados as $aviso) {
					$conexion = crearConexionBD();
					$titulo = $aviso["TITULO_AV"];
					$cuerpo =	$aviso["CUERPO"];
					$oidAv = $aviso["OID_AV"];
					$fechaSuceso = 	$aviso["FECHASUCESO"];
					$fechaPublicacion = $aviso["FECHAPUBLICACION"];

					?>

					<div class="card">
					<div class="card-body">
					<h5 class="card-title"><?php echo $titulo; ?></h5>

					<div class="float-right">
					<form id="borrarAviso" method="post" action="accion_borrar.php">
					<input name="aviso_a_borrar" id="aviso_a_borrar" type="hidden" value="<?php echo $oidAv; ?>" />

					<div class="form-group">
					<button id="borrar" name="borrar" type="submit" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" class="btn btn-outline-primary nohover">
					<img src="./images/002-cross.png" height="30px" width="30px">
					</button>
					</div>
					</form>
					</div>

					<p class="card-text"><?php echo $cuerpo; ?></p>

					<p class="card-text"><small class="text-muted">Fecha fin: <?php echo $fechaSuceso; ?></small></p>
					<p class="card-text"><small class="text-muted"> Fecha Publicación: <?php echo $fechaPublicacion; ?></small></p>

					</div>
					</div>
					<?php	} ?>
					</div>

					<br />

					<nav>
					<div class="botones">
					<ul class="pagination">

					<?php
					for($pagina = 1; $pagina <= $totalPaginas; $pagina++)
					if ( $pagina == $paginaSeleccionada) { 	?>

					<li class="disabled page-item active" aria-current="page">
					<a class="page-link" href='javascript: void(0)'><?php echo $pagina; ?></a>

					</li>

					<?php
					} else {
					?>
					<li class="page-item"><a class="page-link" href='indiceProfesor.php?PAG_NUM=<?php echo $pagina; ?>&PAG_TAM=<?php echo $pagTam; ?>'><?php echo $pagina; ?></a></li>

					<?php
					}
					?>

					</ul>
					</div>

					<div class="botones paginacionMostrar">

					<form class='cambioPagina' method='get' action='indiceProfesor.php'>
						<input id='PAG_NUM' name='PAG_NUM' type='hidden' value='<?php echo $paginaSeleccionada; ?>' />
						<span>Mostrando
							<input id='PAG_TAM' name='PAG_TAM' class="numbersPeque" required type='number' min='1' max='<?php echo $totalRegistros; ?>' value='<?php echo $pagTam; ?>' autofocus />
							entradas de <?php echo $totalRegistros; ?></span>
						<button class="btn btn-info" type='submit' value='Cambiar'>
							Cambiar
						</button>
					</form>

				</div>

				</nav>

				<br />

			</div>

			<br />
			<br />

			<br />
			<br />

		</div>
		<?php } ?>
		<div class="col-sm">
			<a class="btn btn-primary" href="irACrearAviso.php">Crear Aviso</a>
		</div>
		</div>
		<br>
		<br>

		<?php
		include_once ("reutilizables/pie.php");
		?>

	</body>

</html>