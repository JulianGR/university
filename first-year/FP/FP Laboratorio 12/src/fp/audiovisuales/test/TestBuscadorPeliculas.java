package fp.audiovisuales.test;

import java.util.Scanner;
import java.util.Set;

import fp.audiovisuales.BuscadorPeliculas;
import fp.audiovisuales.BuscadorPeliculasImpl;
import fp.audiovisuales.ClienteTMDB;
import fp.audiovisuales.Pelicula;
import fp.ejercicios.audiovisuales.GestorPeliculasFavoritas;
import fp.ejercicios.audiovisuales.UtilesAudiovisuales;

public class TestBuscadorPeliculas {

	public static void main(String[] args) {

		ClienteTMDB cliente = new ClienteTMDB();

		System.out.println("Creando gestor películas con filmografía de Brad Pitt");
		System.out.println("=====================================================");
		GestorPeliculasFavoritas gestor1 = UtilesAudiovisuales.generaPeliculasActor("Brad Pitt", cliente);

		System.out.println("Creando gestor películas con películas top de 2015");
		System.out.println("==================================================");
		GestorPeliculasFavoritas gestor2 = UtilesAudiovisuales.generaPeliculasTopAnyo(2015, cliente);

		BuscadorPeliculas buscador = new BuscadorPeliculasImpl(gestor1);
		buscador.indexaPeliculas(gestor2.getPeliculas());

		busquedaInteractiva(buscador);
	}

	private static void busquedaInteractiva(BuscadorPeliculas buscador) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce una o varias palabras separadas por espacios ($$$ para acabar): ");
		String consulta = sc.nextLine();
		while (!consulta.equals("$$$")) {
			String[] palabras = consulta.split(" ");
			Set<Pelicula> resultado = obtenResultados(buscador, palabras);
			mostrarResultados(resultado);
			System.out.print("Introduce una o varias palabras separadas por espacios ($$$ para acabar): ");
			consulta = sc.nextLine();
		}
		sc.close();
	}

	private static void mostrarResultados(Set<Pelicula> resultado) {
		if (resultado.isEmpty()) {
			System.out.println("No se encontraron películas.\n");
		} else {
			System.out.println("Películas encontradas:");
			for (Pelicula p : resultado) {
				System.out.println("\t\t" + p);
			}
			System.out.println();
		}

	}

	private static Set<Pelicula> obtenResultados(BuscadorPeliculas buscador, String[] palabras) {
		Set<Pelicula> resultado = null;
		if (palabras.length == 1) {
			resultado = buscador.buscaPeliculasPorTitulo(palabras[0]);
		} else {
			resultado = buscador.buscaPeliculasPorTitulo(palabras);
		}
		return resultado;
	}

}
