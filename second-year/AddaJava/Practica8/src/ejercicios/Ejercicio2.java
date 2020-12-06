package ejercicios;

import java.io.PrintWriter;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.alg.tour.TwoOptHeuristicTSP;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;

import us.lsi.common.Files2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio2 {
	// Esto no hace falta pero es útil para la PI
	private static Supplier<Graph<Ciudad, Carretera>> creator = () -> new SimpleWeightedGraph<Ciudad, Carretera>(
			Ciudad::create, Carretera::create);

	public static void main(String[] args) {

		SimpleWeightedGraph<Ciudad, Carretera> g = GraphsReader.newGraph("./ficheros/Andalucia.txt", Ciudad::create,
				Carretera::create, () -> new SimpleWeightedGraph<Ciudad, Carretera>(Ciudad::create, Carretera::create));

		// a) Encontrar el camino más corto entre Huelva y Almería

		g.edgeSet().forEach(a -> g.setEdgeWeight(a, a.getKm()));
		apartadoA(g, "Huelva", "Almeria");

		// b) Encontrar los caminos necesarios para conectar todas las ciudades
		// utilizando el menor número de
		// kilómetros, de forma que siempre exista un camino que conecte dos ciudades.
		apartadoB(g);
		// c) Encontrar un circuito que pase por todas las ciudades sin pasar más de dos
		// veces por una ciudad
		apartadoC(g);
		// d) Encontrar el menor número de colores necesarios para colorear cada ciudad
		// del grafo de forma que
		// dos ciudades adyacentes siempre tengan dos colores distintos.
		apartadoD(g);
		// e) Encontrar el menor número de ciudades que permitan acceder a todas las
		// carreteras de Andalucía
		apartadoE(g);
	}

	private static void apartadoA(SimpleWeightedGraph<Ciudad, Carretera> g, String origen, String destino) {
		Ciudad from = g.vertexSet().stream().filter(v -> v.getNombre().equals(origen)).findFirst().get();
		Ciudad to = g.vertexSet().stream().filter(v -> v.getNombre().equals(destino)).findFirst().get();
		ShortestPathAlgorithm<Ciudad, Carretera> alg = new DijkstraShortestPath<>(g);
		GraphPath<Ciudad, Carretera> gp = alg.getPath(from, to);
		System.out.println("Camino más corto: " + gp.getVertexList());
		System.out.println("Kms totales: " + gp.getWeight());

	}

	private static void apartadoB(SimpleWeightedGraph<Ciudad, Carretera> g) {
		SpanningTreeAlgorithm<Carretera> alg = new KruskalMinimumSpanningTree<Ciudad, Carretera>(g);
		SpanningTree<Carretera> sp = alg.getSpanningTree();
		System.out.println(sp.getEdges());
		System.out.println(sp.getWeight());

	}

	private static void apartadoC(SimpleWeightedGraph<Ciudad, Carretera> g) {
		HamiltonianCycleAlgorithm<Ciudad, Carretera> alg = new HeldKarpTSP<>();
//HamiltonianCycleAlgorithm<Ciudad, Carretera> alg = new TwoOptHeuristicTSP<>();
		GraphPath<Ciudad, Carretera> gp = alg.getTour(g);
		if (gp != null) {
			System.out.println("Ciclo: " + gp.getEdgeList());

		} else {
			System.out.println("No es posible");
		}

	}
	// OJO: QUIZÁS HAYA QUE CAMBIAR LA CABECERA Y LOS PARÁMETROS QUE SE LE PASAN

	private static void apartadoD(SimpleWeightedGraph<Ciudad, Carretera> g) {
		VertexColoringAlgorithm<Ciudad> alg = new GreedyColoring<>(g);
		VertexColoringAlgorithm.Coloring<Ciudad> colors = alg.getColoring();

		System.out.println();
	}

	private static void apartadoE(SimpleWeightedGraph<Ciudad, Carretera> g) {
//TODO
	}

	// Esto no hace falta pero es útil para la PI: para pintar la solución
	private static void generaGrafo(Graph<Ciudad, Carretera> g, Set<Carretera> edges) {
		Graph<Ciudad, Carretera> g2 = Graphs2.subGraph(g, null, a -> edges.contains(a), creator);
		exportar(g2, "./ficheros/SpanningTree.gv");
	}

	private static void exportar(Graph<Ciudad, Carretera> g2, String string) {
		DOTExporter<Ciudad, Carretera> de1 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				x -> x.getNombre(), x -> x.getNombre() + "--" + x.getKm());

		PrintWriter pw = Files2.getWriter("./ficheros/SpanningTree.gv");
		de1.exportGraph(g2, pw);
	}
}
