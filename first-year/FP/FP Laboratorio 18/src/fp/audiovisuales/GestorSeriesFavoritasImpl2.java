package fp.audiovisuales;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Ficheros;

public class GestorSeriesFavoritasImpl2 extends GestorSeriesFavoritasImpl {

	public GestorSeriesFavoritasImpl2(String id, String nombre, Set<SerieTV> seriesTV) {
		super(id, nombre, seriesTV);
	}

	public GestorSeriesFavoritasImpl2(String id, String nombre) {
		super(id, nombre);
	}

	private String[] extraePalabras(String s) {
		return s.replaceAll("[^A-Za-záéíóú]+", " ").replaceAll(" +", " ").toLowerCase().split(" ");
	}

	public SerieTV getSerieMasPopular(String genero) {
		return getSeriesTV().stream().filter(s -> s.getGeneros().stream().anyMatch(g -> g.equals(genero)))
				.max(Comparator.comparing(SerieTV::getPopularidad)).get();
	}

	public void guardaEnFicheroOrdenados(String nombreFichero, String genero) {

		Comparator<SerieTV> c = Comparator.comparing((SerieTV serie) -> serie.getFechaPrimeraEmision().getYear())
				.thenComparing(SerieTV::getPopularidad);

		List<String> cadenas = getSeriesTV().stream().filter(serie -> serie.getGeneros().contains(genero)).sorted(c)
				.map(serie -> formateaSerie(serie)).collect(Collectors.toList());

		Ficheros.escribeFichero(cadenas, nombreFichero);

	}

	private String formateaSerie(SerieTV serie) {

		// House of Cards (2013) Temporadas: 5 Episodios: 53
		return serie.getNombre() + " (" + serie.getFechaPrimeraEmision().getYear() + ") " + "Temporadas: "
				+ serie.getTemporadas() + "Episodios: " + serie.getNumeroEpisodios();
	}

	public SortedSet<String> getNombresSeriesConPalabraEnSinopsis(String palabra) {
		return getSeriesTV().stream().filter(s -> s.getSinopsis() != null && tienePalabrasEnSinopsis(s, palabra))
				.map(SerieTV::getNombre).collect(Collectors.toCollection(TreeSet::new));
	}

	public SortedMap<String, Long> getFrecuenciaPalabrasSinopsis() {

		return getSeriesTV().stream().filter(s -> s.getSinopsis() != null).map(s -> s.getSinopsis())
				.flatMap(s -> extraePalabras(s).stream())
				.collect(Collectors.groupingBy(p -> p, TreeMap::new, Collectors.counting()));
	}

	public Boolean tienePalabrasEnSinopsis(SerieTV serie, String palabra) {
		String[] palabras = extraePalabras(serie.getSinopsis());
		return Arrays.stream(palabras).anyMatch(pal -> pal.equals(palabra));
	}

	// House of Cards (Duración por episodio: 7 minutos, Duración total: 98 minutos)
	private String formateaSerieVersion2(SerieTV serie) {
		Integer numEpi = serie.getNumeroEpisodios();

		Long durTotal = numEpi * serie.getDuracionEpisodio().toMinutes();

		return serie.getNombre() + " (Duración por episodio" + serie.getDuracionEpisodio().toMinutes() + " minutos"
				+ ", Duración total: " + durTotal + "minutos) ";
	}

	public void guardarSeriesDuracion(String nomFichero, Integer duracionEnMinutos) {

		Comparator<SerieTV> c = Comparator.comparing(s -> getDuracionTotalSerieMinutos(s));

		List<String> lineas = getSeriesTV().stream()
				.filter(s -> duracionEnMinutos.compareTo(new Long(s.getDuracionEpisodio().toMinutes()).intValue()) < 0)
				.sorted(c.reversed()).map(s -> formateaSerieVersion2(s)).collect(Collectors.toList());

		Ficheros.escribeFichero(lineas, nomFichero);
	}

	private Long getDuracionTotalSerieMinutos(SerieTV serie) {

		Integer numEpi = serie.getNumeroEpisodios();

		return numEpi * serie.getDuracionEpisodio().toMinutes();
	}

	public SerieTV getSerieMasTemporadas() {

		Comparator<SerieTV> c = Comparator.comparing(s -> s.getNumeroTemporadas());
		Comparator.comparing(SerieTV::getNumeroTemporadas);
		return getSeriesTV().stream().sorted(c.reversed()).findFirst().orElse(null);
	}

	public Integer getNumeroSeriesDeGenero(String genero) {
		return new Long(getSeriesTV().stream().filter(s -> s.getGeneros().contains(genero)).count()).intValue();
	}

	public SerieTV getSerieMasEpisodios() {
		return getSeriesTV().stream().max(Comparator.comparing(SerieTV::getNumeroEpisodios)).orElse(null);
	}

	public Set<SerieTV> getSeriesDe(MiembroStaff persona) {

		Predicate<SerieTV> fil = s -> s.esActor(persona) || s.esArtistaInvitado(persona) || s.esCreador(persona);

		return getSeriesTV().stream().filter(fil).collect(Collectors.toSet());
	}

	public Integer[] contarSeriesSegunEstado() {
		List<SerieTV> a = getSeriesTV().stream().filter(g -> g.getEstado().equals(EstadoSerie.EN_CURSO))
				.collect(Collectors.toList());
		List<SerieTV> b = getSeriesTV().stream().filter(g -> g.getEstado().equals(EstadoSerie.EN_PRODUCCION))
				.collect(Collectors.toList());
		List<SerieTV> c = getSeriesTV().stream().filter(g -> g.getEstado().equals(EstadoSerie.FINALIZADA))
				.collect(Collectors.toList());
		Integer[] aux = { c.size(), a.size(), b.size() };

		return aux;
	}

	public Boolean haySeriesDeCadenaTV(String cadenaTV) {
		return getSeriesTV().stream().anyMatch(p -> p.getCadenasTV().contains(cadenaTV));

	}

	public Boolean seEmitieronTodasEn(Integer anyo) {

		return getSeriesTV().stream().allMatch(s -> anyo.equals(s.getFechaPrimeraEmision().getYear()));

	}

}
