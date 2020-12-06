package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio60 {

	// funcional
	public static List<Integer> primosJava8(Integer n) {
		return IntStream.rangeClosed(2, n).boxed().filter(x -> esPrimoJava8(x)).map(x -> x ^ 2)
				.collect(Collectors.toList());
	}

	public static Boolean esPrimoJava8(Integer n) {
		Integer sqrt = ((int) Math.sqrt(n));
		// Integer sqrt = new Integer ( Math.sqrt(n));
		return IntStream.rangeClosed(2, sqrt).noneMatch(x -> n % x == 0);
	}

	// recursiva
	public static List<Integer> recursiva(Integer n) {
		return primosRe(2, n, new ArrayList<>());

	}

	private static List<Integer> primosRe(Integer i, Integer n, List<Integer> res) {
		if (!(i <= n)) {
			return res;
		} else {
			if (esPrimoRe(i)) {
				res.add(i * i);

			}
			primosRe(i + 1, n, res);
		}
		return res;
	}

	public static Boolean esPrimoRe(Integer a) {
		Integer sqrt = ((int) Math.sqrt(a));
		return esPrimoRe(a, 2, sqrt);

	}

	private static boolean esPrimoRe(Integer a, Integer i, Integer n) {
		if (i > n) {
			return true;
		} else {
			return a % i != 0 && esPrimoRe(a, i + 1, n);
		}
	}

	// while
	public static List<Integer> primosWhile(Integer n) {
		List<Integer> res = new ArrayList<Integer>();
		Integer i = 2;
		while (i <= n) {
			if (esPrimoWhile(i)) {
				res.add(i ^ 2);
				i++;
			} else {
				i++;
			}

		}
		return res;

	}

	public static Boolean esPrimoWhile(Integer a) {
		Boolean res = true;
		Integer b = 3;
		if (a < 2) {
			res = false;
		} else if (a % 2 == 0) {
			res = false;
		} else {
			while (b <= Math.sqrt(a)) {
				if (b % a == 0) {
					res = false;
					break;
				}

				b += 2;
			}
		}

		return res;
	}
}
