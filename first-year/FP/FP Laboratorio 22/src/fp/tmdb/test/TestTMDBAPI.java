package fp.tmdb.test;

import java.util.List;
import fp.tmdb.TMDBAPI;
import fp.tmdb.TMDBAPIImpl;
import fp.tmdb.TMDBAPIImpl;
import fp.tmdb.pojo.TMDBPage;
import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBMovieCredits;
import fp.tmdb.pojo.TMDBNamedObject;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBPersonCredits;
import fp.tmdb.pojo.TMDBReview;
import fp.tmdb.pojo.TMDBTVSerie;
import fp.tmdb.pojo.TMDBTVEpisodeCredits;
import fp.tmdb.pojo.TMDBTVSeason;
import fp.tmdb.pojo.TMDBTVSerieShort;

public class TestTMDBAPI {
	public static void main(String[] args) {
		int i = 1;
		TMDBAPI api = new TMDBAPIImpl();

		System.out.println("========================================== " + (i++));
		TMDBNetwork cadena1 = api.getNetwork(40);
		System.out.println("Cadena TV: " + cadena1);
		System.out.println("========================================== " + (i++));
		TMDBNetwork cadena2 = api.getNetwork(49);
		System.out.println("Cadena TV: " + cadena2);

		// 287: Brad Pitt

		System.out.println("========================================== " + (i++));
		TMDBPerson persona1 = api.getPerson(287);
		System.out.println("Persona: " + persona1);

		System.out.println("========================================== " + (i++));
		// 13848: Charles Chaplin
		TMDBPerson persona2 = api.getPerson(13848);
		System.out.println("Persona: " + persona2);

		// 287: Brad Pitt

		System.out.println("========================================== " + (i++));
		TMDBPersonCredits pelisBrad = api.getPersonCredits(287);
		System.out.println("Pelis Persona: " + pelisBrad);

		System.out.println("========================================== " + (i++));
		// 13848: Charles Chaplin
		TMDBPersonCredits pelisCharlot = api.getPersonCredits(13848);
		System.out.println("Pelis Persona: " + pelisCharlot);

		// 550: El club de la lucha
		System.out.println("========================================== " + (i++));
		TMDBMovie movie = api.getMovie(550);
		System.out.println("Pelicula : " + movie);

		// 3082: Tiempos modernos
		System.out.println("========================================== " + (i++));
		TMDBMovie movie2 = api.getMovie(3082);
		System.out.println("Pelicula : " + movie2);

		System.out.println("========================================== " + (i++));
		TMDBMovieCredits credits = api.getMovieCredits(550);
		System.out.println("Pelicula cr�ditos: " + credits);

		System.out.println("========================================== " + (i++));
		TMDBPage<TMDBNamedObject> ps = api.getPersonsByName("Smith");
		System.out.println(ps);
		System.out.println("========================================== " + (i++));
		TMDBPage<TMDBNamedObject> ps2 = api.getPersonsByName("Brad Pitt");
		System.out.println(ps2);
		System.out.println("========================================== " + (i++));

		System.out.println("Searching anillos");
		TMDBPage<TMDBMovie> ps3a = api.getMoviesByTitle("Anillos");
		System.out.println(ps3a);
		System.out.println("========================================== " + (i++));

		TMDBPage<TMDBMovie> ps3b = api.getMoviesByTitle("Tiempos modernos");
		System.out.println(ps3b);
		System.out.println("========================================== " + (i++));

		List<TMDBMovie> listaPelis = api.getAllMoviesByTitle("Lord of");
		System.out.println(listaPelis.size() + " resultados");
		System.out.println(listaPelis);
		System.out.println("========================================== " + (i++));
		List<TMDBNamedObject> listaPersonas = api.getAllPersonsByName("Chaplin");
		System.out.println(listaPersonas.size() + " resultados");
		System.out.println(listaPersonas);
		System.out.println("========================================== " + (i++));

		TMDBTVSerie serie = api.getTVSerie(62560);
		System.out.println("TV Serie.." + serie);
		System.out.println("========================================== " + (i++));
		TMDBTVSeason temporada1 = api.getTVSeason(62560, 1);
		System.out.println("TV Serie Season.." + temporada1);
		System.out.println("========================================== " + (i++));
		TMDBTVEpisodeCredits episodeCredits1 = api.getTVEpisodeCredits(62560, 1, 1);
		System.out.println("TV Serie Episode Credits.." + episodeCredits1);
		System.out.println("========================================== " + (i++));

		TMDBPage<TMDBTVSerieShort> ps4 = api.getTVSeriesByTitle("Ministerio del tiempo");
		System.out.println(ps4);
		System.out.println("========================================== " + (i++));

		List<TMDBTVSerieShort> listaSeries = api.getAllTVSeriesByTitle("Star Trek");
		System.out.println(listaSeries.size() + " resultados");
		System.out.println(listaSeries);
		System.out.println("========================================== " + (i++));

		TMDBPage<TMDBTVSerieShort> ps5 = api.getTVSeriesByTitle("Thrones");
		System.out.println(ps5);
		System.out.println("========================================== " + (i++));
		// 1399: Juego de tronos
		TMDBTVSerie serie2 = api.getTVSerie(1399);
		System.out.println("TV Serie.." + serie2);
		System.out.println("========================================== " + (i++));
		sleep(1000L);
		List<TMDBMovie> topPelis = api.getAllTopMoviesByYear(2016);
		System.out.println(topPelis.size() + " resultados");
		System.out.println(topPelis);
		sleep(2000L);
		List<TMDBTVSerieShort> topSeries = api.getAllTopTVSeriesByYear(2016);
		System.out.println(topSeries.size() + " resultados");
		System.out.println(topSeries);
		sleep(2000L);
		System.out.println("========================================== " + (i++));
		TMDBReview r = api.getReview("5488c29bc3a3686f4a00004a");
		System.out.println("Review: " + r);
	}

	private static void sleep(Long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
