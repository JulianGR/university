package fp.aeropuertos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Representa un vuelo. Los vuelos se ordenan por su c�digo; a igualdad de
 * c�digo, por el origen; al igualdad de origen, por el destino; y, finalmente,
 * a igualdad de destino, por la fecha de salida.
 * 
 * @author reinaqu
 *
 */
public interface Vuelo extends Comparable<Vuelo> {
	/**
	 * @return El c�digo del vuelo.
	 */
	String getCodigo();

	/**
	 * @return La ciudad origen del vuelo.
	 */
	String getOrigen();

	/**
	 * @return La ciudad destino del vuelo.
	 */
	String getDestino();

	/**
	 * @return La fecha de salida del vuelo.
	 */
	LocalDateTime getFechaSalida();

	/**
	 * @return La fecha de llegada del vuelo.
	 */
	LocalDateTime getFechaLlegada();

	/**
	 * @return La duraci�n del vuelo.
	 */
	Duration getDuracion();

	/**
	 * @return El n�mero de plazas del vuelo.
	 */
	Integer getNumeroPlazas();

	/**
	 * @return El n�mero de pasajeros del vuelo.
	 */
	Integer getNumeroPasajeros();

	/**
	 * @return true si el vuelo est� completo y false en caso contrario.
	 */
	Boolean estaCompleto();

	/**
	 * @return El conjunto de pasajeros del vuelo.
	 */
	Set<Persona> getPasajeros();

	/**
	 * @param fechaSalida
	 *            La nueva fecha de salida.
	 * @throws IllegalArgumentException
	 *             si fechaSalida representa a una fecha anterior a la fecha de
	 *             llegada.
	 * @throws IllegalArgumentException
	 *             si fechaSalida es nula.
	 * 
	 */
	void setFechaSalida(LocalDateTime fechaSalida);

	/**
	 * @param fechaLlegada
	 *            La nueva fecha de llegada.
	 * @throws IllegalArgumentException
	 *             si fechaLlegada representa a una fecha posterior a la fecha
	 *             de salid.
	 * @throws IllegalArgumentException
	 *             si fechaLlegada es nula.
	 */
	void setFechaLlegada(LocalDateTime fechaLlegada);

	/**
	 * A�ade el pasajero p al vuelo, siempre que el pasajero no est� ya en el
	 * vuelo y haya plazas libres.
	 * 
	 * @param p
	 *            Pasajero a a�adir al vuelo.
	 * @throws IllegalArgumentException
	 *             si el vuelo est� completo.
	 */
	void nuevoPasajero(Persona p);

	/**
	 * @param p
	 *            Elima el pasajero p del vuelo siempre que el pasajero ya
	 *            pertenezca al vuelo.
	 */
	void eliminaPasajero(Persona p);

}