package ejercicio1;

import us.lsi.common.Tuple;
import us.lsi.pd.AlgoritmoPD;

public class Test {

	public static void main(String[] args) {

		ProblemaReyPDR.setLado(3);
		ProblemaReyPDR e = ProblemaReyPDR.of(Tuple.create(1, 1));

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
