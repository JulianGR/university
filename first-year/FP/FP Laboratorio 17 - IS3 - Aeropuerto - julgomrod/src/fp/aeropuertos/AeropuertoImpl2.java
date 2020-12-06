package fp.aeropuertos;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AeropuertoImpl2 extends AeropuertoImpl implements Aeropuerto {

	/**
	 * @param nombre
	 *            Nombre del aeropuerto.
	 * @param ciudad
	 *            Ciudad en la que está situado el aeropuerto.
	 * @param getVuelos()
	 *            Colección de getVuelos().
	 * @throws IllegalArgumentException
	 *             si alguno de los parámetros es nulo.
	 * @throws IllegalArgumentException
	 *             si alguna ciudad de la colección de getVuelos() no tiene como
	 *             origen o como destino la ciudad en la que está situado el
	 *             aeropuerto.
	 */

	public AeropuertoImpl2(String nombre, String ciudad, Collection<Vuelo> vuelos) {
		super(nombre, ciudad, vuelos);

	}

	/**
	 * Crea un aeropuerto sin getVuelos()
	 * 
	 * @param nombre
	 *            Nombre del aeropuerto.
	 * @param ciudad
	 *            Ciudad en la que está situado el aeropuerto.
	 */
	public AeropuertoImpl2(String nombre, String ciudad) {
		super(nombre, ciudad);
	}

	public String toString() {
		return super.toString() + "(II)";
	}

	public Vuelo getVueloMasPasajeros() {
		if (getVuelos() == null || getVuelos().isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return getVuelos().stream().max(Comparator.comparing(v -> v.getNumeroPasajeros())).get();
		}
	}

	public Persona getPasajeroMayor() {
		if (getVuelos() == null || getVuelos().isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return getVuelos().stream().flatMap(v -> v.getPasajeros().stream())
					.max(Comparator.comparing(p -> p.getEdad())).get();
		}
	}

	public Map<String, Integer> getNumeroPasajerosPorDestino() {
		return getVuelos().stream().collect(
				Collectors.groupingBy(v -> v.getDestino(), Collectors.summingInt(v -> v.getNumeroPasajeros())));

	}

	public SortedMap<LocalDate, List<Vuelo>> getVuelosPorFecha() {
		return getVuelos().stream().collect(
				Collectors.groupingBy(v -> v.getFechaSalida().toLocalDate(), TreeMap::new, Collectors.toList()));
	}
}
