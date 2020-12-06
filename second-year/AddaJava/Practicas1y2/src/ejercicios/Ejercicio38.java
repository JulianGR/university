package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio38 {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		Long a = 4356448945l;
		// Long b = 4356448945l;

		System.out.println(divisoresWhile(a));
		// System.out.println(divisoresEficiente(a));

		long end = System.currentTimeMillis();

		System.out.println((end - start) + " ms");

	}

	// con Java 8 funcional
	public static List<Integer> divisores(Integer n) {
		return IntStream.rangeClosed(1, n).boxed().filter(x -> esDivisible(n, x)).collect(Collectors.toList());
	}

	public static boolean esDivisible(Integer a, Integer b) {
		return a % b == 0;
	}

	// con Java 5 while
	public static List<Long> divisoresWhile(Long n) {
		List<Long> res = new ArrayList<>();
		Long i = 1l;
		while (i <= n) {
			if (n % i == 0) {
				res.add(i);

			}
			i++;

		}
		return res;

	}

	public static List<Long> divisoresEficiente(Long n) {
		List<Long> res = new ArrayList<>();

		Long i = (n / 2) + 1;
		while (i > 0) {

			if (n % i == 0) {
				res.add(i);
			}
			i--;
		}
		res.add(n);
		return res;
	}

}
