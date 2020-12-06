package fp.universidades.test;

import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import fp.universidades.Asignatura;
import fp.universidades.AsignaturaFactoria;

public class Experimento {

	public static void main(String[] args) {
		List<Asignatura> asignaturas = AsignaturaFactoria.creaAsignatura("resources/asignaturas.txt");
		experimento1(asignaturas);
	}

	private static void experimento1(List<Asignatura> asignaturas) {
		SortedMap<Double, SortedSet<Asignatura>> asignaturasPorCreditos = creaMap(asignaturas);
		System.out.println(("\nAsignaturas por créditos:"));
		muestraMap(asignaturasPorCreditos);
	}

	private static void muestraMap(SortedMap<Double, SortedSet<Asignatura>> asignaturasPorCreditos) {
		for (Entry<Double, SortedSet<Asignatura>> entrada : asignaturasPorCreditos.entrySet()) {
			System.out.println(entrada.getKey() + "-->" + entrada.getValue());
		}

	}

	private static SortedMap<Double, SortedSet<Asignatura>> creaMap(List<Asignatura> asignaturas) {
		@SuppressWarnings("unused")
		Comparator<Double> cmpReales = Comparator.reverseOrder();
		Comparator<Asignatura> cmpAsignatura = Comparator.comparing((Asignatura a) -> a.getNombre())
				.thenComparing((Comparator.naturalOrder()));
		SortedMap<Double, SortedSet<Asignatura>> res = new TreeMap<Double, SortedSet<Asignatura>>();

		for (Asignatura a : asignaturas) {
			Double clave = a.getCreditos();
			if (!res.containsKey(clave)) {
				SortedSet<Asignatura> ss = new TreeSet<Asignatura>(cmpAsignatura);
				ss.add(a);
				res.put(clave, ss);
			} else {
				SortedSet<Asignatura> ss = res.get(clave);
				ss.add(a);
			}
		}
		return res;
	}

}
