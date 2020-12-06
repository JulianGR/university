package fp.audiovisuales;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import fp.utiles.Checkers;

public class GestorPeliculasFavoritasImpl2 extends GestorPeliculasFavoritasImpl {
	public GestorPeliculasFavoritasImpl2(String id, String nombre, Set<Pelicula> peliculas) {
		super(id, nombre, peliculas);
	}

	public GestorPeliculasFavoritasImpl2(String id, String nombre) {
		super(id, nombre);
	}

	// ********* Laboratorio 15 *************
	public Integer cuentaPeliculas(String nombreActor) {

		Long res = getPeliculas().stream().filter(p -> p.esActor(nombreActor)).count();
		return res.intValue();
	}

	public Boolean hayAlgunaPeliculaDirigidaPor(String nombreDirector) {

		return getPeliculas().stream().anyMatch(p -> p.esDirector(nombreDirector));
	}
	// ********* FIN Laboratorio 15 *************

	public MiembroStaff getActorMasPeliculas() {

		return getPeliculas().stream().flatMap(p -> p.getActores().stream())
				.max(Comparator.comparing(a -> cuentaPeliculas(a.getNombre()))).get();

	}

	public Pelicula getPeliculaMasActores() {
		return getPeliculas().stream().max(Comparator.comparing(p -> p.getActores().size())).orElse(null);
	}

	public Set<String> getGeneros() {
		Set<String> res = getPeliculas().stream().flatMap(p -> p.getGeneros().stream()).collect(Collectors.toSet());

		if (res.isEmpty()) {
			return new HashSet<>();
		} else {
			return res;
		}

	}

	public Set<MiembroStaff> seleccionaActoresParticipantesTodas() {

		/*
		 * 
		 * MI SOLUCION Explicación de este: primero consigo cuántas películas hay
		 * (numPeliculastotales), después con actorConNumPeliculasHechas tengo un map
		 * que me relaciona cada MiembroStaff con el número de películas hechas
		 *
		 * 
		 * Long numPeliculasTotales = getPeliculas().stream().count();
		 * 
		 * Map<MiembroStaff, Long> actorConNumPeliculasHechas = getPeliculas().stream()
		 * .flatMap(p -> p.getActores().stream()).collect(Collectors.groupingBy(m -> m,
		 * Collectors.counting()));
		 * 
		 * 
		 * El siguiente paso que me interesa dar es comprobar qué value de ese map
		 * coincide con el número total de películas que hay (eso significaría que si
		 * son igual, ese MiembroStaff ha participado en todas las películas)
		 * 
		 * Así que paso el map a parejas, le saco los values a esas parejas y empiezo a
		 * comprobar, filtrando.
		 * 
		 * Ahora tengo todas las parejas filtradas (todos los entries que tienen como
		 * value el numPeliculasTotales)
		 * 
		 * Y ahora cojo las keys de esas parejas, que es lo que nos piden, y las voy
		 * añadiendo en un set.
		 *
		 * 
		 * Set<MiembroStaff> res = actorConNumPeliculasHechas.entrySet().stream()
		 * .filter(v -> v.getValue().equals(numPeliculasTotales)) .map(a ->
		 * a.getKey()).collect(Collectors.toSet());
		 *
		 * if (res.isEmpty()) { return new HashSet<>(); } else { return res; }
		 * 
		 * 
		 */

		if(getPeliculas().isEmpty()) {
			return new HashSet<MiembroStaff>();
		}
Pelicula primera = getPeliculas().stream().findFirst().get();
return getPeliculas().stream()
		.skip(1)
		.map(p->p.getActores())
		.collect(()->new HashSet<>(primera.getActores()),Collection::retainAll,Collection::retainAll);
	}

	public Set<Pelicula> getPeliculasDirigidasPor(String nombreDirector) {
		return getPeliculas().stream().filter(p -> p.esDirector(nombreDirector)).collect(Collectors.toSet());
	}

	public Set<Pelicula> getPeliculasAnyo(Integer anyo) {
		Checkers.checkNoNull(anyo);
		return getPeliculas().stream()
				.filter(p -> p.getFechaEstreno() != null && anyo.equals(p.getFechaEstreno().getYear()))
				.collect(Collectors.toSet());
	}

	public Set<Pelicula> getPeliculasDeActor(String nombreActor) {
		Set<Pelicula> res = getPeliculas().stream().filter(p -> p.esActor(nombreActor)).collect(Collectors.toSet());

		if (res == null) {
			throw new IllegalArgumentException();
		}

		else if (res.isEmpty()) {
			return new HashSet<>();
		} else {
			return res;
		}

	}

	public Map<String, Set<Pelicula>> getPeliculasPorDirector() {

		return getPeliculas().stream().filter(p -> p.getDirectores().size() == 1)
				.collect(Collectors.groupingBy(p -> p.getDirectores().get(0).getNombre(), Collectors.toSet()));
	}

	public Set<String> getPaises() {
		Set<String> res = getPeliculas().stream().flatMap(p -> p.getPaises().stream()).collect(Collectors.toSet());

		if (res.isEmpty()) {
			return new HashSet<>();
		} else {
			return res;
		}
	}

	public SortedMap<String, Double> getDuracionMediaPorDirector() {
		return getPeliculas().stream().filter(p -> p.getDirectores().size() == 1)
				.collect(Collectors.groupingBy(p -> p.getDirectores().get(0).getNombre(), () -> new TreeMap<>(),
						Collectors.averagingDouble(p -> new Double(p.getDuracion().getSeconds() / 60))));
	}

	// MODO EXAMEN
	public Map<Integer, SortedSet<Pelicula>> getPeliculasPorAnyo() {
		Comparator<Pelicula> c = Comparator.comparing((Pelicula p) -> ((Pelicula) p).getFechaEstreno().getMonth())
				.thenComparing(p -> ((Pelicula) p).getFechaEstreno().getDayOfYear());

		return getPeliculas().stream().collect(Collectors.groupingBy(p -> p.getFechaEstreno().getYear(),
				Collectors.toCollection(() -> new TreeSet<>(c))));
	}

	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof GestorPeliculasFavoritas) {
			GestorPeliculasFavoritas gestor = (GestorPeliculasFavoritas) obj;
			res = this.getId().equals(gestor.getId());
		}
		return res;
	}

	public int hashCode() {
		return this.getId().hashCode();
	}

	public String toString() {
		return getNombre() + " [" + getId() + "]";
	}

}
