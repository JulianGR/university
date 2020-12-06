package fp.musica.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.musica.Cancion;
import fp.musica.Musica;

public class ExperimentoComparator {
	public static final Comparator<Cancion> C_DURACION = Comparator.comparing(Cancion::getDuracion);
	public static final Comparator<Cancion> C_ARTISTA = Comparator
			.comparing((Cancion c) -> c.getArtistas().get(0).getNombre());
	public static final Comparator<Cancion> C_POPULARIDAD = Comparator.comparing(Cancion::getPopularidad);
	public static final Comparator<Cancion> C_NOMBRE = Comparator.comparing(Cancion::getNombre);

	public static void main(String[] args) {

		List<Cancion> canciones = Musica.creaCancion("resources/canciones.txt");

		experimento1(canciones);
		experimento2(canciones);
		experimento3(canciones);
		experimento4(canciones);
		experimento5(canciones);
		experimento6(canciones);
		experimento7(canciones);

	}

	public static void experimento1(List<Cancion> canciones) {

		Comparator<Cancion> c1 = Comparator.comparing(Cancion::getDuracion);

		System.out.println("==============La canción más corta es:================" + Collections.min(canciones, c1));
		System.out.println(
				"\n" + "============La canción más larga es:=================" + Collections.max(canciones, c1));

	}

	public static void experimento2(List<Cancion> canciones) {

		Comparator<Cancion> c2 = Comparator.comparing(Cancion::getNombre);
		List<Cancion> cancionesOrd = new ArrayList<>(canciones);
		Collections.sort(cancionesOrd, c2);
		System.out.println("\n" + "=============Canciones por orden alfabético:==========");
		for (Cancion c : cancionesOrd) {
			System.out.println(c);
		}

	}

	public static void experimento3(List<Cancion> canciones) {
		Comparator<Cancion> c3 = Comparator.comparing((Cancion c) -> c.getArtistas().get(0).getNombre())
				.thenComparing(c -> c.getNombre());

		List<Cancion> copia = new ArrayList<Cancion>(canciones);
		Collections.sort(copia, c3);
		for (Cancion c : copia) {
			System.out.println(c);
		}

	}

	public static void experimento4(List<Cancion> canciones) {
		Comparator<Cancion> c4 = Comparator.comparing((Cancion c) -> c.getPopularidad())
				.thenComparing(c -> c.getDuracion());

		System.out.println(
				"\n" + "=============Canción menos popular y más larga:==========" + Collections.min(canciones, c4));
	}

	public static void experimento5(List<Cancion> canciones) {
		Comparator<Cancion> cmp = Comparator.comparing(Cancion::getPopularidad).thenComparing(Cancion::getDuracion);
		Cancion masPopular = Collections.max(canciones, cmp);
		System.out.println("\n =========La canción más popular y más larga======" + masPopular);
	}

	public static void experimento6(List<Cancion> canciones) {
		Comparator<Cancion> cmp = Comparator.comparing(Cancion::getDuracion);
		SortedSet<Cancion> cancionesSet = new TreeSet<Cancion>(cmp);
		cancionesSet.addAll(canciones);
		System.out.println("\nConjunto de canciones ordenado por duración" + cancionesSet);
		System.out.println("\nNúmero de canciones en el conjunto " + cancionesSet.size());
		System.out.println("\nNúmero de canciones en la lista original " + canciones.size());

		SortedSet<Cancion> cancionesSet2 = new TreeSet<Cancion>(cmp.thenComparing(C_NOMBRE));
		cancionesSet2.addAll(canciones);
		System.out.println("\nConjunto de canciones ordenado por duración y por nombre " + cancionesSet2);
		System.out.println("\n Número de canciones en el conjunto: " + cancionesSet2.size());
		System.out.println("\nNúmero de canciones en la lista original:" + canciones.size());
	}

	public static void experimento7(List<Cancion> canciones) {
		SortedMap<Integer, SortedSet<Cancion>> cancionesPorPopularidad = creaMap(canciones);
		System.out.println(("\nCanciones por popularidad:"));
		muestraMap(cancionesPorPopularidad);
	}

	private static void muestraMap(SortedMap<Integer, SortedSet<Cancion>> m) {
		for (Entry<Integer, SortedSet<Cancion>> entrada : m.entrySet()) {
			System.out.println(entrada.getKey() + "-->" + entrada.getValue());
		}

	}

	private static SortedMap<Integer, SortedSet<Cancion>> creaMap(List<Cancion> canciones) {
		@SuppressWarnings("unused")
		Comparator<Integer> cmpEnterosInv = Comparator.reverseOrder();
		Comparator<Cancion> cmpCancionArtista = Comparator.comparing((Cancion c) -> c.getPopularidad())
				.thenComparing((Comparator.naturalOrder()));
		SortedMap<Integer, SortedSet<Cancion>> res = new TreeMap<Integer, SortedSet<Cancion>>();

		for (Cancion c : canciones) {
			Integer clave = c.getPopularidad();
			if (!res.containsKey(clave)) {
				SortedSet<Cancion> ss = new TreeSet<Cancion>(cmpCancionArtista);
				ss.add(c);
				res.put(clave, ss);
			} else {
				SortedSet<Cancion> ss = res.get(clave);
				ss.add(c);
			}
		}
		return res;
	}
}
