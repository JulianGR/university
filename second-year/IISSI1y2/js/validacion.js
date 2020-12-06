//====================VALIDACION CREAR AVISO====================
function validacionCrearAviso() {

	function validateCrearAviso() {
		var error1 = valTitulo();
		var error2 = valDescripcion();
		var error3 = valFechaSuceso();
		return (error1.length == 0 && error2.length == 0 && error3.length == 0);
	}


	$('#crearAviso').click(function() {
		validateCrearAviso();
	});

	function valTitulo() {
		var titulo_av = document.getElementById("titulo_av");
		var valTitulo_av = $('#titulo_av').val().trim();
		if (valTitulo_av == "" || valTitulo_av == null) {
			var error = "Introduzca un título";
		} else {
			var error = "";
		}
		titulo_av.setCustomValidity(error);
		return error;
	}

	function valDescripcion() {
		var descripcion_av = document.getElementById("descripcion_av");
		var valDescripcion_av = $('#descripcion_av').val().trim();
		if (valDescripcion_av == "" || valDescripcion_av == null) {
			var error = "Introduzca una descripción";
		} else {
			var error = "";
		}
		descripcion_av.setCustomValidity(error);
		return error;
	}

	function valFechaSuceso() {
		var fechaSuceso = document.getElementById("fechaSuceso");
		var valFechaSuceso = new Date($('#fechaSuceso').val());
		var hoy = new Date();

		if (valFechaSuceso == '' || valFechaSuceso == null || valFechaSuceso.getTime() < hoy.getTime()) {
			var error = "Introduzca una fecha posterior a hoy";
		} else {
			var error = "";
		}
		fechaSuceso.setCustomValidity(error);
		return error;
	}

}

//====================VALIDACION INDEX===================

function validateIndex() {

	function validaNick() {
		var nick = document.getElementById("nick");
		var resultado = true;

		if ($('#nick').val().trim() == "" || $('#nick').val().trim() == null) {
			nick.setCustomValidity("Introduzca su nick");
			resultado = false;
		} else {
			nick.setCustomValidity('');
		}

		return resultado;
	}

	function validaContrasenya() {
		var contrasenya = document.getElementById("contrasenya");
		var resultado = true;

		if ($('#contrasenya').val().trim() == "" || $('#contrasenya').val().trim() == null) {
			contrasenya.setCustomValidity("Introduzca su contraseña");
			resultado = false;
		} else {
			contrasenya.setCustomValidity('');
		}

		return resultado;
	}

	function valPerfil() {
		var checkUsuarioFamilia = document.getElementById("usuarioFamilia").checked;
		var checkUsuarioProfesor = document.getElementById("usuarioProfesor").checked;
		var checkAdmin = document.getElementById("admin").checked;
		var resultado = true;

		var boton = document.getElementById("enviar");

		if (checkUsuarioFamilia == false && checkUsuarioProfesor == false && checkAdmin == false) {
			boton.setCustomValidity("Tiene que seleccionar uno de los 3 perfiles");
			resultado = false;
		} else {
			boton.setCustomValidity('');
		}

		return resultado;
	}


	$('#enviar').click(function() {
		validaNick();
		validaContrasenya();
		valPerfil();
	});

}

//====================VALIDACION CREAR CALIFICACION===================
function validateCreaCalificacion() {

	function validaConvocatoria() {
		var convocatoria = document.getElementById("convocatoria");
		var resultado = true;

		if ($('#convocatoria').val().trim() == "" || $('#convocatoria').val().trim() == null) {
			convocatoria.setCustomValidity("Introduzca una convocatoria");
			resultado = false;
		} else {
			convocatoria.setCustomValidity('');
		}

		return resultado;
	}

	function validaValorCa() {
		var valorCa = document.getElementById("VALOR_CA");
		var resultado = true;

		if ($('#VALOR_CA').val().trim() == "" || $('#VALOR_CA').val().trim() == null) {
			valorCa.setCustomValidity("Introduzca un valor");
			resultado = false;
		} else if ($('#VALOR_CA').val() < 0 || $('#VALOR_CA').val() > 10) {
			valorCa.setCustomValidity("Introduzca un valor entre 0 y 10");
			resultado = false;
		} else {
			valorCa.setCustomValidity('');
		}

		return resultado;
	}


	$('#enviar').click(function() {
		validaConvocatoria();
		validaValorCa();
	});

}

