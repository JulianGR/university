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
	 *            Identificador de la cadena de televisi�n dentro del dominio de
	 *            tmdb
	 * @return Un objeto de tipo TMDBNetwork con los datos de la cadena que
	 *         tiene ese id.
	 */
	TMDBNetwork getNetwork(Integer idCadena);

	/**
	 * @param idMovie
	 *            Identificador de la pel�cula dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBMovie con los datos de la pel�cula que
	 *         tiene ese id.
	 */
	TMDBMovie getMovie(Integer idMovie);

	/**
	 * @param idMovie
	 *            Identificador de la pel�cula dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBMovieCredits con los cr�ditos (el personal
	 *         que trabaja) de la pel�cula que tiene ese id.
	 */
	TMDBMovieCredits getMovieCredits(Integer idMovie);
	
	/**
	 * @param idReview Identificador de la cr�tica.
	 * @return Un objeto con la cr�tica de la pel�cula o serie
	 */
	TMDBReview getReview(String idReview);
	
	

	/**
	 * @param name
	 *            Nombre (o parte del nombre) de la persona buscada.
	 * @return La primera p�gina con objetos que contienen el id y el nombre de
	 *         la persona buscada.
	 */
	TMDBPage<TMDBNamedObject> getPersonsByName(String name);

	/**
	 * @param name
	 *            Nombre (o parte del nombre) de la persona buscada.
	 * @param pageNumber
	 *            N�mero de p�gina solicitado
	 * @return La primera p�gina con objetos que contienen el id y el nombre de
	 *         la persona buscada.
	 * @throws IllegalArgumentException
	 *             si la p�gina no est� entre 1 y 1000.
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
	 *            T�tulo (o parte del t�tulo) de la pel�cula buscado
	 * @return La primera p�gina con las pel�culas obtenidas en el t�tulo.
	 */
	TMDBPage<TMDBMovie> getMoviesByTitle(String title);

	/**
	 * @param title
	 *            T�tulo (o parte del t�tulo) de la pel�cula buscado
	 * @param pageNumber
	 *            N�mero de p�gina con resultados de b�squeda solicitado
	 * @return La p�gina con n�mero de p�gina solicitado con las pel�culas
	 *         obtenidas en el t�tulo.
	 * @throws IllegalArgumentException
	 *             si la p�gina no est� entre 1 y 1000.
	 */
	TMDBPage<TMDBMovie> getMoviesByTitle(String title, Integer pageNumber);

	/**
	 * @param title
	 *            T�tulo (o parte del t�tulo) de la pel�cula a buscar.
	 * @return Una lista con todas las pel�culas que tienen title en su t�tulo o
	 *         en parte de su t�tulo.
	 */
	List<TMDBMovie> getAllMoviesByTitle(String title);

	/**
	 * @param year
	 *            A�o buscado
	 * @return La primera p�gina con las pel�culas del a�o buscado cuya
	 *         puntuaci�n sea superior o igual a 8 y haya sido votada por 10 �
	 *         m�s personas.
	 */
	TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year);

	/**
	 * @param year
	 *            A�o buscado
	 * @param pageNumber
	 *            N�mero de p�gina con resultados de b�squeda solicitado
	 * @return La p�gina solicitada con las pel�culas del a�o buscado cuya
	 *         puntuaci�n sea superior o igual a 8 y haya sido votada por 10 �
	 *         m�s personas.
	 * @throws IllegalArgumentException
	 *             si la p�gina no est� entre 1 y 1000.
	 */
	TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year, Integer pageNumber);

	/**
	 * @param year
	 *            A�o buscado
	 * @return Una lista con todas las pel�culas que del a�o dado como par�metro
	 *         cuya puntuaci�n es superior o igual a 8 y han sido votadas por 10
	 *         o m�s personas.
	 */
	List<TMDBMovie> getAllTopMoviesByYear(Integer year);

	/**
	 * @param year
	 *            A�o buscado
	 * @return La primera p�gina con las series del a�o buscado cuya
	 *         puntuaci�n sea superior o igual a 8 y haya sido votada por 10 �
	 *         m�s personas.
	 */
	TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year);

	/**
	 * @param year
	 *            A�o buscado
	 * @param pageNumber
	 *            N�mero de p�gina con resultados de b�squeda solicitado
	 * @return La p�gina solicitada con las series del a�o buscado cuya
	 *         puntuaci�n sea superior o igual a 8 y haya sido votada por 10 �
	 *         m�s personas. Se considera que una serie es de un a�o si su �ltima fecha de emisi�n est� en ese a�o
	 *         o en un a�o posterior a ese.
	 * @throws IllegalArgumentException
	 *             si la p�gina no est� entre 1 y 1000.
	 */
	TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year, Integer pageNumber);

	/**
	 * @param year
	 *            A�o buscado
	 * @return Una lista con todas las pel�culas que del a�o dado como par�metro
	 *         cuya puntuaci�n es superior o igual a 8 y han sido votadas por 10
	 *         o m�s personas.
	 */
	List<TMDBTVSerieShort> getAllTopTVSeriesByYear(Integer year);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBTVSerie con los datos de la serie cuyo id
	 *         es el dado como par�metro.
	 */
	TMDBTVSerie getTVSerie(Integer idTVSerie);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            N�mero de temporada de la serie
	 * @return Un objeto de tipo TMDBTVSeasonFull con los datos de la serie cuyo
	 *         id es el dado como par�metro.
	 */
	TMDBTVSeason getTVSeason(Integer idTVSerie, Integer seasonNumber);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            N�mero de temporada de la serie
	 * @param episodeNumber
	 *            Un objeto de tipo TMDBTVEpisode con los datos de la serie cuyo
	 *            id es el dado como par�metro.
	 * @return El episodio buscado.
	 */
	TMDBTVEpisode getTVEpisode(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber);

	/**
	 * @param idTVSerie
	 *            Identificador de la serie dentro del dominio de tmdb
	 * @param seasonNumber
	 *            N�mero de temporada de la serie
	 * @param episodeNumber
	 *            Un objeto de tipo TMDBTVEpisodeCredits con los datos de la
	 *            serie cuyo id es el dado como par�metro.
	 * @return Un objeto con los cr�ditos del episodio.
	 */
	TMDBTVEpisodeCredits getTVEpisodeCredits(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber);

	/**
	 * @param title
	 *            T�tulo (o parte del t�tulo) de la serie buscada.
	 * @return La primera p�gina con las series obtenidas en el t�tulo.
	 */
	TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title);

	/**
	 * @param title
	 *            T�tulo (o parte del t�tulo) de la serie buscado.
	 * @param pageNumber
	 *            N�mero de p�gina con resultados de b�squeda solicitado
	 * @return La p�gina con n�mero de p�gina solicitado con las pel�culas
	 *         obtenidas en el t�tulo.
	 * @throws IllegalArgumentException
	 *             si la p�gina no est� entre 1 y 1000.
	 */
	TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title, Integer pageNumber);

	/**
	 * @param title
	 *            T�tulo (o parte del t�tulo) de la serie buscado.
	 * @return Una lista con todas las series que tienen title en su t�tulo o en
	 *         parte de su t�tulo.
	 */
	List<TMDBTVSerieShort> getAllTVSeriesByTitle(String title);

}