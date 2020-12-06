package ejercicio1.tests;

import java.util.List;
import java.util.stream.Collectors;

import ejercicio1.Barrio;
import ejercicio1.Ejercicio1AG;
import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.common.Streams2;

public class TestEjercicio1AG {

	private final static String fichero = "./ficheros/BarriosAG.txt";

	public static void main(String[] args) {

		setCondiciones();
		List<List<String>> lsConFichero = Streams2.fromFile(fichero).map(l -> List.of(l.split(",")))
				.collect(Collectors.toList());

		List<List<String>> ls = List.of(List.of("b0", "b1"), List.of("b1", "b0", "b5"), List.of("b2", "b3"),
				List.of("b3", "b2", "b4"), List.of("b4", "b3", "b5"), List.of("b5", "b4", "b1"));

		// sin fichero
		ValuesInRangeProblemAG<Integer, List<Barrio>> p1 = new Ejercicio1AG(ls);

		AlgoritmoAG<ValuesInRangeChromosome<Integer>> a1 = AlgoritmoAG.create(ChromosomeFactory.ChromosomeType.Binary,
				p1);
		a1.ejecuta();

		System.out.println("Sin fichero :" + p1.getSolucion(a1.getBestChromosome()));

		// con fichero
		ValuesInRangeProblemAG<Integer, List<Barrio>> p2 = new Ejercicio1AG(lsConFichero);

		AlgoritmoAG<ValuesInRangeChromosome<Integer>> a2 = AlgoritmoAG.create(ChromosomeFactory.ChromosomeType.Binary,
				p2);
		a2.ejecuta();

		System.out.println("Con fichero :" + p2.getSolucion(a2.getBestChromosome()));
	}

	public static void setCondiciones() {
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.CROSSOVER_RATE = 0.85;
		AlgoritmoAG.MUTATION_RATE = 0.75;
		AlgoritmoAG.POPULATION_SIZE = 50;

		// conidiciones de parada
		StoppingConditionFactory.NUM_GENERATIONS = 150;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
	}
}
