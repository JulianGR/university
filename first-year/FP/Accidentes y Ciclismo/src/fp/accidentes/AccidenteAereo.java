package fp.accidentes;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AccidenteAereo {

	LocalDate getFecha();
	LocalTime getHora();
	String getLugar();
	String getOperadora();
	String getOrigenVuelo();
	String getDestinoVuelo();
	String getModeloAvion();
	Integer getNumPasajeros();
	Integer getNumFallecidos();
	String getResumen();

}