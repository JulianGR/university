package fp.audiovisuales;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface GestorSeriesFavoritas {
	/**
	 * Propiedad básica. Solo consultable.
	 * 
	 * @return El id del gestor de series
	 */
	String getId();

	/**
	 * Propiedad básica solo consultable.
	 * 
	 * @return Nombre del usuario que gestiona las series
	 */
	String getNombre();

	/**
	 * Propiedad básica. Solo consultable.
	 * 
	 * @return Conjunto de series favoritas del usuario.
	 */
	Set<SerieTV> getSeriesTV();

	/**
	 * Añade la serie al conjunto de series favoritas del usuario.
	 * 
	 * @param serie
	 *            Serie a añadir a las favoritas.
	 */
	void nuevaSerie(SerieTV serie);

	/**
	 * Añade la colección de series a la lista de series favoritas del usuario
	 * 
	 * @param series
	 *            Colección de series a añadir a la colección.
	 */
	void nuevasSeries(Collection<SerieTV> series);

	/**
	 * @param serie
	 *            Elimina la serie del conjunto de favoritas;
	 */
	void eliminaSerie(SerieTV serie);

	/**
	 * @param nombre
	 *            Elimina la serie cuyo nombre se da como parámetro. Si no hay
	 *            ninguna serie con ese nombre, la operación no tiene efecto
	 */
	void eliminaSerie(String nombre);

	/**
	 * @return La serie con más temporadas del conjunto de series favoritas del
	 *         usuario. Si no hay ninguna, devuelve null.
	 */
	SerieTV getSerieMasTemporadas();

	/**
	 * @return La serie con un mayor número de episodios del conjunto de series
	 *         favoritas del usuario. Si no hay ninguna, devuelve null.
	 */
	SerieTV getSerieMasEpisodios();

	/**
	 * @param persona
	 *            Persona que trabaja en una serie.
		 * @return El conjunto de series en las que ha trabajado en cualquier rol
		 *         (creador, actor o arstista invitado) la persona dada como
		 *         parámetro.
	 */
	Set<SerieTV> getSeriesDe(MiembroStaff persona);

	/**
	 * @return Un array con el número de series con estado FINALIZADA, EN_CURSO
	 *         y EN_PRODUCCION del conjunto de series favoritas.
	 */
	Integer[] contarSeriesSegunEstado();

	/**
	 * @param cadenaTV
	 *            Nombre de cadena de televisión.
	 * @return Devuelve true si hay alguna serie de la cadena de televisión
	 *         pasada como parámetro.
	 */
	Boolean haySeriesDeCadenaTV(String cadenaTV);

	/**
	 * @param genero
	 *            Género de la serie.
	 * @return Devuelve la serie más popular del género dado como parámetro.
	 * @throws NoSuchElementException Si no hay ninguna serie de ese género.
	 */
	SerieTV getSerieMasPopular(String genero);

	/**
	 * @return true si todas las series del gestor se emitieron en el año dado
	 *         como parámetro. Se considera que una serie se emitió en un año si
	 *         alguno de sus episodios se ha emitido en ese año.
	 */
	Boolean seEmitieronTodasEn(Integer anyo);
	
	/**
	 * @param genero Cadena de televisión
	 * @return El número de series del gestor del género dado como parámetro
	 */
	Integer getNumeroSeriesDeGenero(String genero);
	

	/**
	 * @param nombreFichero
	 *            Nombre de fichero en el que se guardan las series
	 * @param genero
	 *            Género escogido. 
	 * Crea un fichero con el nombre de la serie,
	 * entre paréntesis, el año de la emisión, el número de
	 * temporadas y el de episodios,
	 * El fichero estará ordenado por año, y a igualdad de año por popularidad.
	 * Un ejemplo de línea del fichero generado es el siguiente:
	 *            House of Cards (2013) Temporadas: 5 Episodios: 53
	 */
	void guardaEnFicheroOrdenados(String nombreFichero, String genero);


	/**
	 * @param palabra 
	 * 			Palabra buscada en la sinopsis
	 * @return Devuelve un conjunto con los nombres de las series en cuya sinopsis
	 *     aparece la palabra buscada. Se ha de tener en cuenta que puede haber series
	 *     con una sinopsis nula.
	 */
	SortedSet<String> getNombresSeriesConPalabraEnSinopsis(String palabra);
	
	/**
	 * @return Un map ordenado en el que las claves son las palabras de las sinopsis de todas las
	 * series y los valores se corresponde con la frecuencia de la palabra, es decir, el 
	 * número de veces que aparece la palabra en las sinopsis.
	 */
	SortedMap<String, Long> getFrecuenciaPalabrasSinopsis();
}
