package fp.tmdb.test;

import fp.tmdb.TMDBAPI;
import fp.tmdb.TMDBAPIImpl;
import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBReview;

public class TestTMDBAPI {
	public static void main(String[] args) {
		int i = 1;
		TMDBAPI api = new TMDBAPIImpl();

		// 40: UPN
		System.out.println("========================================== " + (i++));
		TMDBNetwork cadena1 = api.getNetwork(40);
		System.out.println("Cadena TV: " + cadena1);

		// 49: HBO
		System.out.println("========================================== " + (i++));
		TMDBNetwork cadena2 = api.getNetwork(49);
		System.out.println("Cadena TV: " + cadena2);

		// 287: Brad Pitt
		System.out.println("========================================== " + (i++));
		TMDBPerson persona1 = api.getPerson(287);
		System.out.println("Persona: " + persona1);

		// 13848: Charles Chaplin
		System.out.println("========================================== " + (i++));
		TMDBPerson persona2 = api.getPerson(13848);
		System.out.println("Persona: " + persona2);

		// 76341: Mad Max
		System.out.println("========================================== " + (i++));
		TMDBMovie pelicula = api.getMovie(76341);
		System.out.println("Pelicula: " + pelicula);

		// 142061: Batman: The Dark Knight Returns
		System.out.println("========================================== " + (i++));
		TMDBMovie pelicula2 = api.getMovie(142061);
		System.out.println("Pelicula: " + pelicula2);

		// 5884e4c6c3a3680650020400
		System.out.println("========================================== " + (i++));
		TMDBReview review = api.getReview("5884e4c6c3a3680650020400");
		System.out.println("Review: " + review);

		// 5488c29bc3a3686f4a00004a
		System.out.println("========================================== " + (i++));
		TMDBReview review2 = api.getReview("5488c29bc3a3686f4a00004a");
		System.out.println("Review: " + review2);

		/*
		 * // 62715: Dragon Ball Super
		 * System.out.println("========================================== " + (i++));
		 * TMDBTVSerie serie = api.getTVSerie(62715); System.out.println("TV Serie:" +
		 * serie);
		 * 
		 * // 1399: Juego de tronos
		 * System.out.println("========================================== " + (i++));
		 * TMDBTVSerie serie2 = api.getTVSerie(1399); System.out.println("TV Serie:" +
		 * serie2);
		 */
	}
}
