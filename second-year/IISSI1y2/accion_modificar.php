<?php
session_start();
require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

if (isset($_SESSION["haPasadoPorModificar"])) {
	unset($_SESSION["haPasadoPorModificar"]);
}

if (isset($_SESSION["haSidoModificado"])) {
	unset($_SESSION["haSidoModificado"]);
}

$_SESSION["haPasadoPorModificar"] = true;

//------------MODIFICAR UN ALUMNO---------------
if (isset($_SESSION["crear_al"])) {
	$conexion = crearConexionBD();
	$alumnoAModificar = $_SESSION["crear_al"];

	$excepcion = modificar_alumno($conexion, $alumnoAModificar);
	cerrarConexionBD($conexion);
	unset($_SESSION["modificar_al"]);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoModificado"] = true;
		$_SESSION["clase"] = $alumnoAModificar["clase"];
		Header("Location: gestionDeAlumnos.php");
	}

}
//------------MODIFICAR UN USUARIO PROFESOR---------------
elseif (isset($_SESSION["crear_up"])) {
	$conexion = crearConexionBD();
	$usuarioProfesorModificar = $_SESSION["crear_up"];
	$oidUp = $usuarioProfesorModificar["OID_UP"];

	$excepcion = modificar_profesor($conexion, $usuarioProfesorModificar);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_up"]);
	if ($excepcion != "") {
		
		if (strpos($excepcion, "ORA-00001")) {
			Header("Location: modificarUsuarioProfesor.php");
			break;
		}
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoModificado"] = true;
		$_SESSION["oidUpDespues"] = $oidUp;
		Header("Location: modificarUsuarioProfesor.php");
	}
	break;
}
//------------MODIFICAR UN USUARIO FAMILIA---------------
elseif (isset($_SESSION["crear_uf"])) {
	unset($_SESSION["familia"]);
	$conexion = crearConexionBD();
	$modificarUsuarioFamilia = $_SESSION["crear_uf"];
	$oidUf = $modificarUsuarioFamilia["OID_UF"];

	$excepcion = modificar_familia($conexion, $modificarUsuarioFamilia);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_uf"]);
	if ($excepcion != "") {
			if (strpos($excepcion, "ORA-00001")) {
			Header("Location: modificarUsuarioFamilia.php");
			break;
		}
		
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoModificado"] = true;
		$_SESSION["oidUfDespues"] = $oidUf;
		Header("Location: modificarUsuarioFamilia.php");
	}

}
//------------MODIFICAR UNA CALIFICACION---------------
elseif (isset($_SESSION["calificacion"])) {
	$conexion = crearConexionBD();
	$calificacion = $_SESSION["calificacion"];
	$oidCa = $calificacion["OID_CA"];
	$oidAs = $calificacion["OID_AS"];
	$valorCa = $calificacion["VALOR_CA"];

	$excepcion = modificar_calificacion($conexion, $oidCa, $valorCa);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoModificado"] = true;
		$_SESSION["asignaturaDespues"] = $oidAs;
		unset($_SESSION["calificacion"]);
		Header("Location: gestionDeCalificaciones.php");
	}

}

//Si se intenta acceder al accion, fuera de la aplicacion, por malechor
else {
	Header("Location: index.php");
}
?>
