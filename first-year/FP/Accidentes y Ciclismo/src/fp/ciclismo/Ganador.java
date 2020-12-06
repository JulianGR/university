package fp.ciclismo;

import java.time.Duration;

public interface Ganador {
	Integer getAnyo();
	String getNacionalidad();
	String getNombre();
	String getEquipo();
	Integer getKmRecorridos();
	Duration getTiempoEmpleado();
	Integer getNumEtapasGanadas();
	Integer getNumDiasMaillot();
	Double getVelocidadMedia();
}
