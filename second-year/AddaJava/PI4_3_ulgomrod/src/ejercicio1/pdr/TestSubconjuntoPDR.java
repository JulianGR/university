package ejercicio1.pdr;

import java.util.ArrayList;
import java.util.List;

import us.lsi.pd.AlgoritmoPD;

public class TestSubconjuntoPDR {

	public static void main(String[] args) {
		List<Integer> nums = List.of(1, 3, 1, 1, 2, 5, 8, 10, 6, 11);

		ProblemaSubconjuntoPDR.listaOriginal = nums;
		ProblemaSubconjuntoPDR e = new ProblemaSubconjuntoPDR(new ArrayList<>(), new ArrayList<>(), 0);

		AlgoritmoPD.isRandomize = false;

		var alg = AlgoritmoPD.createPDR(e);
		alg.ejecuta();

		// para pintar las 2 listas
		List<Integer> temp = new ArrayList<>(nums);
		temp.removeAll(alg.getSolucion());

		System.out.print(alg.getSolucion() + " + " + temp);

	}

}