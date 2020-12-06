package ejercicio1;

import java.util.List;

import us.lsi.pd.AlgoritmoPD;

public class Test {

	public static void main(String[] args) {

//esto se hace después, para darle valores

		List<Integer> nums = List.of(2, 4, 2, 0, 2, 3, 2, 4, 3, 3, 4);
		ProblemaSaltos e = new ProblemaSaltos(nums);

		// los tests son siempre igual

		// esto es por si quiero que en vez de que sea exacto, sea aproximado, que coja
		// una alternativa en vez de mirar todas
		AlgoritmoPD.isRandomize = false;

		// AlgoritmoPD alg = AlgoritmoPD.createPDR(e);
		// eso va a dar problemas por los tipos. Si ponemos var, pone los tipo
		// automáticamente en tiempo de ejecución

		var alg = AlgoritmoPD.createPDR(e);
		alg.ejecuta();
		System.out.println(alg.getSolucion());
		System.out.println("Saltos dados: " + alg.getSolucion().size());
	}

}
