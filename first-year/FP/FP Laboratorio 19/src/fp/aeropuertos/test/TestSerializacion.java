package fp.aeropuertos.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import fp.aeropuertos.Aeropuerto;
import fp.aeropuertos.AeropuertoImpl;
import fp.aeropuertos.Persona;
import fp.aeropuertos.PersonaImpl;
import fp.aeropuertos.Vuelo;
import fp.aeropuertos.VueloImpl;
import fp.utiles.UtilesJSon;

public class TestSerializacion {
	private static Integer numVuelos = 0;
	private static Random rnd = new Random(System.currentTimeMillis());

	private static final String[] CIUDADES = { "MADRID", "BARCELONA", "VALENCIA", "TENERIFE", "MALLORCA", "LANZAROTE",
			"BILBAO" };

	public static void main(String[] args) {
		Persona personaTest = crearPersonaDeTest();
		Vuelo vueloTest = crearVueloDeTest();
		Aeropuerto aeropuertoTest = crearAeropuertoDeTest();
		testSerializacionPersona(personaTest);
		testSerializacionVuelo(vueloTest);
		testSerializacionAeropuerto(aeropuertoTest);
	}

	private static void testSerializacionPersona(Persona p) {
		System.out.println("\nSerializando persona:");
		String persona = UtilesJSon.toJSON(p);
		System.out.println("Json Persona: " + persona);

		System.out.println("\nDeserializando el JSON anterior:");

		System.out.println("Persona from Json String");
		System.out.println("---------------------------");
		String personaJSon = persona;
		Persona p2 = UtilesJSon.fromJSON(personaJSon, PersonaImpl.class);
		mostrar(p2);
	}

	private static void testSerializacionVuelo(Vuelo v) {
		System.out.println("\nSerializando vuelo:");
		String vuelo = UtilesJSon.toJSON(v);
		System.out.println("Json Vuelo: " + vuelo);

		System.out.println("\nDeserializando el JSON anterior:");
		System.out.println("Vuelo from Jason String");
		System.out.println("---------------------------");
		String vueloJson = vuelo;
		Vuelo v2 = UtilesJSon.fromJSON(vueloJson, VueloImpl.class);
		mostrar(v2);
	}

	private static void testSerializacionAeropuerto(Aeropuerto a) {
		System.out.println("\nSerializando aeropuerto:");
		String aeropuerto = UtilesJSon.toJSON(a);
		System.out.println("Json aeropuerto: " + aeropuerto);

		System.out.println("\nDeserializando el JSON anterior:");
		System.out.println("Aeropuerto from Json String");
		System.out.println("---------------------------");
		String aeropuertoJson = aeropuerto;
		Aeropuerto a2 = UtilesJSon.fromJSON(aeropuertoJson, AeropuertoImpl.class);
		mostrar(a2);

		System.out.println("\nEl aeropuerto será almacenado en el archivo out/aeropuerto.json");
		UtilesJSon.toJSONFile(a, "./out/aeropuerto.json");
	}

	private static Aeropuerto crearAeropuertoDeTest() {
		Aeropuerto res = new AeropuertoImpl("SAN PABLO", "SEVILLA");
		res.nuevosVuelos(crearVuelosDeTest("SEVILLA"));
		return res;
	}

	private static Collection<Vuelo> crearVuelosDeTest(String ciudad) {
		Collection<Vuelo> res = new HashSet<>();

		for (int n = 0; n < 10; n++) {
			res.add(crearVueloDeTest(rnd.nextBoolean(), ciudad));
		}
		return res;
	}

	private static Vuelo crearVueloDeTest(Boolean origen, String ciudad) {
		String ciudadOrigen, ciudadDestino;
		if (origen) {
			ciudadOrigen = ciudad;
			ciudadDestino = CIUDADES[rnd.nextInt(CIUDADES.length)];
		} else {
			ciudadOrigen = CIUDADES[rnd.nextInt(CIUDADES.length)];
			ciudadDestino = ciudad;
		}
		LocalDateTime salida = LocalDateTime.now().plusMinutes(rnd.nextInt(300));
		salida = salida.plusDays(rnd.nextInt(7));
		LocalDateTime llegada = salida.plus(100 + rnd.nextInt(100), ChronoUnit.MINUTES);

		Integer plazas = 50 + rnd.nextInt(150);

		Vuelo res = new VueloImpl("IB-" + (numVuelos++), ciudadOrigen, ciudadDestino, salida, llegada, plazas);
		insertaPasajeros(res);

		return res;
	}

	private static Vuelo crearVueloDeTest() {
		return crearVueloDeTest(true, "SEVILLA");
	}

	private static void insertaPasajeros(Vuelo vuelo) {

		for (int np = 0; np < 5 && np < vuelo.getNumeroPlazas(); np++) {
			Persona p = crearPersonaDeTest();
			vuelo.nuevoPasajero(p);
		}
	}

	private static Persona crearPersonaDeTest() {
		LocalDate fecha = LocalDate.of(1950 + rnd.nextInt(65), 1 + rnd.nextInt(11), 1 + rnd.nextInt(27));
		String letras = "QWERTYUIOPZXCVBNMASDFGHJKL";
		char letra = letras.charAt(rnd.nextInt(letras.length()));
		Integer numero = 10000000 + rnd.nextInt(90000000);
		String dni = "" + numero + letra;
		return new PersonaImpl(dni, "Fulanito", "Menganito", fecha, "fulanito@menganito.com");
	}

	private static void mostrar(Aeropuerto aeropuerto) {
		System.out.println("Representación como cadena del objeto: " + aeropuerto);
		System.out.println("Propiedades del objeto:");
		System.out.println("\t-> Nombre: " + aeropuerto.getNombre());
		System.out.println("\t-> Ciudad: " + aeropuerto.getCiudad());
		System.out.println("\t-> Vuelos: ");
		for (Vuelo vuelo : aeropuerto.getVuelos()) {
			System.out.println("\t\t-> " + vuelo + " (" + vuelo.getNumeroPasajeros() + ")");
		}
	}

	private static void mostrar(Vuelo v) {
		System.out.println("Vuelo: " + v);
		System.out.println("Codigo: " + v.getCodigo());
		System.out.println("Origen: " + v.getOrigen());
		System.out.println("Destino: " + v.getDestino());
		System.out.println(
				"Fecha de salida: " + v.getFechaSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
		System.out.println(
				"Fecha de llegada: " + v.getFechaLlegada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
		System.out.println("Numero de plazas: " + v.getNumeroPlazas());
		System.out.println("Pasajeros: " + v.getPasajeros());
		System.out.println("Numero de pasajeros: " + v.getNumeroPasajeros());
		System.out.println("Completo: " + v.estaCompleto());
	}

	private static void mostrar(Persona p) {
		System.out.println("Persona: " + p);
		System.out.println("Nombre: " + p.getNombre());
		System.out.println("Apellidos: " + p.getApellidos());
		System.out.println("DNI: " + p.getDNI());
		System.out.println("Fecha de nacimiento: " + p.getFechaNacimiento());
		System.out.println("Edad: " + p.getEdad());
		System.out.println("Email: " + p.getEmail());
	}
}
