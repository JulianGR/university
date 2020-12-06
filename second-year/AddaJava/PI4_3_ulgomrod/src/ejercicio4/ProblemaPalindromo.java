package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPD;

public class ProblemaPalindromo implements ProblemaPD<List<String>, Integer, ProblemaPalindromo> {

	private String cadena;

	public ProblemaPalindromo(String cadena) {
		this.cadena = cadena;
	}

	public Tipo getTipo() {
		return AlgoritmoPD.Tipo.Min;
	}

	public int size() {
		return cadena.length();
	}

	public boolean esCasoBase() {
		return (cadena.length() == 1) || esPalindromo(cadena);
	}

	public boolean esPalindromo(String s) {
		String temp = s.toLowerCase();
		return IntStream.range(0, temp.length() / 2)
				.noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
	}

	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 1.0);
	}

	public ProblemaPalindromo getSubProblema(Integer a, int np) {
		ProblemaPalindromo res = null;
		if (np == 0) {

			res = new ProblemaPalindromo(cadena.substring(0, a));

		} else if (np == 1) {
			res = new ProblemaPalindromo(cadena.substring(a, cadena.length()));

		}

		return res;
	}

	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, List<Sp<Integer>> ls) {

		return Sp.create(a, ls.get(0).propiedad + ls.get(1).propiedad);

	}

	public List<Integer> getAlternativas() {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i < cadena.length() - 1; i++) {
			res.add(i);
		}

		return res;
	}

	public int getNumeroSubProblemas(Integer a) {
		return 2;
	}

	public List<String> getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		List<String> res = new ArrayList<>();
		res.add(cadena);
		return res;
	}

	public List<String> getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, List<List<String>> ls) {
		List<String> res = new ArrayList<>();

		res.addAll(ls.get(0));
		res.addAll(ls.get(1));

		return res;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadena == null) ? 0 : cadena.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaPalindromo other = (ProblemaPalindromo) obj;
		if (cadena == null) {
			if (other.cadena != null)
				return false;
		} else if (!cadena.equals(other.cadena))
			return false;
		return true;
	}

}
