package ejercicio4;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;
import us.lsi.common.Files2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio4 {
	public static void main(String[] args) {

		// Para el simple no dirigido con peso
		SimpleWeightedGraph<Monumento, Desplazamiento> graph = GraphsReader.newGraph("./ficheros/P4-Grafo1.txt",
				Monumento::create, Desplazamiento::create,
				() -> new SimpleWeightedGraph<Monumento, Desplazamiento>(Monumento::create, Desplazamiento::create),
				Desplazamiento::getTiempo);

		DOTExporter<Monumento, Desplazamiento> de1 = new DOTExporter<Monumento, Desplazamiento>(
				new IntegerComponentNameProvider<>(), x -> x.getNombre(),
				x -> x.getSource() + " , " + x.getTarget() + "--" + x.getTiempo());

		PrintWriter pw = Files2.getWriter("./ficheros/P4-Grafo1.gv");
		de1.exportGraph(graph, pw);

		// para el simple dirigido sin peso
		SimpleDirectedGraph<Monumento, Desplazamiento> graph2 = GraphsReader.newGraph("./ficheros/P4-Grafo2.txt",
				Monumento::create, Desplazamiento::create,
				() -> new SimpleDirectedGraph<Monumento, Desplazamiento>(Monumento::create, Desplazamiento::create,
						true),
				Desplazamiento::getTiempo);

		DOTExporter<Monumento, Desplazamiento> de2 = new DOTExporter<Monumento, Desplazamiento>(
				new IntegerComponentNameProvider<>(), x -> x.getNombre(), x -> x.getSource() + " , " + x.getTarget());

		PrintWriter pw2 = Files2.getWriter("./ficheros/P4-Grafo2.gv");
		de2.exportGraph(graph, pw2);

		System.out.println("========Apartado 1=======");

		apartado1(graph);
		System.out.println("\n");

		System.out.println("========Apartado 2=======");

		apartado2(graph2);
		System.out.println("\n");
		System.out.println("========Apartado 3=======");

		// lista de los vértices
		List<String> ls = List.of("Sitio0", "Sitio1", "Sitio2");

		apartado3(graph, graph2, ls);

	}

	private static void apartado1(SimpleWeightedGraph<Monumento, Desplazamiento> graph) {
		ConnectivityInspector<Monumento, Desplazamiento> alg = new ConnectivityInspector<>(graph);
		System.out.println("¿Es conexo?: " + alg.isConnected());

	}

// los vértices que se pueden visitar sin haber visitado otros antes son los que no tienen aristas que apuntan hacia ellos
	private static void apartado2(SimpleDirectedGraph<Monumento, Desplazamiento> graph) {
		List<Monumento> vs = new ArrayList<Monumento>(graph.vertexSet());
		List<Monumento> res = vs.stream().filter(i -> (graph.inDegreeOf(i) == (0))).collect(Collectors.toList());
		System.out.println("Vértices que tienen grado entrante 0: " + res);
	}

	private static void apartado3(SimpleWeightedGraph<Monumento, Desplazamiento> grafoConPeso,
			SimpleDirectedGraph<Monumento, Desplazamiento> grafoDirigido, List<String> ls) {

		ConnectivityInspector<Monumento, Desplazamiento> cn = new ConnectivityInspector<>(grafoConPeso);

		Boolean res1 = false;
		Boolean res2 = false;

		ShortestPathAlgorithm<Monumento, Desplazamiento> sp = new DijkstraShortestPath<>(grafoConPeso);

		// verificamos que los vértices que nos dan están conectados, es decir, que
		// existe camino entre ellos

		Boolean existeCamino = IntStream.range(0, ls.size() - 1).boxed()
				.map(i -> cn.pathExists(Monumento.create(ls.get(i)), Monumento.create(ls.get(i + 1)))).allMatch(x -> x);

		if (existeCamino) {
			res1 = true;
			System.out.println("Los vértices están en la misma componente conexa. Seguimos con las condiciones");

		} else {
			System.out.println("Los vértices no están en la misma componente conexa");

		}

		// si están conectados y el camino más corto del primero al último no es nulo
		if (res1 && (sp.getPath(Monumento.create(ls.get(0)), Monumento.create(ls.get(ls.size() - 1))) != null)) {
			res2 = true;
			System.out.println("Sí se puede hacer una visita de " + ls.get(0) + " A "
					+ (ls.get(ls.size() - 1) + " .Seguimos con las condiciones"));

		} else {
			System.out.println("NO se puede hacer una visita de " + ls.get(0) + " A " + (ls.get(ls.size() - 1)));

		}

		if (res2) {
			// camino más corto: Dijkstra
			Monumento from = Monumento.create(ls.get(0));
			Monumento intermediate = Monumento.create(ls.get(1));
			Monumento to = Monumento.create(ls.get(ls.size() - 1));

			ShortestPathAlgorithm<Monumento, Desplazamiento> semiPath1 = new DijkstraShortestPath<>(grafoConPeso);
			GraphPath<Monumento, Desplazamiento> gp1 = semiPath1.getPath(from, intermediate);

			ShortestPathAlgorithm<Monumento, Desplazamiento> semiPath2 = new DijkstraShortestPath<>(grafoConPeso);
			GraphPath<Monumento, Desplazamiento> gp2 = semiPath2.getPath(intermediate, to);

			List<Monumento> verticesFinal = gp1.getVertexList();
			verticesFinal.addAll(gp2.getVertexList());
			System.out.println("Camino más corto: " + verticesFinal);

			Double pesoFinal = gp1.getWeight() + gp2.getWeight();
			System.out.println("Tiempo total: " + pesoFinal);

		} else {

			System.out.println(
					"No se ha podido calcular el camino más corto porque ha fallado alguna de las condiciones");
		}

	}

}
