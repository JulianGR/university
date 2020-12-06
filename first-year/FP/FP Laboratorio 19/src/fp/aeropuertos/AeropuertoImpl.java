package fp.aeropuertos;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.utiles.Checkers;

public class AeropuertoImpl implements Aeropuerto {
	private static final String R_CIUDAD = "LA CIUDAD DE ORIGEN O DESTINO DEBE SER ";

	private String nombre, ciudad;
	private SortedSet<Vuelo> vuelos;

	public AeropuertoImpl(){
		
	}
	
	/**
	 * @param nombre
	 *            Nombre del aeropuerto.
	 * @param ciudad
	 *            Ciudad en la que está situado el aeropuerto.
	 * @param vuelos
	 *            Colección de vuelos.
	 * @throws IllegalArgumentException
	 *             si alguno de los parámetros es nulo.
	 * @throws IllegalArgumentException
	 *             si alguna ciudad de la colección de vuelos no tiene como
	 *             origen o como destino la ciudad en la que está situado el
	 *             aeropuerto.
	 */

	public AeropuertoImpl(String nombre, String ciudad, Collection<Vuelo> vuelos) {
		Checkers.checkNoNull(nombre, ciudad, vuelos);
		Checkers.check(R_CIUDAD + ciudad, restriccionCiudad(ciudad, vuelos));
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.vuelos = new TreeSet<Vuelo>(vuelos);
	}

	/**
	 * Crea un aeropuerto sin vuelos
	 * 
	 * @param nombre
	 *            Nombre del aeropuerto.
	 * @param ciudad
	 *            Ciudad en la que está situado el aeropuerto.
	 */
	public AeropuertoImpl(String nombre, String ciudad) {
		this(nombre, ciudad, new TreeSet<Vuelo>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#getNombre()
	 */
	public String getNombre() {
		return this.nombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#getCiudad()
	 */
	public String getCiudad() {
		return this.ciudad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#getVuelos()
	 */
	public SortedSet<Vuelo> getVuelos() {
		return new TreeSet<>(this.vuelos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#nuevoVuelo(fp.aeropuertos.Vuelo)
	 */
	public void nuevoVuelo(Vuelo vuelo) { // si ya lo contiene no hace nada, no
											// se lanza excepción
		// Checkers.checkNoNull(vuelo);
		if (vuelo != null) {
			Checkers.check(R_CIUDAD + getCiudad(), restriccionCiudad(getCiudad(), vuelo));
			this.vuelos.add(vuelo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#eliminaVuelo(fp.aeropuertos.Vuelo)
	 */

	public void eliminaVuelo(Vuelo vuelo) {// si no lo contiene no hace nada, no
											// se lanza excepción
		// Checkers.checkNoNull(vuelo);
		if (vuelo != null) {
			this.vuelos.remove(vuelo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#nuevosVuelos(java.util.Collection)
	 */
	public void nuevosVuelos(Collection<Vuelo> vuelos) { // Checkers.checkNoNull(vuelos);
		if (vuelos != null) {
			for (Vuelo vuelo : vuelos)
				nuevoVuelo(vuelo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Aeropuerto#contieneVuelo(fp.aeropuertos.Vuelo)
	 */
	public Boolean contieneVuelo(Vuelo vuelo) {
		// Checkers.checkNoNull(vuelo);
		boolean res = false;
		if (vuelos != null) {
			res = this.vuelos.contains(vuelo);
		}
		return res;
	}

	private static Boolean restriccionCiudad(String ciudad, Collection<Vuelo> vuelos) {
		Boolean res = true;
		for (Vuelo vuelo : vuelos) {
			res = vuelo.getOrigen().equals(ciudad) || vuelo.getDestino().equals(ciudad);
			if (!res)
				break;
		}
		return res;
	}

	private static Boolean restriccionCiudad(String ciudad, Vuelo vuelo) {
		return vuelo.getOrigen().equals(ciudad) || vuelo.getDestino().equals(ciudad);
	}

	/**
	 * @return La respresentación como cadena del aeropuerto. La representación
	 *         como cadena de un aeropuerto es el nombre seguido de la ciudad
	 *         entre paréntesis.
	 */
	public String toString() {
		return getNombre() + " (" + getCiudad() + ")";
	}

	/**
	 * @return true si dos aeropuertos son iguales. Dos aeropuertos son iguales
	 *         si tienen el mismo nombre.
	 */
	public boolean equals(Object o) {
		boolean res = (o == this);
		if (!res && o instanceof Aeropuerto) {
			Aeropuerto a = (Aeropuerto) o;
			res = this.getNombre().equals(a.getNombre());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.getNombre().hashCode();
	}

	/**
	 * El criterio de orden de aeropuerto es por el nombre del aeropuerto.
	 */

	public int compareTo(Aeropuerto a) {
		return this.getNombre().compareTo(a.getNombre());
	}

}
