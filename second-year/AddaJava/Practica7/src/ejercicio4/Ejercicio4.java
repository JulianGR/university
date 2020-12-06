package ejercicio4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Streams2;
import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class Ejercicio4 {
	public static final Integer TAMANYO_MAX = 30;

	public static void main(String[] args) {

		List<Elemento> datos = cargaDatos("./ficheros/Ejercicio4.txt");
		String definicion = defineProblema(datos);
//esto es igual, menos que es getSolution porque no hemos escrito el fichero a mano
		SolutionPLI alg = AlgoritmoPLI.getSolution(definicion);
		System.out.println("Valores de las variables de la Función objetivo");
		System.out.println(Arrays.toString(alg.getSolution()));
		System.out.println("valor resultante: " + alg.getGoal());
	}

	private static String defineProblema(List<Elemento> datos) {
		String res = funcionObj(datos);
		res += tamanyoMaxima(datos);
		res += otrasRestricciones(datos);
		return res;
	}

	private static String otrasRestricciones(List<Elemento> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("x%d<=1;", i))
				.collect(Collectors.joining("\n", "", ""));
	}

	private static String tamanyoMaxima(List<Elemento> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("%dx%d", datos.get(i).getTamanyo(), i))
				.collect(Collectors.joining("+", "", "<=" + TAMANYO_MAX + ";\n"));
	}

	private static String funcionObj(List<Elemento> datos) {
		return IntStream.range(0, datos.size()).boxed().map(i -> String.format("%dx%d", 1, i))
				.collect(Collectors.joining("+", "min: ", ";\n"));
	}

	private static List<Elemento> cargaDatos(String f) {
		return Streams2.fromFile(f).map(Elemento::create).collect(Collectors.toList());
	}
}
