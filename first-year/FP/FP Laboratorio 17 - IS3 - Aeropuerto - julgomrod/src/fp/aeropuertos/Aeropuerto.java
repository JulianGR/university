package fp.aeropuertos;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Tipo que representa un aeropuerto.
 * 
 * @author reinaqu
 *
 */
public interface Aeropuerto extends Comparable<Aeropuerto> {
	/**
	 * @return El nombre del aeropuerto.
	 */
	String getNombre();

	/**
	 * @return La ciudad en la que est� situado el aeropuerto.
	 */
	String getCiudad();

	/**
	 * @return El conjunto ordenado de vuelos que gestiona el aeropuerto.
	 */
	SortedSet<Vuelo> getVuelos();

	/**
	 * A�ade vuelo al conjunto de vuelos gestionados por el aeropuerto, siempre
	 * que no sea nulo.
	 * 
	 * @param vuelo
	 *            Vuelo a gestionar por el aeropuerto.
	 * @throws IllegalArgumentException
	 *             si el vuelo no tiene como origen o como destino la ciudad en
	 *             la que est� situado el aeropuerto.
	 */
	void nuevoVuelo(Vuelo vuelo); // el vuelo a a�adir no puede ser nulo

	/**
	 * Elimina vuelo del conjunto de vuelos gestionados del aeropuerto, siempre
	 * que no sea nulo, y que est� gestionado por el aeropuerto.
	 * 
	 * @param vuelo
	 *            Vuelo a eliminar
	 */
	void eliminaVuelo(Vuelo vuelo); // el vuelo a eliminar no puede ser nulo

	/**
	 * A�ade los vuelos de la colecci�n siempre que la colecci�n no sea nula,
	 * los vuelos no sean nulos y la ciudad de origen o la ciudad de destino del
	 * vuelo sea la ciudad en la que est� situado el aeropuerto.
	 * 
	 * @param vuelos
	 *            Colecci�n de vuelos a gestionar por el aeropuerto.
	 * @throws IllegalArgumentException
	 *             si alguno de los vuelos de la colecci�n no tiene como origen
	 *             o como destino la ciudad en la que est� situado el
	 *             aeropuerto.
	 */
	void nuevosVuelos(Collection<Vuelo> vuelos); // supone tratamiento
													// secuencial para la
													// restricci�n

	/**
	 * @param vuelo Vuelo del que se quiere hacer la comprobaci�n.
	 * @return true si el aeropuerto gestiona el vuelo, y false en caso contrario.
	 */
	Boolean contieneVuelo(Vuelo vuelo);	
	
	/**
	 * @param fecha
	 *            Fecha de salida del los vuelos.
	 * @return Un conjunto con todos los vuelos del aeropuerto que salen en la
	 *         fecha de salida dada como par�metro.
	 */
	Set<Vuelo> seleccionaVuelosFecha(LocalDate fechaSalida);

	/**
	 * @return El vuelo con m�s pasajeros del aeropuerto.
	 * @throws NoSuchElementException
	 *             Si el aeropuerto no tiene vuelos.
	 */
	Vuelo getVueloMasPasajeros();

	/**
	 * @return El pasajero con m�s edad de todos los vuelos.
	 * @throws NoSuchElementException
	 *             Si no hay vuelos con pasajeros.
	 */
	Persona getPasajeroMayor();
	/**
	 * @param destino
	 *            Ciudad destino del vuelo
	 * @return Un vuelo con plazas libres a ese destino.
	 * @throws NoSuchElementException
	 *             Si no hay ning�n vuelo con plazas libres a ese destino.
	 */
	Vuelo getVueloPlazasLibresDestino(String destino);

	/**
	 * @param destino
	 *            Ciudad destino del vuelo.
	 * @return El n�mero total de pasajeros que han volado a la ciudad destino
	 *         dada como par�metro.
	 */
	Integer calculaTotalPasajerosDestino(String destino);

	/**
	 * @return El n�mero medio de pasajeros que recibe cada d�a el aeropuerto. 
	 * 		 Los vuelos cuyo destino
	 *         es la ciudad en la que est� situada el aeropuerto.
	 */
	//Hay que contar el n�mero de vuelos cuyo destino sea el aeropuerto y agruparlos por dia
	//Quiz�s esta operaci�n se complique demasiado. Si es as�, se elimina.
	Double calculaMediaPasajerosPorDia();
	
	/**
	 * @return Una aplicaci�n que hace corresponder a cada ciudad de destino
	 *  la suma del n�mero de pasajeros de todos los vuelos del 
	 *  aeropuerto que tengan ese destino
	 */
	Map<String, Integer> getNumeroPasajerosPorDestino();
	
	
	/**
	 * @return Una aplicaci�n ordenada que hace corresponder a una
	 *         fecha una lista con todos los vuelos del aeropuerto que salen esa
	 *         fecha
	 */
	SortedMap<LocalDate, List<Vuelo>> getVuelosPorFecha();

}
