package fp.accidentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fp.utiles.Ficheros;

public class EstadisticasAccidentesImpl implements EstadisticasAccidentes {
	private List<AccidenteAereo> accidentes;

	
	
	/* Como el �nico constructor que existe toma el nombre de un fichero,
	 * se supondr� en todos los m�todos que hay datos cargados.
	 * (es decir, siempre que hay un optional se puede hacer get)
	 * 
	 * Hay muchos datos que no vienen, por lo que hay propiedades que pueden ser
	 * null. En cada m�todo hay una nota al respecto, aunque quiz�s 
	 * se puede resumir en el enunciado diciendo todas las propiedades que
	 * pueden ser null, que son (pongo las que afectan a los ejercicios):
	 * - n�mero de pasajeros
	 * - n�mero de fallecidos
	 * - lugar
	 * - origen del vuelo
	 * - destino del vuelo
	 * - operadora
	 */
	
	public EstadisticasAccidentesImpl(String nombreFicheroCSV) {
		accidentes = Ficheros.leeFichero(nombreFicheroCSV, AccidenteAereoImpl::new);
	}
	
	public EstadisticasAccidentesImpl(){
		accidentes = new ArrayList<AccidenteAereo>();
	}

	public List<AccidenteAereo> getAccidentes(){
		return new ArrayList<AccidenteAereo>(accidentes);
	}
	public Integer getNumeroTotalAccidentes() {
		return accidentes.size();
	}

	public boolean equals(Object obj) {
	    boolean res = false;
	    	if (obj instanceof EstadisticasAccidentes){
	    		EstadisticasAccidentes ea = (EstadisticasAccidentes)obj;
	    		res = this.getAccidentes().equals(ea.getAccidentes());
	    	}
		return res;
	}
	
	public int hashCode() {
	
		return this.getAccidentes().hashCode();
	}
	
	public String toString() {
		return getAccidentes().toString();
	}

	public void agregaAccidentes (Collection<AccidenteAereo> col){
		accidentes.addAll(col);
	}
	/*
	 * Devuelve el n�mero total de accidentes en el que fallecieron todos los
	 * pasajeros
	 * 
	 * (NOTA: el n�mero de pasajeros y el n�mero de fallecidos pueden ser null)
	 */
	public Integer getNumeroAccidentesSiniestroTotal() {
		
		return null;
	}

	/*
	 * Devuelve el n�mero total de supervivientes en todos los accidentes
	 * 
	 * (NOTA: el n�mero de pasajeros y el n�mero de fallecidos pueden ser null)
	 */
	public Integer getNumeroTotalSupervivientes() {
		return  null;
		
	}

	/*
	 * Encuentra el peor accidente (por n�mero de fallecidos). Si 
	 * no se encuentra ninguno se eleva NoSuchElementException.
	 * 
	 * (NOTA: el n�mero de fallecidos puede ser null)
	 */
	public AccidenteAereo getPeorAccidente() {
		//TODO Tarea 12-03-2018
		return null;
	}

	/*
	 * Devuelve una lista con todos los accidentes ocurridos en un lugar
	 * determinado. Tenga en cuenta que la propiedad lugar contiene informaci�n
	 * con distinto grado de detalle, por ejemplo el lugar puede ser
	 * "Off Sabine Pass, Texas", y habr�a que seleccionar ese accidente si el
	 * par�metro lugar es "Texas".
	 * 
	 * (NOTA: la propiedad lugar puede ser null)
	 */
	public List<AccidenteAereo> selecciona(String lugar) {
		return null;
	}

	/*
	 * Devuelve el n�mero total de accidentes ocurridos en un lugar determinado.
	 * Tenga en cuenta que la propiedad lugar contiene informaci�n con distinto
	 * grado de detalle, por ejemplo el lugar puede ser
	 * "Off Sabine Pass, Texas", y habr�a que seleccionar ese accidente si el
	 * par�metro lugar es "Texas".
	 * 
	 * (NOTA: la propiedad lugar puede ser null)
	 */
	public Integer cuentaAccidentes(String lugar) {
		return null;
	}

