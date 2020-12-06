<?php

//===================================
//==============AVISOS===============
//===================================

function consultarTodosAvisosFamilia($conexion) {
	try {
		$consulta = "SELECT * FROM AVISOS";

		$stmt = $conexion -> prepare($consulta);
		$stmt -> execute();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function listarAvisos($conexion) {
	try {

		$consulta = "SELECT * FROM AVISOS, USUARIOPROFESORES" . " WHERE (USUARIOPROFESORES.NICK = :nickSesion" . "	  AND AVISOS.OID_UP = USUARIOPROFESORES.OID_UP)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':nickSesion', $_SESSION["login"]);
		$stmt -> execute();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function crearAviso($conexion, $titulo, $cuerpo, $archivo, $fechaSuceso, $profesor) {
	$fechaS = date('d/m/Y', strtotime($fechaSuceso));
	try {
		$stmt = $conexion -> prepare("CALL CREARAVISO(:titulo,:cuerpo,:archivoURL,:fechasuceso,:profesor)");
		$stmt -> bindParam(':titulo', $titulo);
		$stmt -> bindParam(':cuerpo', $cuerpo);
		$stmt -> bindParam(':archivoURL', $archivo);
		$stmt -> bindParam(':fechasuceso', $fechaS);
		$stmt -> bindParam(':profesor', $profesor);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function borrarAviso($conexion, $avisoABorrar) {
	try {
		$stmt = $conexion -> prepare("CALL BORRARAVISOS(:avisoBorrar)");
		$stmt -> bindParam(':avisoBorrar', $avisoABorrar);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//=============ALUMNOS===============
//===================================

function crear_alumno($conexion, $nuevoAlumno) {
	$fechaNacimiento = date('d/m/Y', strtotime($nuevoAlumno["fechaNacimiento"]));
	try {
		$stmt = $conexion -> prepare('CALL CREARALUMNO(:nombre,:apellidos,:fechaNacimiento,:tutor,:clase)');
		$stmt -> bindParam(':nombre', $nuevoAlumno["nombre_al"]);
		$stmt -> bindParam(':apellidos', $nuevoAlumno["apellidos_al"]);
		$stmt -> bindParam(':fechaNacimiento', $fechaNacimiento);
		$stmt -> bindParam(':tutor', $nuevoAlumno["tutor"]);
		$stmt -> bindParam(':clase', $nuevoAlumno["clase"]);
		$stmt -> execute();
		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function borrar_alumno($conexion, $alumnoABorrar) {
	try {
		$stmt = $conexion -> prepare("CALL BORRARALUMNO(:alumnoBorrar)");
		$stmt -> bindParam(':alumnoBorrar', $alumnoABorrar);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function modificar_alumno($conexion, $alumnoAModificar) {
	$fechaNacimiento = date('d/m/Y', strtotime($alumnoAModificar["fechaNacimiento"]));
	try {
		$stmt = $conexion -> prepare("UPDATE ALUMNOS SET NOMBRE_AL = :nNombre, APELLIDOS_AL = :nApellidos, 
									FECHANACIMIENTO = :nFechaNacimiento, OID_UF = :nOidUf, OID_CL = :nOidCl
									WHERE OID_AL=:alumnoModificar");
		$stmt -> bindParam(':alumnoModificar', $alumnoAModificar["OID_AL"]);
		$stmt -> bindParam(':nNombre', $alumnoAModificar["nombre_al"]);
		$stmt -> bindParam(':nApellidos', $alumnoAModificar["apellidos_al"]);
		$stmt -> bindParam(':nFechaNacimiento', $fechaNacimiento);
		$stmt -> bindParam(':nOidUf', $alumnoAModificar["tutor"]);
		$stmt -> bindParam(':nOidCl', $alumnoAModificar["clase"]);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function listarAlumnos($conexion) {
	try {
		$consulta = "SELECT * FROM ALUMNOS ORDER BY APELLIDOS_AL ASC";
		$stmt = $conexion -> query($consulta);

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function listarAlumnosDeUnaClase($conexion, $oidClase) {
	try {
		$consulta = "SELECT * FROM ALUMNOS WHERE OID_CL=:oidClase ORDER BY APELLIDOS_AL ASC";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidClase', $oidClase);
		$stmt -> execute();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantosAlumnosDeUnaClase($conexion, $oidClase) {
	try {
		$consulta = "SELECT COUNT(*) FROM ALUMNOS WHERE OID_CL=:oidClase";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidClase', $oidClase);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantasAsignaturasAsociadasAUnaClase($conexion, $oidClase) {
	try {
		$consulta = "SELECT COUNT(*) FROM ASIGNATURAS WHERE OID_CL=:oidClase";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidClase', $oidClase);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantasClasesHayCon0AlumnosYCon0Asignaturas($conexion) {
	try {
		$consulta = "SELECT COUNT (*) FROM CLASES WHERE ( (SELECT COUNT (*) FROM ALUMNOS WHERE ALUMNOS.OID_CL = CLASES.OID_CL) = 0) AND ((SELECT COUNT (*) FROM ASIGNATURAS WHERE ASIGNATURAS.OID_CL = CLASES.OID_CL) = 0)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function queClasesHayCon0AlumnosYCon0Asignaturas($conexion) {
	try {
		$consulta = "SELECT OID_CL FROM CLASES WHERE ( (SELECT COUNT (*) FROM ALUMNOS WHERE ALUMNOS.OID_CL = CLASES.OID_CL) = 0) AND ((SELECT COUNT (*) FROM ASIGNATURAS WHERE ASIGNATURAS.OID_CL = CLASES.OID_CL) = 0)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function listarTutores($conexion) {
	try {
		$consulta = "SELECT * FROM USUARIOFAMILIAS ORDER BY APELLIDOS_UF ASC";
		$stmt = $conexion -> query($consulta);

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function getNombreAlumno($conexion, $oidAl) {
	try {
		$consulta = "SELECT NOMBRE_AL, APELLIDOS_AL FROM ALUMNOS WHERE OID_AL=:oidAl";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAl', $oidAl);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["NOMBRE_AL"] . " " . $result["APELLIDOS_AL"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function getNombreTutor($conexion, $oid_uf) {
	try {
		$consulta = "SELECT NOMBRE_UF, APELLIDOS_UF FROM USUARIOFAMILIAS WHERE OID_UF=:familia";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':familia', $oid_uf);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["APELLIDOS_UF"] . ", " . $result["NOMBRE_UF"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//=============CLASES================
//===================================

function listarClases($conexion) {
	try {
		$consulta = "SELECT * FROM CLASES ORDER BY CURSO ASC";
		$stmt = $conexion -> query($consulta);

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function obtenerCursoGrupoDeUnaClase($conexion, $oidClase) {

	try {
		$consulta = "SELECT CURSO, GRUPO FROM CLASES WHERE OID_CL=:oidClase";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidClase', $oidClase);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["CURSO"] . "º " . $result["GRUPO"];

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function borrar_clase($conexion, $claseABorrar) {
	try {
		$stmt = $conexion -> prepare("CALL BORRARCLASE(:claseBorrar)");
		$stmt -> bindParam(':claseBorrar', $claseABorrar);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function crear_clase($conexion, $nuevaClaseCurso, $nuevaClaseGrupo, $nuevaClaseTutor) {
	try {
		$stmt = $conexion -> prepare("CALL CREARCLASE(:curso_cl, :grupo_cl, :profesorTutor)");
		$stmt -> bindParam(':curso_cl', $nuevaClaseCurso);
		$stmt -> bindParam(':grupo_cl', $nuevaClaseGrupo);
		$stmt -> bindParam(':profesorTutor', $nuevaClaseTutor);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//==========USUARIOS PROFESOR=======
//===================================
function listarProfesores($conexion) {
	try {
		$consulta = "SELECT * FROM USUARIOPROFESORES ORDER BY APELLIDOS_UP ASC";
		$stmt = $conexion -> query($consulta);

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function oidProfesor($conexion, $nickConexion) {
	try {
		$stmt = $conexion -> prepare("SELECT * FROM USUARIOPROFESORES WHERE USUARIOPROFESORES.NICK = :nickSesion");
		$stmt -> bindParam(':nickSesion', $nickConexion);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function borrar_profesor($conexion, $profesorABorrar) {
	try {
		$stmt = $conexion -> prepare("CALL BORRARUSUARIOPROFESOR(:profesorBorrar)");
		$stmt -> bindParam(':profesorBorrar', $profesorABorrar);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function crear_profesor($conexion, $nuevoProfesor) {
	$nick = $nuevoProfesor["nick_up"];
	$pass = $nuevoProfesor["pass"];
	$nombre = $nuevoProfesor["nombre_up"];
	$apellidos = $nuevoProfesor["apellidos_up"];
	$email = $nuevoProfesor["email_up"];
	$foto = $nuevoProfesor["foto_up"];

	try {
		$stmt = $conexion -> prepare("CALL CREARUSUARIOPROFESOR(:nick, :pass, :nombre, :apellidos, :email, 0, :foto)");
		$stmt -> bindParam(':nick', $nick);
		$stmt -> bindParam(':pass', $pass);
		$stmt -> bindParam(':nombre', $nombre);
		$stmt -> bindParam(':apellidos', $apellidos);
		$stmt -> bindParam(':email', $email);
		$stmt -> bindParam(':foto', $foto);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function modificar_profesor($conexion, $profesorAModificar) {
	$nick = $profesorAModificar["nick_up"];
	$pass = $profesorAModificar["pass"];
	$nombre = $profesorAModificar["nombre_up"];
	$apellidos = $profesorAModificar["apellidos_up"];
	$email = $profesorAModificar["email_up"];
	//$foto = $profesorAModificar["foto_up_modificar"];
	$viejoProfe = $profesorAModificar["OID_UP"];

	try {
		$stmt = $conexion -> prepare("UPDATE USUARIOPROFESORES SET NICK=:nNICK, CONTRASENYA=:nPASS, NOMBRE_UP=:nNOMBRE, 
									APELLIDOS_UP=:nAPELLIDOS, EMAIL_UP=:nEMAIL, FOTOURL=:nFOTO WHERE OID_UP=:viejoProfe");
		$stmt -> bindParam(':nNICK', $nick);
		$stmt -> bindParam(':nPASS', $pass);
		$stmt -> bindParam(':nNOMBRE', $nombre);
		$stmt -> bindParam(':nAPELLIDOS', $apellidos);
		$stmt -> bindParam(':nEMAIL', $email);
		$stmt -> bindParam(':nFOTO', $foto);
		$stmt -> bindParam(':viejoProfe', $viejoProfe);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}

}

function consultarProfesor($conexion, $oidProfesor) {
	try {
		$consulta = "SELECT * FROM USUARIOPROFESORES WHERE USUARIOPROFESORES.OID_UP = :oidProfesor";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidProfesor', $oidProfesor);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function obtenerNombreApellidosDeUnProfesor($conexion, $oidProfesor) {

	try {
		$consulta = "SELECT NOMBRE_UP, APELLIDOS_UP FROM USUARIOPROFESORES WHERE OID_UP=:oidProfesor";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidProfesor', $oidProfesor);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["NOMBRE_UP"] . " " . $result["APELLIDOS_UP"];

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantosProfesoresHayCon0AvisosYCon0AsignaturasYCon0Clases($conexion) {
	try {
		$consulta = "SELECT COUNT (*) FROM USUARIOPROFESORES WHERE (
((SELECT COUNT (*) FROM AVISOS WHERE AVISOS.OID_UP = USUARIOPROFESORES.OID_UP) = 0) AND 
((SELECT COUNT (*) FROM ASIGNATURAS WHERE ASIGNATURAS.OID_UP = USUARIOPROFESORES.OID_UP) = 0) AND 
((SELECT COUNT (*) FROM CLASES WHERE CLASES.OID_UP = USUARIOPROFESORES.OID_UP) = 0)
)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function queCuantosProfesoresHayCon0AvisosYCon0AsignaturasYCon0Clases($conexion) {
	try {
		$consulta = "SELECT OID_UP FROM USUARIOPROFESORES WHERE (
((SELECT COUNT (*) FROM AVISOS WHERE AVISOS.OID_UP = USUARIOPROFESORES.OID_UP) = 0) AND 
((SELECT COUNT (*) FROM ASIGNATURAS WHERE ASIGNATURAS.OID_UP = USUARIOPROFESORES.OID_UP) = 0) AND 
((SELECT COUNT (*) FROM CLASES WHERE CLASES.OID_UP = USUARIOPROFESORES.OID_UP) = 0)
)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//======USUARIOS FAMILIA=============
//===================================

function borrar_familia($conexion, $familiaABorrar) {
	try {
		$stmt = $conexion -> prepare("CALL BORRARUSUARIOFAMILIA(:familiaBorrar)");
		$stmt -> bindParam(':familiaBorrar', $familiaABorrar);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function crear_familia($conexion, $nuevaFamilia) {
	$nick = $nuevaFamilia["nick_uf"];
	$pass = $nuevaFamilia["pass"];
	$nombre = $nuevaFamilia["nombre_uf"];
	$apellidos = $nuevaFamilia["apellidos_uf"];
	$email = $nuevaFamilia["email_uf"];

	try {
		$stmt = $conexion -> prepare("CALL CREARUSUARIOFAMILIA(:nick, :pass, :nombre, :apellidos, :email)");
		$stmt -> bindParam(':nick', $nick);
		$stmt -> bindParam(':pass', $pass);
		$stmt -> bindParam(':nombre', $nombre);
		$stmt -> bindParam(':apellidos', $apellidos);
		$stmt -> bindParam(':email', $email);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function modificar_familia($conexion, $modificarFamilia) {

	$nick = $modificarFamilia["nick_uf"];
	$pass = $modificarFamilia["pass"];
	$nombre = $modificarFamilia["nombre_uf"];
	$apellidos = $modificarFamilia["apellidos_uf"];
	$email = $modificarFamilia["email_uf"];
	$oidUf = $modificarFamilia["OID_UF"];

	try {
		$stmt = $conexion -> prepare("UPDATE USUARIOFAMILIAS SET NICK=:nNICK, CONTRASENYA=:nPASS, NOMBRE_UF=:nNOMBRE, 
									APELLIDOS_UF=:nAPELLIDOS, EMAIL_UF=:nEMAIL WHERE OID_UF=:oidFamilia");
		$stmt -> bindParam(':nNICK', $nick);
		$stmt -> bindParam(':nPASS', $pass);
		$stmt -> bindParam(':nNOMBRE', $nombre);
		$stmt -> bindParam(':nAPELLIDOS', $apellidos);
		$stmt -> bindParam(':nEMAIL', $email);
		$stmt -> bindParam(':oidFamilia', $oidUf);
		$stmt -> execute();

		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}

}

function consultarFamilia($conexion, $oidFamilia) {
	try {
		$consulta = "SELECT * FROM USUARIOFAMILIAS WHERE USUARIOFAMILIAS.OID_UF = :oidFamilia";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidFamilia', $oidFamilia);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantasFamiliasHayCon0Alumnos($conexion) {
	try {
		$consulta = "SELECT COUNT(*) FROM (SELECT * FROM USUARIOFAMILIAS WHERE ((SELECT COUNT (*) FROM ALUMNOS WHERE ALUMNOS.OID_UF = USUARIOFAMILIAS.OID_UF)) = 0)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function queFamiliasHayCon0Alumnos($conexion) {
	try {
		$consulta = "SELECT OID_UF FROM (SELECT * FROM USUARIOFAMILIAS WHERE ((SELECT COUNT (*) FROM ALUMNOS WHERE ALUMNOS.OID_UF = USUARIOFAMILIAS.OID_UF)) = 0)";
		$stmt = $conexion -> query($consulta);
		$stmt -> execute();
		//$result = $stmt -> fetch();

		return $stmt;
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function obtenerNombreApellidosDeUnaFamilia($conexion, $oidFamilia) {

	try {
		$consulta = "SELECT NOMBRE_UF, APELLIDOS_UF FROM USUARIOFAMILIAS WHERE OID_UF=:oidFamilia";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidFamilia', $oidFamilia);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["NOMBRE_UF"] . " " . $result["APELLIDOS_UF"];

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//==========ASIGNATURAS==============
//===================================

function consultarAsignatura($conexion, $oidAsignatura) {
	try {
		$consulta = "SELECT * FROM ASIGNATURAS WHERE OID_AS = :oidAsignatura";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAsignatura', $oidAsignatura);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["NOMBRE_AS"];

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//=======CALIFICACIONES==============
//===================================

function consultarCalificacionesFamilia($conexion, $oidAlumno) {
	try {
		$consulta = "SELECT * FROM ASIGNATURAS, CALIFICACIONES" . " WHERE (CALIFICACIONES.OID_AL = :oidAlumno" . "   AND CALIFICACIONES.OID_AS = ASIGNATURAS.OID_AS)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAlumno', $oidAlumno);
		$stmt -> execute();

		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}

}

function modificar_calificacion($conexion, $oidViejaCalificacion, $valor_al_modificar) {
	try {
		$stmt = $conexion -> prepare("CALL modificacalificacion(:calificacionModificar,:nValor)");
		$stmt -> bindParam(':calificacionModificar', $oidViejaCalificacion);
		$stmt -> bindParam(':nValor', $valor_al_modificar);
		$stmt -> execute();
		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function crear_calificacion($conexion, $valor, $convocatoria, $oidAl, $oidAs) {
	try {
		$stmt = $conexion -> prepare('CALL crearcalificacion(:valor,:convocatoria,:oid_al,:oid_as)');
		$stmt -> bindParam(':valor', $valor);
		$stmt -> bindParam(':convocatoria', $convocatoria);
		$stmt -> bindParam(':oid_al', $oidAl);
		$stmt -> bindParam(':oid_as', $oidAs);
		$stmt -> execute();
		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function borrar_calificacion($conexion, $oid_ca) {
	try {
		$stmt = $conexion -> prepare('CALL borrarcalificacion(:oid_ca)');
		$stmt -> bindParam(':oid_ca', $oid_ca);
		$stmt -> execute();
		return "";
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarAsignaturasProfesor($conexion) {
	try {
		$consulta = "SELECT ASIGNATURAS.OID_AS,ASIGNATURAS.NOMBRE_AS,ASIGNATURAS.OID_UP,ASIGNATURAS.OID_CL FROM USUARIOPROFESORES,ASIGNATURAS " . "WHERE USUARIOPROFESORES.NICK = :nickSesion AND USUARIOPROFESORES.OID_UP = ASIGNATURAS.OID_UP";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':nickSesion', $_SESSION["login"]);
		$stmt -> execute();

		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarAlumnosAsignatura($conexion, $oidAsignatura) {
	try {
		$consulta = "SELECT OID_AL,NOMBRE_AL,APELLIDOS_AL,FECHANACIMIENTO,OID_UF,OID_UP FROM ALUMNOS, ASIGNATURAS WHERE (ALUMNOS.OID_CL = ASIGNATURAS.OID_CL AND ASIGNATURAS.OID_AS = :oidAsignatura)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAsignatura', $oidAsignatura);
		$stmt -> execute();

		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarCalificacionesAsignatura($conexion, $oidAsignatura) {
	try {
		$consulta = "SELECT * FROM CALIFICACIONES" . "   WHERE OID_AS = :oidAsignatura";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAsignatura', $oidAsignatura);
		$stmt -> execute();

		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarCalificacionesFamiliaPorAsignatura($conexion, $oidAlumno, $oidAsignatura) {
	try {
		$consulta = "SELECT * FROM CALIFICACIONES WHERE (CALIFICACIONES.OID_AL = :oidAlumno AND CALIFICACIONES.OID_AS = :oidAsignatura)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAlumno', $oidAlumno);
		$stmt -> bindParam(':oidAsignatura', $oidAsignatura);
		$stmt -> execute();

		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}

}

//Cuantas calificaciones tiene un alumno
function cuentaCuantasCalificaciones($conexion, $oidAlumno) {
	try {
		$consulta = "SELECT COUNT (*) FROM CALIFICACIONES WHERE OID_AL =:oidAl";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAl', $oidAlumno);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//cuantos alumnos tiene una asignatura
function cuentaCuantosAlumnosTieneUnaAsignatura($conexion, $oidAs) {
	try {
		$consulta = "SELECT COUNT(*) FROM ALUMNOS, ASIGNATURAS WHERE (ALUMNOS.OID_CL = ASIGNATURAS.OID_CL AND ASIGNATURAS.OID_AS = :oidAs)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidAs', $oidAs);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];
	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//=======FICHA PERSONAL==============
//===================================

function consultarFichaPersonalFamilia($conexion) {
	try {
		$consulta = "SELECT * FROM ALUMNOS, CLASES, USUARIOFAMILIAS WHERE (ALUMNOS.OID_UF = USUARIOFAMILIAS.OID_UF AND USUARIOFAMILIAS.NICK = :nickSesion AND ALUMNOS.OID_CL = CLASES.OID_CL)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':nickSesion', $_SESSION["login"]);
		$stmt -> execute();
		return $stmt;

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function cuentaCuantosAlumnos($conexion) {
	try {
		$consulta = "SELECT COUNT(*) FROM ALUMNOS, CLASES, USUARIOFAMILIAS WHERE (ALUMNOS.OID_UF = USUARIOFAMILIAS.OID_UF AND USUARIOFAMILIAS.NICK = :nickSesion AND ALUMNOS.OID_CL = CLASES.OID_CL)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':nickSesion', $_SESSION["login"]);
		$stmt -> execute();
		$result = $stmt -> fetch();

		return $result["COUNT(*)"];

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarFichaPersonalProfesor($conexion) {
	try {
		$consulta = "SELECT * FROM USUARIOPROFESORES WHERE (USUARIOPROFESORES.NICK = :nickSesion)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':nickSesion', $_SESSION["login"]);
		$stmt -> execute();
		return $stmt -> fetch(PDO::FETCH_ASSOC);

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

function consultarFichaPersonalProfesorByFamilia($conexion, $profesor) {
	try {
		$consulta = "SELECT * FROM USUARIOPROFESORES WHERE (USUARIOPROFESORES.OID_UP = :oidProfesor)";
		$stmt = $conexion -> prepare($consulta);
		$stmt -> bindParam(':oidProfesor', $profesor);
		$stmt -> execute();
		return $stmt -> fetch(PDO::FETCH_ASSOC);

	} catch(PDOException $e) {
		return $e -> getMessage();
	}
}

//===================================
//===================LOGIN===========
//===================================

function consultarUsuarioFamilias($conexion, $nick, $contrasenya) {
	$stmt = $conexion -> prepare("SELECT * FROM Usuariofamilias WHERE NICK=:nick AND CONTRASENYA=:contrasenya");
	$stmt -> bindParam(':nick', $nick);
	$stmt -> bindParam(':contrasenya', $contrasenya);
	$stmt -> execute();
	return $stmt -> fetch();

}

function consultarUsuarioProfesores($conexion, $nick, $contrasenya) {
	$stmt = $conexion -> prepare("SELECT * FROM Usuarioprofesores WHERE NICK=:nick AND CONTRASENYA=:contrasenya");
	$stmt -> bindParam(':nick', $nick);
	$stmt -> bindParam(':contrasenya', $contrasenya);
	$stmt -> execute();
	return $stmt -> fetch();

}
?>