package ejercicio1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

public class ProblemaReyPDR implements ProblemaPDR<List<Tuple2<Integer, Integer>>, Movimiento, ProblemaReyPDR> {

//pensar en como sacar las propiedades individuales con el hashCode ¿cuándo son 2 problemas iguales?
	// lo que pinto es mi propiedad individual

	private static Integer lado;
	private List<Tuple2<Integer, Integer>> tablero;

	private ProblemaReyPDR(List<Tuple2<Integer, Integer>> nuevoTab) {
		tablero = nuevoTab;
	}

	public static ProblemaReyPDR of(Tuple2<Integer, Integer> pos) {
		return new ProblemaReyPDR(pos);

	}

	public static void setLado(Integer lado) {
		ProblemaReyPDR.lado = lado;
	}

	public Tipo getTipo() {
		return AlgoritmoPD.Tipo.Min;
	}

	public int size() {
		return lado * lado - tablero.size();
	}

	public boolean esCasoBase() {
		return (tablero.size() == lado * lado);
	}

	public Sp<ejercicio1.Movimiento> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.0);
	}

	public ProblemaReyPDR getSubProblema(ejercicio1.Movimiento a) {
		List<Tuple2<Integer, Integer>> nuevoTab = new ArrayList<>(tablero);
		Tuple2<Integer, Integer> ult = nuevoTab.get(nuevoTab.size() - 1);
		Tuple2<Integer, Integer> siguiente = null;
		switch (a) {
		case ARR:
			siguiente = Tuple.create(ult.v1 - 1, ult.v2);
			break;
		case ABJ:
			siguiente = Tuple.create(ult.v1 + 1, ult.v2);
			break;
		case DCH:
			siguiente = Tuple.create(ult.v1, ult.v2 + 1);
			break;
		case IZQ:
			siguiente = Tuple.create(ult.v1, ult.v2 - 1);
			break;

		}
		nuevoTab.add(siguiente);

		return new ProblemaReyPDR(nuevoTab);
	}

	public Sp<ejercicio1.Movimiento> getSolucionParcialPorAlternativa(ejercicio1.Movimiento a,
			Sp<ejercicio1.Movimiento> s) {
		int x = a.equals(Movimiento.ARR) ? 1 : 0;
		return Sp.create(a, s.propiedad + x);

	}

	public List<ejercicio1.Movimiento> getAlternativas() {
		List<Movimiento> movs = new ArrayList<>();
		Tuple2<Integer, Integer> pos = tablero.get(tablero.size() - 1);
		evalua(pos, Movimiento.ABJ, movs);
		evalua(pos, Movimiento.ARR, movs);
		evalua(pos, Movimiento.IZQ, movs);
		evalua(pos, Movimiento.DCH, movs);

		return movs;
	}

	private void evalua(Tuple2<Integer, Integer> pos, Movimiento mov, List<Movimiento> movs) {

		int x = pos.v1;
		int y = pos.v2;

		switch (mov) {
		case ARR:
			x--;

			break;
		case ABJ:
			x++;
			break;
		case DCH:
			y--;
			break;
		case IZQ:
			y++;
			break;
		}

		if (x >= 0 && x < lado && y >= 0 && y < lado && !existe(x, y)) {
			movs.add(mov);

		}

	}

	private boolean existe(int x, int y) {
		return tablero.stream().anyMatch(t -> t.v1 == x && t.v2 == y);
	}

	public List<Tuple2<Integer, Integer>> getSolucionReconstruidaCasoBase(Sp<ejercicio1.Movimiento> sp) {
		return tablero;
	}

	public List<Tuple2<Integer, Integer>> getSolucionReconstruidaCasoRecursivo(Sp<ejercicio1.Movimiento> sp,
			List<Tuple2<Integer, Integer>> s) {
		return s;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tablero == null) ? 0 : tablero.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaReyPDR other = (ProblemaReyPDR) obj;
		if (tablero == null) {
			if (other.tablero != null)
				return false;
		} else if (!tablero.equals(other.tablero))
			return false;
		return true;
	}

}
