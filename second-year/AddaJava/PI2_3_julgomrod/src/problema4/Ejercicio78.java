package problema4;

import java.util.Arrays;
import java.util.List;

public class Ejercicio78 {
	/*
	 * Dada una lista de palabras, ordenada alfabéticamente, se desea encontrar la
	 * posición de la palabra dada P y si no está devolverá -1. Se quiere diseñar un
	 * algoritmo que divida la lista en tres partes y seguir buscando en el tercio
	 * correspondiente.
	 */

	public static void main(String[] args) {
		System.out.println("==============TESTS=================");

		List<String> ls = Arrays.asList("Afganistán", "Bilbao", "Cuenca", "Danubio", "Egipto", "Faro", "Galia",
				"Holanda", "India", "Jordania", "Kioto", "Lugo", "Madrid");

		System.out.println("+Lista: " + ls + "\n");

		// IMPORTANTE: DAR PALABRA QUE ESTÉ EN LA FRASE (Por ejemplo "Egipto")
		System.out.println("+La palabra \"Egipto\" tiene índice 4");
		System.out.println("+Índice de la palabra: " + encuentraIndicePalabra(ls, "Egipto"));
		if (encuentraIndicePalabra(ls, "Egipto").equals(4)) {
			System.out.println("[OK]" + "\n");
		} else {
			System.out.println("[NOT OK]" + "\n");
		}

	}

	public static Integer encuentraIndicePalabra(List<String> ls, String palabra) {

		return encuentraIndicePalabra(ls, palabra, 0, ls.size());
	}

	// Es como Binary Search pero con 2 pivotes en vez de con 1
	private static Integer encuentraIndicePalabra(List<String> ls, String palabra, Integer i, Integer j) {
		Integer res = 0;
		Integer a = 0;
		Integer b = 0;

		if (j - i == 0) {
			res = -1;
		}

		else {

			a = i + ((j - i) / 3);
			b = i + (2 * ((j - i) / 3));

			if (ls.get(a) == palabra) {
				res = a;
			} else if (ls.get(b) == palabra) {
				res = b;
			} else if (palabra.compareTo(ls.get(a)) < 0) {
				res = encuentraIndicePalabra(ls, palabra, i, a);
			} else if (palabra.compareTo(ls.get(b)) < 0) {
				res = encuentraIndicePalabra(ls, palabra, a, b);
			} else {
				res = encuentraIndicePalabra(ls, palabra, b, j);
			}
		}

		return res;
	}

}