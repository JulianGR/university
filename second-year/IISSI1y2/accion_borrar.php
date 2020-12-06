<?php
session_start();
require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

if (isset($_SESSION["haPasadoPorBorrar"])) {
	unset($_SESSION["haPasadoPorBorrar"]);
}

if (isset($_SESSION["haSidoBorrado"])) {
	unset($_SESSION["haSidoBorrado"]);
}

$_SESSION["haPasadoPorBorrar"] = true;

//------------BORRAR UN ALUMNO---------------
if (isset($_POST["OID_AL"])) {
	$conexion = crearConexionBD();
	$oidAlumnoABorrar = $_POST["OID_AL"];
	$oidClase = $_POST["OID_CL"];
	$excepcion = borrar_alumno($conexion, $oidAlumnoABorrar);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		$_SESSION["claseDespues"] = $oidClase;
		Header("Location: gestionDeAlumnos.php");
	}

}
//------------BORRAR UN PROFESOR---------------
elseif (isset($_SESSION["profesor"])) {
	$conexion = crearConexionBD();
	$profesorBorrar = $_SESSION["profesor"];
	$excepcion = borrar_profesor($conexion, $profesorBorrar);
	cerrarConexionBD($conexion);
	unset($_SESSION["profesor"]);
	if ($excepcion != "") {

		if (strpos($excepcion, "ORA-01407")) {
			Header("Location: gestionDeUsuarioProfesor.php");
			break;
		}

		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		Header("Location: gestionDeUsuarioProfesor.php");
	}

}
//------------BORRAR UNA FAMILIA---------------
elseif (isset($_SESSION["familia_a_borrar"])) {
	$conexion = crearConexionBD();
	$familiaBorrar = $_SESSION["familia_a_borrar"];
	$excepcion = borrar_familia($conexion, $familiaBorrar);
	cerrarConexionBD($conexion);
	unset($_SESSION["familia_a_borrar"]);
	if ($excepcion != "") {

		if (strpos($excepcion, "ORA-01407")) {
			Header("Location: gestionDeUsuarioFamilia.php");
			break;
		}

		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		Header("Location: gestionDeUsuarioFamilia.php");
	}

}
//------------BORRAR UNA CLASE---------------
elseif (isset($_SESSION["clase_a_borrar"])) {
	$conexion = crearConexionBD();
	$claseABorrar = $_SESSION["clase_a_borrar"];
	$excepcion = borrar_clase($conexion, $claseABorrar);
	cerrarConexionBD($conexion);
	unset($_SESSION["clase_a_borrar"]);
	if ($excepcion != "") {

		//Si contiene ese código de error entonces es que se ha intentado borrar una clase con alumnos asociados
		if (strpos($excepcion, "ORA-01407")) {
			Header("Location: gestionDeClases.php");
			break;
		}

		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		Header("Location: gestionDeClases.php");
	}

}
//------------BORRAR UN AVISO---------------
elseif (isset($_POST["aviso_a_borrar"])) {
	$conexion = crearConexionBD();
	$avisoABorrar = $_POST["aviso_a_borrar"];
	$excepcion = borrarAviso($conexion, $avisoABorrar);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		Header("Location: indiceProfesor.php");
	}

}

//------------BORRAR UNA CALIFICACION---------------
elseif (isset($_POST["OID_CA"]) && isset($_POST["OID_AS"])) {
	$conexion = crearConexionBD();
	$oidCa = $_POST["OID_CA"];
	$oidAs = $_POST["OID_AS"];
	$excepcion = borrar_calificacion($conexion, $oidCa);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoBorrado"] = true;
		$_SESSION["asignaturaDespues"] = $oidAs;
		Header("Location: gestionDeCalificaciones.php");
	}

}
//Si se intenta acceder al accion, fuera de la aplicacion, por malechor

else {
	Header("Location: index.php");
}
?>
