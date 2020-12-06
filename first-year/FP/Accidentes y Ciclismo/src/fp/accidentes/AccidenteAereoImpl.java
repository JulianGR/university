package fp.accidentes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.utiles.Checkers;

public class AccidenteAereoImpl implements AccidenteAereo {
	private LocalDate fecha;
	private LocalTime hora;
	private String lugar;
	private String operadora;
	//private Integer numeroVuelo;
	private String origenVuelo;
	private String destinoVuelo;
	private String modeloAvion;
	//private String registro; Esta información la obviamos
	//private String cnln; Esta información la obviamos
	private Integer numPasajeros;
	private Integer numFallecidos;
	//private Integer alturaChoque; Esta información la obviamos
	private String resumen;
	


	public AccidenteAereoImpl(String filaCsv){
		String[] trozos = filaCsv.split("\t");
		
		this.fecha = LocalDate.parse(trozos[0].trim(),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		if(!trozos[1].trim().isEmpty()){
			this.hora = LocalTime.parse(trozos[1].trim(),DateTimeFormatter.ofPattern("H:m"));
			
		}
		
		if(!trozos[2].trim().isEmpty()){
			this.lugar = trozos[2].trim();	
		}
		
		if(!trozos[3].trim().isEmpty()){
			this.operadora = trozos[3].trim();
		}
				
		if(!trozos[5].trim().isEmpty()){
			String[] aux = trozos[5].trim().split("-");
			if(aux.length==2){
				this.origenVuelo = aux[0].trim();
				this.destinoVuelo = aux[1].trim();
			}
			
		}
		
		if(!trozos[6].trim().isEmpty()){
			this.modeloAvion = trozos[6].trim();
		}
		
		if(!trozos[9].trim().isEmpty()){
			Integer numPasajeros = new Integer(trozos[9].trim());
			Checkers.check("Numero pasajeros negativo", numPasajeros>=0);
			this.numPasajeros = numPasajeros;
		}
		
		if(!trozos[10].trim().isEmpty()){
			Integer numFallecidos = new Integer(trozos[10].trim());
			Checkers.check("Numero fallecidos negativo", numFallecidos>=0);
			this.numFallecidos = numFallecidos;
		}
		
		if(!trozos[12].trim().isEmpty()){
			this.resumen = trozos[12].trim();			
		}		
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getFecha()
	 */
	@Override
	public LocalDate getFecha() {
		return fecha;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getHora()
	 */
	@Override
	public LocalTime getHora() {
		return hora;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getLugar()
	 */
	@Override
	public String getLugar() {
		return lugar;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getOperadora()
	 */
	@Override
	public String getOperadora() {
		return operadora;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getOrigenVuelo()
	 */
	@Override
	public String getOrigenVuelo() {
		return origenVuelo;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getDestinoVuelo()
	 */
	@Override
	public String getDestinoVuelo() {
		return destinoVuelo;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getModeloAvion()
	 */
	@Override
	public String getModeloAvion() {
		return modeloAvion;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getNumPasajeros()
	 */
	@Override
	public Integer getNumPasajeros() {
		return numPasajeros;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getNumFallecidos()
	 */
	@Override
	public Integer getNumFallecidos() {
		return numFallecidos;
	}

	/* (non-Javadoc)
	 * @see fp.aeropuertos.AccidenteAereoI#getResumen()
	 */
	@Override
	public String getResumen() {
		return resumen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinoVuelo == null) ? 0 : destinoVuelo.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccidenteAereoImpl other = (AccidenteAereoImpl) obj;
		if (destinoVuelo == null) {
			if (other.destinoVuelo != null)
				return false;
		} else if (!destinoVuelo.equals(other.destinoVuelo))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccidenteAereo [fecha=" + fecha + ", hora=" + hora + ", lugar=" + lugar + ", operadora=" + operadora
				+ ", origenVuelo=" + origenVuelo + ", destinoVuelo=" + destinoVuelo + ", modeloAvion=" + modeloAvion
				+ ", numPasajeros=" + numPasajeros + ", numMuertos=" + numFallecidos + ", resumen=" + resumen + "]";
	}
	
	
}
