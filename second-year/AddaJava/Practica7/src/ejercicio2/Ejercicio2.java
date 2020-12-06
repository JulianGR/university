package ejercicio2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Streams2;
import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class Ejercicio2 {
	public static final Double INVERSION_MAX = 75.;

	public static void main(String[] args) {

		List<Oportunidad> datos = cargaDatos("./ficheros/Ejercicio2.txt");
		String definicion = defineProblema(datos);
//esto es igual, menos que es getSolution porque no hemos escrito el fichero a mano
		SolutionPLI alg = AlgoritmoPLI.getSolution(definicion);
		System.out.println("Valores de las variables de la Función objetivo");
		System.out.println(Arrays.toString(alg.getSolution()));
		System.out.println("valor resultante: " + alg.getGoal());
	}

	private static String defineProblema(List<Oportunidad> datos) {
		String res = funcionObj(datos);
		res += inversionMaxima(datos);
		res += otrasRestricciones(datos);
		return res;
	}

	private static String otrasRestricciones(List<Oportunidad> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("x%d<=1;", i))
				.collect(Collectors.joining("\n", "", ""));
	}

	private static String inversionMaxima(List<Oportunidad> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("%dx%d", datos.get(i).getCapital(), i))
				.collect(Collectors.joining("+", "", "<=" + INVERSION_MAX + ";\n"));
	}

	private static String funcionObj(List<Oportunidad> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("%dx%d", datos.get(i).getBeneficio(), i))
				.collect(Collectors.joining("+", "max: ", ";\n"));
	}

	private static List<Oportunidad> cargaDatos(String f) {
		return Streams2.fromFile(f).map(Oportunidad::create).collect(Collectors.toList());
	}
}
