package Ejercicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.bt.EstadoBT;
import us.lsi.common.Tuple2;
import us.lsi.common.Tuple;

public class EstadoCaballoBT implements EstadoBT<SolucionCaballoBT, Integer, EstadoCaballoBT> {

	private Tuple2<Integer, Integer> posAct;
	private Map<Tuple2<Integer, Integer>, Integer> tablero;
	
	@Override
	public Tipo getTipo() {
		return EstadoBT.Tipo.AlgunasSoluciones;
	}
	
	public static EstadoCaballoBT create() {
		return new EstadoCaballoBT();
	}
	
	private EstadoCaballoBT() {
		posAct = Tuple.create(ProblemaCaballoBT.iniX, ProblemaCaballoBT.iniX);
		tablero = new HashMap<>();
		tablero.put(posAct, 1);
	}

	@Override
	public EstadoCaballoBT getEstadoInicial() {
		return create();
	}

	@Override
	public EstadoCaballoBT avanza(Integer a) {
		posAct = Tuple.create(posAct.v1 + ProblemaCaballoBT.movx[a], posAct.v2 + ProblemaCaballoBT.movy[a]);
		tablero.put(posAct, tablero.size() + 1);
		return this;
	}

	@Override
	public EstadoCaballoBT retrocede(Integer a) {
		tablero.remove(posAct);
		posAct = Tuple.create(posAct.v1 - ProblemaCaballoBT.movx[a], posAct.v2 - ProblemaCaballoBT.movy[a]);
		return this;
	}

	@Override
	public int size() {
		return (ProblemaCaballoBT.lado*ProblemaCaballoBT.lado) - tablero.size();
	}

	@Override
	public boolean esCasoBase() {
		return tablero.size() == ProblemaCaballoBT.lado*ProblemaCaballoBT.lado;
	}

	@Override
	public List<Integer> getAlternativas() {
		return IntStream.range(0, 8).boxed()
				.filter(a -> esValido(a) && esNuevo(a))
				.collect(Collectors.toList());
	}

	private boolean esNuevo(Integer a) {
		Tuple2<Integer, Integer> pos = Tuple.create(posAct.v1 + ProblemaCaballoBT.movx[a], posAct.v2 + ProblemaCaballoBT.movy[a]);
		return tablero.get(pos) == null;
	}

	private boolean esValido(Integer a) {
		Tuple2<Integer, Integer> pos = Tuple.create(posAct.v1 + ProblemaCaballoBT.movx[a], posAct.v2 + ProblemaCaballoBT.movy[a]);
		return pos.v1 >= 0 && pos.v1 < ProblemaCaballoBT.lado && pos.v2 >= 0 && pos.v2 < ProblemaCaballoBT.lado;
	}

	@Override
	public SolucionCaballoBT getSolucion() {
		return SolucionCaballoBT.create(tablero);
	}

}
