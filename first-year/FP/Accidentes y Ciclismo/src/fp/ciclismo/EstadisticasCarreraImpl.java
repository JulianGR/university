package fp.ciclismo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class EstadisticasCarreraImpl implements EstadisticasCarrera {

	private String nombreCarrera;
	private List<Ganador> ganadores;

	/**
	 * @param nombre
	 *            Nombre de la carrera
	 */

	public EstadisticasCarreraImpl() {

	}

	public EstadisticasCarreraImpl(String nombre) {
		this.nombreCarrera = nombre;
		this.ganadores = new ArrayList<Ganador>();
	}

	public EstadisticasCarreraImpl(String nombre, Collection<Ganador> ganadores) {
		this(nombre);
		this.ganadores = new ArrayList<Ganador>(ganadores);
	}

	@Override
	public String getNombreCarrera() {
		return this.nombreCarrera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#getGanadores()
	 */
	public List<Ganador> getGanadores() {
		return new ArrayList<Ganador>(ganadores);
	}

	public boolean equals(Object obj) {
		boolean res = false;
		if (obj != null && obj instanceof EstadisticasCarrera) {
			EstadisticasCarrera est = (EstadisticasCarrera) obj;
			res = getNombreCarrera().equals(est.getNombreCarrera()) && this.ganadores.equals(est.getGanadores());
		}
		return res;
	}

	public int hashCode() {
		return getNombreCarrera().hashCode() + 31 * this.ganadores.hashCode();
	}

	public String toString() {
		return getNombreCarrera() + " - " + this.ganadores;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fp.ciclismo.EstadisticasTour#getGanadoresConRecorridoInferiorA(java.lang.
	 * Integer)
	 */
	public List<String> getGanadoresConRecorridoInferiorA(Integer km) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#getNumeroGanadores()
	 */

	public Long getNumeroGanadores() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#hanGanadoTodosAlgunaEtapa()
	 */
	public Boolean hanGanadoTodosAlgunaEtapa() {
		// TODO Tarea 12-03-2018
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fp.ciclismo.EstadisticasCarrera#hayAlgunGanadorNacionalidad(java.lang.String)
	 */
	public Boolean hayAlgunGanadorNacionalidad(String nacionalidad) {
		// TODO Tarea 12-03-2018
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#getEquiposGanadores()
	 */
	public Set<String> getEquiposGanadores() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#buscarGanador(java.lang.String)
	 */
	@Override
	public Ganador buscaGanador(String nombre) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#buscaGanador(java.lang.Integer)
	 */
	@Override
	public Ganador buscaGanador(Integer anyo) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#calcularDistanciaTotal()
	 */
	@Override
	public Integer calculaDistanciaTotal() {
		return null;
	}

	@Override
	public Double getMediaEtapasGanadas(String equipo) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#getDistanciaMenor()
	 */
	public Integer getKmMenorRecorrido() {
		// Versión 1: con min y Comparator
		return null;
	}

	public Integer getKmMenorRecorrido_sol2() {
		// Versión 2: con IntStream
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasTour#getGanadorMasRapido()
	 */
	public String getGanadorMasRapido() {
		// TODO Tarea 12-03-2018
		return null;
	}

	public Map<String, List<Ganador>> getGanadoresPorNacionalidad() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasCarrera#cuentaGanadoresPorNacionalidad()
	 */
	public Map<String, Long> cuentaGanadoresPorNacionalidad() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.ciclismo.EstadisticasCarrera#getTotalEtapasGanadasPorEquipo()
	 */
	public Map<String, Integer> getTotalEtapasGanadasPorEquipo() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fp.ciclismo.EstadisticasCarrera#guardaGanadoresNacionalidadConAnyos(java.lang
	 * .String, java.lang.String)
	 */
	public void guardaGanadoresNacionalidadConAnyos(String nombreFichero, String nacionalidad) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fp.ciclismo.EstadisticasCarrera#guardaGanadoresDeAnyosOrdenados(java.lang.
	 * String, java.lang.Integer, java.lang.Integer)
	 */
	public void guardaGanadoresDeAnyosOrdenados(String nombreFichero, Integer anyoInicial, Integer anyoFinal) {

	}

}
