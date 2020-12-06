package ejercicio2;

import java.util.List;

import ejercicio2.tipos.Ciudad;
import us.lsi.ag.IndexChromosome;
import us.lsi.ag.IndexProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestTspAG {

	private static final String fichero = "./ficheros/TSP.txt";

	public static void main(String[] args) {
		setCondiciones();
		IndexProblemAG<List<Ciudad>> problem = new ProblemTspAG(fichero);
		AlgoritmoAG<IndexChromosome> alg = AlgoritmoAG.create(ChromosomeType.IndexPermutation, problem);
		alg.ejecuta();
		System.out.println("Ciclo obtenido: " + problem.getSolucion(alg.getBestChromosome()));
		System.out.println("Coste del ciclo: " + problem.fitnessFunction(alg.getBestChromosome()) * -1);
	}

	private static void setCondiciones() {
		// condiciones evolutivas
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.MUTATION_RATE = 0.5;
		AlgoritmoAG.POPULATION_SIZE = 300;

		// conidiciones de parada
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 3;
		StoppingConditionFactory.FITNESS_MIN = 0;

	}
}
