package fp.aeropuertos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.utiles.Checkers;

/**
 * Clase que implementa al tipo Persona.
 * 
 * @author reinaqu
 *
 */
public class PersonaImpl implements Persona {
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fechaNacimiento;
	private String email;

	public PersonaImpl(String s) {
		String[] trozos = s.split(",");
		Checkers.check("Formato incorrecto de la cadena de entrada", trozos.length == 5);

		String dni = trozos[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String email = trozos[4].trim();
		Checkers.check("PersonaImpl: el dni debe tener ocho digitos y una letral final",
				PersonaImpl.restriccionDni(dni));
		Checkers.check("PersonaImpl: la fecha de nacimiento debe ser anterior a la fecha actual",
				PersonaImpl.restriccionFechaNacimiento(fechaNacimiento));
		Checkers.check("PersonaImpl: el email debe contener el caracter arroba€™", PersonaImpl.restriccionEmail(email));

		this.nombre = trozos[0].trim();
		this.apellidos = trozos[1].trim();
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}

	/**
	 * @param dni
	 *            DNI de la persona. Debe estar formado por 8 dígitos y una letra.
	 * @param nombre
	 *            Nombre de la persona.
	 * @param apellidos
	 *            Apellidos de la persona.
	 * @param fechaNacimiento
	 *            Fecha de nacimiento de la persona. Debe ser anterior al día de
	 *            hoy.
	 * @param email
	 *            Correo electrónico de la persona. Debe contener una arroba.
	 * @throws IllegalArgumentException
	 *             si dni no está formado por 8 dígitos y una letra.
	 * @throws IllegalArgumentException
	 *             si fechaNacimiento no es anterior al día de hoy.
	 * @throws IllegalArgumentException
	 *             si email no contiene una @
	 */
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		//
		Checkers.checkNoNull("PersonaImpl: parametros nulos", dni, nombre, apellidos, fechaNacimiento, email);
		Checkers.check("PersonaImpl: el dni debe tener ocho digitos y una letral final",
				PersonaImpl.restriccionDni(dni));
		Checkers.check("PersonaImpl: la fecha de nacimiento debe ser anterior a la fecha actual",
				PersonaImpl.restriccionFechaNacimiento(fechaNacimiento));
		Checkers.check("PersonaImpl: el email debe contener el caracter arroba€™", PersonaImpl.restriccionEmail(email));
		//
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}

	/**
	 * Crea una persona con el correo electrónico nulo.
	 * 
	 * @param dni
	 *            DNI de la persona. Debe estar formado por 8 dígitos y una letra.
	 * @param nombre
	 *            Nombre de la persona.
	 * @param apellidos
	 *            Apellidos de la persona.
	 * @param fechaNacimiento
	 *            Fecha de nacimiento de la persona. Debe ser anterior al día de
	 *            hoy.
	 * @param email
	 *            Correo electrónico de la persona. Debe contener una arroba.
	 * @throws IllegalArgumentException
	 *             si dni no está formado por 8 dígitos y una letra.
	 * @throws IllegalArgumentException
	 *             si fechaNacimiento no es anterior al día de hoy.
	 */

	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento) {
		//
		Checkers.checkNoNull("PersonaImpl", dni, nombre, apellidos, fechaNacimiento);
		Checkers.check("PersonaImpl: el dni debe tener ocho digitos y una letral final",
				PersonaImpl.restriccionDni(dni));
		Checkers.check("PersonaImpl: la fecha de nacimiento debe ser anterior a la fecha actual",
				PersonaImpl.restriccionFechaNacimiento(fechaNacimiento));
		//
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.email = null;
	}

	public PersonaImpl() {

	}

	//////////
	// R1: el dni debe tener ocho dÃ­gitos y una letral final.
	// R2: la fecha de nacimiento debe ser anterior a la fecha actual.
	// R3: el email debe contener el carÃ¡cter â€˜@â€™.
	private static Boolean restriccionDni(String dni) {
		Boolean res;
		res = (dni.length() == 9) && Character.isLetter(dni.charAt(8));
		return res;
	}

	private static Boolean restriccionFechaNacimiento(LocalDate fechaNacimiento) {
		Boolean res;
		res = fechaNacimiento.isBefore(LocalDate.now());
		return res;
	}

	private static Boolean restriccionEmail(String email) {
		Boolean res;
		res = email.contains("@");
		return res;
	}

	//////////
	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getEdad()
	 */
	public Integer getEdad() {
		return (int) getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getApellidos()
	 */
	public String getApellidos() {
		return apellidos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getDNI()
	 */
	public String getDNI() {
		return dni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getFechaNacimiento()
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#setApellidos(java.lang.String)
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#setDNI(java.lang.String)
	 */
	public void setDNI(String dni) {
		Checkers.check("PersonaImpl: el dni debe tener ocho dÃ­gitos y una letral final",
				PersonaImpl.restriccionDni(dni));
		//
		this.dni = dni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#setFechaNacimiento(java.time.LocalDate)
	 */
	public void setFechaNacimiento(LocalDate fecha) {
		Checkers.check("PersonaImpl: la fecha de nacimiento debe ser anterior a la fecha actual",
				restriccionFechaNacimiento(fecha));
		//
		this.fechaNacimiento = fecha;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Persona#setEmail(java.lang.String)
	 */
	public void setEmail(String email) {
		Checkers.check("PersonaImpl: el email debe contener el caracter arroba", PersonaImpl.restriccionEmail(email));
		//
		this.email = email;
	}

	/**
	 * @return La representación como cadena de una persona. La representación como
	 *         cadena de una persona es el dni seguido de un guión, seguido de los
	 *         apellidos, seguido de una coma, seguido del nombre, seguido de un
	 *         guión, y finalmente, su fecha de nacimiento en formato dd/mm/yyyy.
	 */
	public String toString() {
		return getDNI() + " - " + getApellidos() + ", " + getNombre() + " - "
				+ getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	////////////////////////////////////////////////////////////////
	//
	/**
	 * @return true si las dos personas son iguales. Dos personas son iguales si
	 *         tienen el mismo dni.
	 */
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Persona) {
			res = this.getDNI().equals(((Persona) o).getDNI());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.getDNI().hashCode();
	}

	/**
	 * Una persona es anterior a otra si su dni es anterior.
	 */
	public int compareTo(Persona p) {
		return this.getDNI().compareTo(p.getDNI());
	}
}
