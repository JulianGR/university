package fp.audiovisuales.test;

import fp.audiovisuales.ClienteTMDB;
import fp.audiovisuales.GestorPeliculasFavoritas;
import fp.audiovisuales.Pelicula;
import fp.audiovisuales.UtilesAudiovisuales;
import fp.utiles.Imagenes;

public class TestGestorPeliculasFavoritas {

	public static void main(String[] args) {

		ClienteTMDB cliente = new ClienteTMDB();

		System.out.println("Creando gestor pelï¿½culas con filmografía de Brad Pitt");
		System.out.println("=====================================================");
		GestorPeliculasFavoritas gestor1 = UtilesAudiovisuales.generaPeliculasActor("Brad Pitt", cliente);
		testGestor(gestor1, "Angelina Jolie", "George Clooney", 2012, "RidleyScott", "Joel Coen");

		System.out.println("Creando gestor pelï¿½culas con pelï¿½culas top de 2015");
		System.out.println("==================================================");
		GestorPeliculasFavoritas gestor2 = UtilesAudiovisuales.generaPeliculasTopAnyo(2015, cliente);
		testGestor(gestor2, "Jennifer Lawrence", "Matt Damon", 2015, "RidleyScott", "Joel Coen");
		cliente.close();

	}

	public static void testGestor(GestorPeliculasFavoritas gestor, String actor1, String actor2, Integer anyo,
			String director1, String director2) {

		System.out.println("¿uantas peliculas de " + actor1 + " hay en el gestor?");
		System.out.println("Respuesta: " + gestor.cuentaPeliculas(actor1));

		System.out.println("ï¿½Cuï¿½les son los gï¿½neros de pelis que hay en el gestor");
		System.out.println("Respuesta: " + gestor.getGeneros());

		System.out.println("ï¿½Quï¿½ pelï¿½culas de " + actor2 + "hay en el gestor?");
		System.out.println("Respuesta: " + gestor.getPeliculasDeActor(actor2));

		System.out.println("ï¿½Cuï¿½l es el actor o actriz que ha trabajado en mï¿½s pelï¿½culas del gestor?");
		System.out.println("Respuesta:" + gestor.getActorMasPeliculas());

		System.out.println("ï¿½Cuï¿½l la pelï¿½cula del gestor en la que trabajan mï¿½s actores?");
		Pelicula peliMasActores = gestor.getPeliculaMasActores();
		System.out.println("Respuesta:" + peliMasActores);
		muestraPoster(gestor.getNombre() + "==> Peli con mï¿½s actores", peliMasActores);

		System.out.println("ï¿½Quï¿½ pelï¿½culas hay en el gestor del aï¿½o " + anyo + "?");
		System.out.println("Respuesta:" + gestor.getPeliculasAnyo(anyo));

		System.out.println("ï¿½Cuales de las pelï¿½culas del gestor han sido dirigidas por " + director1 + "?");
		System.out.println("Respuesta:" + gestor.getPeliculasDirigidasPor("Ridley Scott"));

		System.out.println("ï¿½Hay alguna pelï¿½cula en el gestor dirigida por " + director2 + "?");
		System.out.println("Respuesta:" + gestor.hayAlgunaPeliculaDirigidaPor(director2));

		System.out.println("ï¿½Cuï¿½les son los paï¿½ses de las pelï¿½culas del gestor? ");
		System.out.println("Respuesta:" + gestor.getPaises());

		System.out.println("ï¿½Cuï¿½les son los actores que trabajan en todas las peliculas del gestor? ");
		System.out.println("Respuesta: " + gestor.seleccionaActoresParticipantesTodas());
	}

	private static void muestraPoster(String msg, Pelicula peli) {
		if (peli != null) {
			Imagenes.show(msg, peli.getURLPoster());
		}
	}
}
