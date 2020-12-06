<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "admin") || ($_SESSION["perfil"] == "usuarioProfesor")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");

} else {

	$conexion = crearConexionBD();

	$tutelados = consultarFichaPersonalFamilia($conexion);
	$cuantosAlumnosAsociados = cuentaCuantosAlumnos($conexion);

	cerrarConexionBD($conexion);
}
?>

<!DOCTYPE html>
<html lang = "es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
		<script src="js/notasDeNumeroATexto.js" type="text/javascript"></script>
	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraFamilia.php");
		?>
		<h1>Ficha personal de tus alumnos asociados</h1>

		<?php
		if ($cuantosAlumnosAsociados==0) {
echo "<br>";
			echo "<div class='alert alert-info' role='alert'>No tienes ningún alumno asociado</div>";
echo "<br>";
		} else{
		?>



		<?php
foreach($tutelados as $tutelado) {
	$oidAlumno = $tutelado["OID_AL"];
	

	
	 $apellidos =$tutelado["APELLIDOS_AL"];
	 $nombre =$tutelado["NOMBRE_AL"];
	 $fechaNacimiento =$tutelado["FECHANACIMIENTO"];
	 $curso =$tutelado["CURSO"];
	 $grupo =$tutelado["GRUPO"];
	 
		?>


<div class="card">
  
  <div class="card-body">

<h5 class='card-title'><?php echo $nombre . " ". $apellidos ?></h5>

<p class='card-text'>Fecha de nacimiento: <?php echo $fechaNacimiento ?></p>
<p class='card-text'>Curso y grupo: <?php echo $curso . "º " . $grupo ?></p>	
			
<?php
$cuantasCalificaciones = cuentaCuantasCalificaciones($conexion,$oidAlumno);
if ($cuantasCalificaciones==0){
echo "<div class='alert alert-info' role='alert'>Este alumno no tiene ninguna calificación asociada</div>";
}
else{
			?>

<table class="table table-hover">
  <thead>
    <tr>
    <th scope="col">Asignatura</th>
      <th scope="col">Convocatoria</th>
      <th scope="col">Valor</th>
      <th scope="col">Nota</th>
         </tr>
  </thead>
  
  <tbody>
    
<?php

$calificaciones = consultarCalificacionesFamilia($conexion, $oidAlumno);


		if (empty($calificaciones)) {

			echo "<div class='alert alert-info' role='alert'>Este alumno no tiene calificaciones todavía</div>";

		}
		



foreach($calificaciones as $calificacion) {
				
			 $nombreAsignatura =$calificacion['NOMBRE_AS'];
		 $convocatoria = $calificacion['CONVOCATORIA'];
	$valor = $calificacion['VALOR_CA'];
?>


   <tr>
<td><?php echo $nombreAsignatura; ?></td>	
<td><?php echo $convocatoria; ?></td>	
<td><?php echo $valor; ?></td>	
<td><script>
								getNotaTexto(<?php echo $valor; ?>
									);
								</script></td>	


</tr>
<?php } ?>

 </tbody>
</table>

<?php  } ?>
			
		</div>
		</div>
<br>
<br>
		<?php }} ?>

		<?php
		include_once ("reutilizables/pie.php");
		?>
	</body>

</html>