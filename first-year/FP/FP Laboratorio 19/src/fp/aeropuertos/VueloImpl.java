package fp.aeropuertos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import fp.utiles.Checkers;

/**
 * Clase que implementa el tipo vuelo.
 * 
 * @author reinaqu
 *
 */
public class VueloImpl implements Vuelo {
	private String codigo;
	private String origen;
	private String destino;
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaLlegada;
	private Integer numeroPlazas;
	private Set<Persona> pasajeros;
	
	public VueloImpl(){
		
	}
	
	public VueloImpl(String s){
		String[] trozos = s.split(",");
		Checkers.check("Formato incorrecto de la cadena de entrada", trozos.length==6);
		
		LocalDateTime fechaSalida = LocalDateTime.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		LocalDateTime fechaLlegada = LocalDateTime.parse(trozos[4].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		Checkers.check("La fecha de llegada debe ser posterior a la fecha de salida",
				VueloImpl.restriccionFechaSalidaPosteriorAFechaLlegada(fechaSalida, fechaLlegada));		
	
		this.codigo = trozos[0].trim();
		this.origen = trozos[1].trim();
		this.destino = trozos[2].trim();
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.numeroPlazas = new Integer(trozos[5].trim());
		this.pasajeros = new HashSet<>();
	}

	/**
	 * @param codigo
	 *            Código del vuelo.
	 * @param origen
	 *            Ciudad de origen del vuelo.
	 * @param destino
	 *            Destino del vuelo.
	 * @param fechaSalida
	 *            Fecha de salida del vuelo.
	 * @param fechaLlegada
	 *            Fecha de llegada del vuelo.
	 * @param numeroPlazas
	 *            Número de plazas del vuelo.
	 * @param pasajeros
	 *            Conjunto de pasajeros del vuelo.
	 * @throws IllegalArgumentException
	 *             si alguno de los argumentos es nulo.
	 * @throws IllegalArgumentException
	 *             si la fecha de llegada es anterior a la fecha de salida.
	 * @throws IllegalArgumentException
	 *             si el numero de pasajeros del conjunto pasajeros es superior
	 *             al número de plazas del vuelo.
	 */
	public VueloImpl(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, Integer numeroPlazas, Set<Persona> pasajeros) {

		Checkers.checkNoNull("Parámetros nulos", codigo, origen, destino, fechaSalida, fechaLlegada, numeroPlazas,
				pasajeros);
		Checkers.check("La fecha de llegada debe ser posterior a la fecha de salida",
				VueloImpl.restriccionFechaSalidaPosteriorAFechaLlegada(fechaSalida, fechaLlegada));
		Checkers.check("El número de pasajeros debe ser menor o igual que el número de plazas",
				VueloImpl.restriccionNumeroPasajeros(pasajeros.size(), numeroPlazas));

		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.numeroPlazas = numeroPlazas;
		this.pasajeros = new HashSet<>(pasajeros);
	}

	// ////////
	// R1: la fecha de llegada debe ser posterior a la fecha de salida.
	// R2: el número de pasajeros debe ser menor o igual que el número de
	// plazas.
	private static Boolean restriccionFechaSalidaPosteriorAFechaLlegada(LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada) {
		Boolean res;
		res = fechaSalida==null || fechaLlegada==null || fechaSalida.isBefore(fechaLlegada);
		return res;
	}

	private static Boolean restriccionNumeroPasajeros(Integer numeroPasajeros, Integer numeroPlazas) {
		Boolean res;
		res = numeroPasajeros <= numeroPlazas;
		return res;
	}

	/**
	 * Crea un vuelo con un conjunto vacío de pasajeros.
	 * 
	 * @param codigo
	 *            Código del vuelo.
	 * @param origen
	 *            Ciudad de origen del vuelo.
	 * @param destino
	 *            Destino del vuelo.
	 * @param fechaSalida
	 *            Fecha de salida del vuelo.
	 * @param fechaLlegada
	 *            Fecha de llegada del vuelo.
	 * @param numeroPlazas
	 *            Número de plazas del vuelo.
	 * @param pasajeros
	 *            Conjunto de pasajeros del vuelo.
	 * @throws IllegalArgumentException
	 *             si alguno de los argumentos es nulo.
	 * @throws IllegalArgumentException
	 *             si la fecha de llegada es anterior a la fecha de salida.
	 * @throws IllegalArgumentException
	 *             si el numero de pasajeros del conjunto pasajeros es superior
	 *             al número de plazas del vuelo.
	 */

	public VueloImpl(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, Integer numeroPlazas) {
		this(codigo, origen, destino, fechaSalida, fechaLlegada, numeroPlazas, new HashSet<>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getFechaSalida()
	 */
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#setFechaSalida(java.time.LocalDateTime)
	 */
	public void setFechaSalida(LocalDateTime fechaSalida) {
		Checkers.checkNoNull(fechaSalida);
		Checkers.check("La fecha de llegada debe ser posterior a la fecha de salida",
				VueloImpl.restriccionFechaSalidaPosteriorAFechaLlegada(fechaSalida, this.getFechaLlegada()));
		this.fechaSalida = fechaSalida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getFechaLlegada()
	 */
	public LocalDateTime getFechaLlegada() {
		return fechaLlegada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#setFechaLlegada(java.time.LocalDateTime)
	 */
	public void setFechaLlegada(LocalDateTime fechaLlegada) {
		Checkers.checkNoNull(fechaLlegada);
		Checkers.check("La fecha de llegada debe ser posterior a la fecha de salida",
				VueloImpl.restriccionFechaSalidaPosteriorAFechaLlegada(this.getFechaSalida(), fechaLlegada));

		this.fechaLlegada = fechaLlegada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getCodigo()
	 */
	public String getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getOrigen()
	 */
	public String getOrigen() {
		return origen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getDestino()
	 */
	public String getDestino() {
		return destino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getNumeroPlazas()
	 */
	public Integer getNumeroPlazas() {
		return numeroPlazas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#getDuracion()
	 */
	public Duration getDuracion() {
		return Duration.ofMinutes(getFechaSalida().until(getFechaLlegada(), ChronoUnit.MINUTES));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fp.aeropuertos.Vuelo#estaCompleto()
	 */
	public Boolean estaCompleto() {
		return getNumeroPlazas().equals(getNumeroPasajeros());
	}

	/**
	 * @return La representación como cadena del Vuelo. La representación como
	 *         cadena de un vuelo es su código entre paréntesis, seguido del
	 *         origen del vuelo, un guión, el destino del vuelo, y la fecha de
	 *         salida en formato dd/mm/yyyy hh:mm.
	 */
	public String toString() {
		return "(" + getCodigo() + ") " + getOrigen() + " - " + getDestino() + " "
				+ getFechaSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

	// //////////////////////////////////////////////////////////////
	//
	/**
	 * @return true si los dos vuelos son iguales. Dos vuelos son iguales si
	 *         tienen el mismo código, el mismo origen, el mismo destino y la
	 *         misma fecha de salida.
	 */
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Vuelo) {
			boolean aux1 = this.getCodigo().equals(((Vuelo) o).getCodigo());
			boolean aux2 = this.getOrigen().equals(((Vuelo) o).getOrigen());
			boolean aux3 = this.getDestino().equals(((Vuelo) o).getDestino());
			boolean aux4 = this.getFechaSalida().equals(((Vuelo) o).getFechaSalida());
			res = aux1 && aux2 && aux3 && aux4;
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.getCodigo().hashCode() + this.getOrigen().hashCode() * 31 + this.getDestino().hashCode() * 31 * 31
				+ this.getFechaSalida().hashCode() * 31 * 31 * 31;
	}

	/**
	 * El criterio de orden de los vuelos es por código, a igualdad de código
	 * por origen, a igualdad de origen, por destino, y a igualdad de destino
	 * por fecha de salida.
	 */
	public int compareTo(Vuelo v) {
		int res = this.getCodigo().compareTo(v.getCodigo());
		if (res == 0) {
			res = this.getOrigen().compareTo(v.getOrigen());
			if (res == 0) {
				res = this.getDestino().compareTo(v.getDestino());
				if (res == 0) {
					res = this.getFechaSalida().compareTo(v.getFechaSalida());
				}
			}
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.Vuelo#getNumeroPasajeros()
	 */
	public Integer getNumeroPasajeros() {
		return pasajeros.size();
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.Vuelo#getPasajeros()
	 */
	public Set<Persona> getPasajeros() {
		return new HashSet<>(pasajeros);
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.Vuelo#nuevoPasajero(fp.aeropuertos.Persona)
	 */
	public void nuevoPasajero(Persona p) {
		if (p!= null && !getPasajeros().contains(p)) {
			Checkers.check("No hay sitio en el vuelo",
					VueloImpl.restriccionNumeroPasajeros(pasajeros.size() + 1, numeroPlazas));
			pasajeros.add(p);
		}
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.Vuelo#eliminaPasajero(fp.aeropuertos.Persona)
	 */
	public void eliminaPasajero(Persona p) {
		pasajeros.remove(p);
	}
}
