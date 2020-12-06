package fp.aeropuertos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Tipo que representa a una persona. Las personas se pueden ordenar por su dni.
 * 
 * @author reinaqu
 *
 */

public interface Persona extends Comparable<Persona> {

	/**
	 * @return El nombre de la persona.
	 */
	String getNombre();

	/**
	 * @return Los apellidos de la persona.
	 */
	String getApellidos();

	/**
	 * @return El dni de la persona. El dni debe estar formado por ocho dígitos y
	 *         una letra.
	 */
	String getDNI();

	/**
	 * @return La fecha de nacimiento de la persona. Debe ser anterior al día de
	 *         hoy.
	 */
	@JsonDeserialize(converter = fp.conversores.ConverterString2LocalDate.class)
	@JsonSerialize(converter = fp.conversores.ConverterLocalDate2String.class)
	LocalDate getFechaNacimiento();

	/**
	 * @return El correo electrónico de la persona. Debe contener una arroba.
	 */
	String getEmail();

	/**
	 * @return La edad de la persona.
	 */
	@JsonIgnore
	Integer getEdad();

	/**
	 * @param nombre
	 *            El nuevo nombre de la persona.
	 */
	void setNombre(String nombre);

	/**
	 * @param apellidos
	 *            Los nuevos apellidos de la persona.
	 */
	void setApellidos(String apellidos);

	/**
	 * @param dni
	 *            El nuevo D.N.I. de la persona. Debe estar formado por 8 dígitos y
	 *            una letra.
	 * @throws IllegalArgumentException
	 *             si dni no está formado por 8 dígitos y una letra.
	 */
	void setDNI(String dni);

	/**
	 * @param fecha
	 *            Nueva fecha de nacimiento de la persona. Debe ser anterior al día
	 *            de hoy.
	 * @throws IllegalArgumentException
	 *             si la fecha de nacimiento no es anterior al día de hoy.
	 */
	void setFechaNacimiento(LocalDate fecha);

	/**
	 * @param email
	 *            Nuevo email de la persona. Debe contener una @.
	 * @throws IllegalArgumentException
	 *             si el email no contiene una @.
	 */
	void setEmail(String email);

}
