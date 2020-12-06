package p1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ejercicio57 {
	public static void main(String[] args) {
		// Esto es simplemente para probarlo metiendo una frase
		String frase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Vestibulum eget porttitor ante. Aliquam eleifend mi palabraMasLargaConMinusculasFinal";

		System.out.println("+Frase: " + frase);

		List<String> ls = new ArrayList<>();

		String[] palabras = frase.replace(".", " ").replaceAll(",", " ").replaceAll("  ", " ").split(" ");
		for (String s : palabras) {
			s.trim();
			ls.add(s);
		}
		System.out.println("+Lista: " + ls + "\n");
		System.out.println("+Minúsculas de \"Test\" con while: " + cuentaCuantasMinusculasWhile("Test"));
		System.out.println("+Minúsculas de \"Test\" con Java 10: " + cuentaCuantasMinusculasJava10("Test"));
		System.out.println("+Minúsculas de \"Test\" con Recursivo: " + cuentaCuantasMinusculasRecursivo("Test") + "\n");

		System.out.println("+String con más minúsculas Java10: " + conMasMinusculasJava10(ls));
		System.out.println("+String con más minúsculas while: " + conMasMinusculasWhile(ls));
		System.out.println("+String con más minúsculas recursivo: " + conMasMinusculasRecursivo(ls));

	}

	/*
	 * ENUNCIADO: PI1.1 (57). Dada una lista de String buscar la cadena que tiene un
	 * mayor número de caracteres en minúscula
	 */

	// intento de hacerlo con regex fallido
	public static Integer cuentaCuantasMinusculasJava10(String s) {

		/*
		 * Predicate<String> pred = Pattern.compile("[a-z]").asPredicate();
		 * 
		 * return (int) Stream.of(s.toCharArray()).map(x -> "" +
		 * x).filter(pred).count();
		 * 
		 * return (int) Stream.of(s.toCharArray()).map(x ->
		 * x.toString()).filter(pred).count();
		 * 
		 * Long res = s.chars().filter(pred).count();
		 */
		return (int) s.chars().filter(Character::isLowerCase).count();

	}

	// si el un caracter es minúscula, el contador sube
	public static Integer cuentaCuantasMinusculasWhile(String s) {
		Integer res = 0;
		int i = 0;

		while (s.length() > i) {

			if (Character.isLowerCase(s.charAt(i))) {
				res++;
			}
			i++;

		}

		return res;
	}

	public static String conMasMinusculasJava10(List<String> ls) {
		return ls.stream().max(Comparator.comparing(str -> cuentaCuantasMinusculasJava10(str))).get();

	}

// incialmente el índice está al principio, y el contadorMinusculas está con las minúsculas de la primera palabra
	public static String conMasMinusculasWhile(List<String> ls) {
		int i = 0;
		String res = "No hay palabras con minúsculas";
		Integer contadorMinusculas = cuentaCuantasMinusculasWhile(ls.get(i));

		// hasta que se llene, el índice avanza, si la palabra del índice tiene más
		// minúsculas que la del acumulador, entonces se la queda
		while (ls.size() > i) {

			if (cuentaCuantasMinusculasWhile(ls.get(i)) >= contadorMinusculas) {

				res = ls.get(i);
				contadorMinusculas = cuentaCuantasMinusculasWhile(ls.get(i));

			}
			i++;
		}

		return res;
	}

	public static Integer cuentaCuantasMinusculasRecursivo(String s) {
		return cuentaCuantasMinusculasRecursivo(s, 0, 0);
	}

	public static Integer cuentaCuantasMinusculasRecursivo(String s, Integer res, int i) {

		if (s.length() > i) {
			if (Character.isLowerCase(s.charAt(i))) {
				return cuentaCuantasMinusculasRecursivo(s, res + 1, i + 1);
			} else {

				return cuentaCuantasMinusculasRecursivo(s, res, i + 1);
			}

		}
		return res;
	}

	// si me dan solo la lisya, generalizo
	public static String conMasMinusculasRecursivo(List<String> ls) {
		return conMasMinusculasRecursivo(ls, 0, "No hay palabras con minúsculas", 0);
	}

//generalización
	public static String conMasMinusculasRecursivo(List<String> ls, Integer i, String res, Integer contadorMinusculas) {
//caso base: si estamos al principio, cojo la palabra directamente
		if (i == 0) {
			return conMasMinusculasRecursivo(ls, i + 1, ls.get(i), cuentaCuantasMinusculasRecursivo(ls.get(i)));
//si estamos avanzando, tengo que ver si la palabra del índice es más grande que la del acumulador
		} else if (i > 0 && ls.size() > i) {
			if (cuentaCuantasMinusculasRecursivo(ls.get(i)) > contadorMinusculas) {
				return conMasMinusculasRecursivo(ls, i + 1, ls.get(i), cuentaCuantasMinusculasRecursivo(ls.get(i)));
			} else {
				return conMasMinusculasRecursivo(ls, i + 1, res, contadorMinusculas);
			}
		}

		return res;
	}

}
