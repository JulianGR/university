package fp.audiovisuales;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public interface BuscadorPeliculas {

	void indexaPeliculas(Collection<Pelicula> peliculas);

	void indexaPelicula(Pelicula p);

	Map<String, Set<Pelicula>> getIndicePeliculas();

	Set<Pelicula> getPeliculas();

	Set<Pelicula> buscaPeliculasPorTitulo(String palabra);

	Set<Pelicula> buscaPeliculasPorTitulo(String[] palabras);
	
	SortedMap<String, Set<Pelicula>> getPeliculasPorActor();
}