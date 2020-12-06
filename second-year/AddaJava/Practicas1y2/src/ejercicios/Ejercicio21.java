package ejercicios;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio21 {

	public static void main(String[] args) {

	}

	public static String invertirCadenaWhile(String cadena) {
		String res = "";
		int i = cadena.length() - 1;
		while (i >= 0) {
			res += cadena.charAt(i);
			i--;
		}
		return res;
	}

//funcional
	public static String invertirCadenaJava8(String cadena) {
		return IntStream.rangeClosed(1, cadena.length()).boxed()
				.map(i -> String.valueOf(cadena.charAt(cadena.length() - i))).collect(Collectors.joining());
	}
}
