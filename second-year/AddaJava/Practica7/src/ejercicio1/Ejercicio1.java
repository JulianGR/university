package ejercicio1;

import java.util.Arrays;

import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class Ejercicio1 {
	public static void main(String[] args) {

		SolutionPLI alg = AlgoritmoPLI.getSolutionFromFile("./ficheros/Ejercicio1.txt");
		System.out.println("Valores de las variables de la Función objetivo");
		System.out.println(Arrays.toString(alg.getSolution()));
		System.out.println("valor resultante: " + alg.getGoal());
	}

}