//====================VALIDACION MODIFICAR CALIFICACION===================
function validateModificarCalificacion() {

	function validaValorCa() {
		var valorCa = document.getElementById("VALOR_CA_MOD");

		var resultado = true;

		if ($('#VALOR_CA_MOD').val().trim() == "" || $('#VALOR_CA_MOD').val().trim() == null) {
			valorCa.setCustomValidity("Introduzca una calificación");
			resultado = false;
		} else if ($('#VALOR_CA_MOD').val() < 0 || $('#VALOR_CA_MOD').val() > 10) {
			valorCa.setCustomValidity("Introduzca un valor entre 0 y 10");
			resultado = false;
		} else {
			valorCa.setCustomValidity('');
		}

		return resultado;
	}


	$('#grabar').click(function() {
		validaValorCa();
	});

}

//====================VALIDACION CREAR CLASE===================
function validateCreaClase() {

	function validaCurso() {
		var curso = document.getElementById("curso_cl");
		var resultado = true;

		if ($('#curso_cl').val().trim() == "" || $('#curso_cl').val().trim() == null) {
			curso.setCustomValidity("Introduzca un curso");
			resultado = false;
		} else if ($('#curso_cl').val() < 0 || $('#curso_cl').val() > 6) {
			valorCa.setCustomValidity("Introduzca un valor entre 0 y 6");
			resultado = false;
		} else {
			curso.setCustomValidity('');
		}

		return resultado;
	}

	function validaGrupo() {
		var grupo = document.getElementById("grupo_cl");
		var resultado = true;

		if ($('#grupo_cl').val().trim() == "" || $('#grupo_cl').val().trim() == null) {
			grupo.setCustomValidity("Introduzca un valor");
			resultado = false;
		}else if($('#grupo_cl').val().trim().length != 1){
			grupo.setCustomValidity("El grupo debe tener únicamente un caracter");
			resultado = false;
		} else {
			grupo.setCustomValidity('');
		}

		return resultado;
	}


	$('#crearClaseForm').click(function() {
		validaCurso();
		validaGrupo();
	});

}

//====================VALIDACION BORRAR CLASE===================
function validateBorraClase() {

	function validaClaseABorrar() {
		var claseABorrar = document.getElementById("clase_a_borrar");
		var resultado = true;

		if ($('#clase_a_borrar').val().trim() == "" || $('#clase_a_borrar').val().trim() == null || isNaN($('#clase_a_borrar').val().trim())) {
			claseABorrar.setCustomValidity("Seleccione una clase");
			resultado = false;
		} else {
			claseABorrar.setCustomValidity('');
		}

		return resultado;
	}


	$('#enviarBorrarClase').click(function() {
		validaClaseABorrar();
	});

}

//====================VALIDACION SELECCIONAR ASIGNATURA===================
function validateSeleccionarAsignatura() {

	function validaSeleccionarAsignatura() {
		var seleccionarAsignatura = document.getElementById("asignatura");
		var resultado = true;

		if ($('#asignatura').val().trim() == "" || $('#asignatura').val().trim() == null || isNaN($('#asignatura').val().trim())) {
			seleccionarAsignatura.setCustomValidity("Seleccione una asignatura");
			resultado = false;
		} else {
			seleccionarAsignatura.setCustomValidity('');
		}

		return resultado;
	}


	$('#seleccionaAsig').click(function() {
		validaSeleccionarAsignatura();
	});

}

//====================VALIDACION BORRAR USUARIO PROFESOR===================
function validateBorraUsuarioProfesor() {

	function validaProfesorABorrar() {
		var profeABorrar = document.getElementById("profesor");
		var resultado = true;

		if ($('#profesor').val().trim() == "" || $('#profesor').val().trim() == null || isNaN($('#profesor').val().trim())) {
			profeABorrar.setCustomValidity("Seleccione una profesor");
			resultado = false;
		} else {
			profeABorrar.setCustomValidity('');
		}

		return resultado;
	}


	$('#borrarProfesor').click(function() {
		validaProfesorABorrar();
	});

}

//====================VALIDACION SELECT MODIFICAR USUARIO PROFESOR===================
function validateModificarUsuarioProfesor() {

	function validaProfesorAModificar() {
		var profeAModificar = document.getElementById("profesor");
		var resultado = true;

		if ($('#profesor').val().trim() == "" || $('#profesor').val().trim() == null|| isNaN($('#profesor').val().trim())) {
			profeAModificar.setCustomValidity("Seleccione una profesor");
			resultado = false;
		} else {
			profeAModificar.setCustomValidity('');
		}

		return resultado;
	}


	$('#modificarProfesor').click(function() {
		validaProfesorAModificar();
	});

}

