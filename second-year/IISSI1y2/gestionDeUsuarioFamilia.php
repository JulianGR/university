<?php
session_start();

require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

//si no hay sesion de login, para index.php
if (!isset($_SESSION["login"]) || ($_SESSION["perfil"] == "usuarioFamilia") || ($_SESSION["perfil"] == "usuarioProfesor")) {
	session_unset();
	session_destroy();
	Header("Location:index.php");

}
if (!isset($_SESSION["crear_uf"])) {
	$crear_uf['nick_uf'] = "";
	$crear_uf['pass'] = "";
	$crear_uf['confirmpass'] = "";
	$crear_uf['nombre_uf'] = "";
	$crear_uf['apellidos_uf'] = "";
	$crear_uf['email_uf'] = "";

	$_SESSION["crear_uf"] = $crear_uf;
} else {
	$crear_uf = $_SESSION["crear_uf"];
}

$_SESSION["haPasadoPorGestion"] = true;

if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$conexion = crearConexionBD();
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<?php
		include_once ("reutilizables/head.php");
		?>

		<script>
			$(document).ready(function() {
				validateBorraUsuarioFamilia();
				validateModificarUsuarioFamilia();
				validateUsuarioFamilia();
				passwordValidation();
				passwordConfirmation();
			});
		</script>

	</head>

	<body>

		<?php
		include_once ("reutilizables/cabeceraAdmin.php");
		?>

		<div class="row">
			<div class="col-sm">
				<h1 class="text-center">Gestión de usuarios familia</h1>
				<br>

				<?php
				include_once ("reutilizables/errorFormulario.php");
				?>

				<?php
				//Mensaje de creación
				if (isset($_SESSION["haPasadoPorCrear"]) && isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-success' role='alert'>El usuario familia se ha creado correctamente</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
					unset($_SESSION["crear_uf"]);
					$crear_uf['nick_uf'] = "";
					$crear_uf['pass'] = "";
					$crear_uf['confirmpass'] = "";
					$crear_uf['nombre_uf'] = "";
					$crear_uf['apellidos_uf'] = "";
					$crear_uf['email_uf'] = "";

					$_SESSION["crear_uf"] = $crear_uf;
				} else if (isset($_SESSION["haPasadoPorCrear"]) && !isset($_SESSION["haSidoCreado"])) {
					echo "<div class='alert alert-danger' role='alert'>El usuario familia no se ha podido crear. Le recordamos que no puede repetir ni el nick ni el email</div>";
					unset($_SESSION["haSidoCreado"]);
					unset($_SESSION["haPasadoPorCrear"]);
				}

				//Mensaje de borrado
				else if (isset($_SESSION["haPasadoPorBorrar"]) && isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-success' role='alert'>El usuario familia se ha borrado correctamente</div>";
					unset($_SESSION["haSidoBorrado"]);
					unset($_SESSION["haPasadoPorBorrar"]);
				} else if (isset($_SESSION["haPasadoPorBorrar"]) && !isset($_SESSION["haSidoBorrado"])) {
					echo "<div class='alert alert-danger' role='alert'>El usuario familia no se ha podido borrar. Le recordamos que no puede borrar familias con alumnos asignados</div>";
					unset($_SESSION["haSidoBorrado"]);
					unset($_SESSION["haPasadoPorBorrar"]);

				}
				?>
			</div>
		</div>

		<div class="row">
			<div class="col-sm">

				<fieldset class="border p-2">
					<legend class="w-auto">
						Crear Usuario Familia
					</legend>

					<form id="FamiliaAForm" method="post" action="validacionUsuarioFamilia.php">

						<div class="form-group">
							<label for="nick_uf">Nick:</label>
							<input class="form-control" id="nick_uf" name="nick_uf" type="text" value="<?php echo $crear_uf['nick_uf']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="pass">Contraseña:</label>
							<input id="pass" name="pass" type="password" class="form-control" oninput="passwordValidation();"
							required/>
						</div>

						<div class="form-group">
							<label for="confirmpass">Confirmar Contraseña: </label>
							<input type="password" name="confirmpass" id="confirmpass" class="form-control" oninput="passwordConfirmation();" value=""
							required/>
						</div>

						<div class="form-group">
							<label for="nombre_uf">Nombre:</label>
							<input id="nombre_uf" name="nombre_uf" type="text" class="form-control"  value="<?php echo $crear_uf['nombre_uf']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="apellidos_uf">Apellidos:</label>
							<input id="apellidos_uf" name="apellidos_uf" type="text" class="form-control"  value="<?php echo $crear_uf['apellidos_uf']; ?>"
							required/>
						</div>

						<div class="form-group">
							<label for="email_uf">Email:</label>
							<input id="email_uf" name="email_uf" type="email" class="form-control"  value="<?php echo $crear_uf['email_uf']; ?>"
							required/>
						</div>

						<button type="submit" id="FamiliaA" class="btn btn-primary" value="Crear familia">
							Crear familia
						</button>
					</form>
				</fieldset>
			</div>
			<br>
			<br>

			<div class="col-sm">

				<fieldset class="border p-2">
					<legend class="w-auto">
						Modificar Familia
					</legend>

					<form id="modificarFamiliaForm" method="post" action="validacionModificarUsuarioFamilia.php">

						<div class="form-group">
							<label for="familia">Familia:</label>
							<select class="custom-select" name="familia" id="familia" required>
								<?php
								$familias = listarTutores($conexion);
								foreach ($familias as $familia) {
									$oidFamilia = $familia["OID_UF"];
									$nombreApellidosFamilia = $familia["NOMBRE_UF"] . " " . $familia["APELLIDOS_UF"];

									echo "<option value='$oidFamilia' label='$nombreApellidosFamilia'/>$nombreApellidosFamilia</option>";
								}
								?>
							</select>
						</div>

						<button type="submit"  id="modificarFamilia" class="btn btn-primary" value="Modificar familia">
							Modificar familia
						</button>
					</form>
				</fieldset>

				<br>
				<br>

				<fieldset class="border p-2">
					<legend class="w-auto">
						Borrar Familia
					</legend>

					<form id="borrarFamiliaForm" method="post" action="validacionBorrarUsuarioFamilia.php">

						<?php
