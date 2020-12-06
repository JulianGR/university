package fp.accidentes.test;

import fp.accidentes.EstadisticasAccidentes;
import fp.accidentes.EstadisticasAccidentesImpl;

public class TestEstadisticasAccidentes {

	public static void main(String[] args) {
		EstadisticasAccidentes estadisticas = new EstadisticasAccidentesImpl("resources/accidentes.txt");		
		mostrarEstadisticas(estadisticas);
	}
	
	public static void mostrarEstadisticas (EstadisticasAccidentes estadisticas){
		System.out.println("N�mero total de accidentes: "+estadisticas.getNumeroTotalAccidentes());
		System.out.println("N�mero total de accidentes con siniestro total: "+estadisticas.getNumeroAccidentesSiniestroTotal());
		System.out.println("N�mero total de supervivientes: "+estadisticas.getNumeroTotalSupervivientes());
		System.out.println("Peor accidente registrado por n�mero de muertos: "+estadisticas.getPeorAccidente());
		System.out.println("Accidentes ocurridos en Espa�a: "+estadisticas.selecciona("Spain"));
		System.out.println("N� de accidentes ocurridos en Espa�a: "+estadisticas.cuentaAccidentes("Spain"));
		System.out.println("N� de v�ctimas totales de accidentes ocurridos en Espa�a: "+estadisticas.getTotalFallecidos("Spain"));
		System.out.println("Or�genes de los vuelos siniestro total: "+estadisticas.getOrigenesSiniestroTotal());
		System.out.println("Destinos de los vuelos siniestro total: "+estadisticas.getDestinosSiniestroTotal());
		System.out.println("N� de v�ctimas totales de accidentes ocurridos en Espa�a: "+estadisticas.getTotalFallecidos("Spain"));
		System.out.println("N� de accidentes ocurridos en Espa�a, por a�o:"+estadisticas.cuentaPorAnyo("Spain"));
		System.out.println("N� de v�ctimas totales de accidentes ocurridos en Espa�a, por a�o:"+estadisticas.getVictimasTotalesPorAnyo("Spain"));
		System.out.println("N� de accidentes por operadora:"+estadisticas.cuentaAccidentesPorOperadora());
		System.out.println("Operadora con m�s accidentes:"+estadisticas.getOperadoraMasAccidentes());
		System.out.println("N� de accidentes por modelo de avi�n:"+estadisticas.cuentaAccidentesPorModeloAvion());
		System.out.println("Modelo de avi�n con m�s accidentes:"+estadisticas.getModeloAvionMasAccidentes());		
	}
}
