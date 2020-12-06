package ejercicio1.bt;

import java.util.ArrayList;
import java.util.List;

import us.lsi.bt.AlgoritmoBT;

public class TestSubconjuntoBT {
	public static void main(String[] args) {

		List<Integer> nums = List.of(1, 3, 1, 1, 2, 5, 8, 10, 6, 11);

		EstadoSubconjuntoBT e = new EstadoSubconjuntoBT(nums, new ArrayList<>(), new ArrayList<>(), 0);

		AlgoritmoBT<SolucionSubconjuntoBT, Integer, EstadoSubconjuntoBT> alg = AlgoritmoBT.create(e);

		alg.ejecuta();
//si sale null, mirar getSolucion()
		System.out.println("Solución: " + alg.getSolucion().toString());

	}

}