$familiasSinAlumnos = cuentaCuantasFamiliasHayCon0Alumnos($conexion);

if ($familiasSinAlumnos==0){
echo "<div class='alert alert-info' role='alert'>No hay familias que se puedan borrar puesto que todas tienen alumnos asociados</div>";
}else {

$familiasSinAlumnos = queFamiliasHayCon0Alumnos($conexion);

$familias = array();

foreach($familiasSinAlumnos as $ufalumnos){
array_push($familias, $ufalumnos);

}
if(count($familias) ==0){
echo "<div class='alert alert-info' role='alert'>No hay familias que se puedan borrar puesto que todas tienen alumnos asociados</div>";

}
else{

						?>

						<div class="form-group">
							<p>
								<em>Le recordamos que, puesto que solo se pueden borrar familias sin alumnos asignados, solo aparecerán las familias que tienen 0 alumnos</em>

							</p>
						</div>

						<div class="form-group">
							<label for="familia_a_borrar">Familia:</label>
							<select class="custom-select" name="familia_a_borrar" id="familia_a_borrar" required>
								<?php
								foreach ($familias as $familia) {
									$oidFamilia = $familia["OID_UF"];
									$nombreApellidosFamilia = obtenerNombreApellidosDeUnaFamilia($conexion, $oidFamilia);

									echo "<option value='$oidFamilia' label='$nombreApellidosFamilia'/>$nombreApellidosFamilia</option>";
								}
								?>
							</select>
						</div>

						<button type="submit" id="borrarFamilia" class="btn btn-primary" onclick="return confirm('¿Estás seguro de que lo quieres borrar?');" value="Modificar familia">
							Borrar familia
						</button>
						<?php }} ?>
					</form>
				</fieldset>

			</div>
		</div>
		<?php
		cerrarConexionBD($conexion);

		include_once ("reutilizables/pie.php");
		?>
	</body>
</html>
