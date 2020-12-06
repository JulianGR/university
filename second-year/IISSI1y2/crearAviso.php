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
if (!isset($_SESSION["crear_av"])) {
	$crear_av['titulo_av'] = "";
	$crear_av['descripcion_av'] = "";
	$crear_av['fechaSuceso'] = "";
	$crear_av['archivoURL_av'] = "";

	$_SESSION["crear_av"] = $crear_av;

} else {
	$crear_av = $_SESSION["crear_av"];
}
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();

$nickConexion = $_SESSION["login"];

$temp = oidProfesor($conexion, $nickConexion);
$oidProfesor = $temp["OID_UP"];
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>

		<script>
			$(document).ready(function() {
				validacionCrearAviso();
			});
		</script>

	</head>

	<body>
		<?php
		include_once ("reutilizables/cabeceraProfesor.php");
		?>
		<div class="row">
			<div class="col-sm">

				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php
				//Mensaje de creación
				if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-success' role='alert'>El aviso se ha creado correctamente</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
					unset($_SESSION["crear_av"]);
					$crear_av['titulo_av'] = "";
					$crear_av['descripcion_av'] = "";
					$crear_av['fechaSuceso'] = "";
					$crear_av['archivoURL_av'] = "";
					$_SESSION["crear_av"] = $crear_av;
				} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-danger' role='alert'>El aviso no se ha podido crear</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
				}
				?>
			</div>
		</div>
		<div class="row">
			<div class="col-sm">

				<! --Creación de un aviso-->
				<fieldset class="border p-2">
					<legend class="w-auto">
						Crear aviso
					</legend>
					<form id="creacionAviso" name ="creacionAviso" method="post" action="validacionAviso.php">

						<div class="form-group">
							<label for="titulo_av">Título:</label>
							<input class="form-control" id="titulo_av" name="titulo_av" type="text"  value="<?php echo $crear_av['titulo_av']; ?>" required/>
						</div>

						<div class="form-group">
							<label for="descripcion_av">Descripción:</label>
							<input class="form-control" id="descripcion_av" name="descripcion_av" type="text"  value="<?php echo $crear_av['descripcion_av']; ?>" required/>
						</div>

						<div class="form-group">
							<label for="fechaSuceso">Fecha Suceso:</label>
							<input id="fechaSuceso" name="fechaSuceso" type="date" max="date-local" placeholder="dd/mm/yyyy" value="<?php echo $crear_av['fechaSuceso']; ?>" required/>
							<br>

						</div>

						<div class="form-group">
							<label for="archivoURL_av">Archivo URL:</label>
							<input class="form-control" id="archivoURL_av" name="archivoURL_av" type="text"  value="URLDePrueba"/>
						</div>

						<input type='hidden' name='profesor_av' value='<?php echo $oidProfesor ?>'/>

						<button type="submit"  id ="crearAviso" class="btn btn-primary" value="Crear aviso">
							Crear aviso
						</button>
					</form>
				</fieldset>
				<br>
				<br>
				<br>
				<br>
			</div>

		</div>
		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
		?>
	</body>
</html>