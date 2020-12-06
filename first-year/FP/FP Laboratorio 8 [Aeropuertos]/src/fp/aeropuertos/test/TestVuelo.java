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
		Persona p = new PersonaImpl("G�mez Rodr�guez", "758391W", "emailDeTest@server.com", "Juli�n",
				LocalDate.of(1980, 4, 5));
		VueloCompanyia v = new VueloCompanyiaImpl("934638576X", "Espa�a", "Alemania",
				LocalDateTime.of(2017, 9, 15, 11, 00), LocalDateTime.of(2017, 9, 15, 15, 20), 200, 199, p, "Iberia");
		return v;
	}

	private static void muestraVuelo(VueloCompanyia v) {
		System.out.println("==========Propiedades del vuelo========== ");
		System.out.println("C�digo: " + v.getCodigo());
		System.out.println("Destino: " + v.getDestino());
		System.out.println("Origen: " + v.getOrigen());
		System.out.println("Nombre de la compa��a: " + v.getNombreCompania());
		System.out.println("Capit�n: " + v.getCapitan());
		System.out.println("Duraci�n: " + v.getDuracion());
		System.out.println("Fecha llegada: " + v.getFechaLlegada());
		System.out.println("Fecha salida: " + v.getFechaSalida());
		System.out.println("N�mero de  pasajeros: " + v.getNumeroPasajeros());
		System.out.println("N�mero de plazas: " + v.getNumeroPlazas());
	}

}
