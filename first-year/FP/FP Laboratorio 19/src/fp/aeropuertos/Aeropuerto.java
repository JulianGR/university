package fp.aeropuertos;

import java.util.Collection;
import java.util.SortedSet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
	 * @return La ciudad en la que está situado el aeropuerto.
	 */
	String getCiudad();

	/**
	 * @return El conjunto ordenado de vuelos que gestiona el aeropuerto.
	 */
	SortedSet<Vuelo> getVuelos();

	@JsonDeserialize(contentAs = VueloImpl.class)

	/**
	 * Añade vuelo al conjunto de vuelos gestionados por el aeropuerto, siempre que
	 * no sea nulo.
	 * 
	 * @param vuelo
	 *            Vuelo a gestionar por el aeropuerto.
	 * @throws IllegalArgumentException
	 *             si el vuelo no tiene como origen o como destino la ciudad en la
	 *             que está situado el aeropuerto.
	 */
	void nuevoVuelo(Vuelo vuelo); // el vuelo a añadir no puede ser nulo

	/**
	 * Elimina vuelo del conjunto de vuelos gestionados del aeropuerto, siempre que
	 * no sea nulo, y que esté gestionado por el aeropuerto.
	 * 
	 * @param vuelo
	 *            Vuelo a eliminar
	 */
	void eliminaVuelo(Vuelo vuelo); // el vuelo a eliminar no puede ser nulo

	/**
	 * Añade los vuelos de la colección siempre que la colección no sea nula, los
	 * vuelos no sean nulos y la ciudad de origen o la ciudad de destino del vuelo
	 * sea la ciudad en la que está situado el aeropuerto.
	 * 
	 * @param vuelos
	 *            Colección de vuelos a gestionar por el aeropuerto.
	 * @throws IllegalArgumentException
	 *             si alguno de los vuelos de la colección no tiene como origen o
	 *             como destino la ciudad en la que está situado el aeropuerto.
	 */
	void nuevosVuelos(Collection<Vuelo> vuelos); // supone tratamiento
													// secuencial para la
													// restricción

	/**
	 * @param vuelo
	 *            Vuelo del que se quiere hacer la comprobación.
	 * @return true si el aeropuerto gestiona el vuelo, y false en caso contrario.
	 */
	Boolean contieneVuelo(Vuelo vuelo);

}
