package ejercicioKesimo;

import java.time.Duration;

public interface Cancion {

	String getNombre();

	void setNombre(String nombre);

	Duration getDuracion();

	void setDuracion(Duration duracion);

	int hashCode();

}