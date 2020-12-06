package fp.tmdb;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import fp.rest.ClienteREST;
import fp.rest.ClienteRESTImpl;
import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBMovieCredits;
import fp.tmdb.pojo.TMDBNamedObject;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPage;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBPersonCredits;
import fp.tmdb.pojo.TMDBReview;
import fp.tmdb.pojo.TMDBTVEpisode;
import fp.tmdb.pojo.TMDBTVEpisodeCredits;
import fp.tmdb.pojo.TMDBTVSeason;
import fp.tmdb.pojo.TMDBTVSerie;
import fp.tmdb.pojo.TMDBTVSerieShort;
import fp.utiles.Checkers;
import fp.utiles.Propiedades;

/**
 * @author Toñi Reina Clase que es un reflejo de los servicios que ofrece tmdb.
 */
public class TMDBAPIImpl implements TMDBAPI {

	public static final java.io.File FICHERO_KEYS = new java.io.File(".", ".store/keys.properties");
	private static final String PROPIEDAD_APIKEY = "tmdb_key";

	/* Constantes con las URL base para los endpoints de la API */
	private static final String URL_BASE = "https://api.themoviedb.org/3/";
	private static final String URL_PERSON_SEARCH = URL_BASE + "search/person";
	private static final String URL_MOVIE_SEARCH = URL_BASE + "search/movie";

	private static final String URL_NETWORK = URL_BASE + "network/";
	private static final String URL_PERSON = URL_BASE + "person/";
	private static final String URL_MOVIE = URL_BASE + "movie/";
	private static final String URL_REVIEW = URL_BASE + "review/";

	/* Parï¿½metros de configuraciï¿½n */
	private static final Integer MAX_NUMPAGINAS = 1000; // Este nï¿½mero lo da el
														// servicio
	private static final String PARAM_APIKEY = "api_key";
	private static final String PARAM_IDIOMA = "language";
	private static final String API_KEY = getAPIKey();
	private static final String DEFAULT_IDIOMA = "es";
	private static final String[] DEFAULT_PARAMS = { PARAM_APIKEY, PARAM_IDIOMA };
	private static final String[] DEFAULT_VALS = { API_KEY, DEFAULT_IDIOMA };

	private static String getAPIKey() {

		return Propiedades.getProperty(FICHERO_KEYS, PROPIEDAD_APIKEY);
	}

	private ClienteREST cliente;

	public TMDBAPIImpl() {
		this.cliente = new ClienteRESTImpl();
	}

	// Realizados en el laboratorio 20

	public TMDBPerson getPerson(Integer idPersona) {
		String[] parametros = { "api_key" };
		String[] valores = { getAPIKey() };
		return cliente.get(URL_PERSON + idPersona, parametros, valores, TMDBPerson.class);
	}

	public TMDBNetwork getNetwork(Integer idCadena) {
		String[] parametros = { "api_key" };
		String[] valores = { getAPIKey() };
		return cliente.get(URL_NETWORK + idCadena, parametros, valores, TMDBNetwork.class);
	}

	public TMDBMovie getMovie(Integer idMovie) {
		String[] parametros = { "api_key", "&language" };
		String[] valores = { getAPIKey(), "es" };
		return cliente.get(URL_MOVIE + idMovie, parametros, valores, TMDBMovie.class);
	}

	public TMDBReview getReview(String idReview) {
		String[] parametros = { "api_key", "&language" };
		String[] valores = { getAPIKey(), "es" };
		return cliente.get(URL_REVIEW + idReview, parametros, valores, TMDBReview.class);
	}

	// TODO: Clase de Teoría - Paginación TMDB

	public TMDBPage<TMDBNamedObject> getPersonsByName(String name) {

		return getPersonsByName(name, 1);
	}

