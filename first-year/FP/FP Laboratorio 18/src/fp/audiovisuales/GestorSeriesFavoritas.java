package fp.audiovisuales;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface GestorSeriesFavoritas {
	/**
	 * Propiedad b�sica. Solo consultable.
	 * 
	 * @return El id del gestor de series
	 */
	String getId();

	/**
	 * Propiedad b�sica solo consultable.
	 * 
	 * @return Nombre del usuario que gestiona las series
	 */
	String getNombre();

	/**
	 * Propiedad b�sica. Solo consultable.
	 * 
	 * @return Conjunto de series favoritas del usuario.
	 */
	Set<SerieTV> getSeriesTV();

	/**
	 * A�ade la serie al conjunto de series favoritas del usuario.
	 * 
	 * @param serie
	 *            Serie a a�adir a las favoritas.
	 */
	void nuevaSerie(SerieTV serie);

	/**
	 * A�ade la colecci�n de series a la lista de series favoritas del usuario
	 * 
	 * @param series
	 *            Colecci�n de series a a�adir a la colecci�n.
	 */
	void nuevasSeries(Collection<SerieTV> series);

	/**
	 * @param serie
	 *            Elimina la serie del conjunto de favoritas;
	 */
	void eliminaSerie(SerieTV serie);

	/**
	 * @param nombre
	 *            Elimina la serie cuyo nombre se da como par�metro. Si no hay
	 *            ninguna serie con ese nombre, la operaci�n no tiene efecto
	 */
	void eliminaSerie(String nombre);

	/**
	 * @return La serie con m�s temporadas del conjunto de series favoritas del
	 *         usuario. Si no hay ninguna, devuelve null.
	 */
	SerieTV getSerieMasTemporadas();

	/**
	 * @return La serie con un mayor n�mero de episodios del conjunto de series
	 *         favoritas del usuario. Si no hay ninguna, devuelve null.
	 */
	SerieTV getSerieMasEpisodios();

	/**
	 * @param persona
	 *            Persona que trabaja en una serie.
		 * @return El conjunto de series en las que ha trabajado en cualquier rol
		 *         (creador, actor o arstista invitado) la persona dada como
		 *         par�metro.
	 */
	Set<SerieTV> getSeriesDe(MiembroStaff persona);

	/**
	 * @return Un array con el n�mero de series con estado FINALIZADA, EN_CURSO
	 *         y EN_PRODUCCION del conjunto de series favoritas.
	 */
	Integer[] contarSeriesSegunEstado();

	/**
	 * @param cadenaTV
	 *            Nombre de cadena de televisi�n.
	 * @return Devuelve true si hay alguna serie de la cadena de televisi�n
	 *         pasada como par�metro.
	 */
	Boolean haySeriesDeCadenaTV(String cadenaTV);

	/**
	 * @param genero
	 *            G�nero de la serie.
	 * @return Devuelve la serie m�s popular del g�nero dado como par�metro.
	 * @throws NoSuchElementException Si no hay ninguna serie de ese g�nero.
	 */
	SerieTV getSerieMasPopular(String genero);

	/**
	 * @return true si todas las series del gestor se emitieron en el a�o dado
	 *         como par�metro. Se considera que una serie se emiti� en un a�o si
	 *         alguno de sus episodios se ha emitido en ese a�o.
	 */
	Boolean seEmitieronTodasEn(Integer anyo);
	
	/**
	 * @param genero Cadena de televisi�n
	 * @return El n�mero de series del gestor del g�nero dado como par�metro
	 */
	Integer getNumeroSeriesDeGenero(String genero);
	

	/**
	 * @param nombreFichero
	 *            Nombre de fichero en el que se guardan las series
	 * @param genero
	 *            G�nero escogido. 
	 * Crea un fichero con el nombre de la serie,
	 * entre par�ntesis, el a�o de la emisi�n, el n�mero de
	 * temporadas y el de episodios,
	 * El fichero estar� ordenado por a�o, y a igualdad de a�o por popularidad.
	 * Un ejemplo de l�nea del fichero generado es el siguiente:
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
	 * n�mero de veces que aparece la palabra en las sinopsis.
	 */
	SortedMap<String, Long> getFrecuenciaPalabrasSinopsis();
}
