package puzzlegv;

import java.util.Set;
import java.util.function.BiFunction;

import org.jgrapht.Graphs;

import us.lsi.astar.AStarAlgorithm;
import us.lsi.astar.AStarGraph;
import us.lsi.graphs.SimpleEdge;

public class TestPuzzle {

	public static void main(String[] args) {

		VerticePuzzle.NUM_FILAS = 3;
		VerticePuzzle estadoInicial = VerticePuzzle.create(0, 1, 2, 3, 4, 5, 6, 7, 8);
		VerticePuzzle estadoFinal = VerticePuzzle.create(1, 2, 3, 4, 0, 5, 6, 7, 8);
		// TODO: EXPLICACION EN EL LABORATORIO
		System.out.println("\nConfiguracion o estado inicial:\n"+estadoInicial);
		System.out.println("\nConfiguracion o estado final:\n"+estadoFinal);
		
		AStarGraph<VerticePuzzle,SimpleEdge<VerticePuzzle>> grafo =
				GrafoPuzzle.create(estadoInicial, estadoFinal);
		BiFunction<VerticePuzzle, VerticePuzzle, Double> heuristica=
				(e1,e2) -> e1.getNumDiferentes(e2)-1.;
				
		AStarAlgorithm<VerticePuzzle,SimpleEdge<VerticePuzzle>> alg=
				AStarAlgorithm.of(grafo, estadoInicial, estadoFinal, heuristica);
	}

}
