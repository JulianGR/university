package fp.tmdb;

import java.util.List;

import fp.tmdb.pojo.TMDBPage;
import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBMovieCredits;
import fp.tmdb.pojo.TMDBNamedObject;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBPersonCredits;
import fp.tmdb.pojo.TMDBReview;
import fp.tmdb.pojo.TMDBTVEpisode;
import fp.tmdb.pojo.TMDBTVEpisodeCredits;
import fp.tmdb.pojo.TMDBTVSeason;
import fp.tmdb.pojo.TMDBTVSerie;
import fp.tmdb.pojo.TMDBTVSerieShort;

public interface TMDBAPI {

	/**
	 * @param idPersona
	 *            Identificador de la persona dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDB con los datos de la persona que tiene ese
	 *         id
	 */
	TMDBPerson getPerson(Integer idPersona);

	/**
	 * @param idCadena
	 *            Identificador de la cadena de televisión dentro del dominio de
	 *            tmdb
	 * @return Un objeto de tipo TMDBNetwork con los datos de la cadena que
	 *         tiene ese id.
	 */
	TMDBNetwork getNetwork(Integer idCadena);

	/**
	 * @param idMovie
	 *            Identificador de la película dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBMovie con los datos de la película que
	 *         tiene ese id.
	 */
	TMDBMovie getMovie(Integer idMovie);

	/**
	 * @param idMovie
	 *            Identificador de la película dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBMovieCredits con los créditos (el personal
	 *         que trabaja) de la película que tiene ese id.
	 */
	TMDBMovieCredits getMovieCredits(Integer idMovie);
	
	/**
	 * @param idReview Identificador de la crítica.
	 * @return Un objeto con la crítica de la película o serie
	 */
	TMDBReview getReview(String idReview);
	
	

	/**
	 * @param name
	 *            Nombre (o parte del nombre) de la persona buscada.
	 * @return La primera página con objetos que contienen el id y el nombre de
	 *         la persona buscada.
	 */
	TMDBPage<TMDBNamedObject> getPersonsByName(String name);

	/**
	 * @param name
	 *            Nombre (o parte del nombre) de la persona buscada.
	 * @param pageNumber
	 *            Número de página solicitado
	 * @return La primera página con objetos que contienen el id y el nombre de
	 *         la persona buscada.
	 * @throws IllegalArgumentException
	 *             si la página no está entre 1 y 1000.
	 */
	TMDBPage<TMDBNamedObject> getPersonsByName(String name, Integer pageNumber);

	/**
	 * @param name
	 *            Nombre (o parte del nombre) de la persona a buscar.
	 * @return Una lista con todas objectos que contienen el id y el nombre de
	 *         todas las personas encontradas.
	 */
	List<TMDBNamedObject> getAllPersonsByName(String name);

	TMDBPersonCredits getPersonCredits(Integer idPerson);

	/**
	 * @param title
	 *            Título (o parte del título) de la película buscado
	 * @return La primera página con las películas obtenidas en el título.
	 */
	TMDBPage<TMDBMovie> getMoviesByTitle(String title);

	/**
	 * @param title
	 *            Título (o parte del título) de la película buscado
	 * @param pageNumber
	 *            Número de página con resultados de búsqueda solicitado
	 * @return La página con número de página solicitado con las películas
	 *         obtenidas en el título.
	 * @throws IllegalArgumentException
	 *             si la página no está entre 1 y 1000.
	 */
	TMDBPage<TMDBMovie> getMoviesByTitle(String title, Integer pageNumber);

	/**
	 * @param title
	 *            Título (o parte del título) de la película a buscar.
	 * @return Una lista con todas las películas que tienen title en su título o
	 *         en parte de su título.
	 */
	List<TMDBMovie> getAllMoviesByTitle(String title);

