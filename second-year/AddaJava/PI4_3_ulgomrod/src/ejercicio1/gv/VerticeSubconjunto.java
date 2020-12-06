package ejercicio1.gv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.VirtualVertex;

public class VerticeSubconjunto implements VirtualVertex<VerticeSubconjunto, SimpleEdge<VerticeSubconjunto>> {

	private static List<Integer> listaOriginal;
	private List<Integer> subList1;
	private List<Integer> subList2;
	private Integer sumaSubList1;
	private Integer sumaSubList2;
	private Integer pos;

	public VerticeSubconjunto(List<Integer> subList1, List<Integer> subList2, Integer pos) {
		this.subList1 = subList1;
		this.subList2 = subList2;
		this.sumaSubList1 = suma(subList1);
		this.sumaSubList2 = suma(subList2);
		this.pos = pos;
	}

	public static VerticeSubconjunto create(List<Integer> listaOriginal) {
		VerticeSubconjunto.listaOriginal = listaOriginal;
		return new VerticeSubconjunto(new ArrayList<>(), new ArrayList<>(), 0);
	}

	public static List<Integer> getListaOriginal() {
		return listaOriginal;
	}

	public List<Integer> getSubList1() {
		return subList1;
	}

	public List<Integer> getSubList2() {
		return subList2;
	}

	public Integer getSumaSubList1() {
		return sumaSubList1;
	}

	public Integer getSumaSubList2() {
		return sumaSubList2;
	}

	public Integer getPos() {
		return pos;
	}

	private Integer suma(List<Integer> ls) {
		return ls.stream().mapToInt(x -> x).sum();

	}

//si la suma de alguna de la suma las sublistas es mayor, fuera
	public boolean isValid() {
		return (sumaSubList1 <= suma(listaOriginal) / 2) && (sumaSubList2 <= suma(listaOriginal) / 2);
	}

	public Set<VerticeSubconjunto> getNeighborListOf() {
		Set<VerticeSubconjunto> res = new HashSet<>();
		if (listaOriginal.size() == pos) {
			return res;
		}

		List<Integer> temp1 = new ArrayList<>(subList1);
		List<Integer> temp2 = new ArrayList<>(subList2);

		temp1.add(listaOriginal.get(pos));
		temp2.add(listaOriginal.get(pos));

		res.add(new VerticeSubconjunto(temp1, new ArrayList<>(subList2), pos + 1));
		res.add(new VerticeSubconjunto(new ArrayList<>(subList1), temp2, pos + 1));

		return res;
	}

	public Set<SimpleEdge<VerticeSubconjunto>> edgesOf() {
		return getNeighborListOf().stream().map(vecino -> SimpleEdge.of(this, vecino)).collect(Collectors.toSet());
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((subList1 == null) ? 0 : subList1.hashCode());
		result = prime * result + ((subList2 == null) ? 0 : subList2.hashCode());
		result = prime * result + ((sumaSubList1 == null) ? 0 : sumaSubList1.hashCode());
		result = prime * result + ((sumaSubList2 == null) ? 0 : sumaSubList2.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerticeSubconjunto other = (VerticeSubconjunto) obj;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (subList1 == null) {
			if (other.subList1 != null)
				return false;
		} else if (!subList1.equals(other.subList1))
			return false;
		if (subList2 == null) {
			if (other.subList2 != null)
				return false;
		} else if (!subList2.equals(other.subList2))
			return false;
		if (sumaSubList1 == null) {
			if (other.sumaSubList1 != null)
				return false;
		} else if (!sumaSubList1.equals(other.sumaSubList1))
			return false;
		if (sumaSubList2 == null) {
			if (other.sumaSubList2 != null)
				return false;
		} else if (!sumaSubList2.equals(other.sumaSubList2))
			return false;
		return true;
	}

	public String toString() {
		return subList1.toString() + "+" + subList2.toString();
	}

}