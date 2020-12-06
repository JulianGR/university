package fp.aeropuertos;

import java.time.LocalDate;
import java.time.Period;

public class PersonaImpl implements Persona {
	private String apellidos;
	private String dni;
	private String email;
	private String nombre;
	private LocalDate fechaNacimiento;

	public PersonaImpl(String apellidos, String dni, String email, String nombre, LocalDate fechaNacimiento) {
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public PersonaImpl(String apellidos, String dni, String nombre, LocalDate fechaNacimiento) {
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.email = "";
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Integer getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String toString() {
		return getDni() + " - " + getApellidos() + ", " + getNombre() + " - " + getFechaNacimiento();

	}

}
