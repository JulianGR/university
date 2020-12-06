package fp.audiovisuales.test;

import fp.audiovisuales.ClienteTMDB;
import fp.audiovisuales.GestorPeliculasFavoritas;
import fp.audiovisuales.Pelicula;
import fp.audiovisuales.UtilesAudiovisuales;
import fp.utiles.Imagenes;

public class TestGestorPeliculasFavoritas {

	public static void main(String[] args) {

		ClienteTMDB cliente = new ClienteTMDB();

		System.out.println("Creando gestor películas con filmografía de Brad Pitt");
		System.out.println("=====================================================");
		GestorPeliculasFavoritas gestor1 = UtilesAudiovisuales.generaPeliculasActor("Brad Pitt", cliente);
		testGestor(gestor1, "Angelina Jolie", "George Clooney", 2012, "RidleyScott", "Joel Coen");

		System.out.println("Creando gestor películas con películas top de 2015");
		System.out.println("==================================================");
		GestorPeliculasFavoritas gestor2 = UtilesAudiovisuales.generaPeliculasTopAnyo(2015, cliente);
		testGestor(gestor2, "Jennifer Lawrence", "Matt Damon", 2015, "RidleyScott", "Joel Coen");
		cliente.close();

	}

	public static void testGestor(GestorPeliculasFavoritas gestor, String actor1, String actor2, Integer anyo,
			String director1, String director2) {

		System.out.println("¿Cuantas peliculas de " + actor1 + " hay en el gestor?");
		System.out.println("Respuesta: " + gestor.cuentaPeliculas(actor1));

		System.out.println("¿Cuáles son los géneros de pelis que hay en el gestor");
		System.out.println("Respuesta: " + gestor.getGeneros());

		System.out.println("¿Qué películas de " + actor2 + " hay en el gestor?");
		System.out.println("Respuesta: " + gestor.getPeliculasDeActor(actor2));

		System.out.println("¿Cuál es el actor o actriz que ha trabajado en más películas del gestor?");
		System.out.println("Respuesta:" + gestor.getActorMasPeliculas());

		System.out.println("¿Cuál la película del gestor en la que trabajan más actores?");
		Pelicula peliMasActores = gestor.getPeliculaMasActores();
		System.out.println("Respuesta:" + peliMasActores);
		muestraPoster(gestor.getNombre() + "==> Peli con más actores", peliMasActores);

		System.out.println("¿Qué películas hay en el gestor del año " + anyo + "?");
		System.out.println("Respuesta:" + gestor.getPeliculasAnyo(anyo));

		System.out.println("¿Cuales de las películas del gestor han sido dirigidas por " + director1 + "?");
		System.out.println("Respuesta:" + gestor.getPeliculasDirigidasPor("Ridley Scott"));

		System.out.println("¿Hay alguna película en el gestor dirigida por " + director2 + "?");
		System.out.println("Respuesta:" + gestor.hayAlgunaPeliculaDirigidaPor(director2));

		System.out.println("¿Cuáles son los países de las películas del gestor? ");
		System.out.println("Respuesta:" + gestor.getPaises());

		System.out.println("¿Cuáles son los actores que trabajan en todas las peliculas del gestor? ");
		System.out.println("Respuesta: " + gestor.seleccionaActoresParticipantesTodas());

		System.out.println("¿Cuáles son las películas dirigidas en solitario por cada director? ");
		System.out.println("Respuesta: " + gestor.getPeliculasPorDirector());

		System.out.println(
				"¿Cuál es la duración media en minutos de las películas dirigidas en solitario por cada director? ");
		System.out.println("Respuesta: " + gestor.getDuracionMediaPorDirector());
	}

	private static void muestraPoster(String msg, Pelicula peli) {
		if (peli != null) {
			Imagenes.show(msg, peli.getURLPoster());
		}
	}
}
