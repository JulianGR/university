package fp.musica;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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

	Boolean esAntologia(String artista);

	Boolean contieneArtista(String artista);

	int getPosicionCancion(String tituloCancion);

	Cancion getCancionMasLarga();

	Duration getDuracion();

	ListaReproduccion getSublistaArtista(String artista);

	Set<Artista> getArtistas();

	void muestraFotosArtistas();

	// MODO EXAMEN
	Boolean tieneTodasLasCancionesDuracionInferiorA(Duration d);

}