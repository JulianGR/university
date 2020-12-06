package fp.aeropuertos.test;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Collection;

import java.util.NoSuchElementException;

import java.util.Scanner;


import fp.aeropuertos.Aeropuerto;
import fp.aeropuertos.AeropuertoImpl;
import fp.aeropuertos.Vuelo;
import fp.aeropuertos.VueloImpl;

public class TestAeropuerto2 {
	
	
	private static Aeropuerto aeropuertoDeTest = 
			FactoriaTestPool.crearAeropuertoDeTest();
	private static Scanner lectorTeclado = new Scanner(System.in);

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();
		testGetVuelos();
		testNuevoVuelo();
		testNuevosVuelos();
		testContieneVuelo();
		testSeleccionaVuelosFecha();
		testGetVueloMasPasajeros();
		testGetPasajeroMayor();
		testGetVueloPlazasLibresDestino();
		testCalculaTotalPasajerosDestino();
		testCalculaMediaPasajerosPorDia();
		testNumeroPasajerosPorDestino();
		testVuelosPorFecha();
	}

	private static void testConstructor1() {
		testConstructor1(1);
		testConstructor1(2);
		testConstructor1(3);
		testConstructor1(4);
		testConstructor1(5);
	}

	private static void testConstructor1(Integer casoPrueba) {
		Collection<Vuelo> vuelos = FactoriaTestPool.crearVuelosDeTest(aeropuertoDeTest.getCiudad());
		switch (casoPrueba) {
		case 1:
			testConstructor1("SAN PABLO", "SEVILLA", vuelos);
			break;
		case 2:
			testConstructor1(null, "SEVILLA", vuelos);
			break;
		case 3:
			testConstructor1("SAN PABLO", null, vuelos);
			break;
		case 4:
			testConstructor1("SAN PABLO", "SEVILLA", null);
			break;
		case 5:
			vuelos.add(new VueloImpl("IB7636", "Barcelona", "Madrid", LocalDateTime.now(),
					LocalDateTime.now().plusDays(1l), 50));
			testConstructor1("SAN PABLO", "SEVILLA", vuelos);
			break;
		default:
			System.out.println("CASO DE PRUEBA NO CONTEMPLADO");
		}
	}

	private static void testConstructor1(String nombre, String ciudad, Collection<Vuelo> vuelos) {
		try {
//			Aeropuerto aeropuerto = new AeropuertoImpl2(nombre, ciudad, vuelos);
			Aeropuerto aeropuerto = new AeropuertoImpl("SAN PABLO", "SEVILLA");
			mostrar(aeropuerto);
		} catch (IllegalArgumentException excepcion) {
			System.out.println("Excepción capturada:\n" + excepcion);
		} catch (Exception excepcion) {
			System.out.println("Excepción inesperada:\n" + excepcion);
		}
	}

	private static void testConstructor2() {
		testConstructor2(1);
		testConstructor2(2);
		testConstructor2(3);
	}

	private static void testConstructor2(Integer casoPrueba) {
		switch (casoPrueba) {
		case 1:
			testConstructor2("SAN PABLO", "SEVILLA");
			break;
		case 2:
			testConstructor2(null, "SEVILLA");
			break;
		case 3:
			testConstructor2("SAN PABLO", null);
			break;
		default:
			System.out.println("CASO DE PRUEBA NO CONTEMPLADO");
		}
	}

	private static void testConstructor2(String nombre, String ciudad) {
		try {
//			Aeropuerto aeropuerto = new AeropuertoImpl2(nombre, ciudad);
			Aeropuerto aeropuerto = new AeropuertoImpl("SAN PABLO", "SEVILLA");
			mostrar(aeropuerto);
		} catch (IllegalArgumentException excepcion) {
			System.out.println("Excepción capturada:\n" + excepcion);
		} catch (Exception excepcion) {
			System.out.println("Excepción inesperada:\n" + excepcion);
		}
	}

	private static void testGetVuelos() {
		System.out.println("Usando aeropuerto de test para testear getVuelos. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("Intentando borrar los vuelos...");
		aeropuertoDeTest.getVuelos().clear();
		System.out.println("Mostrando de nuevo el estado...");
		mostrar(aeropuertoDeTest);
	}

	private static void testNuevoVuelo() {
		testNuevoVuelo(true);
		testNuevoVuelo(false);
	}



	// private static void testNuevoVuelo(String codigo, String origen, String
	// destino, LocalDateTime salida, LocalDateTime llegada, Integer plazas) {
	private static void testNuevoVuelo(Boolean exito) {
		Vuelo vuelo = null;
		if (exito) {
			vuelo = FactoriaTestPool.crearVueloDeTest(false, aeropuertoDeTest.getCiudad());
		} else {
			vuelo = FactoriaTestPool.crearVueloDeTest(true, "MADRID");
		}
		try {
			System.out.println("---> Antes de la operación el aeropuerto de test es:");
			mostrar(aeropuertoDeTest);
			System.out.println("---> Invocando a nuevoVuelo usando: " + vuelo);
			aeropuertoDeTest.nuevoVuelo(vuelo);
			System.out.println("---> Después de la operación el aeropuerto de test es:");
			mostrar(aeropuertoDeTest);
		} catch (IllegalArgumentException excepcion) {
			System.out.println("Excepción capturada:\n" + excepcion);
		} catch (Exception excepcion) {
			System.out.println("Excepción inesperada:\n" + excepcion);
		}
	}

	private static void testNuevosVuelos() {
		testNuevosVuelos(true);
		testNuevosVuelos(false);
	}

	private static void testNuevosVuelos(Boolean exito) {
		Collection<Vuelo> vuelos = null;
		if (exito) {
			vuelos = FactoriaTestPool.crearVuelosDeTest(aeropuertoDeTest.getCiudad());
		} else {
			vuelos = FactoriaTestPool.crearVuelosDeTest("Madrid");
		}
		try {
			System.out.println("---> Antes de la operación el aeropuerto de test es:");
			mostrar(aeropuertoDeTest);
			System.out.println("---> Invocando a nuevoVuelo usando: " + vuelos);
			aeropuertoDeTest.nuevosVuelos(vuelos);
			System.out.println("---> Después de la operación el aeropuerto de test es:");
			mostrar(aeropuertoDeTest);
		} catch (IllegalArgumentException excepcion) {
			System.out.println("Excepción capturada:\n" + excepcion);
		} catch (Exception excepcion) {
			System.out.println("Excepción inesperada:\n" + excepcion);
		}

	}

	private static void testContieneVuelo() {
		testContieneVuelo(true);
		testContieneVuelo(false);
	}

	private static void testContieneVuelo(Boolean exito) {
		Vuelo vuelo = null;
		if (exito) {
			vuelo = FactoriaTestPool.crearVueloDeTest(false, aeropuertoDeTest.getCiudad());
		} else {
			vuelo = FactoriaTestPool.crearVueloDeTest(true, "Madrid");
		}
		System.out.println("Usando aeropuerto de test para testear contieneVuelo. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("---> Invocando a contieneVuelo usando: " + vuelo);
		Boolean resultado = aeropuertoDeTest.contieneVuelo(vuelo);
		System.out.println("El resultado de la operación es: " + resultado);
	}

	private static void testSeleccionaVuelosFecha() {
		System.out.println("Usando aeropuerto de test para testear seleccionaVuelosFecha. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		LocalDate fechaSalida = FactoriaTestPool.leerFechaTeclado();
		System.out.println(
				"Los vuelos que salen en dicha fecha son:\n" + aeropuertoDeTest.seleccionaVuelosFecha(fechaSalida));
	}

	private static void testGetVueloMasPasajeros() {
		System.out.println("Usando aeropuerto de test para testear getVueloMasPasajeros. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		try {
			System.out.println("El vuelo con más pasajeros es: " + aeropuertoDeTest.getVueloMasPasajeros());
		} catch (NoSuchElementException e) {
			System.out.println("Excepción capturada: " + e);
		} catch (Exception e) {
			System.out.println("Excepción inesperada: " + e);
		}
	}

	private static void testGetPasajeroMayor() {
		System.out.println("Usando aeropuerto de test para testear getPasajeroMayor. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		try {
			System.out.println("El pasajero de mayor edad es: " + aeropuertoDeTest.getPasajeroMayor());
		} catch (NoSuchElementException e) {
			System.out.println("Excepción capturada: " + e);
		} catch (Exception e) {
			System.out.println("Excepción inesperada: " + e);
		}
	}

	private static void testGetVueloPlazasLibresDestino() {
		System.out
				.println("Usando aeropuerto de test para testear getVueloPlazasLibresDestino. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("Introduzca una ciudad de destino: ");
		String destino = lectorTeclado.nextLine().toUpperCase();
		try {
			System.out.println("El vuelo con plazas libres a " + destino + " es: "
					+ aeropuertoDeTest.getVueloPlazasLibresDestino(destino));
		} catch (NoSuchElementException e) {
			System.out.println("Excepción capturada: " + e);
		} catch (Exception e) {
			System.out.println("Excepción inesperada: " + e);
		}
	}

	private static void testCalculaTotalPasajerosDestino() {
		System.out.println("Usando aeropuerto de test para testear calculaTotalPasajerosDestino. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("Introduzca una ciudad de destino: ");
		String destino = lectorTeclado.nextLine().toString();
		System.out.println(
				"El total de pasajeros hacia "+destino+" es: " + aeropuertoDeTest.calculaTotalPasajerosDestino(destino));
	}

	private static void testCalculaMediaPasajerosPorDia() {
		System.out.println("Usando aeropuerto de test para testear calculaMediaPasajerosPorDia. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("La afluencia media diaria es: " + aeropuertoDeTest.calculaMediaPasajerosPorDia());
	}

	private static void testNumeroPasajerosPorDestino() {
		System.out.println("Usando aeropuerto de test para testear numeroPasajerosPorDestino. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("El número de pasajeros por destino es: " + aeropuertoDeTest.getNumeroPasajerosPorDestino());
	}

	private static void testVuelosPorFecha() {
		System.out.println("Usando aeropuerto de test para testear testVuelosPorFecha. Mostrando el estado...");
		mostrar(aeropuertoDeTest);
		System.out.println("Los vuelos por fecha son: " + aeropuertoDeTest.getVuelosPorFecha());
	}
	
	
	private static void mostrar(Aeropuerto aeropuerto) {
		System.out.println("Representación como cadena del objeto: " + aeropuerto);
		System.out.println("Propiedades del objeto:");
		System.out.println("\t-> Nombre: " + aeropuerto.getNombre());
		System.out.println("\t-> Ciudad: " + aeropuerto.getCiudad());
		System.out.println("\t-> Vuelos: ");
		for (Vuelo vuelo : aeropuerto.getVuelos()) {
			System.out.println("\t\t-> " + vuelo +" ("+ vuelo.getNumeroPasajeros()+ ")");
		}
	}


}
