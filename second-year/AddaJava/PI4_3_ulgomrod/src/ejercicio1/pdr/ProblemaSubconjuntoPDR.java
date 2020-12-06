package ejercicio1.pdr;

import java.util.ArrayList;
import java.util.List;

import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

public class ProblemaSubconjuntoPDR implements ProblemaPDR<List<Integer>, Integer, ProblemaSubconjuntoPDR> {
	public static List<Integer> listaOriginal;
	private List<Integer> subList1;
	private List<Integer> subList2;
	private Integer sumaSubList1;
	private Integer sumaSubList2;
	private Integer pos;

	public ProblemaSubconjuntoPDR(List<Integer> subList1, List<Integer> subList2, Integer pos) {
		this.subList1 = new ArrayList<>(subList1);
		this.subList2 = new ArrayList<>(subList2);
		this.sumaSubList1 = suma(subList1);
		this.sumaSubList2 = suma(subList2);
		this.pos = pos;
	}

	public static ProblemaSubconjuntoPDR create(List<Integer> listaOriginal) {
		ProblemaSubconjuntoPDR.listaOriginal = listaOriginal;
		return new ProblemaSubconjuntoPDR(new ArrayList<>(), new ArrayList<>(), 0);
	}

	private Integer suma(List<Integer> ls) {
		return ls.stream().mapToInt(x -> x).sum();

	}

	public Tipo getTipo() {
		return AlgoritmoPD.Tipo.Min;
	}

	public int size() {
		return listaOriginal.size() - pos;
	}

	public boolean esCasoBase() {
		return pos.equals(listaOriginal.size());
	}

	public Sp<Integer> getSolucionParcialCasoBase() {
		Sp<Integer> res = null;
		if (sumaSubList1 == sumaSubList2) {
			res = Sp.create(null, 0.0);
		}
		return res;
	}

	public ProblemaSubconjuntoPDR getSubProblema(Integer a) {
		List<Integer> temp1 = new ArrayList<>(subList1);
		List<Integer> temp2 = new ArrayList<>(subList2);
		if (a == 0) {
			temp1.add(listaOriginal.get(pos));

		} else if (a == 1) {

			temp2.add(listaOriginal.get(pos));

		}

		ProblemaSubconjuntoPDR res = new ProblemaSubconjuntoPDR(temp1, temp2, pos + 1);

		return res;
	}

	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> s) {
		Sp<Integer> res = null;
		if (a == 0) {
			return res = Sp.create(a, s.propiedad + 1);

		} else if (a == 1) {
			return res = Sp.create(a, s.propiedad);

		}

		return res;
	}

	public List<Integer> getAlternativas() {
		List<Integer> temp = List.of(0, 1);
		return temp;
	}

	public List<Integer> getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return this.subList1;
	}

	public List<Integer> getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, List<Integer> s) {
		return s;
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
		ProblemaSubconjuntoPDR other = (ProblemaSubconjuntoPDR) obj;
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

}
