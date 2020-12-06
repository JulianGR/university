package ejercicio1.tests;

import java.util.List;

import ejercicio1.Ejercicio1PL;
import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class TestEjercicio1PL {
	public static void main(String[] args) {
		// especifico

		SolutionPLI a = AlgoritmoPLI.getSolutionFromFile("./ficheros/BomberosPL.txt");
		Ejercicio1PL.printSolution(a);

		// generico

		List<List<String>> ls = List.of(List.of("b0", "b1"), List.of("b1", "b0", "b5"), List.of("b2", "b3"),
				List.of("b3", "b2", "b4"), List.of("b4", "b3", "b5"), List.of("b5", "b4", "b1"));

		List<String> bar = List.of("b0", "b1", "b2", "b3", "b4", "b5");

		SolutionPLI a2 = AlgoritmoPLI.getSolution(Ejercicio1PL.constraint(bar, ls));
		Ejercicio1PL.printSolution(a2);
		
	}
}
