package fp.musica;

import java.util.List;

import fp.musica.Album;
import fp.musica.Cancion;

public interface ListaReproduccion {
	String getNombre();

	void setNombre(String nombre);

	List<Cancion> getCanciones();

	Integer getNumeroCanciones();

	void aleatoriza();

	void incorpora(Cancion c);

	void incorpora(Album a);

	void incorpora(List<Cancion> canciones);
	
	void eliminaPrimera(Cancion c);

	void eliminaUltima(Cancion c);

	void eliminaTrozo(int primeraPosicion, int ultimaPosicion);
}