<?php
session_start();
require_once ("reutilizables/gestionBD.php");
require_once ("reutilizables/funciones.php");

if (isset($_SESSION["haPasadoPorCrear"])) {
	unset($_SESSION["haPasadoPorCrear"]);
}

if (isset($_SESSION["haSidoCreado"])) {
	unset($_SESSION["haSidoCreado"]);
}

$_SESSION["haPasadoPorCrear"] = true;

//------------CREAR UNA CLASE---------------
if (isset($_SESSION["crear_cl"])) {
	$conexion = crearConexionBD();
	$nuevaClase = $_SESSION["crear_cl"];

	$nuevaClaseCurso = $nuevaClase["curso_cl"];
	$nuevaClaseGrupo = $nuevaClase["grupo_cl"];
	$nuevaClaseTutor = $nuevaClase["profesor_tutor"];

	$excepcion = crear_clase($conexion, $nuevaClaseCurso, $nuevaClaseGrupo, $nuevaClaseTutor);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_cl"]);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		Header("Location: gestionDeClases.php");
	}

}

//------------CREAR UN ALUMNO---------------
elseif (isset($_SESSION["crear_al"]) && ((($_SESSION["enEstaClase"]) == false) || !isset($_SESSION["enEstaClase"]))) {
	$conexion = crearConexionBD();
	$nuevoAlumno = $_SESSION["crear_al"];

	$excepcion = crear_alumno($conexion, $nuevoAlumno);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		Header("Location: seleccionarClase.php");
	}

}
//------------CREAR UN ALUMNO EN ESTA CLASE	---------------
elseif (isset($_SESSION["crear_al"]) && (($_SESSION["enEstaClase"]) == true)) {
	$conexion = crearConexionBD();
	$nuevoAlumno = $_SESSION["crear_al"];

	$excepcion = crear_alumno($conexion, $nuevoAlumno);
	cerrarConexionBD($conexion);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		$_SESSION["clase"] = $nuevoAlumno["clase"];
		Header("Location: gestionDeAlumnos.php");
	}

}
//------------CREAR UN USUARIO PROFESOR---------------
elseif (isset($_SESSION["crear_up"])) {
	$conexion = crearConexionBD();
	$nuevoUsuarioProfesor = $_SESSION["crear_up"];

	$excepcion = crear_profesor($conexion, $nuevoUsuarioProfesor);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_up"]);
	if ($excepcion != "") {
		if (strpos($excepcion, "ORA-00001")) {
			Header("Location: gestionDeUsuarioProfesor.php");
			break;
		}

		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		Header("Location: gestionDeUsuarioProfesor.php");
	}

}
//------------CREAR UN USUARIO FAMILIA---------------
elseif (isset($_SESSION["crear_uf"])) {
	$conexion = crearConexionBD();
	$nuevoUsuarioFamilia = $_SESSION["crear_uf"];

	$excepcion = crear_familia($conexion, $nuevoUsuarioFamilia);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_uf"]);
	if ($excepcion != "") {
		if (strpos($excepcion, "ORA-00001")) {
			Header("Location: gestionDeUsuarioFamilia.php");
			break;
		}

		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		Header("Location: gestionDeUsuarioFamilia.php");
	}

}
//------------CREAR UN AVISO---------------

elseif (isset($_SESSION["crear_av"])) {
	$conexion = crearConexionBD();

	$avisoNuevo = $_SESSION["crear_av"];

	$nuevoAvisoTitulo = $avisoNuevo["titulo_av"];
	$nuevoAvisoDescripcion = $avisoNuevo["descripcion_av"];
	$nuevoAvisoSuceso = $avisoNuevo["fechaSuceso"];
	$nuevoAvisoArchivo = $avisoNuevo["archivoURL_av"];
	$nuevoAvisoProfesor = $avisoNuevo["profesor_av"];

	$excepcion = crearAviso($conexion, $nuevoAvisoTitulo, $nuevoAvisoDescripcion, $nuevoAvisoArchivo, $nuevoAvisoSuceso, $nuevoAvisoProfesor);
	cerrarConexionBD($conexion);
	unset($_SESSION["crear_av"]);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		Header("Location: crearAviso.php");
	}

}

//------------CREAR UNA CALIFICACION---------------
elseif (isset($_SESSION["crearCalificacion"])) {
	$conexion = crearConexionBD();
	$crearCalificacion = $_SESSION["crearCalificacion"];

	$nuevaConvocatoria = $crearCalificacion["convocatoria"];
	$nuevoValor = $crearCalificacion["VALOR_CA"];
	$nuevoOID_AS = $crearCalificacion["OID_AS"];
	$nuevoOID_AL = $crearCalificacion["OID_AL"];

	$excepcion = crear_calificacion($conexion, $nuevoValor, $nuevaConvocatoria, $nuevoOID_AL, $nuevoOID_AS);
	cerrarConexionBD($conexion);
	unset($_SESSION["crearCalificacion"]);
	if ($excepcion != "") {
		$_SESSION["excepcion"] = $excepcion;
		Header("Location: excepcion.php");
	} else {
		$_SESSION["haSidoCreado"] = true;
		$_SESSION["asignaturaDespues"] = $nuevoOID_AS;
		$_SESSION["alumnoDespues"] = $nuevoOID_AL;

		Header("Location: gestionDeCalificaciones.php");
	}

}

//Si se intenta acceder al accion, fuera de la aplicacion, por malechor
else {
	Header("Location: index.php");
}
?>
