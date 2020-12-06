package Ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Tuple2;

public class SolucionCaballoBT {
	
	private Map<Tuple2<Integer, Integer>, Integer> tablero;
	
	public static SolucionCaballoBT create(Map<Tuple2<Integer, Integer>, Integer> tab) {
		return new SolucionCaballoBT(tab);
	}

	private SolucionCaballoBT(Map<Tuple2<Integer, Integer>, Integer> tab) {
		tablero = new HashMap<>(tab);
	}

	@Override
	public String toString() {
		int tam = ProblemaCaballoBT.lado;
		Integer[][] tab = new Integer[tam][tam];
		for(Tuple2<Integer, Integer> key : tablero.keySet()) {
			tab[key.v1][key.v2] = tablero.get(key);
		}
		
		String res = "";
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				res += String.format("%3d", tab[i][j]);
			}
			res += "\n";
		}
		return res;
	}
}
