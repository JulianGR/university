package fp.audiovisuales;

import java.time.LocalDate;

public interface MiembroStaff {
	Integer getId();

	String getNombre();

	void setNombre(String nombre);

	LocalDate getFechaNacimiento();

	void setFechaNacimiento(LocalDate nacimiento);

	LocalDate getFechaDefuncion();

	void setFechaDefuncion(LocalDate defuncion);

	Integer getEdad();

	String getLugarNacimiento();

	void setLugarNacimiento(String lugar);

	String getAlias();

	void setAlias(String alias);

}
