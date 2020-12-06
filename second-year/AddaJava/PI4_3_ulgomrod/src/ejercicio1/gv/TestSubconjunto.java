package ejercicio1.gv;

import java.util.List;
import java.util.function.Predicate;

import us.lsi.astar.AStarAlgorithm;
import us.lsi.astar.AStarGraph;
import us.lsi.astar.AStarSimpleVirtualGraph;
import us.lsi.astar.PredicateHeuristic;
import us.lsi.graphs.SimpleEdge;

public class TestSubconjunto {
	public static void main(String[] args) {
		List<Integer> listaInicial = List.of(1, 3, 1, 1, 2, 5, 8, 10, 6, 11);

		VerticeSubconjunto inicio = VerticeSubconjunto.create(listaInicial);

		Predicate<VerticeSubconjunto> obj = v -> v.getSumaSubList1() == v.getSumaSubList2()
				&& listaInicial.size() == v.getPos();

		AStarGraph<VerticeSubconjunto, SimpleEdge<VerticeSubconjunto>> grafo = AStarSimpleVirtualGraph
				.of(x -> x.getEdgeWeight());

		PredicateHeuristic<VerticeSubconjunto> heuristica = (v, p) -> Double.valueOf(v.getSubList1().size());

		var alg = AStarAlgorithm.of(grafo, inicio, obj, heuristica);

		alg.getPathVertexList().stream().forEach(v -> System.out.println(v + "\n"));
	}

}