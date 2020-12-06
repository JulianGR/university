package fp.audiovisuales;

import java.time.LocalDate;
import java.util.List;
/**
 * @author Toñi Reina
 * 
 */
public interface MiembroStaff extends Comparable<MiembroStaff> {

	Integer getId(); 
	String getNombre();
	LocalDate getFechaNacimiento();
	LocalDate getFechaDefuncion();
	String getLugarNacimiento();
	Integer getEdad();
	List<String> getAlias(); // antes era String getAlias()
	void setNombre(String nombre);
	void setLugarNacimiento(String lugarNacimiento);
	void setFechaNacimiento(LocalDate fechaNacimiento);
	void setFechaDefuncion(LocalDate fechaDefuncion);
	void setAlias(List<String> alias); // antes recibía String
	
}