//====================VALIDACION CREAR Y MODIFICAR USUARIO PROFESOR===================
function validateUsuarioProfesor() {

	function validaNickUp() {
		var nick = document.getElementById("nick_up");
		var resultado = true;

		if ($('#nick_up').val().trim() == "" || $('#nick_up').val().trim() == null) {
			nick.setCustomValidity("Introduzca un nick");
			resultado = false;
		} else {
			nick.setCustomValidity('');
		}

		return resultado;
	}

	function validaNombreUp() {
		var nombre = document.getElementById("nombre_up");
		var resultado = true;

		if ($('#nombre_up').val().trim() == "" || $('#nombre_up').val().trim() == null) {
			nombre.setCustomValidity("Introduzca un nombre");
			resultado = false;
		} else {
			nombre.setCustomValidity('');
		}

		return resultado;
	}

	function validaApellidosUp() {
		var apellidos = document.getElementById("apellidos_up");
		var resultado = true;

		if ($('#apellidos_up').val().trim() == "" || $('#apellidos_up').val().trim() == null) {
			apellidos.setCustomValidity("Introduzca un apellido");
			resultado = false;
		} else {
			apellidos.setCustomValidity('');
		}

		return resultado;
	}

	function validaEmailUp() {
		var email = document.getElementById("email_up");
		var resultado = true;

		if ($('#email_up').val().trim() == "" || $('#email_up').val().trim() == null || !validateEmailGlobal($('#email_up').val().trim())) {
			email.setCustomValidity("Introduzca un email");
			resultado = false;
		} else {
			email.setCustomValidity('');
		}

		return resultado;
	}


	$('#profeA').click(function() {
		validaNickUp();
		validaNombreUp();
		validaApellidosUp();
		validaEmailUp();
	});

}

//====================VALIDACION BORRAR USUARIO FAMILIA===================
function validateBorraUsuarioFamilia() {

	function validaFamiliaABorrar() {
		var familiaABorrar = document.getElementById("familia_a_borrar");
		var resultado = true;

		if ($('#familia_a_borrar').val().trim() == "" || $('#familia_a_borrar').val().trim() == null || isNaN($('#familia_a_borrar').val().trim())) {
			familiaABorrar.setCustomValidity("Seleccione una familia");
			resultado = false;
		} else {
			familiaABorrar.setCustomValidity('');
		}

		return resultado;
	}


	$('#borrarFamilia').click(function() {
		validaFamiliaABorrar();
	});

}

//====================VALIDACION SELECT MODIFICAR USUARIO FAMILIA===================
function validateModificarUsuarioFamilia() {

	function validaFamiliaAModificar() {
		var familiaAModificar = document.getElementById("familia");
		var resultado = true;

		if ($('#familia').val().trim() == "" || $('#familia').val().trim() == null || isNaN($('#familia').val().trim())) {
			familiaAModificar.setCustomValidity("Seleccione una familia");
			resultado = false;
		} else {
			familiaAModificar.setCustomValidity('');
		}

		return resultado;
	}


	$('#modificarFamilia').click(function() {
		validaFamiliaAModificar();
	});

}

//====================VALIDACION CREAR Y MODIFICAR USUARIO FAMILIA===================
function validateUsuarioFamilia() {

	function validaNickUf() {
		var nick = document.getElementById("nick_uf");
		var resultado = true;

		if ($('#nick_uf').val().trim() == "" || $('#nick_uf').val().trim() == null) {
			nick.setCustomValidity("Introduzca un nick");
			resultado = false;
		} else {
			nick.setCustomValidity('');
		}

		return resultado;
	}

	function validaNombreUf() {
		var nombre = document.getElementById("nombre_uf");
		var resultado = true;

		if ($('#nombre_uf').val().trim() == "" || $('#nombre_uf').val().trim() == null) {
			nombre.setCustomValidity("Introduzca un nombre");
			resultado = false;
		} else {
			nombre.setCustomValidity('');
		}

		return resultado;
	}

	function validaApellidosUf() {
		var apellidos = document.getElementById("apellidos_uf");
		var resultado = true;

		if ($('#apellidos_uf').val().trim() == "" || $('#apellidos_uf').val().trim() == null) {
			apellidos.setCustomValidity("Introduzca un apellido");
			resultado = false;
		} else {
			apellidos.setCustomValidity('');
		}

		return resultado;
	}

	function validaEmailUf() {
		var email = document.getElementById("email_uf");
		var resultado = true;

		if ($('#email_uf').val().trim() == "" || $('#email_uf').val().trim() == null || !validateEmailGlobal($('#email_uf').val().trim())) {
			email.setCustomValidity("Introduzca un email");
			resultado = false;
		} else {
			email.setCustomValidity('');
		}

		return resultado;
	}


	$('#FamiliaA').click(function() {
		validaNickUf();
		validaNombreUf();
		validaApellidosUf();
		validaEmailUf();
		passwordValidation();
		passwordConfirmation();
	});

}

function validateEmailGlobal(email) {
	var re = /\S+@\S+\.\S+/;
	return re.test(email);
}

