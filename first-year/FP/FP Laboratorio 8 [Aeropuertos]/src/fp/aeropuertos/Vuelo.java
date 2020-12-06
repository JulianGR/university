package fp.aeropuertos;

import java.time.Duration;
import java.time.LocalDateTime;

public interface Vuelo {
	String getCodigo();

	String getOrigen();

	String getDestino();

	LocalDateTime getFechaSalida();

	void setFechaSalida(LocalDateTime fechaSalida);

	LocalDateTime getFechaLlegada();

	void setFechaLlegada(LocalDateTime fechaLlegada);

	Duration getDuracion();

	Integer getNumeroPlazas();

	Integer getNumeroPasajeros();

	void setNumeroPasajeros(Integer numeroPasajeros);

	Boolean esCompleto();

}
