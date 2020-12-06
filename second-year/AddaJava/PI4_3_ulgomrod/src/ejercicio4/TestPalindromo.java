package ejercicio4;

import us.lsi.pd.AlgoritmoPD;

public class TestPalindromo {

	public static void main(String[] args) {

		String cadena = "ababbbabbababa";
		ProblemaPalindromo e = new ProblemaPalindromo(cadena);

		// los tests son siempre igual

		// esto es por si quiero que en vez de que sea exacto, sea aproximado, que coja
		// una alternativa en vez de mirar todas
		AlgoritmoPD.isRandomize = false;

		// AlgoritmoPD alg = AlgoritmoPD.createPD(e);
		// eso va a dar problemas por los tipos. Si ponemos var, pone los tipo
		// automáticamente en tiempo de ejecución

		var alg = AlgoritmoPD.createPD(e);
		alg.ejecuta();
		System.out.println(alg.getSolucion());
	}

}