//====================VALIDACION GESTIONAR ALUMNOS====================
function validacionGestionarAlumnos() {

	function validateSeleccionaClase() {
		var error1 = valClase_al();
		return (error1.length == 0);
	}


	$('#seleccionarClase').click(function() {
		validateSeleccionaClase();
	});

	function validateCrearAlumno() {
		var error1 = valNombre_al();
		var error2 = valApellidos_al();
		// var error3 = valDescripcion_al();
		var error4 = valFechaNacimiento_al();
		var error5 = valTutor_al();
		var error6 = valClase_al();
		return (error1.length == 0 && error2.length == 0 && error4.length == 0 && error5.length == 0 && error6.length == 0);
	}


	$('#crearAlumno').click(function() {
		validateCrearAlumno();
	});

	function validateBorrarAlumno() {
		var error1 = valOid_al();
		return (error1.length == 0 && error2.length == 0 && error3.length == 0 && error4.length == 0 && error5.length == 0);
	}


	$('#borrar').click(function() {
		validateCrearAlumno();
	});

	function validateModificarAlumno() {
		var error1 = valNombre_al();
		var error7 = valApellidos_al();
		var error2 = valDescripcion_al();
		var error3 = valFechaNacimiento_al();
		var error4 = valTutor_al();
		var error5 = valClase_al();
		var error6 = valOid_al();
		return (error1.length == 0 && error2.length == 0 && error3.length == 0 && error4.length == 0 && error5.length == 0 && error6.length == 0 && error7.length == 0);
	}


	$('#modificarAlumnos').click(function() {
		validateCrearAlumno();
	});

	function valNombre_al() {
		var nombre_al = document.getElementById("nombre_al");
		var valnombre_al = $('#nombre_al').val().trim();
		if (valnombre_al == "" || valnombre_al == null) {
			var error = "Introduzca el nombre";
		} else {
			var error = "";
		}
		nombre_al.setCustomValidity(error);
		return error;
	}

	function valApellidos_al() {
		var apellidos_al = document.getElementById("apellidos_al");
		var valapellidos_al = $('#apellidos_al').val().trim();
		if (valapellidos_al == "" || valapellidos_al == null) {
			var error = "Introduzca los apellidos";
		} else {
			var error = "";
		}
		apellidos_al.setCustomValidity(error);
		return error;
	}

	function valFechaNacimiento_al() {
		var fechaNacimiento = document.getElementById("fechaNacimiento");
		var valfechaNacimiento = new Date($('#fechaNacimiento').val());
		var hoy = new Date();
		if (valfechaNacimiento == "" || valfechaNacimiento == null || valfechaNacimiento.getTime() > hoy.getTime()) {
			var error = "Introduzca una fecha posterior a hoy";
		} else {
			var error = "";
		}
		fechaNacimiento.setCustomValidity(error);
		return error;
	}

	function valTutor_al() {
		var tutor = document.getElementById("tutor");
		var valtutor = $('#tutor').val().trim();
		if (valtutor == "" || valtutor == null || isNaN(valtutor)) {
			var error = "Introduzca un tutor ";
		} else {
			var error = "";
		}
		tutor.setCustomValidity(error);
		return error;
	}

	function valClase_al() {
		var clase = document.getElementById("clase");
		var valclase = $('#clase').val().trim();
		if (valclase == "" || valclase == null || isNaN(valclase)) {
			var error = "Introduzca una clase ";
		} else {
			var error = "";
		}
		clase.setCustomValidity(error);
		return error;
	}

	function valOid_al() {
		var OID_AL = document.getElementById("OID_AL");
		var valOID_AL = $('#OID_AL').val().trim();
		if (valOID_AL == "" || valOID_AL == null || isNaN(valOID_AL)) {
			var error = "El alumno debe tener valor";
		} else {
			var error = "";
		}
		OID_AL.setCustomValidity(error);
		return error;
	}

}

//====================VALIDACION CONTRASENYAS====================
function passwordValidation() {
	var password = document.getElementById("pass");
	var pwd = password.value;
	var valid = pwd.length >= 8;

	var hasNumber = /\d/;
	var hasUpperCases = /[A-Z]/;
	var hasLowerCases = /[a-z]/;
	valid = valid && (hasNumber.test(pwd)) && (hasUpperCases.test(pwd)) && (hasLowerCases.test(pwd));

	if (!valid) {
		password.setCustomValidity("Por favor introduce una contraseña con al menos 8 caracteres, mayúsculas y minúsculas, y números");
	} else {
		password.setCustomValidity('');
	}

	return valid;
}

function passwordConfirmation() {
	var password = document.getElementById("pass");
	var pwd = password.value;
	var passconfirm = document.getElementById("confirmpass");
	var confirmation = passconfirm.value;
	var resultado = true;

	if (pwd != confirmation) {
		passconfirm.setCustomValidity("Las contraseñas no coinciden");
		resultado = false;
	} else {
		passconfirm.setCustomValidity('');
	}

	return resultado;
}