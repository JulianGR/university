package fp.accidentes.test;

import fp.accidentes.EstadisticasAccidentes;
import fp.accidentes.EstadisticasAccidentesImpl;

public class TestEstadisticasAccidentes {

	public static void main(String[] args) {
		EstadisticasAccidentes estadisticas = new EstadisticasAccidentesImpl("resources/accidentes.txt");		
		mostrarEstadisticas(estadisticas);
	}
	
	public static void mostrarEstadisticas (EstadisticasAccidentes estadisticas){
		System.out.println("Número total de accidentes: "+estadisticas.getNumeroTotalAccidentes());
		System.out.println("Número total de accidentes con siniestro total: "+estadisticas.getNumeroAccidentesSiniestroTotal());
		System.out.println("Número total de supervivientes: "+estadisticas.getNumeroTotalSupervivientes());
		System.out.println("Peor accidente registrado por número de muertos: "+estadisticas.getPeorAccidente());
		System.out.println("Accidentes ocurridos en España: "+estadisticas.selecciona("Spain"));
		System.out.println("Nº de accidentes ocurridos en España: "+estadisticas.cuentaAccidentes("Spain"));
		System.out.println("Nº de víctimas totales de accidentes ocurridos en España: "+estadisticas.getTotalFallecidos("Spain"));
		System.out.println("Orígenes de los vuelos siniestro total: "+estadisticas.getOrigenesSiniestroTotal());
		System.out.println("Destinos de los vuelos siniestro total: "+estadisticas.getDestinosSiniestroTotal());
		System.out.println("Nº de víctimas totales de accidentes ocurridos en España: "+estadisticas.getTotalFallecidos("Spain"));
		System.out.println("Nº de accidentes ocurridos en España, por año:"+estadisticas.cuentaPorAnyo("Spain"));
		System.out.println("Nº de víctimas totales de accidentes ocurridos en España, por año:"+estadisticas.getVictimasTotalesPorAnyo("Spain"));
		System.out.println("Nº de accidentes por operadora:"+estadisticas.cuentaAccidentesPorOperadora());
		System.out.println("Operadora con más accidentes:"+estadisticas.getOperadoraMasAccidentes());
		System.out.println("Nº de accidentes por modelo de avión:"+estadisticas.cuentaAccidentesPorModeloAvion());
		System.out.println("Modelo de avión con más accidentes:"+estadisticas.getModeloAvionMasAccidentes());		
	}
}
