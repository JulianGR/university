package fp.tmdb;

import java.io.File;

import fp.rest.ClienteREST;
import fp.rest.ClienteRESTImpl;
import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBReview;
import fp.utiles.Propiedades;

public class TMDBAPIImpl implements TMDBAPI {

	private static final String URL_BASE = "http://api.themoviedb.org/3/";
	private static final String URL_NETWORK = URL_BASE + "network/";
	private static final String URL_PERSON = URL_BASE + "person/";
	private static final String URL_MOVIE = URL_BASE + "movie/";
	private static final String URL_REVIEW = URL_BASE + "review/";
	private static final File FICHERO_KEYS = new File(".", ".store/keys.properties");

	private ClienteREST cliente;

	public TMDBAPIImpl() {
		this.cliente = new ClienteRESTImpl();
	}

	private static String getAPIKey() {
		return Propiedades.getProperty(FICHERO_KEYS, "tmdb_key");
	}

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
}
