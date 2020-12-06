package fp.musica;

import java.time.Duration;
import fp.musica.Cancion;
import fp.utiles.Imagenes;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaReproduccionImpl2 extends ListaReproduccionImpl {
	public ListaReproduccionImpl2(String nombre) {
		super(nombre);
	}

	// ***************** Laboratorio 15 ***********************
	/**
	 * @param artista
	 *            Nombre del artista
	 * @return Devuelve true si todas las canciones de la lista de reproducción son
	 *         del artista especificado.
	 */
	public Boolean esAntologia(String artista) {
		return getCanciones().stream().allMatch(c -> existe(artista, c.getArtistas()));

	}

	private Boolean existe(String nomArtista, List<Artista> artistas) {
		return getArtistas().stream().anyMatch(a -> a.getNombre().equals(nomArtista));
	}

	/**
	 * @param artista
	 *            Nombre del artista
	 * @return Devuelve true si existe alguna canción en la lista de reproducción
	 *         del artista dado como parámetro.
	 */
	public Boolean contieneArtista(String artista) {
		return getArtistas().stream().anyMatch(a -> a.getNombre().contains(artista));
	}

	// ***************** FIN Laboratorio 15 ***********************

	/**
	 * @return Devuelve la duración total de la lista de reproducción
	 */
	public Duration getDuracion() {
		Long total = getCanciones().stream().mapToLong(c -> c.getDuracion().getSeconds()).sum();
		return Duration.ofSeconds(total);
	}

	public Duration getDuracionMedia() {
		Double res = getCanciones().stream().mapToDouble(c -> c.getDuracion().getSeconds()).average().orElse(0.0);
		return Duration.ofSeconds(res.longValue());
	}

	/**
	 * @param artista
	 *            Nombre del artista
	 * @return Devuelve una nueva lista de reproducción que contenga todas las
	 *         canciones del artista dado como parámetro.
	 */
	public ListaReproduccion getSublistaArtista(String artista) {
		List<Cancion> listaCanciones = getCanciones().stream().filter(c -> esDeArtista(artista, c.getArtistas()))
				.collect(Collectors.toList());

		ListaReproduccion res = new ListaReproduccionImpl2(artista);
		res.incorpora(listaCanciones);
		return res;
	}

	private Boolean esDeArtista(String nomArtista, List<Artista> artistas) {
		return getArtistas().stream().anyMatch(a -> a.getNombre().equals(nomArtista));
	}

	/**
	 * @return Devuelve el conjunto de artistas de la lista de reproducción.
	 */
	public Set<Artista> getArtistas() {
		return getCanciones().stream().flatMap(c -> c.getArtistas().stream()).collect(Collectors.toSet());
	}

	/**
	 * Muestra las fotos de los artistas que aparecen en la lista
	 */

	// la primera de cada artista
	public void muestraFotosArtistas() {

		getArtistas().stream().sorted(Comparator.comparing(Artista::getNombre)).map(a -> a.getURLImagenes().get(0))
				.forEach(url -> Imagenes.show(getNombre(), url));

	}

	/**
	 * @param tituloCancion
	 *            Titulo de una canción.
	 * @return Devuelve la posición (índice) que ocupa en la lista de reproducción
	 *         la primera canción cuyo título se especifica. Si la canción no está
	 *         en la lista de reproducción, devuelve -1.
	 * 
	 */
	public int getPosicionCancion(String tituloCancion) {
		int res = -1;
		Cancion cancion = getCanciones().stream().filter(c -> c.getNombre().equals(tituloCancion)).findFirst()
				.orElse(null);
		res = getCanciones().indexOf(cancion);
		if (cancion == null) {
			res = -1;
		}
		return res;
	}

	/**
	 * @return Devuelve la canción con mayor duración de la lista de reproducción.
	 *         Si no hay ninguna canción en la lista, devuelve null.
	 */
	public Cancion getCancionMasLarga() {
		return getCanciones().stream().max(Comparator.comparing(c -> c.getDuracion())).orElse(null);
	}

	/**
	 * @return Devuelve la canción con menor duración de la lista de reproducción.
	 *         Si no hay ninguna canción en la lista, devuelve null.
	 */
	public Cancion getCancionMasCorta() {
		return getCanciones().stream().min(Comparator.comparing(c -> c.getDuracion())).orElse(null);
	}

	public Map<String, List<Cancion>> getCancionesPorArtista() {
		return getCanciones().stream().filter(c -> c.getArtistas().size() == 1)
				.collect(Collectors.groupingBy((Cancion c) -> c.getArtistas().get(0).getNombre()));
	}

	public Map<String, Long> getNumeroCancionesPorArtista() {
		return getCanciones().stream().filter(c -> c.getArtistas().size() == 1).collect(
				Collectors.groupingBy(((Cancion c) -> c.getArtistas().get(0).getNombre()), Collectors.counting()));
	}

	public Map<String, Integer> getDuracionTotalPorArtista() {
		return getCanciones().stream().filter(c -> c.getArtistas().size() == 1)
				.collect(Collectors.groupingBy(((Cancion c) -> c.getArtistas().get(0).getNombre()),
						Collectors.summingInt(c -> new Long(c.getDuracion().getSeconds()).intValue())));

	}
}
