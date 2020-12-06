package fp.aeropuertos;

import java.time.LocalDateTime;

public class VueloCompanyiaImpl extends VueloImpl implements VueloCompanyia {
	private Persona capitan;
	private String nombreCompania;

	public VueloCompanyiaImpl(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, Integer numeroPlazas, Integer numeroPasajeros, Persona capitan,
			String nombreCompania) {
		super(codigo, origen, destino, fechaSalida, fechaLlegada, numeroPlazas, numeroPasajeros);
		this.capitan = capitan;
		this.nombreCompania = nombreCompania;
	}

	public Persona getCapitan() {
		return capitan;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setCapitan(Persona capitan) {
		this.capitan = capitan;
	}

	public String toString() {
		return super.toString() + " - compañía: " + this.getNombreCompania();
	}

}
