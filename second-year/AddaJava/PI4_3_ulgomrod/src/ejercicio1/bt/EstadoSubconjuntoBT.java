package ejercicio1.bt;

import java.util.ArrayList;
import java.util.List;

import us.lsi.bt.EstadoBT;

public class EstadoSubconjuntoBT implements EstadoBT<SolucionSubconjuntoBT, Integer, EstadoSubconjuntoBT> {

	private static List<Integer> listaOriginal;
	private List<Integer> subList1;
	private List<Integer> subList2;
	private Integer sumaSubList1;
	private Integer sumaSubList2;
	private Integer pos;

	public EstadoSubconjuntoBT(List<Integer> listaOriginal, List<Integer> subList1, List<Integer> subList2,
			Integer pos) {
		EstadoSubconjuntoBT.listaOriginal = listaOriginal;
		this.subList1 = subList1;
		this.subList2 = subList2;
		this.sumaSubList1 = suma(subList1);
		this.sumaSubList2 = suma(subList2);
		this.pos = pos;
	}

	private Integer suma(List<Integer> ls) {
		return ls.stream().mapToInt(x -> x).sum();
	}

	public Tipo getTipo() {
		return EstadoBT.Tipo.Min;
	}

	public EstadoSubconjuntoBT getEstadoInicial() {
		return new EstadoSubconjuntoBT(listaOriginal, new ArrayList<>(), new ArrayList<>(), 0);
	}

	public EstadoSubconjuntoBT avanza(Integer a) {

		List<Integer> temp1 = new ArrayList<>(subList1);
		List<Integer> temp2 = new ArrayList<>(subList2);

		if (a == 0) {
			temp1.add(listaOriginal.get(pos));

		} else if (a == 1) {

			temp2.add(listaOriginal.get(pos));

		}

		// OJO: no poner pos++
		EstadoSubconjuntoBT res = new EstadoSubconjuntoBT(listaOriginal, temp1, temp2, pos + 1);

		return res;

	}

	public EstadoSubconjuntoBT retrocede(Integer a) {
		List<Integer> temp1 = new ArrayList<>(subList1);
		List<Integer> temp2 = new ArrayList<>(subList2);

		pos = pos - 1;
		if (a == 0) {
			temp1.remove(listaOriginal.get(pos));

		} else if (a == 1) {

			temp2.remove(listaOriginal.get(pos));

		}

		EstadoSubconjuntoBT res = new EstadoSubconjuntoBT(listaOriginal, temp1, temp2, pos);

		return res;

	}

	public int size() {
		return listaOriginal.size() - pos;
	}

	public boolean esCasoBase() {
		return pos == listaOriginal.size();

	}

	public List<Integer> getAlternativas() {
		return List.of(0, 1);
	}

	public SolucionSubconjuntoBT getSolucion() {
		if (suma(subList1) == suma(subList2)) {
			return SolucionSubconjuntoBT.create(subList1, subList2);
		} else {
			return null;
		}

	}

	public Double getObjetivo() {
		return (double) Math.min(subList1.size(), subList2.size());
	}

}
