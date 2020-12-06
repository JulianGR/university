package ejercicio77;

import java.util.List;
import java.util.stream.Stream;

public class Practica4 {
	public static void main(String[] args) {
//hace falta poner los casos
		Integer v[] = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		System.out.println("El v[k] = k  es" + conArrays(v));
		System.out.println("\nEl v[k] = k  es" + conArraysRecursivo(v));
	}

	public static Integer busquedaBinaria(List<Integer> ls) {
		int i = 0;
		int j = ls.size();
		int k = (ls.size() / 2);

		while (j - i > 0 && !(ls.get(k) == k)) {

			if (k < ls.get(k)) {
				j = k;
				k = (i + k) / 2;

			} else {
				i = k + 1;
				k = (k + 1 + j) / 2;

			}
		}
		return k;
	}

	// con funcional la idea es crear una secuencia de objetos que intervienen en
	// las iteraciones

	// vamos a crearnos una clase con 3 atributos (las variables del while)

	// también se puede hacer con tuple3

	// solución funcional
	public static Integer ejercicio77F(Integer v[]) {

		Aux semilla = new Aux(0, v.length, v.length / 2);
		return Stream.iterate(semilla, t -> siguiente(v, t)).limit(v.length).dropWhile(x -> v[x.k] != x.k).findFirst()
				.get().k;
	}

	private static class Aux {
		public int i, j, k;

		public Aux(int a, int b, int c) {
			i = a;
			j = b;
			k = c;
		}
	}

	private static Aux siguiente(Integer v[], Aux t) {
		Aux res;

		if (v[t.k] > t.k) {
			res = new Aux(t.i, t.k - 1, (t.i + t.k - 1) / 2);
		} else {
			res = new Aux(t.k + 1, t.j, (t.k + 1 + t.j) / 2);
		}

		return res;
	}

	// pensar solución en la que cada número está ordenado con su índice (Integer 7
	// en la posición 7, el 8 en la 8, el 9 en la 9... qué devolvería?
	public static Integer conArrays(Integer v[]) {

		int i = 0;
		int j = v.length;
		int k = (i + j) / 2;

		while (v[k] != k && i < j) {
			if (v[k] > k) {
				j = k - 1;
			} else {
				i = k + 1;
			}
		}
		k = (i + j) / 2;
		if (v[k] != k) {
			k = -1;
		}
		return k;
	}

	public static Integer conArraysRecursivo(Integer[] v) {
		return conArraysRecursivo(v, 0, v.length, v.length / 2);
	}

	private static Integer conArraysRecursivo(Integer[] v, int i, int j, int k) {
		if (i >= j) {
			return -1;
		} else if (v[k] == k) {
			return k;
		} else if (v[k] > k) {
			return conArraysRecursivo(v, i, k - 1, (i + k - 1) / 2);
		} else {
			return conArraysRecursivo(v, k + 1, j, (k + 1 + j) / 2);
		}
	}

}
