package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class Ejercicio1AG implements ValuesInRangeProblemAG<Integer, List<Barrio>> {

	private static List<Barrio> barrios;
	private static Integer constante = 123456789;

	public Ejercicio1AG(List<List<String>> ls) {

		Barrio b0 = Barrio.create(ls.get(0).get(0), ls.get(0).stream().collect(Collectors.toList()));
		Barrio b1 = Barrio.create(ls.get(1).get(0), ls.get(1).stream().collect(Collectors.toList()));
		Barrio b2 = Barrio.create(ls.get(2).get(0), ls.get(2).stream().collect(Collectors.toList()));
		Barrio b3 = Barrio.create(ls.get(3).get(0), ls.get(3).stream().collect(Collectors.toList()));
		Barrio b4 = Barrio.create(ls.get(4).get(0), ls.get(4).stream().collect(Collectors.toList()));
		Barrio b5 = Barrio.create(ls.get(5).get(0), ls.get(5).stream().collect(Collectors.toList()));
		barrios = new ArrayList<>();
		barrios.add(b0);
		barrios.add(b1);
		barrios.add(b2);
		barrios.add(b3);
		barrios.add(b4);
		barrios.add(b5);

	}

	public Integer getVariableNumber() {
		return barrios.size();
	}

	public Integer getMax(Integer i) {
		return 2;
	}

	public Integer getMin(Integer i) {
		return 0;
	}

	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {

		List<Barrio> sol = getSolucion(cr);

		// Barrios adyacentes a los que tienen estación
		Set<Barrio> s = sol.stream().flatMap(c -> c.getAdyacencias().stream()).collect(Collectors.toSet());

		Double BarriosNoLlegaEstacion = (double) (barrios.size() - s.size());
		Double BarriosConEstacion = (double) sol.size();

		return -(BarriosNoLlegaEstacion * constante + BarriosConEstacion);

	}

	// devuelve lista con los barrios que los valores de cr.decode() sean 1
	// (significa que hay estacion de bomberos en ese barrio)
	public List<Barrio> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return IntStream.range(0, barrios.size()).boxed().filter(c -> (cr.decode().get(c)).equals(1))
				.map(i -> barrios.get(i)).collect(Collectors.toList());

	}
}
