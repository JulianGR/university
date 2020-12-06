<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "admin")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");
}

if (isset($_SESSION["asignatura"]) || isset($_SESSION["asignaturaDespues"])) {
	if (isset($_SESSION["asignaturaDespues"])) {
		$asignaturaSeleccionada = $_SESSION["asignaturaDespues"];
	} else {
		$asignaturaSeleccionada = $_SESSION["asignatura"];
	}

	if (isset($_SESSION["calificacion"])) {
		$calificacion = $_SESSION["calificacion"];
		unset($_SESSION["calificacion"]);
	}

} else {
	Header("Location:seleccionarAsignatura.php");
}

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
$alumnos = consultarAlumnosAsignatura($conexion, $asignaturaSeleccionada);
$nombreAsignatura = consultarAsignatura($conexion, $asignaturaSeleccionada);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>
		
		<script>
			$(document).ready(function() {
				validateModificarCalificacion();
			});
		</script>	
			
	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>

		<div class="row">
			<div class="col-sm">
			<h1>Gestión de calificaciones de alumnos de <strong><?php
			if ($nombreAsignatura == "" || strlen($nombreAsignatura) == 0 || $nombreAsignatura == null) {
				echo "[ASIGNATURA INEXISTENTE]";
			} else {

				echo $nombreAsignatura;
			}
			?></strong></h1>
					<br>	
			
		
			
			<?php
			include_once ("reutilizables/errorFormulario.php");
			?>

			<?php
			//Mensaje de creación
			if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
				echo "<div class='alert alert-success' role='alert'>La calificación se ha creado correctamente</div>";
				unset($_SESSION["haSidoCreado"]);
				unset($_SESSION["haPasadoPorCrear"]);
			} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
				echo "<div class='alert alert-danger' role='alert'>La calificación no se ha podido borrar</div>";
				unset($_SESSION["haSidoCreado"]);
				unset($_SESSION["haPasadoPorCrear"]);
			}
			//Mensaje de modificación
			else if (isset($_SESSION["haPasadoPorModificar"]) && isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-success' role='alert'>La calificación se ha modificado correctamente</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
			} else if (isset($_SESSION["haPasadoPorModificar"]) && !isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-danger' role='alert'>La calificación no se ha podido modificar</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
			}
			//Mensaje de borrado
			else if (isset($_SESSION["haPasadoPorBorrar"]) && isset($_SESSION["haSidoBorrado"])) {
				echo "<div class='alert alert-success' role='alert'>La calificación se ha borrado correctamente</div>";
				unset($_SESSION["haSidoBorrado"]);
				unset($_SESSION["haPasadoPorBorrar"]);
			} else if (isset($_SESSION["haPasadoPorBorrar"]) && !isset($_SESSION["haSidoBorrado"])) {
				echo "<div class='alert alert-danger' role='alert'>La calificación no se ha podido borrar</div>";
				unset($_SESSION["haSidoBorrado"]);
				unset($_SESSION["haPasadoPorBorrar"]);

			}
			?>
			</div>
			</div>
			<br>
					
			<div class="row">
				<div class="col-sm">
					
					<?php
				$cuantosAlumnos = cuentaCuantosAlumnosTieneUnaAsignatura($conexion, $asignaturaSeleccionada);
						if ($cuantosAlumnos==0) {
							echo "<div class='alert alert-info' role='alert'>Esta asignatura no tiene ningún alumno todavía</div>";
							
						} else {
						
						foreach ($alumnos as $alumno) { 

							echo "<div class='card'>"; 
  							echo "<div class='card-body'>";
   		
							$oidAl = $alumno["OID_AL"];
							$nombreAl = $alumno["NOMBRE_AL"];
							$apellidosAl = $alumno["APELLIDOS_AL"];
							echo "<h5 class='card-title'>" . $nombreAl . " " . $apellidosAl . "</h5>";
			
			
			$calificaciones = consultarCalificacionesFamiliaPorAsignatura($conexion,$oidAl, $asignaturaSeleccionada );
			
			
			
			
			
$cuantasCalificaciones = cuentaCuantasCalificaciones($conexion,$oidAl);
if ($cuantasCalificaciones==0){
echo "<div class='alert alert-info' role='alert'>Este alumno no tiene ninguna calificación asociada</div>";
}
else{ ?>
						
						<table class="table table-hover">
  				<thead>
    				<tr>
				      <th  scope="col">Convocatoria</th>
				      <th  scope="col">Valor</th>
				      <th  scope="col">Nota</th>
				      <th class="text-center" scope="col"> Acciones sobre esta calificación</th>
				    </tr>
				  </thead>
  
  				<tbody>    
						
						<?php 
							foreach ($calificaciones as $cal) {
							echo "<tr>";
							$oidCal = $cal["OID_CA"];
							$convo = $cal["CONVOCATORIA"];
							$valor = $cal["VALOR_CA"];
	?>
							
							
							<td><?php echo $convo; ?></td>
							<form method='post' action='controladorCalificaciones.php'>
							<input id='OID_CA' name='OID_CA' type='hidden' value="<?php echo $oidCal; ?>"/>
							<input id='OID_AS' name='OID_AS' type='hidden' value="<?php echo $asignaturaSeleccionada; ?>"/>
<?php

if ((isset($calificacion) && (trim($calificacion["OID_CA"]) == $oidCal))) {
	?>
	
	<td><input id='VALOR_CA_MOD' name='VALOR_CA' type='number' required class="numbersPeque" min='0' max='10' value="<?php echo trim($calificacion["VALOR_CA"]); ?>"/></td>
<?php
} else {
?>
	<input id='VALOR_CA' name='VALOR_CA' type='hidden' value="<?php echo $valor; ?>"/>
	<td><?php echo $valor; ?></td>
<?php } ?>		
					
							<td>
							<script>
								getNotaTexto(<?php echo $valor; ?>
									);
								</script>
							</td>	
									
													
							<td class="text-center" id="botonesFila">
							
							<div class="botones">
							
								<?php if ((isset($calificacion) && (trim($calificacion["OID_CA"]) == $oidCal))) { ?>
					
												<button id="grabar" name="grabar" type="submit" class="btn btn-outline-primary nohover">
													<img src="./images/save.png" height="30px" width="30px">
												</button>
											
								<?php } else { ?>
					
												<button id="editar" name="editar" type="submit"  class="btn btn-outline-primary nohover">
													<img src="./images/001-exchange.png" height="30px" width="30px">
												</button>
											
								<?php } ?>
								
									</form>
											
									<div class= "float-right">	
										<form method="post" action="accion_borrar.php">							
												<input id="OID_CA" name="OID_CA" type="hidden" value="<?php echo $cal["OID_CA"]; ?>"/>	
												<input id="OID_AS" name="OID_AS" type="hidden" value="<?php echo $asignaturaSeleccionada; ?>"/>																				 			
												<button id="borrar" name="borrar" type="submit" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" class="btn btn-outline-primary nohover">															
													<img src="./images/002-cross.png" height="30px" width="30px" alt="Borrar calificación">
												</button>										
										</form>	
									</div>
									
									</div>	
								</td>
								
							</tr>
								
			
				 				<?php } ?>
			
			<br />
		
  				</tbody>
			</table>
			
	
	<?php  } ?>

<form method="post" action="crearCalificacion.php">							
						<input id="OID_AL" name="OID_AL" type="hidden" value="<?php echo $oidAl; ?>"/>						
						<input id="OID_AS" name="OID_AS" type="hidden" value="<?php echo $asignaturaSeleccionada; ?>"/>			
						<div class="form-group">							
						<button id="borrar" name="borrar" type="submit" class="btn btn-primary">															
							Crear calificación para este alumno
						</button>
					</div>
				</form>	



		</div></div>
		<br />
<?php
}
}
 ?>

	</div>	
	</div>
<br>
<br>
<a class="btn btn-primary" href="volverASeleccionarAsignatura.php">Volver a seleccionar Asignatura</a>
</div>
	</div>	
<br>
<br>

		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
			?>
	</body>
</html>