package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;
import us.lsi.pd.ProblemaPDR;

// ProblemaPDR<S, A, P>
// S es la solución que quiero, en este caso List<Integer>.
// A es las alternativas que hay, en este caso Integer
// P es el nombre de mi clase
public class ProblemaSaltos implements ProblemaPDR<List<Integer>, Integer, ProblemaSaltos> {
	private List<Integer> nums;
	private int pos;

	public ProblemaSaltos(List<Integer> nums) {
		pos = 0;
		this.nums = nums;
	}

	// el getTipo es si para indicarle si es máximo o mínimo, en este caso mínimo
	public Tipo getTipo() {
		return AlgoritmoPD.Tipo.Min;
	}

	// size() es si el tamaño del problema se reduce o aumenta. En este caso n-i (n-
	// posición actual)
	public int size() {
		return nums.size() - pos;
	}

	// esCasoBase() para cuando llegue al caso base
	public boolean esCasoBase() {
		return pos == nums.size() - 1;
	}

	// Sp<A> es la solución parcial es la solución del caso base. en este caso será
	// cuando tenga que dar 0 saltos para llegar a la solución (estoy ya en la
	// solución)
	public Sp<Integer> getSolucionParcialCasoBase() {
		// La alternativa es la alternativa del caso base y la propiedad es lo que se va
		// añadiendo o quitando, como un reduce. 0.0 es el elemento neutro de la suma
		return Sp.create(0, 0.0);
	}

	public ProblemaSaltos getSubProblema(Integer a) {
		ProblemaSaltos res = new ProblemaSaltos(nums);
		res.pos = pos + a;

		return res;

	}

	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> s) {

		return Sp.create(a, 1 + s.propiedad);
	}

//son las alternativas posibles. Si nos quedan 3 casillas para saltar y puedo dar 4 salto, no voy a saltar 4, así que esa alternativa no la cuento
	public List<Integer> getAlternativas() {

		// ver APUNTES *
		return IntStream.rangeClosed(1, Math.min(nums.get(pos), nums.size() - 1 - pos)).boxed()
				.collect(Collectors.toList());
	}

//cuando lleguemos al casdo base, tendremos una lista vacía. Para llenarla, tendremos que subir y hacerlo todo a la inversa llenando la lista con las soluciones
	public List<Integer> getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return new ArrayList<>();
	}
	//Le pasa la alternativa que este usando 
	public List<Integer> getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, List<Integer> s) {
		List<Integer> res = new ArrayList<>(s);
		res.add(0, sp.alternativa);
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nums == null) ? 0 : nums.hashCode());
		result = prime * result + pos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaSaltos other = (ProblemaSaltos) obj;
		if (nums == null) {
			if (other.nums != null)
				return false;
		} else if (!nums.equals(other.nums))
			return false;
		if (pos != other.pos)
			return false;
		return true;
	}

}
