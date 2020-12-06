package fp.accidentes;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

public interface EstadisticasAccidentes {
	List<AccidenteAereo> getAccidentes();

	Integer getNumeroTotalAccidentes();

	Integer getNumeroAccidentesSiniestroTotal();

	Integer getNumeroTotalSupervivientes();

	AccidenteAereo getPeorAccidente();

	List<AccidenteAereo> selecciona(String lugar);

	Integer cuentaAccidentes(String lugar);

	Integer getTotalFallecidos(String lugar);

	SortedSet<String> getOrigenesSiniestroTotal();

	SortedSet<String> getDestinosSiniestroTotal();

	SortedMap<Integer, Long> cuentaPorAnyo(String lugar);

	SortedMap<Integer, Integer> getVictimasTotalesPorAnyo(String lugar);

	SortedMap<String, Long> cuentaAccidentesPorOperadora();

	String getOperadoraMasAccidentes();

	SortedMap<String, Long> cuentaAccidentesPorModeloAvion();

	String getModeloAvionMasAccidentes();

}