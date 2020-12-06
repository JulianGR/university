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
	Header("Location: gestionDeUsuarioFamilia.php");
} else {
	if (!isset($_SESSION["crear_uf"])) {
		$crear_uf["nombre_uf"] = "";
		$crear_uf["nick_uf"] = "";
		$crear_uf["apellidos_uf"] = "";
		$crear_uf["email_uf"] = "";
		$crear_uf["pass"] = "";
		$crear_uf["confirmpass"] = "";

		$_SESSION["crear_uf"] = $crear_uf;
	} else {
		$crear_uf = $_SESSION["crear_uf"];
	}
}

if (isset($_SESSION["familia"])) {
	$oidFamilia = $_SESSION["familia"];
} else {
	$oidFamilia = $_SESSION["oidUfDespues"];
}

$conexion = crearConexionBD();
$datosFamila = consultarFamilia($conexion, $oidFamilia);
if (isset($_SESSION["errores"])) {
	$errores = $_SESSION["errores"];
	unset($_SESSION["errores"]);
}
$nickFamilia = $datosFamila["NICK"];
$nombre = $datosFamila["NOMBRE_UF"];
$apellidos = $datosFamila["APELLIDOS_UF"];
$email = $datosFamila["EMAIL_UF"];
?>

<!DOCTYPE html>
<html lang="es">
<head>
  <?php
include_once ("reutilizables/head.php");
		?>
		
		<script>
			$(document).ready(function() {
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

<h1 class="text-center">Modificando a <strong><?php echo $nombre . " " . $apellidos; ?></strong></h1>

<?php
include_once ("reutilizables/errorFormulario.php");
			?>

			<?php

			//Mensaje de modificación
			if (isset($_SESSION["haPasadoPorModificar"]) && isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-success' role='alert'>El usuario familia se ha modificado correctamente</div>";
				unset($_SESSION["haSidoModificado"]);
				unset($_SESSION["haPasadoPorModificar"]);
				unset($_SESSION["crear_uf"]);
				$crear_uf["nombre_uf"] = "";
				$crear_uf["nick_uf"] = "";
				$crear_uf["apellidos_uf"] = "";
				$crear_uf["email_uf"] = "";
				$crear_uf["pass"] = "";
				$crear_uf["confirmpass"] = "";

				$_SESSION["crear_uf"] = $crear_uf;
			} else if (isset($_SESSION["haPasadoPorModificar"]) && !isset($_SESSION["haSidoModificado"])) {
				echo "<div class='alert alert-danger' role='alert'>El usuario familia no se ha podido modificar. Le recordamos que no puede repetir ni el nick ni el email</div>";
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
			Modificar Familia
			</legend>

			<form id="FamiliaAForm" method="post" action="validacionUsuarioFamilia.php">
		
			<div class="form-group">
			<label for="nick_uf">Nuevo Nick</label>
			<input id="nick_uf" name="nick_uf" type="text" class="form-control"  value="<?php echo $nickFamilia; ?>" required/>
			</div>

			<div class="form-group">
			<label for="pass">Nueva contraseña</label>
			<input id="pass" name="pass" type="password" class="form-control" oninput="passwordValidation();" required/>
			</div>

			<div class="form-group">
			<label for="confirmpass">Confirmar Contraseña</label>
			<input type="password" name="confirmpass" id="confirmpass" class="form-control" oninput="passwordConfirmation();" required/>
			</div>

			<div class="form-group">
			<label for="nombre_uf">Nuevo Nombre</label>
			<input id="nombre_uf" name="nombre_uf" type="text" class="form-control" value="<?php echo $nombre; ?>" required/>
			</div>

			<div class="form-group">
			<label for="apellidos_uf">Nuevos Apellidos</label>
			<input id="apellidos_uf" name="apellidos_uf" type="text" class="form-control" value="<?php echo $apellidos; ?>" required/>
			</div>

			<div class="form-group">
			<label for="email_uf">Nuevo Email</label>
			<input class="form-control" id="email_uf" name="email_uf" type="email" value="<?php echo $email; ?>" required/>
			<br>
			</div>

<input name="OID_UF" id="OID_UF" type="hidden" value="<?php echo $oidFamilia; ?>"/>
<input name="modificar" id="modificar" type="hidden" value="true"/>
			<button type="submit" id="FamiliaA" class="btn btn-primary" value="Modificar familia">
			Modificar familia
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