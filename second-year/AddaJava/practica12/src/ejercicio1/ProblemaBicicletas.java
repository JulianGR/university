package ejercicio1;

import us.lsi.graphs.GraphPaths;
import us.lsi.graphs.GraphView;
import us.lsi.graphs.GraphsReader;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPDR;
import us.lsi.pd.floyd.FloydPD;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import tipos.Calle;
import tipos.Estacion;

public class ProblemaBicicletas implements ProblemaPD<Estacion, AlternativaBicicleta, ProblemaBicicletas> {

	public static ProblemaBicicletas create(String fichero) {
		return new ProblemaBicicletas(fichero);
	}

	private Graph<Estacion, Calle> grafo;

	private ProblemaBicicletas(String fichero) {
		super();
		leeDatos(fichero);
	}

	public void leeDatos(String fichero) {
		Supplier<Graph<Estacion, Calle>> factoriaGrafos = () -> new SimpleDirectedWeightedGraph<Estacion, Calle>(
				Estacion::create, Calle::create);
		grafo = GraphsReader.newGraph(fichero, Estacion::create, Calle::create, factoriaGrafos, Calle::getWeight);

		for (Calle c : grafo.edgeSet()) {
			grafo.setEdgeWeight(c, c.getMinutos());
		}
	}

	public Graph<Estacion, Calle> getGrafo() {
		return grafo;
	}

	// a partir de aqui no nos lo han dado hecho

	private int i;
	private int j;
	private int k;
	// private GraphView<Estacion, Calle> vistaGrafo;

	private ProblemaBicicletas(int i, int j, int k, Graph<Estacion, Calle> vistaGrafo) {
		super();
		this.i = i;
		this.j = j;
		this.k = k;
		this.grafo = grafo;
	}

	public AlgoritmoPD.Tipo getTipo() {
		return AlgoritmoPD.Tipo.Min;
	}

	public int size() {
		return 0;
	}

	public List<AlternativaBicicleta> getAlternativas() {
		return Arrays.asList(AlternativaBicicleta.values());
	}

	public int getNumeroSubProblemas(AlternativaBicicleta a) {
		return a.equals(AlternativaBicicleta.No) ? 1 : 2;
	}

	public Sp<AlternativaBicicleta> getSolucionParcialPorAlternativa(AlternativaBicicleta a,
			List<Sp<AlternativaBicicleta>> ls) {
		Double r = null;
		Sp<AlternativaBicicleta> r0 = ls.get(0);
		switch (a) {
		case No:
			r = r0.propiedad;
			break;
		case Yes:
			Sp<AlternativaBicicleta> r1 = ls.get(1);
			r = r0.propiedad + r1.propiedad;
		}
		return Sp.create(a, r);
	}

	public boolean esCasoBase() {
		return grafo.isEdge(i, j) || k == ((GraphView<Estacion, Calle>) grafo).getNumVertices();
	}

	public Sp<AlternativaBicicleta> getSolucionParcialCasoBase() {
		Sp<AlternativaBicicleta> r = null;
		if (grafo.isEdge(i, j)) {
			Double w = grafo.getWeight(i, j);
			r = Sp.<AlternativaBicicleta>create(null, w);
		}
		return r;
	}

	public FloydPD<Estacion, Calle> getSubProblema(AlternativaBicicleta a, int np) {
		FloydPD<Estacion, Calle> r = null;
		switch (a) {
		case No:
			r = FloydPD.create(i, j, k + 1, grafo);
			break;
		case Yes:
			switch (np) {
			case 0:
				r = FloydPD.create(i, k, k + 1, grafo);
				break;
			case 1:
				r = FloydPD.create(k, j, k + 1, grafo);
				break;
			default:
				throw new IllegalArgumentException("El número del subproblema no puede ser " + np);
			}
		}
		return r;
	}

	public Estacion getSolucionReconstruidaCasoBase(Sp<AlternativaBicicleta> sp) {
		E e = grafo.getEdge(i, j);
		return GraphPaths.create(grafo.getGrafo(), grafo.getVertice(i), grafo.getVertice(j), e, grafo.getWeight(i, j));
	}

	

	public Estacion getSolucionReconstruidaCasoRecursivo(Sp<AlternativaBicicleta> sp, List<Estacion> ls) {
		GraphPath<Estacion, Calle> r = null;
		switch (sp.alternativa) {
		case No:
			r = ls.get(0);
			break;
		case Yes:
			r = GraphPaths.addGraphPath(ls.get(0), ls.get(1));
			break;
		}
		return r;
	}

}
