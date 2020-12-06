<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");
require_once ("reutilizables/paginacionConsulta.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "admin") || ($_SESSION["perfil"] == "usuarioProfesor")) {
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
		include_once ("reutilizables/cabeceraFamilia.php");
		?>
		
		<div class="row">
				<div class="col-sm">
					<div><h1 class="text-center">Avisos</h1>
					<br>
					<?php

					if($totalRegistros==0){
echo "<div class='alert alert-info' role='alert'>No hay avisos</div>";
}
					else{
							foreach ($avisosPaginados as $aviso) {
								$conexion = crearConexionBD();
								echo "<div class=\"card\"> <div class=\"card-body\"><h5 class=\"card-title\">" . $aviso["TITULO_AV"] . "</h5>" . "<p class=\"card-text\">" . $aviso["CUERPO"] . "</p><p class=\"card-text\"><small class=\"text-muted\">Fecha fin: " . $aviso["FECHASUCESO"] . "</small></p>" . "<p class=\"card-text\"><small class=\"text-muted\"> Fecha Publicacion: " . $aviso["FECHAPUBLICACION"] . "</small></p></div></div>";

							}
 ?>
			    		
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
 								<li class="page-item"><a class="page-link" href='indiceFamilia.php?PAG_NUM=<?php echo $pagina; ?>&PAG_TAM=<?php echo $pagTam; ?>'><?php echo $pagina; ?></a></li>
								
							<?php
							}
 ?>
							
				 </ul>
				 	</div>
				 
				 		<div class="botones paginacionMostrar">	
				 		
		   			<form class='cambioPagina' method='get' action='indiceFamilia.php'>
						<input id='PAG_NUM' name='PAG_NUM' type='hidden' value='<?php echo $paginaSeleccionada; ?>' />
						<span>Mostrando <input id='PAG_TAM' name='PAG_TAM' required class="numbersPeque" type='number' min='1' max='<?php echo $totalRegistros; ?>' value='<?php echo $pagTam; ?>' autofocus /> entradas de <?php echo $totalRegistros; ?></span>
						<button class="btn btn-info" type='submit' value='Cambiar'>Cambiar</button>
					</form>	
					
							</div>		
			
				 
				 
			</nav>				
			
	</div>
	</div>
	<?php } ?>
	<br><br>
		<?php
		include_once ("reutilizables/pie.php");
			?>
	</body>
</html>