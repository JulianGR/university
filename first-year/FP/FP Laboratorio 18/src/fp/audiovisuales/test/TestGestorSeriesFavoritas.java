package fp.audiovisuales.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import fp.audiovisuales.ClienteTMDB;
import fp.audiovisuales.GestorSeriesFavoritas;
import fp.audiovisuales.MiembroStaff;
import fp.audiovisuales.SerieTV;
import fp.audiovisuales.UtilesAudiovisuales;

public class TestGestorSeriesFavoritas {

	public static void main(String[] args) {

		ClienteTMDB cliente = new ClienteTMDB();

		System.out.println("Creando gestor series TOP 2010-2017");
		System.out.println("===================================");
		GestorSeriesFavoritas gestor1 = UtilesAudiovisuales.generaSeriesTopAnyos(2010, 2017, cliente);
		mostrarSeries(gestor1.getSeriesTV());
		testGestor(gestor1, "HBO", 2012, "Drama", "Sean Bean", "guerra",  cliente);
		testGestor(gestor1, "Netflix", 1960, "Comedia", "Winona Ryder", "hermanos", cliente);
		
		System.out.println("Creando Mis seriesFavoritas");
		System.out.println("===========================");
		GestorSeriesFavoritas gestor2 = UtilesAudiovisuales.generaSeriesFavoritasDeFichero(
				"Mis series favoritas", "resources/misSeriesFavoritas.txt", cliente);
		
		mostrarSeries(gestor2.getSeriesTV());
		testGestor(gestor2, "Netflix", 1960, "Comedia", "Winona Ryder", "hermanos", cliente);
		
		cliente.close();

	}

	private static void mostrarSeries(Collection<SerieTV> series) {
		series.stream()
			.forEach(serie->System.out.println(serie.getNombre() + "-->" + serie.getGeneros()));
		
	}

	public static void testGestor(GestorSeriesFavoritas gestor, String cadenaTV,
			Integer anyo, String genero, String nombreActor, String palabra, ClienteTMDB cliente) {

		System.out.println("¿Hay alguna serie de la cadena " + cadenaTV+ " hay en el gestor?");
		System.out.println("Respuesta: " + gestor.haySeriesDeCadenaTV(cadenaTV));

		System.out.println("¿Se emitieron todas las series en el año "+ anyo+ " ?");
		System.out.println("Respuesta: " + gestor.seEmitieronTodasEn(anyo));
		
		System.out.println("¿Cuantas series del género " + genero + " hay en el gestor?");
		System.out.println("Respuesta: " + gestor.getNumeroSeriesDeGenero(genero));
		
		System.out.println("¿Cuál es la serie que tiene más temporadas de las hay en el gestor?");
		SerieTV serieMasTemporadas =gestor.getSerieMasTemporadas();
		System.out.println("Respuesta: " + serieMasTemporadas.getNombre() + " con " + serieMasTemporadas.getNumeroTemporadas() + " temporadas");

		System.out.println("¿Cuál es la serie que tiene más episodios de las hay en el gestor?");
		SerieTV serieMasEpisodios =gestor.getSerieMasEpisodios();
		System.out.println("Respuesta: " + serieMasEpisodios.getNombre() + " con " + serieMasEpisodios.getNumeroEpisodios() + " episodios");
	
		System.out.println("¿Cuál es la serie que tiene más episodios de las hay en el gestor?");
		SerieTV serieMasPopular =gestor.getSerieMasPopular(genero);
		System.out.println("Respuesta: " + serieMasPopular.getNombre() + " de "+ genero +" con " + serieMasPopular.getPopularidad() + " de popularidad");
	
		System.out.println("¿Cuáles son las series en las que trabaja "+ nombreActor+ "?");
		MiembroStaff actor = UtilesAudiovisuales.getStaff(nombreActor, cliente);
		Set<SerieTV> seriesDe =gestor.getSeriesDe(actor);
		System.out.println("Respuesta: " );
		mostrarSeries(seriesDe);
		
		System.out.println("¿Cuales son las series, segun su estado?");
		Integer [] cuenta=gestor.contarSeriesSegunEstado();
		System.out.println("Respuesta: " + Arrays.toString(cuenta));
		
		String nombreFichero = gestor.getNombre().replace(" ", "")+"_"+ genero +".txt";
		System.out.println("Creando archivo "+  nombreFichero);
		gestor.guardaEnFicheroOrdenados("out/"+nombreFichero, genero);
		
		System.out.println("¿Qué series tienen la palabra "+ palabra + " en su sinopsis?");
		System.out.println("Respuesta: " + gestor.getNombresSeriesConPalabraEnSinopsis(palabra) );
		
		System.out.println("¿Cuál es la frecuencia de palabras de las sinopsis?");
		System.out.println("Respuesta: " );
		System.out.println(gestor.getFrecuenciaPalabrasSinopsis());
	}
}