	public TMDBPage<TMDBNamedObject> getPersonsByName(String name, Integer pageNumber) {
		checkPageNumber("TMDBApi.getPersonsByName::", pageNumber);
		String[] params = { "api_key", "language", "query", "page" };
		String[] vals = { API_KEY, "es", name, pageNumber.toString() };
		TypeReference<TMDBPage<TMDBNamedObject>> ref = new TypeReference<TMDBPage<TMDBNamedObject>>() {
		};

		return cliente.get(URL_PERSON_SEARCH, params, vals, ref);
	}

	private void checkPageNumber(String msgHeader, Integer pageNumber) {
		Checkers.check(msgHeader + "El número de página supera los límites,",
				pageNumber >= 1 && pageNumber <= MAX_NUMPAGINAS);

	}

	public List<TMDBNamedObject> getAllPersonsByName(String name) {
		TMDBPage<TMDBNamedObject> page = getPersonsByName(name);
		List<TMDBNamedObject> res = new LinkedList<TMDBNamedObject>();
		if (page != null) {
			res.addAll(page.getResults());
		}

		while (page.getCurrentPage() < page.getTotalPages()) {
			Integer nextPageNumber = page.getCurrentPage() + 1;
			page = getPersonsByName(name, nextPageNumber);
			res.addAll(page.getResults());
		}
		return res;
	}

	// TODO: Laboratorio 22 - Paginación TMDB

	public TMDBPage<TMDBMovie> getMoviesByTitle(String title) {

		return getMoviesByTitle(title, 1);
	}

	public TMDBPage<TMDBMovie> getMoviesByTitle(String title, Integer pageNumber) {
		checkPageNumber("TMDBAPI.getMoviesByTitle::", pageNumber);
		String[] params = { "api_key", "language", "query", "page" };
		String[] vals = { getAPIKey(), "es", title, pageNumber.toString() };

		TypeReference<TMDBPage<TMDBMovie>> ref = new TypeReference<TMDBPage<TMDBMovie>>() {
		};

		return cliente.get(URL_MOVIE_SEARCH, params, vals, ref);
	}

	public List<TMDBMovie> getAllMoviesByTitle(String title) {
		TMDBPage<TMDBMovie> page = getMoviesByTitle(title);
		List<TMDBMovie> res = new LinkedList<TMDBMovie>();

		if (page != null) {
			res.addAll(page.getResults());
		}
		while (page.getCurrentPage() < page.getTotalPages()) {
			Integer nextPageNumber = page.getCurrentPage() + 1;
			page = getMoviesByTitle(title, nextPageNumber);
			res.addAll(page.getResults());
		}

		return res;
	}

	// TODO: Para casa

	public TMDBMovieCredits getMovieCredits(Integer idMovie) {
		return null;
	}

	public TMDBPersonCredits getPersonCredits(Integer idPerson) {
		return null;
	}

	public TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year) {
		return null;
	}

	public TMDBPage<TMDBMovie> getTopMoviesByYear(Integer year, Integer pageNumber) {
		return null;
	}

	public List<TMDBMovie> getAllTopMoviesByYear(Integer year) {
		return null;
	}

	public TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year) {
		return null;
	}

	public TMDBPage<TMDBTVSerieShort> getTopTVSeriesByYear(Integer year, Integer pageNumber) {
		return null;
	}

	public List<TMDBTVSerieShort> getAllTopTVSeriesByYear(Integer year) {
		return null;
	}

	public TMDBTVSerie getTVSerie(Integer idTVSerie) {
		return null;
	}

	public TMDBTVSeason getTVSeason(Integer idTVSerie, Integer seasonNumber) {
		return null;
	}

	public TMDBTVEpisode getTVEpisode(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber) {
		return null;
	}

	public TMDBTVEpisodeCredits getTVEpisodeCredits(Integer idTVSerie, Integer seasonNumber, Integer episodeNumber) {
		return null;
	}

	public TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title) {
		return null;
	}

	public TMDBPage<TMDBTVSerieShort> getTVSeriesByTitle(String title, Integer pageNumber) {
		return null;
	}

	public List<TMDBTVSerieShort> getAllTVSeriesByTitle(String title) {
		return null;
	}

}
