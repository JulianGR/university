package fp.aeropuertos.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fp.aeropuertos.Persona;
import fp.aeropuertos.PersonaImpl;
import fp.aeropuertos.VueloCompanyia;
import fp.aeropuertos.VueloCompanyiaImpl;

public class TestVuelo {
	public static void main(String[] args) {
		VueloCompanyia v = inicializaVuelo();
		System.out.println(v);
		muestraVuelo(v);

	}

	private static VueloCompanyia inicializaVuelo() {
		Persona p = new PersonaImpl("Gómez Rodríguez", "758391W", "emailDeTest@server.com", "Julián",
				LocalDate.of(1980, 4, 5));
		VueloCompanyia v = new VueloCompanyiaImpl("934638576X", "España", "Alemania",
				LocalDateTime.of(2017, 9, 15, 11, 00), LocalDateTime.of(2017, 9, 15, 15, 20), 200, 199, p, "Iberia");
		return v;
	}

	private static void muestraVuelo(VueloCompanyia v) {
		System.out.println("==========Propiedades del vuelo========== ");
		System.out.println("Código: " + v.getCodigo());
		System.out.println("Destino: " + v.getDestino());
		System.out.println("Origen: " + v.getOrigen());
		System.out.println("Nombre de la compañía: " + v.getNombreCompania());
		System.out.println("Capitán: " + v.getCapitan());
		System.out.println("Duración: " + v.getDuracion());
		System.out.println("Fecha llegada: " + v.getFechaLlegada());
		System.out.println("Fecha salida: " + v.getFechaSalida());
		System.out.println("Número de  pasajeros: " + v.getNumeroPasajeros());
		System.out.println("Número de plazas: " + v.getNumeroPlazas());
	}

}
