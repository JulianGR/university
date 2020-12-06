package ejercicios;

import java.io.PrintWriter;

import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;

import us.lsi.common.Files2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.GraphsReader;

public class Ejercicio1 {

	public static void main(String[] args) {

		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph("./ficheros/Andalucia.txt", Ciudad::create,
				Carretera::create, () -> new SimpleWeightedGraph<Ciudad, Carretera>(Ciudad::create, Carretera::create),
				Carretera::getKm);

		System.out.println(graph);
		DOTExporter<Ciudad, Carretera> de1 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				x -> x.getNombre(), x -> x.getNombre() + "--" + x.getKm());

		PrintWriter pw = Files2.getWriter("./ficheros/Andalucia1.gv");
		de1.exportGraph(graph, pw);

	}
}
