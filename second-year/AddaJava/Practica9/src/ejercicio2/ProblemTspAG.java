package ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicio2.tipos.Carretera;
import ejercicio2.tipos.Ciudad;
import us.lsi.ag.IndexChromosome;
import us.lsi.ag.IndexProblemAG;
import us.lsi.graphs.GraphsReader;

//en IndexProblemAG pongo el tipo de la solución, quiero que se me interprete como lista de Ciudad
public class ProblemTspAG implements IndexProblemAG<List<Ciudad>> {

	private static Graph<Ciudad, Carretera> grafo;
	private static List<Ciudad> ciudades;
	private static Double TOTAL_KMS; // Penalizamos que no haya arista entre una ciudad y otra con una carretera con
										// el mayor de los kms, es decir, la suma de todos

	public ProblemTspAG(String ficheroGrafo) {
		grafo = cargaGrafo(ficheroGrafo);
		ciudades = new ArrayList<>(grafo.vertexSet());
		TOTAL_KMS = grafo.edgeSet().stream().mapToDouble(Carretera::getKm).sum();
	}

	private static Graph<Ciudad, Carretera> cargaGrafo(String f) {
		return GraphsReader.newGraph(f, Ciudad::create, Carretera::create,
				() -> new SimpleWeightedGraph<>(Ciudad::create, Carretera::create), Carretera::getKm);

	}

	public Integer getObjectsNumber() {

		return ciudades.size();
	}

	// es la función de bondad, la que no da qué buena es la aproximación
	public Double fitnessFunction(IndexChromosome cr) {
		List<Ciudad> sol = getSolucion(cr);

		return IntStream.range(0, sol.size() - 1).boxed().mapToDouble(i -> pesoArista(sol.get(i), sol.get(i + 1)))
				.sum();
	}

	private double pesoArista(Ciudad c1, Ciudad c2) {
		if (grafo.containsEdge(c1, c2)) {
			return grafo.getEdge(c1, c2).getKm();
		} else {
			return TOTAL_KMS;
		}
	}

	public List<Ciudad> getSolucion(IndexChromosome cr) {
		List<Integer> cromosoma = cr.decode();
		List<Ciudad> sol = new ArrayList<>();
		cromosoma.forEach(i -> sol.add(ciudades.get(i)));
		sol.add(sol.get(0));
		return sol;
	}
}