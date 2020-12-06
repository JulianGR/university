package ejercicio1.bt;

import java.util.ArrayList;
import java.util.List;

public class SolucionSubconjuntoBT {

	private List<Integer> subList1;
	private List<Integer> subList2;

	private SolucionSubconjuntoBT(List<Integer> ls1, List<Integer> ls2) {
		this.subList1 = new ArrayList<>(ls1);
		this.subList2 = new ArrayList<>(ls2);

	}

	public static SolucionSubconjuntoBT create(List<Integer> ls1, List<Integer> ls2) {
		return new SolucionSubconjuntoBT(ls1, ls2);

	}

	public String toString() {
		return subList1.toString() + " + " + subList2.toString();
	}

}
