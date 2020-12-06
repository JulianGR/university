package ejercicio1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.lpsolve.solution.SolutionPLI;

public class Ejercicio1PL {

	public static String constraint(List<String> bar, List<List<String>> ady) {
		String r = "";
		
		//min: b0+b1+b2+b3+b4+b5;
		r += IntStream.range(0, bar.size()).boxed().map(i -> bar.get(i))
				.collect(Collectors.joining("+", "min: ", ";\n"));

		// b2+b3+b4>=1;
		r += IntStream.range(0, ady.size()).boxed()
				.map(i -> ady.get(i).stream().collect(Collectors.joining("+", "", ">=1;\n")))
				.collect(Collectors.joining("", "", ""));

		// bin b0,b1,b2,b3,b4,b5;
		r += IntStream.range(0, bar.size()).boxed().map(i -> bar.get(i))
				.collect(Collectors.joining(",", "bin ", ";\n"));
		System.out.println(r);
		return r;
	}

	public static void printSolution(SolutionPLI a) {
		System.out.println("________");
		System.out.println("Goal = " + a.getGoal());
		for (int j = 0; j < a.getNames().size(); j++) {
			if (a.getSolution(j) > 0) {
				System.out.println(a.getName(j) + " = " + a.getSolution()[j]);
			}
		}
		System.out.println("________");

	}
}
