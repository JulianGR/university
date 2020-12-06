package ejercicio1;

import org.jgrapht.GraphPath;

import tipos.Calle;
import tipos.Estacion;
import us.lsi.graphs.GraphView;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.floyd.FloydPD;

public class TestBicicleta {

	public static void main(String[] args) {
		AlgoritmoPD.isRandomize = false;

		ProblemaBicicletas g = ProblemaBicicletas.create("./ficheros/mapa.txt");

		System.out.println(g.getGrafo());

		GraphView<Estacion, Calle> gv = GraphView.create(g.getGrafo());

		int origen = gv.getIndex(new Estacion("Estacion0"));
		int destino = gv.getIndex(new Estacion("Estacion0"));

		FloydPD<Estacion, Calle> p = FloydPD.create(origen, destino, gv);
		var a = AlgoritmoPD.createPD(p);

		a.ejecuta();

		GraphPath<Estacion, Calle> s = a.getSolucion();

		a.showAllGraph("ficheros/solucionMapa.gv", "SolucionMapa");

		System.out.println(s.getVertexList());

		a.getAlternativasDeSolucion(p).toDOT("ficheros/alternativasMapa.gv", "Alternativas");

	}

}
