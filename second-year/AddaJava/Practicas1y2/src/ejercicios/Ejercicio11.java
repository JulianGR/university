package ejercicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import us.lsi.common.ListMultimap;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejercicio11 {
	public static void main(String[] args) {

		List<Punto2D> lst = new ArrayList<>();
		Punto2D punto = Punto2D.create(1.0, 1.0);
		lst.add(punto);

		System.out.println(porCuadrante(lst));
		System.out.println(porCuadranteWhile(lst));

	}

	// con Java funcional

	/*
	 * public static ListMultimap<Punto2D.Cuadrante, List<Punto2D>>
	 * porCuadranteJava(List<Punto2D> ls) { return
	 * ls.stream().collect(Collectors.groupingBy(p -> p.getCuadrante())); }
	 */
	public static ListMultimap<Cuadrante, List<Punto2D>> porCuadranteJava8(List<Punto2D> ls) {
		ListMultimap<Cuadrante, List<Punto2D>> res = new ListMultimap<>();
		return res;
	}

	public static ListMultimap<Cuadrante, Punto2D> porCuadrante(List<Punto2D> ls) {

		// ListMultimap: asocia las keys con una lista de valores de lo que aparece como
		// value
		ListMultimap<Cuadrante, Punto2D> res = new ListMultimap<>();
		Iterator<Punto2D> it1 = ls.iterator();
		Punto2D paux = null;
		while (it1.hasNext()) {
			paux = it1.next();
			res.put(paux.getCuadrante(), paux);

		}
		return res;

	}

	// con Java 5 while
	public static ListMultimap<Cuadrante, List<Punto2D>> porCuadranteWhile(List<Punto2D> ls) {
		List<Punto2D> c1 = new ArrayList<>();
		List<Punto2D> c2 = new ArrayList<>();
		List<Punto2D> c3 = new ArrayList<>();
		List<Punto2D> c4 = new ArrayList<>();
		ListMultimap<Cuadrante, List<Punto2D>> m = new ListMultimap<>();
		m.put(Punto2D.Cuadrante.PRIMER_CUADRANTE, c1);
		m.put(Punto2D.Cuadrante.SEGUNDO_CUADRANTE, c2);
		m.put(Punto2D.Cuadrante.TERCER_CUADRANTE, c3);
		m.put(Punto2D.Cuadrante.CUARTO_CUADRANTE, c4);
		int i = 0;

		while (i < ls.size()) {
			if (ls.get(i).getCuadrante().equals(Punto2D.Cuadrante.PRIMER_CUADRANTE)) {
				c1.add((ls.get(i)));
			} else if (ls.get(i).getCuadrante().equals(Punto2D.Cuadrante.SEGUNDO_CUADRANTE)) {
				c2.add((ls.get(i)));
			} else if (ls.get(i).getCuadrante().equals(Punto2D.Cuadrante.TERCER_CUADRANTE)) {
				c3.add((ls.get(i)));
			} else if (ls.get(i).getCuadrante().equals(Punto2D.Cuadrante.CUARTO_CUADRANTE)) {
				c4.add((ls.get(i)));
			}
			i++;
		}

		return m;
	}
}