	/*
	 * Devuelve el n�mero total de fallecidos en los accidentes ocurridos en un
	 * lugar determinado. Tenga en cuenta que la propiedad lugar contiene
	 * informaci�n con distinto grado de detalle, por ejemplo el lugar puede ser
	 * "Off Sabine Pass, Texas", y habr�a que seleccionar ese accidente si el
	 * par�metro lugar es "Texas".
	 * 
	 * (NOTA: la propiedad lugar y la propiedad n�mero de fallecidos pueden ser null)
	 */
	public Integer getTotalFallecidos(String lugar) {
		return null;
	}

	/*
	 * Devuelve un conjunto ordenado con los or�genes de los vuelos accidentados
	 * en los que no hubo supervivientes.
	 * 
	 * (NOTA: las propiedades n�mero de pasajeros, n�mero de fallecidos y origen del vuelo pueden ser null)
	 */
	public SortedSet<String> getOrigenesSiniestroTotal() {
				
		return null;
	}

	/*
	 * Devuelve un conjunto ordenado con los destinos de los vuelos accidentados
	 * en los que no hubo supervivientes.
	 * 
	 * (NOTA: las propiedades n�mero de pasajeros, n�mero de fallecidos y destino del vuelo pueden ser null)
	 */
	public SortedSet<String> getDestinosSiniestroTotal() {

		return null;
	}

	/*
	 * Devuelve un SortedMap que asocia los a�os registrados en los datos con el n�mero total
	 * de accidentes ocurridos en esos a�os, s�lo para los accidentes ocurridos en el lugar 
	 * especificado.
	 * Tenga en cuenta que la propiedad lugar contiene
	 * informaci�n con distinto grado de detalle, por ejemplo el lugar puede ser
	 * "Off Sabine Pass, Texas", y habr�a que seleccionar ese accidente si el
	 * par�metro lugar es "Texas".
	 * 
	 * (NOTA: la propiedad lugar puede ser null)
	 */
	public SortedMap<Integer, Long> cuentaPorAnyo(String lugar) {
		

		return null;
	}

	/*
	 * Devuelve un SortedMap que asocia los a�os registrados en los datos con el n�mero total
	 * de v�ctimas en esos a�os, s�lo para los accidentes ocurridos en el lugar 
	 * especificado.
	 * Tenga en cuenta que la propiedad lugar contiene
	 * informaci�n con distinto grado de detalle, por ejemplo el lugar puede ser
	 * "Off Sabine Pass, Texas", y habr�a que seleccionar ese accidente si el
	 * par�metro lugar es "Texas".
	 * 
	 * (NOTA: las propiedades lugar y n�mero de a�os pueden ser null)
	 */
	public SortedMap<Integer, Integer> getVictimasTotalesPorAnyo(String lugar) {
	
		return null;
	}


	/*
	 * Devuelve un SortedMap que asocia el nombre de las operadoras con el n�mero
	 * de accidentes ocurridos en sus vuelos.
	 * 
	 * 
	 * (NOTA: la propiedad operadora puede ser null)
	 */
	public SortedMap<String, Long> cuentaAccidentesPorOperadora() {
		return null;
	}

	/*
	 * Devuelve la operadora que registr� m�s accidentes. 
	 * 
	 */
	public String getOperadoraMasAccidentes() {
		return null;
	}

	/*
	 * RETO:
	 * Devuelve un SortedMap que asocia el nombre del modelo del avi�n con el n�mero
	 * de accidentes ocurridos en sus vuelos.
	 * 
	 * 
	 * (NOTA: la propiedad operadora puede ser null)
	 */
	public SortedMap<String, Long> cuentaAccidentesPorModeloAvion() {
		return null;
	}

	/*
	 * Devuelve el modelo de avi�n con m�s accidentes.
	 * 
	 */
	public String getModeloAvionMasAccidentes() {
		return null;
	}
}
