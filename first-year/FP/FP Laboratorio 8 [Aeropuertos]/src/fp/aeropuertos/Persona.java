package fp.aeropuertos;

import java.time.LocalDate;

public interface Persona {
	String getDni();

	String getNombre();

	String getApellidos();

	LocalDate getFechaNacimiento();

	String getEmail();

	Integer getEdad();

	public void setDni(String dni);

	public void setNombre(String nombre);

	public void setApellidos(String apellidos);

	public void setFechaNacimiento(LocalDate fechaNacimiento);

	public void setEmail(String email);

}
