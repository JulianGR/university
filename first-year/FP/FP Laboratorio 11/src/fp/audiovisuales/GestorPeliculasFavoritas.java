package fp.audiovisuales;

import java.util.Collection;
import java.util.Set;

public interface GestorPeliculasFavoritas {

	/**
	 * Propiedad básica. Solo consultable.
	 * 
	 * @return El id del gestor de peliculas
	 */
	String getId();

	/**
	 * Propiedad básica solo consultable.
	 * 
	 * @return Nombre del usuario que gestiona las películas
	 */
	String getNombre();

	/**
	 * Propiedad básica. Solo consultable.
	 * 
	 * @return Conjunto de películas favoritas del usuario.
	 */
	Set<Pelicula> getPeliculas();

	/**
	 * Añade la película al conjunto de películas favoritas del usuario.
	 * 
	 * @param pelicula
	 *            Película a añadir a las favoritas.
	 */
	void nuevaPelicula(Pelicula pelicula);

	/**
	 * Añade la colección de películas a la lista de películas favoritas del
	 * usuario
	 * 
	 * @param peliculas
	 *            Colección de películas a añadir a la colección.
	 */
	void nuevasPeliculas(Collection<Pelicula> peliculas);

	/**
	 * @param pelicula
	 *            Elimina la pelicula del conjunto de favoritas;
	 */
	void eliminaPelicula(Pelicula pelicula);

	/**
	 * @param nombreActor
	 *            Nombre del un actor
	 * @return El número de películas en las que ha participado el actor dado
	 *         como parámetro.
	 */
	Integer cuentaPeliculas(String nombreActor);

	/**
	 * @return Devuelve el actor de que ha participado en más películas.
	 */
	MiembroStaff getActorMasPeliculas();

	/**
	 * @param director
	 *            Nombre de director
	 * @return True si hay alguna película dirigida por el director con el
	 *         nombre que se pasa como parámetro.
	 */
	Boolean hayAlgunaPeliculaDirigidaPor(String nombreDirector);

	/**
	 * @return Devuelve la película en la que han participado más actores.
	 */
	Pelicula getPeliculaMasActores();
	
	/**
	 * @return Devuelve el conjunto de actores que ha participado en todas las películas.
	 */
	Set<MiembroStaff> seleccionaActoresParticipantesTodas();
	
	/**
	 * @param director Nombre de director
	 * @return El conjunto de películas dirigidas por el director dado como parámetro.
	 */
	Set<Pelicula> getPeliculasDirigidasPor(String nombreDirector);
	
	/**
	 * @return El conjunto de géneros de las películas del gestor.	
	 */
	Set<String> getGeneros();
	
	/**
	 * @param any Año
	 * @return El conjunto de películas estrenadas en el año dado como parámetro
	 */
	Set<Pelicula> getPeliculasAnyo(Integer anyo);
	
	/**
	 * @param actor Nombre de actor
	 * @return El conjunto de las películas en las que trabaja el actor dado como parámetro
	 */
	Set<Pelicula> getPeliculasDeActor(String nombreActor);
	
	/**
	 * @return El conjunto de paises a los que pertenecen las peliculas del gestor.
	 */
	Set<String> getPaises();
}
