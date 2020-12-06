package Ejercicios;

import us.lsi.bt.AlgoritmoBT;

public class TestCaballoBT {
	
	public static void main(String[] args) {
		ProblemaCaballoBT.lado = 6;
		ProblemaCaballoBT.iniX = 1;
		ProblemaCaballoBT.iniY = 1;
		
		AlgoritmoBT.numeroDeSoluciones = 10;
		
		EstadoCaballoBT estadoInicial = EstadoCaballoBT.create();
		AlgoritmoBT<SolucionCaballoBT, Integer, EstadoCaballoBT> alg = AlgoritmoBT.create(estadoInicial);
		
		alg.ejecuta();
		for(SolucionCaballoBT sol : alg.getSoluciones()) {
			System.out.println(sol);
		}
	}

//	private static void muestraSolucion(Map<Tuple2<Integer, Integer>, Integer> sol) {
//		int tam = ProblemaCaballoBT.lado;
//		Integer[][] tab = new Integer[tam][tam];
//		for(Tuple2<Integer, Integer> key : sol.keySet()) {
//			tab[key.v1][key.v2] = sol.get(key);
//		}
//		
//		String res = "";
//		for(int i = 0; i < tam; i++) {
//			for(int j = 0; j < tam; i++) {
//				res += String.format("%3d", args)
//			}
//		}
//		
//	}

}
