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

if (!isset($_SESSION["haPasadoPorGestion"])) {
	Header("Location: gestionDeUsuarioProfesor.php");
} else {
	if (!isset($_SESSION["crear_up"])) {
		$crear_up["nombre_up"] = "";
		$crear_up["nick_up"] = "";
		$crear_up["apellidos_up"] = "";
		$crear_up["email_up"] = "";
		$crear_up["pass"] = "";
		$crear_up["confirmpass"] = "";

		$_SESSION["crear_up"] = $crear_up;
	} else {
		$crear_up = $_SESSION["crear_up"];
	}
}
if (isset($_SESSION["profesor"])) {
	$oidProfesor = $_SESSION["profesor"];
	unset($_SESSION["profesor"]);
	$_SESSION["oidUpDespues"] = $oidProfesor;
} else {
	$oidProfesor = $_SESSION["oidUpDespues"];
}
$conexion = crearConexionBD();
$datosProfesor = consultarProfesor($conexion, $oidProfesor);
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}

$nickProfesor = $datosProfesor["NICK"];
$nombre = $datosProfesor["NOMBRE_UP"];
$apellidos = $datosProfesor["APELLIDOS_UP"];
$email = $datosProfesor["EMAIL_UP"];
$oidProfesor = $datosProfesor["OID_UP"];
?>

<!DOCTYPE html>
<html lang="es">
<head>
  <?php
include_once ("reutilizables/head.php");
		?>
	
		<script>
			$(document).ready(function() {
				validateUsuarioProfesor();
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

<h1 class="text-center">Modificando a <strong><?php echo $nombre . " " . $apellidos; ?></strong></h1>

<?php
include_once ("reutilizables/errorFormulario.php");
			?>

			<?php

			//Mensaje de modificación
			if (isset($_SESSION["haPasadoPorModificar"]) && isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-success' role='alert'>El usuario profesor se ha modificado correctamente</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
				unset($_SESSION["crear_up"]);
				$crear_up["nombre_up"] = "";
				$crear_up["nick_up"] = "";
				$crear_up["apellidos_up"] = "";
				$crear_up["email_up"] = "";
				$crear_up["pass"] = "";
				$crear_up["confirmpass"] = "";

				$_SESSION["crear_up"] = $crear_up;
			} else if (isset($_SESSION["haPasadoPorModificar"]) && !isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-danger' role='alert'>El usuario profesor no se ha podido modificar. Le recordamos que no puede repetir ni el nick ni el email</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
			}
		?>


</div>
</div>


	
<div class="row">
<div class="col-sm">
			
				<fieldset class="border p-2">
			<legend class="w-auto">
			Modificar Usuario Profesor
			</legend>

			<form id="profeAForm" method="post" action="validacionUsuarioProfesor.php">
		
			<div class="form-group">
			<label for="nick_up">Nuevo Nick</label>
			<input id="nick_up" name="nick_up" type="text" class="form-control"  value="<?php echo $nickProfesor; ?>" required/>
			</div>

			<div class="form-group">
			<label for="pass">Nueva contraseña</label>
			<input id="pass" name="pass" type="password" class="form-control" oninput="passwordValidation();" required/>
			</div>

			<div class="form-group">
			<label for="confirmpass">Confirmar Contraseña</label>
			<input type="password" name="confirmpass" id="confirmpass" class="form-control" oninput="passwordConfirmationMod();" required/>
			</div>

			<div class="form-group">
			<label for="nombre_up">Nuevo Nombre</label>
			<input id="nombre_up" name="nombre_up" type="text" class="form-control" value="<?php echo $nombre; ?>" required/>
			</div>

			<div class="form-group">
			<label for="apellidos_up">Nuevos Apellidos</label>
			<input id="apellidos_up" name="apellidos_up" type="text" class="form-control" value="<?php echo $apellidos; ?>" required/>
			</div>

			<div class="form-group">
			<label for="email_up">Nuevo Email</label>
			<input class="form-control" id="email_up" name="email_up" type="email" value="<?php echo $email; ?>" required/>
			<br>
			</div>

<input name="OID_UP" id="OID_UP" type="hidden" value="<?php echo $oidProfesor; ?>"/>
<input name="modificar" id="modificar" type="hidden" value="true"/>
			<button type="submit"  id="profeA" class="btn btn-primary" value="Modificar profesor">
			Modificar profesor
			</button>
			</form>
			</fieldset>
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