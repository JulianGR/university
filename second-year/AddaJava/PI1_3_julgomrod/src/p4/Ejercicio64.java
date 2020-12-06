package p4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio64 {
	public static void main(String[] args) {
		// para probar que funciona
		System.out.println("Tests:\n");
		List<Integer> ls1 = new ArrayList<>();
		List<Integer> ls2 = new ArrayList<>();
		Integer i = 0;
		Integer j = 0;

		while (i < 10) {
			ls1.add(i);
			i += 2;
		}

		while (j < 10) {
			ls2.add(j);
			j++;
		}

		System.out.println("Listas iniciales");
		System.out.println(ls1);
		System.out.println(ls2 + "\n");

		System.out.println("Fusion de listas con Java 10\n" + fusionListasJava10(ls1, ls2) + "\n");
		System.out.println("Fusion de listas con while\n" + fusionListasWhile(ls1, ls2) + "\n");
		System.out.println("Fusion de listas con recursividad\n" + fusionListasRecursivo(ls1, ls2) + "\n");

	}

	/*
	 * ENUNCIADO: PI1.4 (64). Dadas dos listas ordenadas obtener otra
	 * cadena{entiendo que se refiere a otra lista} ordenada que contenga los
	 * elementos de las dos anteriores (fusión de listas)
	 */

	/*
	 * T extends Comparable<?super T> signifca que un tipo genérico es comparable
	 * (para que se pueda ordenar) y este tipo genérico tendrá también el orden del
	 * padre
	 */
	public static <T extends Comparable<? super T>> List<T> fusionListasJava10(List<T> ls1, List<T> ls2) {
		Stream<T> resultante = Stream.concat(ls1.stream(), ls2.stream());

		return resultante.sorted().collect(Collectors.toList());
	}

	public static <T extends Comparable<? super T>> List<T> fusionListasWhile(List<T> ls1, List<T> ls2) {
		List<T> res = new ArrayList<T>();

		int i = 0;
		int j = 0;

		while (ls1.size() > i || ls2.size() > j) {

			// si la i ya ha llegado al final pero la j no, todos los de la j para dentro
			if (ls1.size() == i && ls2.size() > j) {
				res.add(ls2.get(j));
				j++;

				// si la j ha llegado pero la i no, todos los de la i para dentro
			} else if (ls1.size() > i && ls2.size() == j) {
				res.add(ls1.get(i));
				i++;

				// si han llegado los 2, fuera
			} else if (ls1.size() == i && ls2.size() == j) {
				return res;
			}

			// si los 2 están llegando
			else if (ls1.get(i).compareTo(ls2.get(j)) <= 0) {
				res.add(ls1.get(i));
				i++;
			} else {
				res.add(ls2.get(j));
				j++;
			}

		}
		return res;
	}

	public static <T extends Comparable<? super T>> List<T> fusionListasRecursivo(List<T> ls1, List<T> ls2) {

		return fusionListasRecursivo(ls1, ls2, 0, 0, new ArrayList<T>());
	}

	public static <T extends Comparable<? super T>> List<T> fusionListasRecursivo(List<T> ls1, List<T> ls2, Integer i,
			Integer j, List<T> res) {

		if (ls1.size() > i || ls2.size() > j) {

			if (ls1.size() == i && ls2.size() > j) {
				res.add(ls2.get(j));
				return fusionListasRecursivo(ls1, ls2, i, j + 1, res);

				// si la j ha llegado pero la i no, todos los de la i para dentro
			} else if (ls1.size() > i && ls2.size() == j) {
				res.add(ls1.get(i));
				return fusionListasRecursivo(ls1, ls2, i + 1, j, res);

				// si han llegado los 2, fuera
			} else if (ls1.size() == i && ls2.size() == j) {
				return res;
			}

			// si los 2 están llegando
			else if (ls1.get(i).compareTo(ls2.get(j)) <= 0) {
				res.add(ls1.get(i));
				return fusionListasRecursivo(ls1, ls2, i + 1, j, res);
			} else {
				res.add(ls2.get(j));
				return fusionListasRecursivo(ls1, ls2, i, j + 1, res);
			}
		}

		return res;

	}

}
