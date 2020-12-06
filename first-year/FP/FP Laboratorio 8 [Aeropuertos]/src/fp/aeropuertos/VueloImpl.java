package fp.aeropuertos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VueloImpl {
	private String codigo;
	private String origen;
	private String destino;
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaLlegada;
	private Integer numeroPlazas;
	private Integer numeroPasajeros;

	// Constructor 1
	public VueloImpl(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, Integer numeroPlazas, Integer numeroPasajeros) {
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.numeroPlazas = numeroPlazas;
		this.numeroPasajeros = numeroPasajeros;
	}

	// Constructor 2
	public VueloImpl(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, Integer numeroPlazas) {
		this.codigo = codigo;
		numeroPasajeros = 0;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.numeroPlazas = numeroPlazas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDateTime getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public Integer getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setNumeroPlazas(Integer numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	public Integer getNumeroPasajeros() {
		return numeroPasajeros;
	}

	public void setNumeroPasajeros(Integer numeroPasajeros) {
		this.numeroPasajeros = numeroPasajeros;
	}

	public Boolean esCompleto() {
		Boolean completo = false;
		if ((getNumeroPasajeros() == getNumeroPlazas()) || (getNumeroPasajeros() >= getNumeroPlazas()))
			completo = true;
		return completo;
	}

	public String getCompleto() {
		String res = "No";
		if (esCompleto() == true)
			res = "Si";
		return res;
	}

	public Duration getDuracion() {
		Duration duracion = Duration.between(fechaSalida, fechaLlegada);
		return duracion;
	}

	public String toString() {
		return "(" + getCodigo() + ") " + getOrigen() + " - " + getDestino() + " "
				+ getFechaSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

}