	/**
	 * @param year
	 *            Año buscado
	 * @return La primera página con las películas del año buscado cuya
	 *         puntuación sea superior o igual a 8 y haya sido votada por 10 ó
	 *         más personas.
	 */
	TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year);

	/**
	 * @param year
	 *            Año buscado
	 * @param pageNumber
	 *            Número de página con resultados de búsqueda solicitado
	 * @return La página solicitada con las películas del año buscado cuya
	 *         puntuación sea superior o igual a 8 y haya sido votada por 10 ó
	 *         más personas.
	 * @throws IllegalArgumentException
	 *             si la página no está entre 1 y 1000.
	 */
	TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year, Integer pageNumber);

	/**
	 * @param year
	 *            Año buscado
	 * @return Una lista con todas las películas que del año dado como parámetro
	 *         cuya puntuación es superior o igual a 8 y han sido votadas por 10
	 *         o más personas.
	 */
	List<TMDBMovie> getAllTopMoviesByYear(Integer year);

	/**
	 * @param year
	 *            Año buscado
	 * @return La primera página con las series del año buscado cuya
	 *         puntuación sea superior o igual a 8 y haya sido votada por 10 ó
	 *         más personas.
	 */
	TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year);

	/**
	 * @param year
	 *            Año buscado
	 * @param pageNumber
	 *            Número de página con resultados de búsqueda solicitado
	 * @return La página solicitada con las series del año buscado cuya
	 *         puntuación sea superior o igual a 8 y haya sido votada por 10 ó
	 *         más personas. Se considera que una serie es de un año si su última fecha de emisión está en ese año
	 *         o en un año posterior a ese.
	 * @throws IllegalArgumentException
	 *             si la página no está entre 1 y 1000.
	 */
	TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year, Integer pageNumber);

	/**
	 * @param year
	 *            Año buscado
	 * @return Una lista con todas las películas que del año dado como parámetro
	 *         cuya puntuación es superior o igual a 8 y han sido votadas por 10
	 *         o más personas.
	 */
	List<TMDBTVSerieShort> getAllTopTVSeriesByYear(Integer year);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBTVSerie con los datos de la serie cuyo id
	 *         es el dado como parámetro.
	 */
	TMDBTVSerie getTVSerie(Integer idTVSerie);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            Número de temporada de la serie
	 * @return Un objeto de tipo TMDBTVSeasonFull con los datos de la serie cuyo
	 *         id es el dado como parámetro.
	 */
	TMDBTVSeason getTVSeason(Integer idTVSerie, Integer seasonNumber);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            Número de temporada de la serie
	 * @param episodeNumber
	 *            Un objeto de tipo TMDBTVEpisode con los datos de la serie cuyo
	 *            id es el dado como parámetro.
	 * @return El episodio buscado.
	 */
	TMDBTVEpisode getTVEpisode(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            Número de temporada de la serie
	 * @param episodeNumber
	 *            Un objeto de tipo TMDBTVEpisodeCredits con los datos de la
	 *            serie cuyo id es el dado como parámetro.
	 * @return Un objeto con los créditos del episodio.
	 */
	TMDBTVEpisodeCredits getTVEpisodeCredits(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber);

	/**
	 * @param title
	 *            Título (o parte del título) de la serie buscada.
	 * @return La primera página con las series obtenidas en el título.
	 */
	TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title);

	/**
	 * @param title
	 *            Título (o parte del título) de la serie buscado.
	 * @param pageNumber
	 *            Número de página con resultados de búsqueda solicitado
	 * @return La página con número de página solicitado con las películas
	 *         obtenidas en el título.
	 * @throws IllegalArgumentException
	 *             si la página no está entre 1 y 1000.
	 */
	TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title, Integer pageNumber);

	/**
	 * @param title
	 *            Título (o parte del título) de la serie buscado.
	 * @return Una lista con todas las series que tienen title en su título o en
	 *         parte de su título.
	 */
	List<TMDBTVSerieShort> getAllTVSeriesByTitle(String title);

}